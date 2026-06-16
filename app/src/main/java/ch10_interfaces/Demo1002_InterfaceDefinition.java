package ch10_interfaces;

/**
 * 接口定义与实现 (10.2 节)
 *
 * 接口（interface）是比抽象类更纯粹的抽象：
 * - 接口中所有方法默认都是 public abstract 的
 * - 接口中所有字段默认都是 public static final 的
 * - 类使用 implements 来实现接口
 * - 一个类可以实现多个接口
 *
 * 接口的演变（Java 8+）：
 * - Java 8 引入 default 和 static 方法
 * - Java 9 引入 private 方法
 *
 * 本 Demo 演示最基本的接口定义和实现。
 */
public class Demo1002_InterfaceDefinition {

    public static void main(String[] args) {
        System.out.println("=== 接口定义与实现 ===\n");

        // 向上转型：用接口引用指向实现类对象
        CanDo c = new Car();

        c.start();      // 接口方法
        c.stop();       // 接口方法
        // c.chargeBattery();  // ❌ 接口引用看不到实现类特有方法

        System.out.println("---");
        System.out.println("实现类特有的方法需要向下转型：");
        Car car = (Car) c;
        car.chargeBattery();
    }
}

/**
 * 交通工具接口
 */
interface CanDo {
    // 接口方法：默认是 public abstract
    void start();
    void stop();
}

/**
 * 汽车 — 实现 CanDo 接口
 */
class Car implements CanDo {
    @Override
    public void start() {
        System.out.println("🚗 汽车启动：发动机轰鸣");
    }

    @Override
    public void stop() {
        System.out.println("🚗 汽车停止：刹车片夹紧");
    }

    /**
     * 实现类特有的方法（不在接口中）
     */
    void chargeBattery() {
        System.out.println("🔋 给汽车电池充电");
    }
}
/* 输出:
=== 接口定义与实现 ===

🚗 汽车启动：发动机轰鸣
🚗 汽车停止：刹车片夹紧
---
实现类特有的方法需要向下转型：
🔋 给汽车电池充电
*/