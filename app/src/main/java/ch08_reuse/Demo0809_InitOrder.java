package ch08_reuse;

/**
 * 初始化顺序 (8.9 节)
 *
 * 类加载和初始化的完整过程：
 *
 * 【类的加载】
 * Java 类只有在首次被使用时才会加载（按需加载）。
 * 类的加载发生在创建第一个对象 或 访问 static 成员时。
 *
 * 【继承关系中的初始化顺序】
 * 当创建一个子类对象时，初始化的完整顺序是：
 *
 * 1. 加载类（如果尚未加载）
 *    a. 加载基类（先加载基类的 static 成员）
 *    b. 加载子类（再加载子类的 static 成员）
 *
 * 2. 创建对象
 *    a. 基类的成员变量初始化（按声明顺序）
 *    b. 调用基类构造器
 *    c. 子类的成员变量初始化（按声明顺序）
 *    d. 调用子类构造器体
 *
 * 一句话口诀：类加载 static -> 父类初始化 -> 子类初始化
 *   —— 先静态，后非静；先父类，后子类；先变量，后构造。
 */
public class Demo0809_InitOrder {

    public static void main(String[] args) {
        System.out.println("===== 第1次创建子类对象 =====");
        // 第1次触发类加载：static 块只执行一次
        new ChildClass();

        System.out.println("\n===== 第2次创建子类对象 =====");
        // 类已加载，static 块不再执行，只走对象初始化流程
        new ChildClass();
    }
}

/**
 * 基类 — 包含 static 成员和构造器
 */
class BaseClass {
    // static 成员变量（类加载时初始化，仅一次）
    static int baseStaticField = initBaseStatic();

    static int initBaseStatic() {
        System.out.println("[基类] static 成员变量初始化: baseStaticField = 100");
        return 100;
    }

    // static 代码块（类加载时执行，仅一次；与 static 变量按代码顺序执行）
    static {
        System.out.println("[基类] static 代码块执行");
    }

    // 非 static 成员变量（每次创建对象时初始化）
    int baseField = initBaseField();

    int initBaseField() {
        System.out.println("[基类] 非 static 成员变量初始化: baseField = 10");
        return 10;
    }

    // 基类构造器
    BaseClass() {
        System.out.println("[基类] 构造器执行");
    }
}

/**
 * 子类 — 继承自 BaseClass
 */
class ChildClass extends BaseClass {
    // static 成员变量
    static int childStaticField = initChildStatic();

    static int initChildStatic() {
        System.out.println("[子类] static 成员变量初始化: childStaticField = 200");
        return 200;
    }

    // static 代码块
    static {
        System.out.println("[子类] static 代码块执行");
    }

    // 非 static 成员变量
    int childField = initChildField();

    int initChildField() {
        System.out.println("[子类] 非 static 成员变量初始化: childField = 20");
        return 20;
    }

    // 子类构造器
    ChildClass() {
        // 这里隐式调用 super()
        System.out.println("[子类] 构造器执行");
    }
}
/* 输出:
===== 第1次创建子类对象 =====
[基类] static 成员变量初始化: baseStaticField = 100
[基类] static 代码块执行
[子类] static 成员变量初始化: childStaticField = 200
[子类] static 代码块执行
[基类] 非 static 成员变量初始化: baseField = 10
[基类] 构造器执行
[子类] 非 static 成员变量初始化: childField = 20
[子类] 构造器执行

===== 第2次创建子类对象 =====
[基类] 非 static 成员变量初始化: baseField = 10
[基类] 构造器执行
[子类] 非 static 成员变量初始化: childField = 20
[子类] 构造器执行
*/