package com.demo.proj.service;

import java.util.HashMap;
import java.util.Map;

public class Result {
    private int code;
    private String msg;
    private Object data;


    private static Map errorMap = Map.of( -1, "性别必须为 male 或者 female",
            -2,"用户名已存在" ,-3,"该用户名不存在",
            -4,"账号或密码错误",-5,"该页未找到数据",
            -6,"没有匹配的id",-7,"没有匹配的用户名",
            -8,"必须填写名字",-9,"需要输入的字段是id或者用户名",
            -10,"token解码异常");

    private static Map errorMap2 = Map.of(-11, "请求过于频繁，请稍后再试", -12, "必须填写用户名"
                    ,-13,"必须填写密码",-14,"数据已经发生变化，请重新请求",-15,"Not login");

    public static String getError(int code) {
        if (errorMap.get(code)!=null) {
            return errorMap.get(code).toString();
        }
        else{
            return errorMap2.get(code).toString();
        }
    }

    public Result(Object data) {
        this.data=data;
    }

    public Result(int code, String msg,Object data) {
        this.code=code;
        this.msg=msg;
        this.data=data;
    }

    public  int getCode(){
        return  code;
    }

    public  String getMsg() {
        return msg;
    }

    public  Object getData(){
        return  data;
    }

    public static Result success(Object data){
            return new Result(1,"success", data);
    }

    public static Result success(){
        return new Result(0,"success",null);
    }

    public static Result fail(String msg){
        return new Result(-1,msg,null);
    }
}

