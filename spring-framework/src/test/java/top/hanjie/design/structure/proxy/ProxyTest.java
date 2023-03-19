package top.hanjie.design.structure.proxy;

// 抽象主题接口
interface Subject {
    void request();
}
// 真实主题类
class RealSubject implements Subject {
    public void request() {
        System.out.println("真实主题方法被调用");
    }
}
// 代理类
class Proxy implements Subject {
    private volatile RealSubject realSubject;
    public void request() {
        if (realSubject == null) {
            synchronized (Proxy.class) {
                if (realSubject == null) {
                    realSubject = new RealSubject();
                }
            }
        }
        preRequest();
        realSubject.request();
        postRequest();
    }
    public void preRequest() {
        System.out.println("代理对象对真实主题对象进行预处理");
    }
    public void postRequest() {
        System.out.println("代理对象对真实主题对象进行后续处理");
    }
}
// 客户端
public class ProxyTest {
    public static void main(String[] args) {
        Proxy proxy = new Proxy();
        proxy.request();
    }
}
