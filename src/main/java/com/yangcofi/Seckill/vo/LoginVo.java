package com.yangcofi.Seckill.vo;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class LoginVo {

    @NotNull
    private String Mobile;

    @NotNull
    @Length(min = 32)
    private String password;

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginVo{" +
                "Mobile='" + Mobile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
