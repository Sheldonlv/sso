package com.sheldon.util;

/**
 * Created by Sheldon on 2019/8/24.
 * Project Name: sso.
 * Package Name: com.sheldon.util.
 */
public class ResultUtil {

    public static ResultData error(String msg){
        return new ResultData(000, msg, null);
    }

    public static ResultData success(Object data){
        return new ResultData(001, "登录成功", data);
    }
}
