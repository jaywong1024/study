package top.hanjie.design.structure;

// 旧接口
interface OldInterface {
    void doSomethingOld();
}
// 旧接口的实现
class OldImplementation implements OldInterface {
    @Override
    public void doSomethingOld() {
        System.out.println("Doing something the old way.");
    }
}
// 新接口
interface NewInterface {
    void doSomethingNew();
}
// 新接口的实现
class NewImplementation implements NewInterface {
    @Override
    public void doSomethingNew() {
        System.out.println("Doing something the new way.");
    }
}
// 适配器类，将旧接口适配到新接口
class Adapter implements NewInterface {
    private final OldInterface oldImplementation;
    public Adapter(OldInterface oldImplementation) {
        this.oldImplementation = oldImplementation;
    }
    @Override
    public void doSomethingNew() {
        // 在适配器中使用旧接口实现新接口的方法
        oldImplementation.doSomethingOld();
    }
}

// 测试适配器
public class AdapterExample {
    public static void main(String[] args) {
        // 使用旧接口实现类创建适配器
        OldInterface oldImplementation = new OldImplementation();
        NewInterface adapter = new Adapter(oldImplementation);
        // 使用适配器调用新接口的方法
        adapter.doSomethingNew();
    }
}