package ch10_interfaces;

/**
 * 接口中的默认方法 (10.11 节)
 *
 * default 方法（Java 8 引入）：
 * 允许在接口中提供方法的默认实现。
 *
 * 为什么需要 default 方法？
 * - 接口演化的需要：给已有接口添加新方法而不破坏现有实现
 * - 例如 Java 8 为 Collection 接口添加了 stream() 方法
 *
 * 冲突解决规则：
 * 1. 类中的方法优先级最高
 * 2. 子接口的 default 方法优先级高于父接口
 * 3. 如果仍有冲突，必须显式覆盖
 *
 * Java 9 还引入了 private 方法：
 * - 接口中的 private 方法（辅助方法，不暴露给外部）
 * - 可以被 default 方法或其它 private 方法调用
 */
public class Demo1011_DefaultMethod {

    public static void main(String[] args) {
        System.out.println("=== 接口中的默认方法 ===\n");

        // 测试默认方法
        Duck duck = new Duck();
        duck.swim();        // 继承默认实现
        duck.quack();       // 自定义实现
        duck.dive();        // 默认方法调用 private 方法

        System.out.println();

        // 多态：通过接口引用调用
        Swimmable s = new Duck();
        s.swim();
        s.quack();
    }
}

/**
 * 可游泳的 — 接口
 *
 * Java 8+ 可以在接口中添加带默认实现的方法
 */
interface Swimmable {
    /**
     * 游泳 — 默认方法（有默认实现）
     */
    default void swim() {
        System.out.println("🏊 在水中游泳（默认动作：狗刨）");
        // 默认方法可以调用接口中的 private 方法
        warmUp();
    }

    /**
     * 叫 — 抽象方法（子类必须实现）
     */
    void quack();

    /**
     * 潜水 — 默认方法
     */
    default void dive() {
        System.out.print("🤿 潜水：");
        holdBreath();
        System.out.println("潜了10秒");
    }

    /**
     * private 方法（Java 9+）：
     * 接口内部的辅助方法，不对外暴露
     */
    private void holdBreath() {
        System.out.print("屏住呼吸...");
    }

    /**
     * static 方法（Java 8+）：
     * 接口中的工具方法，通过"接口名.方法名"调用
     */
    static void info() {
        System.out.println("Swimmable 接口：定义了游泳相关行为");
    }

    private void warmUp() {
        System.out.println("   ↳ 热身运动：活动关节");
    }
}

/**
 * 鸭子 — 实现 Swimmable 接口
 *
 * 只需要实现抽象方法 quack()，
 * swim() 和 dive() 可以使用默认实现。
 */
class Duck implements Swimmable {
    @Override
    public void quack() {
        System.out.println("🦆 嘎嘎嘎！");
    }
}
/* 输出:
=== 接口中的默认方法 ===

🏊 在水中游泳（默认动作：狗刨）
   ↳ 热身运动：活动关节
🦆 嘎嘎嘎！
🤿 潜水：屏住呼吸...潜了10秒

🏊 在水中游泳（默认动作：狗刨）
   ↳ 热身运动：活动关节
🦆 嘎嘎嘎！
*/