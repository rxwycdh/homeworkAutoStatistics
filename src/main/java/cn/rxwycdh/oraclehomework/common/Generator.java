package cn.rxwycdh.oraclehomework.common;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @file: Generator
 * @author: <a href="https://yujian95.cn/about/">YuJian</a>
 * @description: 代码生成工具
 * @date: Created in 2019/11/12 15:29
 * @modified:
 * @version: 1.0
 */

public class Generator {

    public static void main(String[] args) throws Exception {
        // MBG 执行过程中的警告信息
        List<String> warnings = new ArrayList<>();
        // 当生成的代码重复时，覆盖原代码(默认会追加)
        boolean overwrite = true;
        // 读取MBG 配置文件
        InputStream is = Generator.class.getResourceAsStream("/config/mybatis_generator.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(is);
        is.close();

        DefaultShellCallback callback = new DefaultShellCallback(overwrite);

        // 创建 MBG
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        // 执行生成代码
        myBatisGenerator.generate(null);
        // 输出警告信息
        for (String warning : warnings) {
            System.out.println(warning);
        }

    }
}

