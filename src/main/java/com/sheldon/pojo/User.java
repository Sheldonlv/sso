package com.sheldon.pojo;

/**
 * 用户信息实体类
 * Created by Sheldon on 2019/8/26.
 * Project Name: sso.
 * Package Name: com.sheldon.pojo.
 */
public class User {
    private int id;
    private String name;
    private String passwd;
    private String tel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
