package ch10_interfaces;

/**
 * 多重继承 (10.4 节)
 *
 * Java 不支持类的多重继承（一个类只能继承一个父类），
 * 但一个类可以实现多个接口，从而达到多重继承的效果。
 *
 * 接口的多重继承优势：
 * - 没有菱形继承问题（接口中的方法没有实现，没有冲突）
 * - 可以组合多个接口的能力
 * - 类可以向上转型到任意一个实现的接口类型
 *
 * 注意：Java 8+ 的 default 方法可能引发冲突，需要显式解决。
 */
public class Demo1004_MultipleInheritance {

    public static void main(String[] args) {
        System.out.println("=== 通过接口实现多重继承 ===\n");

        SuperHero hero = new SuperHero("蜘蛛侠");

        // 一个对象可以向上转型为任意一个实现的接口类型
        CanFly asFlyer = hero;    // 当作 Flyer 使用
        CanFight asFighter = hero; // 当作 Fighter 使用
        CanSwim asSwimmer = hero;  // 当作 Swimmer 使用

        System.out.println("--- 作为飞行者 ---");
        asFlyer.fly();

        System.out.println("\n--- 作为格斗者 ---");
        asFighter.fight();

        System.out.println("\n--- 作为游泳者 ---");
        asSwimmer.swim();

        System.out.println("\n--- 全面展示 ---");
        hero.showIdentity();
    }
}

// === 接口定义 ===

interface CanFly {
    void fly();
}

interface CanFight {
    void fight();
}

interface CanSwim {
    void swim();
}

/**
 * 超级英雄 — 同时实现三个接口！
 *
 * 这就是多重继承的效果：
 * 一个类从多个接口继承了行为规范。
 */
class SuperHero implements CanFly, CanFight, CanSwim {

    private final String name;

    SuperHero(String name) {
        this.name = name;
    }

    @Override
    public void fly() {
        System.out.println(name + " 在高空飞行 🦸");
    }

    @Override
    public void fight() {
        System.out.println(name + " 与敌人搏斗 💥");
    }

    @Override
    public void swim() {
        System.out.println(name + " 在水中穿梭 🌊");
    }

    void showIdentity() {
        System.out.println("我是 " + name + "！我能飞、能打、能游！");
    }
}
/* 输出:
=== 通过接口实现多重继承 ===

--- 作为飞行者 ---
蜘蛛侠 在高空飞行 🦸

--- 作为格斗者 ---
蜘蛛侠 与敌人搏斗 💥

--- 作为游泳者 ---
蜘蛛侠 在水中穿梭 🌊

--- 全面展示 ---
我是 蜘蛛侠！我能飞、能打、能游！
*/