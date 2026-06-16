package ch08_reuse;

/**
 * 继承语法 (8.2 节)
 *
 * 继承（Inheritance）：使用 extends 关键字，子类自动获得基类的所有
 * 非 private 成员和方法。这是一种"is-a"（是一个）关系。
 *
 * 关键点：
 * - Java 使用 extends 关键字实现继承
 * - 子类可以调用基类的构造器（通过 super）
 * - 子类可以添加新方法，也可以重写基类方法
 * - 所有继承会形成一棵继承树
 */
public class Demo0802_InheritanceSyntax {

    public static void main(String[] args) {
        Dog dog = new Dog("金毛", "黄色");
        dog.eat();          // 继承自 Animal
        dog.sleep();        // 继承自 Animal
        dog.bark();         // Dog 自己的方法

        System.out.println("——————");

        Cat cat = new Cat("布偶猫", "白色");
        cat.eat();          // 继承自 Animal
        cat.sleep();        // 继承自 Animal
        cat.meow();         // Cat 自己的方法
    }
}

/**
 * 基类（父类）— 所有动物的共同特征
 */
class Animal {
    String name;
    String color;

    /**
     * 基类构造器 — 子类必须通过 super 调用
     */
    Animal(String name, String color) {
        this.name = name;
        this.color = color;
        System.out.println("Animal 构造器: " + name);
    }

    void eat() {
        System.out.println(name + " 正在吃东西");
    }

    void sleep() {
        System.out.println(name + " 正在睡觉");
    }
}

/**
 * Dog 类 — 继承自 Animal
 * 演示：子类通过 super 调用基类构造器
 */
class Dog extends Animal {

    Dog(String name, String color) {
        // super 必须放在构造器第一行，调用基类构造器
        super(name, color);
    }

    // Dog 独有的方法
    void bark() {
        System.out.println(name + " 汪汪叫");
    }
}

/**
 * Cat 类 — 继承自 Animal
 * 演示：子类添加新行为
 */
class Cat extends Animal {

    Cat(String name, String color) {
        super(name, color);
    }

    void meow() {
        System.out.println(name + " 喵喵叫");
    }
}
/* 输出:
Animal 构造器: 金毛
金毛 正在吃东西
金毛 正在睡觉
金毛 汪汪叫
——————
Animal 构造器: 布偶猫
布偶猫 正在吃东西
布偶猫 正在睡觉
布偶猫 喵喵叫
*/