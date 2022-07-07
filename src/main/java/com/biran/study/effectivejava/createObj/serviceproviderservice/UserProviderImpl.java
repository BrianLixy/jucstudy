package com.biran.study.effectivejava.createObj.serviceproviderservice;

public class UserProviderImpl implements UserProvider{
    @Override
    public UserService getUserService() {
        return new UserServiceImpl();
    }

    public UserProviderImpl() {
        System.out.println("被初始化......");
    }
    static{
        ServiceManager.registerProvider("logonRegister", new UserProviderImpl());
    }
}
