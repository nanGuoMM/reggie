package com.nanGuoMM.reggie.front.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;

/**
 * 代码生成器
 */
public class CodeGeneratorPO {
    //表名
    private static final String tableName = "order_detail";
    //工程路径
    private static final String workSpace = "E:\\javaWeb\\workspace\\reggie_take_out_front";


    public static void main(String[] args) {
        FastAutoGenerator
                .create("jdbc:mysql://localhost:3306/reggie?useUnicode=true&useSSL=false&characterEncoding=utf8"
                        , "root", "1234")
                .globalConfig(builder -> {
                    builder.author("nanGuoMM") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .outputDir(workSpace + "/src/main/java") // 指定输出目录
                            .disableOpenDir();//不打开资源管理器
                })
                .dataSourceConfig(builder ->
                        builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                            int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                            if (typeCode == Types.SMALLINT) {
                                // 自定义类型转换
                                return DbColumnType.INTEGER;
                            }
                            return typeRegistry.getColumnType(metaInfo);
                        })
                )
                .packageConfig(builder ->
                        builder.parent("com.nanGuoMM.reggie") // 设置父包名
                                .moduleName("front") // 设置父包模块名
                                .entity("domain." + tableName)//设置实体类包名
                                .pathInfo(Collections.singletonMap(OutputFile.entity, workSpace +
                                        "/src/main/java/com/nanGuoMM/reggie/front/domain/" + tableName))// 设置实体类生成路径
                                .pathInfo(Collections.singletonMap(OutputFile.xml, workSpace +
                                        "/src/main/resources/mapper")) // 设置mapperXml生成路径
                )
                .strategyConfig(builder ->
                                builder.addInclude(tableName) // 设置需要生成的表名
                                        .addTablePrefix("", "") // 设置过滤表前缀
                                        .addTableSuffix("","")//设置过滤表后缀
                                        .entityBuilder()
                                        .enableLombok()//使用lombok框架生成实体
                                        .enableTableFieldAnnotation() // 启用字段注解
                                        .formatFileName("%sPO")
//                                .enableFileOverride()//开启文件覆盖----po
                                        .controllerBuilder()
                                        .enableRestStyle() // 启用 REST 风格
//                                .enableFileOverride()//开启文件覆盖------controller
//                                .serviceBuilder().enableFileOverride()//开启文件覆盖----service
                                        .mapperBuilder().
                                        enableMapperAnnotation()
//                                        .enableFileOverride()//开启文件覆盖-----mapper
                )
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
