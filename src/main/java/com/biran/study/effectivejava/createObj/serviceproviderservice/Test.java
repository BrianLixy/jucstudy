package com.biran.study.effectivejava.createObj.serviceproviderservice;

public class Test {


    public static void main(String[] args){
        try {
            Class.forName("serviceproviderservice.UserProviderImpl");
            UserService userService = ServiceManager.getService("logonRegister");
            userService.register();
            userService.login();

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
