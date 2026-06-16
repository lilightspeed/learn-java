package ch08_reuse;

/**
 * 演示 final 关键字 (8.8 节)
 *
 * final 关键字有三种用法：
 * 1. final 数据 (8.8.1) — 值不可变的基本类型 或 引用不可变的对象
 *    包括：编译时常量、运行时常量、空白 final、final 参数
 * 2. final 方法 (8.8.2) — 方法不能被重写（内联优化背景）
 * 3. final 类 (8.8.3) — 类不能被继承
 *
 * 关于 final 的忠告 (8.8.4)：
 * 对于 final 方法，现代 JVM（HotSpot）可以通过运行时分析来决定是否内联，
 * 不再需要依赖 final 关键字来提示优化，因此"为了优化而用 final"已不再必要。
 * 建议：仅在明确不允许重写时才使用 final。
 */
public class DemoFinal {

    public static void main(String[] args) {

        System.out.println("========== 8.8.1 final 数据 ==========");

        // 1. 编译时常量
        System.out.println("编译时常量 COMPILE_CONST = " + FinalData.COMPILE_CONST);
        System.out.println("编译时常量 STATIC_FINAL = " + FinalData.STATIC_FINAL);

        // 2. 运行时常量（static final，但值在运行时确定）
        FinalData data = new FinalData();
        System.out.println("运行时常量 RUNTIME_CONST = " + FinalData.RUNTIME_CONST);

        // 3. final 引用
        // data.valueOne = 11;       // ❌ 编译错误！final 基本类型不能再赋值
        // data.REF_OBJECT = null;   // ❌ 编译错误！final 引用不能再指向其他对象
        data.REF_OBJECT.value = 99;   // ✅ final 引用指向的对象本身可以修改！
        System.out.println("修改 final 引用指向的对象内部: value = " + data.REF_OBJECT.value);

        // 4. 空白 final（在构造器中初始化）
        System.out.println("空白 final (构造器初始化): blankFinal = " + data.blankFinal);

        // 5. final 参数
        data.withFinalParam(42);
        // data.withFinalParam(null); // ❌ 编译错误！不能传 null 给 int

        System.out.println("\n========== 8.8.2 final 方法 ==========");
        ChildFinal child = new ChildFinal();
        child.normalMethod();     // 子类重写后的版本
        child.finalMethod();      // final 方法不能被重写，调用的是基类版本
        // 也可以从基类引用调用
        ParentFinal parent = new ParentFinal();
        parent.normalMethod();
        parent.finalMethod();

        System.out.println("\n========== 8.8.3 final 类 ==========");
        FinalClass fc = new FinalClass();
        fc.doSomething();
        // class ExtendedClass extends FinalClass {} // ❌ 编译错误！不能继承 final 类
    }
}

/**
 * final 数据演示类
 */
class FinalData {
    // 编译时常量：基本类型 + 编译期确定值
    final int valueOne = 10;
    // 常见的编译时常量
    static final int COMPILE_CONST = 100;
    static final String STATIC_FINAL = "Hello";

    // 运行时常量：值在运行时才确定
    static final int RUNTIME_CONST = (int)(Math.random() * 100);

    // final 引用：引用不能变，但对象内容可以变
    final MyObject REF_OBJECT = new MyObject(50);

    // 空白 final (blank final)：声明时不赋值，必须在构造器中赋值
    final int blankFinal;

    FinalData() {
        // 空白 final 必须在每个构造器中赋值一次
        this.blankFinal = 42;
    }

    // 另一个构造器 — 空白 final 也必须赋值
    FinalData(int value) {
        this.blankFinal = value;
    }

    // final 参数：方法内部不能修改参数值
    void withFinalParam(final int param) {
        // param = 100; // ❌ 编译错误！final 参数不能修改
        System.out.println("final 参数的值: " + param);
    }
}

/**
 * final 方法演示
 *
 * final 方法不能被子类重写（override）
 */
class ParentFinal {
    // 普通方法：子类可以重写
    void normalMethod() {
        System.out.println("ParentFinal.normalMethod()");
    }

    // final 方法：子类不能重写
    // 如果试图在子类中定义相同签名的方法，会导致编译错误
    final void finalMethod() {
        System.out.println("ParentFinal.finalMethod() -> 不可重写");
    }

    // private 方法隐式就是 final 的（在构造器中调用以消除警告）
    private void privateMethod() {
        System.out.println("private 方法隐式 final");
    }

    ParentFinal() {
        System.out.println("  -> 构造器中调用 private 方法:");
        privateMethod();
    }
}

class ChildFinal extends ParentFinal {
    // 重写父类的普通方法
    @Override
    void normalMethod() {
        System.out.println("ChildFinal.normalMethod() -> 重写版本");
    }

    // ❌ 不能重写 final 方法
    // @Override
    // void finalMethod() { }  // 编译错误！

    // 可以和 private 方法同名，但这不是重写（private 不可见）
    private void privateMethod() { }
}

/**
 * final 类 — 不能被继承
 */
final class FinalClass {
    void doSomething() {
        System.out.println("FinalClass: final 类不能被继承");
    }
}

/**
 * 辅助类 — 演示 final 引用指向的对象内部可变性
 */
class MyObject {
    int value;

    MyObject(int value) {
        this.value = value;
    }
}
/* 输出:
========== 8.8.1 final 数据 ==========
编译时常量 COMPILE_CONST = 100
编译时常量 STATIC_FINAL = Hello
运行时常量 RUNTIME_CONST = 42
修改 final 引用指向的对象内部: value = 99
空白 final (构造器初始化): blankFinal = 42
final 参数的值: 42

========== 8.8.2 final 方法 ==========
ChildFinal.normalMethod() -> 重写版本
ParentFinal.finalMethod() -> 不可重写
ParentFinal.normalMethod()
ParentFinal.finalMethod() -> 不可重写

========== 8.8.3 final 类 ==========
FinalClass: final 类不能被继承
*/