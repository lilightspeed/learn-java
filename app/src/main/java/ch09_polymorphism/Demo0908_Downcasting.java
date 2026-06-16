package ch09_polymorphism;

/**
 * 向下转型 (9.8 节)
 *
 * 【向上转型 Upcasting】
 * - 子类 → 基类，自动、安全
 * - 丢失了子类特有信息
 *
 * 【向下转型 Downcasting】
 * - 基类 → 子类，需要强制类型转换
 * - 不一定安全：如果对象实际类型不是目标类型，会抛出 ClassCastException
 *
 * 【RTTI — Run-Time Type Identification（运行时类型识别）】
 * - Java 在运行时通过 instanceof 检查对象类型
 * - 每个对象都携带运行时类型信息（在 Class 对象中）
 * - 向下转型前用 instanceof 做安全检查
 *
 * 注意：Java 21 中可以使用更现代的 switch 模式匹配来替代
 * instanceof 的强制转型。
 */
public class Demo0908_Downcasting {

    public static void main(String[] args) {
        System.out.println("=== 向下转型与 RTTI ===\n");

        // 向上转型 — 自动安全
        Creature[] creatures = {
            new Hound(),
            new Feline()
        };

        // 向下转型 — 需要强制转换，且可能不安全
        for (Creature c : creatures) {
            // RTTI：每个 Creature 对象都知道自己的实际类型
            System.out.println("对象的实际类型: " + c.getClass().getSimpleName());

            // 安全的向下转型：先检查 instanceof
            if (c instanceof Hound) {
                Hound h = (Hound) c;    // 向下转型
                h.bark();               // 调用 Hound 特有方法
            }

            if (c instanceof Feline) {
                Feline f = (Feline) c;  // 向下转型
                f.meow();               // 调用 Feline 特有方法
            }
            System.out.println("---");
        }

        // ⚠️ 不安全的向下转型
        System.out.println("=== 不安全的向下转型 ===");
        Creature creature = new Hound();  // 表面上：Hound
        try {
            // ❌ Hound 对象不能转成 Feline！
            Feline feline = (Feline) creature;
        } catch (ClassCastException e) {
            System.out.println("捕获到 ClassCastException: " + e.getMessage());
        }

        // 用 instanceof 保护：
        System.out.println("\n使用 instanceof 保护后：");
        if (creature instanceof Feline feline) {
            // Java 16+ 模式匹配：转型后直接使用 feline 变量
            feline.meow();
        } else {
            System.out.println("creature 不是 Feline 类型，安全跳过");
        }
    }
}

/**
 * 生物 — 基类
 */
abstract class Creature {
    abstract void makeSound();
}

/**
 * 猎犬
 */
class Hound extends Creature {
    @Override
    void makeSound() {
        System.out.println("汪汪");
    }

    /**
     * 猎犬特有的方法
     */
    void bark() {
        System.out.println("🐕 猎犬在摇尾巴汪汪叫");
    }
}

/**
 * 猫科动物
 */
class Feline extends Creature {
    @Override
    void makeSound() {
        System.out.println("喵喵");
    }

    /**
     * 猫科动物特有的方法
     */
    void meow() {
        System.out.println("🐱 猫科动物在优雅地喵喵叫");
    }
}
/* 输出:
=== 向下转型与 RTTI ===

对象的实际类型: Hound
🐕 猎犬在摇尾巴汪汪叫
---
对象的实际类型: Feline
🐱 猫科动物在优雅地喵喵叫
---
=== 不安全的向下转型 ===
捕获到 ClassCastException: class ch09_polymorphism.Hound cannot be cast to class ch09_polymorphism.Feline

使用 instanceof 保护后：
creature 不是 Feline 类型，安全跳过
*/