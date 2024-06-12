package com.nanGuoMM.reggie.backend.controller;

import com.nanGuoMM.reggie.backend.domain.Result;
import com.nanGuoMM.reggie.backend.exception.CustomException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

@Api(tags = "图片上传")
@RestController
@RequestMapping("/common")
public class UploadController {

    @Value("${basePath}")
    private String basePath;

    @ApiOperation(value = "上传", notes = "图片上传")
    @PostMapping("/upload")
    public Result<String> upload(@ApiParam("照片") MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        assert originalFilename != null;
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));

        String fileName = UUID.randomUUID() + substring;
        File fileTemp = new File(basePath);
        if(!fileTemp.exists()) {
            if(!fileTemp.mkdir()) {
                throw new CustomException("上传文件时，创建新目录失败");
            }
        }
        file.transferTo(new File(basePath + fileName));

        return Result.success(fileName);
    }

    @ApiOperation(value = "下载",notes = "回显和显示图片")
    @GetMapping("/download")
    public void download(HttpServletResponse response,@ApiParam("照片全名") String name) throws FileNotFoundException {

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
