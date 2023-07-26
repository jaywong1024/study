package io.github.jaywong1024.design.structure.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface UserService {
    void save();
}
class UserServiceImpl implements UserService {
    @Override
    public void save() {
        System.out.println("保存用户信息");
    }
}
public class UserServiceProxy implements InvocationHandler {
    private final UserService userService;
    public UserServiceProxy(UserService userService) {
        this.userService = userService;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用方法前");
        Object result = method.invoke(userService, args);
        System.out.println("调用方法后");
        return result;
    }
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        InvocationHandler handler = new UserServiceProxy(userService);
        UserService proxy = (UserService) Proxy.newProxyInstance(
                userService.getClass().getClassLoader(),
                userService.getClass().getInterfaces(),
                handler
        );
        proxy.save();
    }
}