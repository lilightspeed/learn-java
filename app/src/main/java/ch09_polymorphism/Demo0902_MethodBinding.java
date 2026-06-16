package ch09_polymorphism;

/**
 * 方法调用绑定 (9.2 节)
 *
 * 将一个方法调用与一个方法主体关联起来的过程叫做"绑定"。
 *
 * 【静态绑定 / 前期绑定】
 * - 编译阶段就确定了调用哪个方法
 * - Java 中 private、static、final 方法使用静态绑定
 * - 由编译器和链接器完成，性能好
 *
 * 【动态绑定 / 后期绑定】
 * - 运行时才确定调用哪个方法
 * - Java 中除 private、static、final 方法外，都是动态绑定的
 * - JVM 在运行时查找正确的方法版本
 * - 动态绑定是多态实现的技术基础
 */
public class Demo0902_MethodBinding {

    public static void main(String[] args) {
        System.out.println("=== 动态绑定：多态的核心机制 ===\n");

        // 向上转型：SubClass → BaseClass
        BaseClass ref = new SubClass();
        // 动态绑定：运行时根据对象实际类型调用 SubClass 的 method()
        ref.method();       // 输出: SubClass.method()

        // 静态绑定：private 方法编译期绑定，无法从外部调用
        // 通过公有方法间接调用（内部调用是静态绑定的）
        ref.callPrivate();   // 输出: BaseClass.privateMethod()
        // 因为 private 方法在 BaseClass 内部调用，静态绑定到 BaseClass 版本

        System.out.println("\n=== 编译时类型 vs 运行时类型 ===\n");
        // ref 的编译时类型是 BaseClass，运行时类型是 SubClass
        System.out.println("编译时类型: BaseClass（由引用类型决定）");
        System.out.println("运行时类型: " + ref.getClass().getSimpleName());
    }
}

/**
 * 基类
 */
class BaseClass {
    /**
     * 普通实例方法 — 动态绑定
     */
    void method() {
        System.out.println("BaseClass.method()");
    }

    /**
     * private 方法 — 静态绑定
     * 注意：虽然子类定义了同名方法，但这并不是"覆盖"
     * private 方法被子类"隐藏"了
     */
    private void privateMethod() {
        System.out.println("BaseClass.privateMethod()");
    }

    /**
     * 通过公有方法间接调用 private 方法
     */
    void callPrivate() {
        // 这个调用发生在 BaseClass 内部，静态绑定到 BaseClass.privateMethod()
        privateMethod();
    }
}

/**
 * 子类 — 继承自 BaseClass
 */
class SubClass extends BaseClass {
    /**
     * 覆盖基类的 method() — 动态绑定的体现
     */
    @Override
    void method() {
        System.out.println("SubClass.method()");
    }

    /**
     * 这不是覆盖！private 方法不能被覆盖。
     * 这只是一个与基类 private 方法同名的新方法。
     */
    @SuppressWarnings("unused")
    private void privateMethod() {
        System.out.println("SubClass.privateMethod()");
    }
}
/* 输出:
=== 动态绑定：多态的核心机制 ===

SubClass.method()
BaseClass.privateMethod()

=== 编译时类型 vs 运行时类型 ===

编译时类型: BaseClass
运行时类型: SubClass
*/