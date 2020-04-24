package cn.rxwycdh.oraclehomework.schedule;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.URLUtil;
import cn.rxwycdh.oraclehomework.dto.MinioUploadDto;
import cn.rxwycdh.oraclehomework.entity.ContactBasicInfo;
import cn.rxwycdh.oraclehomework.entity.HomeworkBasicInfo;
import cn.rxwycdh.oraclehomework.entity.UserHomeworkRelation;
import cn.rxwycdh.oraclehomework.entity.param.HomeWorkAnalysisDTO;
import cn.rxwycdh.oraclehomework.service.ContactBasicInfoService;
import cn.rxwycdh.oraclehomework.service.HomeworkBasicInfoService;
import cn.rxwycdh.oraclehomework.service.MinioOssService;
import cn.rxwycdh.oraclehomework.service.UserHomeWorkRelationService;
import com.alibaba.excel.EasyExcel;
import com.sun.jndi.toolkit.url.UrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.management.relation.Relation;
import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @FileName TaskContoller
 * @Description
 * @Author jiuhao
 * @Date 2020/3/19 20:29
 * @Modified
 * @Version 1.0
 */
@Service
public class TaskService {

    @Autowired
    private HomeworkBasicInfoService homeworkBasicInfoService;

    @Autowired
    private UserHomeWorkRelationService relationService;

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Autowired
    private ContactBasicInfoService contactBasicInfoService;

    @Autowired
    private MinioOssService minioOssService;


//    @Scheduled(cron = "0 0 18 * * Tue")
    public void sendEmailToClassMate() throws InterruptedException {
        System.out.println("开始向未完成的同学发送邮件");

        List<ContactBasicInfo> unfinishContact = relationService.listUnSubMit();
        int flag = 0;
        if(unfinishContact.size() >8) {
            flag = 1;
        }

        for (ContactBasicInfo contactBasicInfo : unfinishContact) {

            sendEmail("请尽快提交oracle实验", "过时不候", contactBasicInfo.getEmail());
            if(flag == 1){
                Thread.sleep(60000);
            }
            else{
                Thread.sleep(30000);
            }
        }
    }

    public void sendEmail(String title, String content, String targetEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(title);
        message.setText(content);
        message.setTo(targetEmail);
        message.setFrom("597701764@qq.com");
        javaMailSender.send(message);
    }

//    @Scheduled(cron = "0 0 21 * * Tue")
//    @Scheduled(cron = "0 57 15 * * *")
    public void sendEmailToTeacher() throws Exception {

        List<UserHomeworkRelation> finishRelation = relationService.listFinish();

        List<ContactBasicInfo> unfinishContact = relationService.listUnSubMit();
        String contextPrefix = "老师你好，这是外包181班本周实验报告 \n";

        String content1 = "完成人数: " + finishRelation.size() +  "人，未完成人数:  " + unfinishContact.size()  + "人\n";
        String content2 = "未完成的同学名单如下: \n";

        String contextStufix = "这是已提交的同学的excel报告清单，你可以点击上面的链接进行下载查看 里面包含了同学们的实验的下载地址";

        StrBuilder builder = StrBuilder.create();

        unfinishContact.forEach(contact -> {
            builder.append("姓名：  ")
                    .append(contact.getName())
                    .append("学号：  ")
                    .append(contact.getNumber())
                    .append("  联系方式：  ")
                    .append(contact.getEmail())
                    .append("\n");
        } );


        List<HomeWorkAnalysisDTO> result = new ArrayList<>();
        HomeworkBasicInfo homeworkBasicInfo = homeworkBasicInfoService.getHomework();


        finishRelation.forEach(relation -> {
            HomeWorkAnalysisDTO dto = new HomeWorkAnalysisDTO();
            dto.setFileUrl(relation.getFileUrl());
            dto.setCreateTime(relation.getCreateTime());

            ContactBasicInfo info = contactBasicInfoService.get(relation.getContactId());
            dto.setTitle(homeworkBasicInfo.getTitle());
            dto.setEmail(info.getEmail());
            dto.setName(info.getName());
            dto.setNumber(info.getNumber());
            result.add(dto);
        });

        Path path = Paths.get("analysis");

        if(!Files.exists(path)) {
            Files.createDirectories(path);
        }

        String fileName ="一班实验提交清单" + DateUtil.format(new Date(), "MM月dd日") +  ".xlsx";
        System.out.println(path.resolve(fileName).toString());

        EasyExcel.write(path.resolve(fileName).toString(), HomeWorkAnalysisDTO.class).sheet("清单").doWrite(result);


        String filePath = path.toString() + File.separator + fileName;
        File file = new File(filePath);


        InputStream inputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(fileName, fileName,
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",inputStream);
        multipartFile.getOriginalFilename();
        MinioUploadDto minioUploadDto = minioOssService.uploadFile(multipartFile);

        String context = contextPrefix
                + content1
                + content2
                + builder.toString()
                + "\n"
                + minioUploadDto.getUrl()
                + "\n"
                + contextStufix;


        sendEmail("本周实验提交情况报告", context, "59497363@qq.com");
        System.out.println("已向老师发送邮件，excel" + minioUploadDto.getUrl());
    }





}
