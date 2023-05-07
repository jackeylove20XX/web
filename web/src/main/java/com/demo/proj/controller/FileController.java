//package com.demo.proj.controller;
//
//import com.demo.proj.service.Result;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.UUID;
//
//@Slf4j
//@RestController
//public class FileController {
//
//    @PostMapping(value = "/upload")
//    public Result upload(String name, MultipartFile file_u){
//        log.info("文件上传：{},{}",(name),(file_u));
//
//        //local save
//        String filename= file_u.getOriginalFilename();
//
//        //uuid
//        int last_index=filename.lastIndexOf(".");
//        String ext_name=filename.substring(last_index);
//        String new_name=UUID.randomUUID().toString()+ext_name;
//
//        try {
//            file_u.transferTo(new File("D:\\"+new_name));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return Result.success();
//    }
//
//}
