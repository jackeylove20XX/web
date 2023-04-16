package com.demo.proj.controller;

import com.demo.proj.service.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {

    @PostMapping(value = "upload")
    public Result upload(){
        return Result.success();
    }

}
