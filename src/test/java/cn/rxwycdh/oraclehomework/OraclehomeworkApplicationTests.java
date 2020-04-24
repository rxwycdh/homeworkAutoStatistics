package cn.rxwycdh.oraclehomework;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.rxwycdh.oraclehomework.schedule.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@SpringBootTest
class OraclehomeworkApplicationTests {

    @Autowired
    TaskService taskService;

    @Test
    void contextLoads() throws Exception {
//        taskService.sendEmailToClassMate();
//        taskService.sendEmailToTeacher();
//        Path path = Paths.get("analysis");
//        String fileName ="一班实验提交清单" + DateUtil.format(new Date(), "MM月dd日") +  ".xlsx";
//
//        String filePath = path.toString() + File.separator + fileName;
//
//        File file = new File(filePath);
////        file.createNewFile();
//
//        System.out.println(file.exists());
//        System.out.println(file.getAbsolutePath());
//        System.out.println(FileUtil.exist(file));
    }

}
