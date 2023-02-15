package top.hanjie.jvm.gc;

/**
 * 交叉引用
 *
 * @author 黄汉杰
 * <p>描述：交叉引用测试<p>
 * <p>创建时间：2023/2/13 22:51<p>
 */
public class CrossReference {

    static class Human {
        String name;
        Human like;
        Human(String name) { this.name = name; }
    }

    public static void main(String[] args) {
        // 使用三角恋来模拟互相循环引用
        Human a = new Human("one");
        Human b = new Human("two");
        Human c = new Human("three");
        a.like = b;
        b.like = c;
        c.like = a;
        // 由上述代码可知，对象 one 被引用了 2 次，分别是变量 a 和 c.like 引用了它
        // 同理可得另外两个对象也都被引用了 2 次，3 个对象的引用计数都为 2

        // 那么，进行如下操作，将变量 a、b、c 都赋值为 null
        // 此时 3 个对象的引用计数都为 1（因为对象中的 like 变量引用了其他对象）
        // 根据引用计数算法，这 3 个对象引用都为 1，判定为存活
        // 但是，将变量 a、b、c 都赋值为 null 后，就无法从任何地方拿到 3 个对象了，应该释放内存才对
        // 这就是引用计数算法存在的问题
        a = b = c = null;
    }

}
