package cn.rxwycdh.oraclehomework.entity.param;

import com.alibaba.excel.annotation.ExcelProperty;

import java.util.Date;

/**
 * @FileName HomeWorkAnalysisDTO
 * @Description
 * @Author jiuhao
 * @Date 2020/3/20 9:02
 * @Modified
 * @Version 1.0
 */
public class HomeWorkAnalysisDTO {

    @ExcelProperty("实验名称")
    private String title;
    @ExcelProperty("同学名称")
    private String name;
    @ExcelProperty("同学学号")
    private String number;
    @ExcelProperty("同学联系方式")
    private String email;
    @ExcelProperty("文件地址")
    private String fileUrl;
    @ExcelProperty("提交时间")
    private Date createTime;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
