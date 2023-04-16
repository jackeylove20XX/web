package com.demo.proj.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.proj.mapper.UserMapper;
import com.demo.proj.obj.MyUser;
import com.demo.proj.obj.MyUserF;
import com.demo.proj.service.InterfaceLimit;
import com.demo.proj.service.JWTUtil;
import com.demo.proj.service.Result;
import com.demo.proj.service.UserInfoCheck;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class loginController {
    @Autowired
    private UserMapper userMapper;


    @InterfaceLimit(value = 100)
    @Transactional(rollbackFor = Exception.class)//开启aop事务管理
    @PostMapping(value = "/user/login")
    public Result login(@RequestBody JSONObject object){
        MyUserF local=userMapper.FindByUNameAndPassWord(object.getString("username"),object.getString("password"));
        if(local==null)
        {
            return Result.fail(Result.getError(-4));
        }
        else
        {
            String token= JWTUtil.createToken(local);
            return Result.success(token);
        }
    }

    @InterfaceLimit(value = 100)
    @Transactional(rollbackFor = Exception.class)//开启aop事务管理
    @PostMapping(value = "/user/register")
    public Result register(@RequestBody JSONObject object){
        Result result = UserInfoCheck.Check_all(object);
        if(result.getCode()==-1)
        {
            return result;
        }
        MyUserF user = (MyUserF) result.getData();

        //check name
        MyUserF local = userMapper.FindByUName(user.getUserName());
        if (local==null){
            user.setVersion(0);
            int ret= userMapper.AddUserWithVersion(user);
            if(ret<1){
                return Result.fail(Result.getError(-14));
            }
            else
            {
                return Result.success(user);
            }
        }
        else{
            return Result.fail(Result.getError(-2));
        }
    }
}
