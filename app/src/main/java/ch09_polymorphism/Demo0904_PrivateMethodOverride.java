package ch09_polymorphism;

/**
 * 缺陷："覆盖"私有方法 (9.4 节)
 *
 * ⚠️ 重要：private 方法不是多态的！
 *
 * Java 中 private 方法被自动视为 final 方法，
 * 对子类来说是"不可见的"。子类定义的"同名"方法
 * 只是一个全新的方法，并不是覆盖（override）。
 *
 * 只有非 private 方法才能被覆盖，才有动态绑定的多态行为。
 *
 * 常见的陷阱：在基类公有方法中调用 private 方法，
 * 以为子类可以覆盖它来改变行为 —— 实际上不行！
 */
public class Demo0904_PrivateMethodOverride {

    public static void main(String[] args) {
        System.out.println("=== private 方法不是多态的 ===\n");

        // 向上转型
        Base b = new Derived();

        // 调用公有方法，内部调用了 private 方法
        // 很多人以为这里会调用 Derived 的 "同名" 方法
        System.out.println("b.publicMethod():");
        b.publicMethod();
        // 输出: Base.privateMethod()，因为私有方法是静态绑定的

        System.out.println("\n--- 对比：普通方法（非 private）---");
        b.normalMethod();
        // 输出: Derived.normalMethod()，这是多态的正常行为
    }
}

class Base {
    /**
     * private 方法 — 子类不可见，也不可覆盖
     */
    private void privateMethod() {
        System.out.println("Base.privateMethod()");
    }

    /**
     * 普通方法 — 可以被覆盖
     */
    void normalMethod() {
        System.out.println("Base.normalMethod()");
    }

    /**
     * 在公有方法中调用 private 方法
     * 这个调用在 Base 内部，静态绑定到 Base 的 privateMethod()
     */
    void publicMethod() {
        // 静态绑定：编译时就确定了调用 Base.privateMethod()
        privateMethod();
        // 即使实际对象是 Derived，这里也不会调用 Derived 的 privateMethod()
    }
}

class Derived extends Base {
    /**
     * 这不是覆盖！这是一个全新的方法。
     * 因为 Base.privateMethod() 是 private 的，对 Derived 不可见。
     *
     * 去掉 @Override 注解编译可以通过，
     * 加上 @Override 反而会报错（说明不是覆盖）。
     */
    // @Override  // ❌ 编译错误！没有覆盖任何方法
    @SuppressWarnings("unused")
    private void privateMethod() {
        System.out.println("Derived.privateMethod()");
    }

    /**
     * 这才是真正的覆盖 — 有多态行为
     */
    @Override
    void normalMethod() {
        System.out.println("Derived.normalMethod()");
    }
}
/* 输出:
=== private 方法不是多态的 ===

b.publicMethod():
Base.privateMethod()

--- 对比：普通方法（非 private）---
Derived.normalMethod()
*/