//package com.demo.proj.controller;
//
//import com.demo.proj.service.InterfaceLimit;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.web.bind.annotation.*;
//import com.demo.proj.obj.MyUser;
//
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.List;
//
//@RestController
//public class helloC {
//    @InterfaceLimit(value = 5)
//    @RequestMapping(value = "/hello")
//    public String hello() {
//        System.out.println("HELLO");
//        return "hello world222";
//    }
//
//
//    @RequestMapping(value = "/para")
//    public String hello2(HttpServletRequest request) {
//        String name=request.getParameter("name");
//        String id=request.getParameter("id");
//        System.out.println("ID: "+id+", HELLO"+name);
//        return "ID: "+id+", HELLO"+name;
//    }
//
//    @RequestMapping(value = "/class")
//    public String hello_class(MyUser user) {
//        System.out.println(user.getId()+user.getName());
//        return "ID:";
//    }
//
//    @RequestMapping(value = "/list")
//    public String hello_list(String[] name) {
//        System.out.println(Arrays.toString(name));
//        return "ID:";
//    }
//
//    @RequestMapping(value = "/array")
//    public String hello_array(@RequestParam List<String>name) {
//        System.out.println(name);
//        return "ID:";
//    }
//
//    @RequestMapping(value = "/datetime")
//    public String hello_date(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime updateTime) {
//        System.out.println(updateTime);
//        return "ID:";
//    }
//
//    @RequestMapping(value = "/jsonp")
//    public String hello_date(@RequestBody MyUser user) {
//        System.out.println(user);
//        System.out.println(user.getName()+user.getId());
//        return "ID:";
//    }
//
//    @RequestMapping(value = "/path/{id}")
//    public String hello_date(@PathVariable int id) {
//        System.out.println(id);
//        return "ID:"+id;
//    }
//
////    @RequestMapping(value = "/para2")
////    public String hello3(String name, int id) {
////        System.out.println("ID: "+id+", HELLO"+name);
////        return "ID: "+id+", HELLO"+name;
////    }
////
////    @RequestMapping(value = "/para3")
////    public String hello4(@RequestParam(name="name")String usrname, int id) {
////        System.out.println("ID: "+id+", HELLO"+usrname);
////        return "ID: "+id+", HELLO"+usrname;
////    }
//}
