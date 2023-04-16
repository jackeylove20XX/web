package com.demo.proj.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyUserF {
    private int ID;
    private String Name;
    private String UserName;
    private String GenDer;  //male female
    private String PassWord;
//    private int level;   //0,1,2
    private LocalDateTime UpdateTime;
    private LocalDateTime CreateTime;
//    private String opt;
//    private String status;
    private int version;

    public static MyUserF getUserF(){
        return new MyUserF();
    }
}
