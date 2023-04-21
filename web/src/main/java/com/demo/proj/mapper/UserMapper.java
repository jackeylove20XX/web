package com.demo.proj.mapper;

import com.demo.proj.obj.MyUserF;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;


import java.util.List;

@Mapper
public interface UserMapper {
    @Select({
            "<script>",
            "SELECT ",
            "ID,username,name,gender,updateTime,createTime, version",
            "FROM user for update",
            "</script>"})
    public List<MyUserF> ListUser();

//    @Select("select ID,username,name,gender,updateTime,createTime from user limit #{limit} offset #{offset} for update")
    @Select({
            "<script>",
            "SELECT ",
            "ID,username,name,gender,updateTime,createTime, version",
            "FROM user",
            "limit #{limit} offset #{offset} for update",
            "</script>"})
    public List<MyUserF> ListByPage(int limit, int offset);

    @Select("select ID,username,name,gender,updateTime,createTime,version from user where id=#{id} for update")
    public MyUserF FindByID(int id);

    @Select("select * from user where username=#{username} for update")
    public MyUserF FindByUName(String username);

    @Select("select * from user where username=#{username} and password=#{password} for update")
    public MyUserF FindByUNameAndPassWord(String username,String password);

    @Delete("delete from user where username=#{username}")
    public int DeleteByUName(String username);

    @Delete("delete from user where ID = #{id} ")
    public int DeleteID(int id);

//    @Options(useGeneratedKeys = true,keyProperty = "ID")
    @Insert("Insert into user(username,name,gender,password,updateTime,createTime)  "+ "values(#{UserName},#{Name},#{GenDer},#{PassWord},now(),now())")
    public int AddNewUser(MyUserF user);

    @Insert({
            "<script>",
                    "INSERT INTO user",
                    "(username,name,gender,password,updateTime,createTime,version) ",
                    " values (#{UserName},#{Name},#{GenDer},#{PassWord},now(),now(),#{version})",
                    "</script>"
        })
    public  int AddUserWithVersion(MyUserF user);

    @Update("update user set username=#{UserName},name=#{Name},gender=#{GenDer},password=#{PassWord},updateTime=now() where ID=#{ID} ")
    public int UpdateUser(MyUserF user);

    @Update({
            "<script>",
            " update user set",
            " username=#{UserName},name=#{Name},gender=#{GenDer},password=#{PassWord},updateTime=now(),version=version+1 ",
            " where ID=#{ID} and version = #{version}",
            "</script>"
    })
    public int UpdateUserWithVersion(MyUserF user);

//    @Options(useGeneratedKeys = true,keyProperty = "ID")
//    @Insert("Insert into user(name,gender,password,level,updateTime,createTime)"+
//            "values(#{name},#{gender},#{password},2,now(),now())")
//    public int AddNewAdmin(MyUserF user);
//    @Delete("delete  from user where ID = #{id}")
//    public int Insert();
}
