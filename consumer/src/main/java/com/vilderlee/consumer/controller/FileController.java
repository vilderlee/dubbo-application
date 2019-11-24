package com.vilderlee.consumer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 功能描述:
 *
 * @package com.vilderlee.provider.controller
 * @auther vilderlee
 * @date 2019/11/23 6:55 下午
 */
@Controller
public class FileController {

    @GetMapping(value = "/index")
    public String html() {
        return "index";
    }

    @PostMapping(value = "/upload")
    @ResponseBody
    public String upload(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            DataInputStream inputStream = new DataInputStream(file.getInputStream());
            File jarFile = new File("/Users/vilderlee/jar/" + fileName);
            FileOutputStream dataOutputStream = new FileOutputStream(jarFile);
            byte[] bytes = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(bytes, 0, 1024)) != -1) {
                dataOutputStream.write(bytes, 0, length);
            }
            dataOutputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Success";
    }
}
