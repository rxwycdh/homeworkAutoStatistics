package cn.rxwycdh.oraclehomework.dto;

/**
 * @FileName MinioUploadDto
 * @Description 文件上传返回结果
 * @Author jiuhao
 * @Date 2020/3/14 11:09
 * @Modified
 * @Version 1.0
 */
public class MinioUploadDto {

    private String url;

    private String name;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
