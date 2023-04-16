package com.demo.proj.controller;

import ch.qos.logback.core.joran.spi.ElementSelector;
import com.alibaba.fastjson.JSONObject;
import com.demo.proj.mapper.UserMapper;
import com.demo.proj.obj.MyUserF;
import com.demo.proj.service.*;
import jakarta.servlet.http.HttpServletRequest;
import netscape.javascript.JSObject;
import org.apache.ibatis.javassist.bytecode.analysis.ControlFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Block;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.Thread.sleep;

@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)//开启aop事务管理
    @RequestMapping(value = "/user/listpage")
    public Result listpage(@RequestBody JSONObject object) {
        Result result = JWTUtil.verifyToken(object.getString("token"));
        if (result.getCode() == -1) {
            return Result.fail(Result.getError(-10));
        }

        List<MyUserF> list = userMapper.ListByPage(object.getInteger("limit"), object.getInteger("offset"));
        System.out.println(list);
        if (!list.isEmpty()) {
            return Result.success(list);
        } else {
            return Result.fail(Result.getError(-5));
        }
    }

    @Transactional(rollbackFor = Exception.class)//开启aop事务管理
    @PostMapping(value = "/user/delete")
    public Result delete_id(@RequestBody JSONObject object) {
        Result result = JWTUtil.verifyToken(object.getString("token"));
        if (result.getCode() == -1) {
            return Result.fail(Result.getError(-10));
        }
        int flag = 0;
        if (object.getString("id") != null) {
            flag = userMapper.DeleteID(object.getInteger("id"));
            if (flag == 1) {
                return Result.success();
            } else {
                return Result.fail(Result.getError(-6));
            }
        } else if (object.getString("username") != null) {
            flag = userMapper.DeleteByUName(object.getString("username"));
            if (flag == 1) {
                return Result.success();
            } else {
                return Result.fail(Result.getError(-7));
            }
        } else {
            return Result.fail(Result.getError(-9));
        }
    }

    @Transactional(rollbackFor = Exception.class)//开启aop事务管理
    @PostMapping(value = "/user/addUser")
    public Result add_post(@RequestBody JSONObject object) {
        Result result = JWTUtil.verifyToken(object.getString("token"));
        if (result.getCode() == -1) {
            return Result.fail(Result.getError(-10));
        }

        result = UserInfoCheck.Check_all(object);
        if(result.getCode()==-1)
        {
            return result;
        }
        MyUserF user = (MyUserF) result.getData();

        MyUserF local = userMapper.FindByUName(user.getUserName());
        if (local !=null) {
                return Result.fail(Result.getError(-2));
            }
        else
        {
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
    }

    @Transactional(rollbackFor = Exception.class)//开启aop事务管理
    @PostMapping(value = "/user/update")
    public Result update(@RequestBody JSONObject object) {
        Result result = JWTUtil.verifyToken(object.getString("token"));
        if (result.getCode() == -1) {
            return Result.fail(Result.getError(-10));
        }

        //check gender
        result = UserInfoCheck.Check_all(object);
        if(result.getCode()==-1)
        {
            return result;
        }
        MyUserF user = (MyUserF) result.getData();
        user.setID(object.getInteger("id"));

        //check username
        MyUserF local = userMapper.FindByUName(user.getUserName());
        MyUserF local2 = userMapper.FindByID(user.getID());
        if (local == null) {
            if(local2!=null)
            {
                user.setVersion(local2.getVersion());
                int ret= userMapper.UpdateUserWithVersion(user);
                if(ret<1){
                    return Result.fail(Result.getError(-14));
                }
                else
                {
                    return Result.success(user);
                }
            }
            else
            {
                return Result.fail(Result.getError(-6));
            }

        } else {
            return Result.fail(Result.getError(-2));
        }
    }
}


