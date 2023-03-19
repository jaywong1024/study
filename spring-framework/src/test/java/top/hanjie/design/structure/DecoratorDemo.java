package top.hanjie.design.structure;

// Component 接口定义了被装饰对象的行为
interface Text {
    // 显示文本
    void display();
}
// ConcreteComponent 是一个具体的被装饰对象
class ConcreteText implements Text {
    public void display() {
        System.out.println("Displaying text.");
    }
}
// Decorator 装饰器基类，实现了 Component 接口，并持有一个 Component 对象
abstract class TextDecorator implements Text {
    protected Text text;
    public TextDecorator(Text text) {
        this.text = text;
    }
    public void display() {
        text.display();
    }
}
// 具体装饰器 RedBorder，扩展了 TextDecorator，并添加了一个新的行为
class RedBorderDecorator extends TextDecorator {
    public RedBorderDecorator(Text text) {
        super(text);
    }
    public void display() {
        super.display();
        // 添加红色边框
        System.out.println("Adding red border.");
    }
}
// 使用示例
public class DecoratorDemo {
    public static void main(String[] args) {
        // 创建一个被装饰对象
        Text text = new ConcreteText();

        // 对被装饰对象进行装饰
        Text decoratedText = new RedBorderDecorator(text);

        // 调用装饰后的对象的行为
        decoratedText.display();
    }
}
