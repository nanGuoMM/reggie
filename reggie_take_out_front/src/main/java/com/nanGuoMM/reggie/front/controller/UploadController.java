package com.nanGuoMM.reggie.front.controller;

import com.nanGuoMM.reggie.front.domain.Result;
import com.nanGuoMM.reggie.front.exception.CustomException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

@Api(tags = "图片上传")
@RestController
@RequestMapping("/common")
public class UploadController {

    @Value("${basePath}")
    private String basePath;

    @ApiOperation(value = "下载",notes = "回显和显示图片")
    @GetMapping("/download")
    public void download(HttpServletResponse response,@ApiParam("图片全名") String name) throws FileNotFoundException {

        //检查照片是否存在
        if (!new File(basePath + name).exists()) {
            return;
        }

        response.setContentType("image/jpeg");

        try (FileInputStream inputStream = new FileInputStream(basePath + name)){

            ServletOutputStream outputStream = response.getOutputStream();
            int len=0;
            byte[] bytes = new byte[1024];
            while ((len = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
