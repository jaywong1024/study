package io.github.jaywong1024.design.behavioral;

abstract class AbstractClass {
    // 模板方法
    public final void templateMethod() {
        // 固定步骤
        doStep1();
        doStep2();
        // 可变步骤
        if (hookMethod()) {
            doStep3();
        }
        // 固定步骤
        doStep4();
    }
    protected abstract void doStep1();
    protected abstract void doStep2();
    protected abstract void doStep3();
    protected void doStep4() {
        System.out.println("AbstractClass doStep4");
    }
    // 钩子方法
    protected boolean hookMethod() {
        return true;
    }
}

class ConcreteClass extends AbstractClass {
    @Override
    protected void doStep1() {
        System.out.println("ConcreteClass doStep1");
    }
    @Override
    protected void doStep2() {
        System.out.println("ConcreteClass doStep2");
    }
    @Override
    protected void doStep3() {
        System.out.println("ConcreteClass doStep3");
    }
    @Override
    protected void doStep4() {
        System.out.println("ConcreteClass doStep4");
    }
}

public class TemplateMethodDemo {
    public static void main(String[] args) {
        AbstractClass obj = new ConcreteClass();
        obj.templateMethod();
    }
}