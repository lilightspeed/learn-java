package ch09_polymorphism;

/**
 * 协变返回类型 (9.7 节)
 *
 * Java 5（JDK 1.5）引入的特性：
 * 子类覆盖方法时，可以返回比基类方法"更具体"的返回类型。
 *
 * 协变返回类型的前提：
 * - 子类的返回类型是基类返回类型的子类型（子类/实现类）
 * - 例如：基类返回 Animal，子类返回 Dog（Dog 是 Animal 的子类）
 *
 * 意义：让覆盖方法可以返回更具体的类型，提高类型精确性。
 */
public class Demo0907_CovariantReturn {

    public static void main(String[] args) {
        System.out.println("=== 协变返回类型 ===\n");

        // 通过基类引用获取谷物加工器
        GrainMill mill = new WheatMill();
        Grain grain = mill.process();
        System.out.println("加工结果: " + grain);

        // 通过子类引用可以获取更具体的类型
        WheatMill wheatMill = new WheatMill();
        Wheat wheat = wheatMill.process();  // 直接返回 Wheat！无需强制转型
        System.out.println("具体的加工结果: " + wheat);
        wheat.grind();  // 可以调用 Wheat 特有的方法
    }
}

// === 基类型层次结构 ===

/**
 * 谷物
 */
class Grain {
    @Override
    public String toString() {
        return "谷物";
    }
}

/**
 * 谷物加工器 — 基类
 */
class GrainMill {
    /**
     * 加工谷物，返回 Grain 类型
     */
    Grain process() {
        System.out.println("GrainMill.process()");
        return new Grain();
    }
}

// === 子类型层次结构 ===

/**
 * 小麦 — 继承自 Grain
 */
class Wheat extends Grain {
    @Override
    public String toString() {
        return "小麦粉 🥟";
    }

    /**
     * 小麦特有的方法
     */
    void grind() {
        System.out.println("把小麦磨成细腻的面粉");
    }
}

/**
 * 面粉厂 — 继承自 GrainMill
 *
 * 覆盖 process() 时，返回类型从 Grain 变为 Wheat，
 * Wheat 是 Grain 的子类，这就是协变返回类型。
 */
class WheatMill extends GrainMill {
    /**
     * 协变返回类型：Grain → Wheat
     * 在 Java 5 之前，这会产生编译错误
     */
    @Override
    Wheat process() {
        System.out.println("WheatMill.process()");
        return new Wheat();
    }
}
/* 输出:
=== 协变返回类型 ===

GrainMill.process()
加工结果: 谷物
WheatMill.process()
具体的加工结果: 小麦粉 🥟
把小麦磨成细腻的面粉
*/