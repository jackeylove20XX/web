package com.demo.proj.service;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.Claim;
import com.demo.proj.mapper.UserMapper;
import com.demo.proj.obj.MyUserF;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Map;
import static com.demo.proj.service.JWTUtil.verifyToken;

public class UserInfoCheck {
    @Autowired
    private UserMapper userMapper;

    public static int CheckGender(String GenDer){
        if(GenDer.equals("male")||GenDer.equals("female"))
        {
            return 0;
        }
        else
        {
            return -1;
        }
    }

    public static Result CheckToken(String token){
        Result r=verifyToken(token);
        System.out.println(r);
        return r;
    }

    public static  Result Check_all(JSONObject object)
    {
        int flag = 0;
        MyUserF user = MyUserF.getUserF();

        //gender
        if (object.getString("gender") != null) {
            flag = UserInfoCheck.CheckGender(object.getString("gender"));
        } else {
            return Result.fail(Result.getError(-1));
        }
        if (flag != 0) {
            return Result.fail(Result.getError(-1));
        }
        user.setGenDer(object.getString("gender"));

        //name
        if (object.getString("name") != null && object.getString("name") != "") {
            user.setName(object.getString("name"));
        } else {
            return Result.fail(Result.getError(-8));
        }
        user.setName(object.getString("name"));

        //username
        if (object.getString("username") != null && object.getString("username") != "") {
            user.setName(object.getString("username"));
        } else {
            return Result.fail(Result.getError(-12));
        }
        user.setUserName(object.getString("username"));

        //password
        if (object.getString("password") != null && object.getString("password") != "") {
            user.setName(object.getString("password"));
        } else {
            return Result.fail(Result.getError(-13));
        }
        user.setPassWord(object.getString("password"));

        return Result.success(user);
    }



}
