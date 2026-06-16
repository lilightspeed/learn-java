package ch09_polymorphism;

/**
 * 缺陷：域与静态方法 (9.5 节)
 *
 * ⚠️ 两个重要的局限性：
 *
 * 1. 域（字段）没有多态性
 *    - 只有普通方法调用是动态绑定的
 *    - 字段访问由编译器解析（静态绑定），不会多态分发
 *    - 因此：不要直接暴露公有字段，使用 getter/setter
 *
 * 2. 静态方法没有多态性
 *    - 静态方法与类关联，不与对象关联
 *    - 静态方法的行为像 static 字段一样，编译期绑定
 *
 * 结论：多态只对普通实例方法有效！
 */
public class Demo0905_FieldAndStaticMethod {

    @SuppressWarnings("static-access")
    public static void main(String[] args) {
        System.out.println("=== 1. 域（字段）没有多态性 ===\n");

        Parent obj = new Child();
        // 通过 Parent 引用访问 field —— 编译时绑定到 Parent.field
        System.out.println("Parent 引用访问 obj.field  = " + obj.field);
        // 通过 Child 引用访问 field —— 编译时绑定到 Child.field
        System.out.println("Child  引用访问 ((Child)obj).field = " + ((Child)obj).field);
        // 通过方法访问 —— 动态绑定，方法内返回的是子类的 field
        System.out.println("通过 getField() 方法        = " + obj.getField());

        System.out.println("\n💡 启示：域访问不会多态分发，" +
            "这也是为什么 Java 推荐将字段设为 private，" +
            "通过 getter/setter 访问。\n");

        System.out.println("=== 2. 静态方法没有多态性 ===\n");

        Parent ref = new Child();
        ref.staticMethod();   // 编译时绑定到 Parent.staticMethod()

        Child child = new Child();
        child.staticMethod(); // 编译时绑定到 Child.staticMethod()

        System.out.println("\n💡 启示：静态方法属于类，不属于对象，" +
            "没有多态行为。推荐通过类名调用：Parent.staticMethod()");
    }
}

class Parent {
    // 公有字段 — 糟糕的设计，仅用于演示
    String field = "Parent 的 field";

    String getField() {
        return field;
    }

    /**
     * 静态方法
     */
    static void staticMethod() {
        System.out.println("Parent.staticMethod()");
    }
}

class Child extends Parent {
    // 子类的字段 — 与父类字段同名，但并没有"覆盖"父类的字段
    // 子类和父类的 field 是两个独立的字段
    String field = "Child 的 field";

    @Override
    String getField() {
        return field;   // 返回的是子类的 field
    }

    /**
     * 这不是覆盖，只是"隐藏"了父类的静态方法
     */
    static void staticMethod() {
        System.out.println("Child.staticMethod()");
    }
}
/* 输出:
=== 1. 域（字段）没有多态性 ===

Parent 引用访问 obj.field  = Parent 的 field
Child  引用访问 ((Child)obj).field = Child 的 field
通过 getField() 方法        = Child 的 field

💡 启示：域访问不会多态分发，这也是为什么 Java 推荐将字段设为 private，通过 getter/setter 访问。

=== 2. 静态方法没有多态性 ===

Parent.staticMethod()
Child.staticMethod()

💡 启示：静态方法属于类，不属于对象，没有多态行为。推荐通过类名调用：Parent.staticMethod()
*/