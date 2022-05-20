package test;

import org.junit.Test;
import pojo.User;
import service.UserService;
import service.UserServiceImpl;

import static org.junit.Assert.*;

public class UserServiceTest {
    private UserService userService=new UserServiceImpl();

    @Test
    public void registerUser() {
        userService.registerUser(new User(null,"cxk","111","111@qq.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null, "cxk", "12211", null)));
    }

    @Test
    public void existsUsername() {
        if (userService.existsUsername("cxk")){
            System.out.println("用户名已存在");
        }else{
            System.out.println("用户名可用");
        }
    }
}