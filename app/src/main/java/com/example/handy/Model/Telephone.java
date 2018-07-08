package com.example.handy.Model;

/**
 * Created by t520win764zy on 2017/12/10.
 */

public class Telephone {
    private String name;  //部门
    private String tel;//电话
    private String mail;//邮箱

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String time) {
        this.tel = tel;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String content) {
        this.mail = mail;
    }

    public Telephone(String name,String tel, String mail) {
        this.name = name;
        this.tel = tel;
        this.mail = mail;
    }
}