package io.github.jaywong1024.design.structure.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import java.lang.reflect.Method;

class GoodsService {
    public void save() {
        System.out.println("保存商品信息");
    }
}
public class GoodsServiceInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method,
                            Object[] args,
                            MethodProxy proxy) throws Throwable {
        System.out.println("调用方法前");
        Object result = proxy.invokeSuper(obj, args);
        System.out.println("调用方法后");
        return result;
    }
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(GoodsService.class);
        enhancer.setCallback(new GoodsServiceInterceptor());
        GoodsService goodsService = (GoodsService) enhancer.create();
        goodsService.save();
    }
}
