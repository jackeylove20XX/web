package com.demo.proj.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.proj.mapper.UserMapper;
import com.demo.proj.obj.MyUserF;
import com.demo.proj.service.InterfaceLimit;
import com.demo.proj.service.JWTUtil;
import com.demo.proj.service.Result;
import com.demo.proj.service.UserInfoCheck;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class loginController {
    @Autowired
    private UserMapper userMapper;

//    @Async("AsyncThreadPool")
    @InterfaceLimit(value = 100)
    @Transactional(rollbackFor = Exception.class)//开启aop事务管理
    @PostMapping(value = "/user/login")
    public Result login(@RequestBody JSONObject object,HttpServletResponse response){
        MyUserF local=userMapper.FindByUNameAndPassWord(object.getString("username"),object.getString("password"));
        if(local==null)
        {
            return Result.fail(Result.getError(-4));
        }
        else
        {
//            System.out.printf("%d%s",local.getID(),local.getUserName());
            //cookie
            response.addCookie(new Cookie("username",object.getString("username")));
            //jwt token
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





//    //cookie test
//    @GetMapping("/c1")
//    public Result set_cookie(HttpServletResponse response){
//        response.addCookie(new Cookie("login_username","tt"));
//        return  Result.success();
//    }
//
//    @GetMapping("/c2")
//    public Result get_cookie(HttpServletRequest request){
//        Cookie[] cookies=request.getCookies();
//        for (Cookie cookie:cookies){
//            if(cookie.getName().equals("login_username")){
//                System.out.println("login_username:"+cookie.getValue());
//            }
//        }
//        return  Result.success();
//    }
//
//    //cookie test
//    @GetMapping("/s1")
//    public Result set_session(HttpSession session){
//        log.info("session: "+session.hashCode());
//        session.setAttribute("login_username","tt");  //类似hashmap?
//        return  Result.success();
//    }
//
//    @GetMapping("/s2")
//    public Result get_session(HttpServletRequest request){
//        HttpSession session=request.getSession();
//        log.info("session: "+session.hashCode());
//        Object object= session.getAttribute("login_username");  //类似hashmap?
//        log.info("login_username: "+object);
//        return  Result.success();
//    }

}
