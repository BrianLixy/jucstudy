package com.biran.study.effectivejava.createObj.serviceproviderservice;

public class UserServiceImpl implements UserService{
    @Override
    public void login() {
        System.out.println("cmazxiaoma登录成功");
    }

    @Override
    public void register() {
        System.out.println("cmazxiaoma注册成功");
    }
}
