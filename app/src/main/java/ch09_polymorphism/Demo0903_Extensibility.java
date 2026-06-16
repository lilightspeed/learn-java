package ch09_polymorphism;

/**
 * 可扩展性 (9.3 节)
 *
 * 多态的一个关键优势：程序对扩展开放，对修改封闭（开闭原则）。
 *
 * 利用多态，我们可以编写只操作基类接口的代码，
 * 当需要添加新类型时，只需继承基类并实现方法即可，
 * 完全不需要修改已有的代码。
 *
 * 本例演示：模拟一个「动物园表演」系统，
 * 通过 Animal 基类接口统一处理所有动物。
 */
public class Demo0903_Extensibility {

    public static void main(String[] args) {
        System.out.println("=== 动物园表演 ===\n");

        // 只操作 Animal 引用的方法
        Animal[] zoo = {
            new Dog(),
            new Cat(),
            new Bird()
        };

        System.out.println("--- 表演开始 ---");
        performShow(zoo);

        // 扩展：新增动物种类，无需修改 performShow() 方法！
        System.out.println("\n--- 加场表演（新增海豚🐬）---");
        Animal[] extendedZoo = {
            new Dog(),
            new Cat(),
            new Bird(),
            new Dolphin()   // 新类型！不需要修改 performShow()
        };
        performShow(extendedZoo);
    }

    /**
     * 统一处理所有 Animal 的表演流程。
     * 这个方法只依赖基类 Animal 的接口，
     * 对任何新添加的 Animal 子类都适用。
     *
     * 这就是"对扩展开放，对修改封闭"的体现：
     * - 开放：可以随时添加新子类
     * - 封闭：已有代码（此方法）不需要修改
     */
    static void performShow(Animal[] animals) {
        for (Animal a : animals) {
            a.appear();     // 出场致意
            a.perform();    // 表演才艺
            a.leave();      // 退场
            System.out.println("---");
        }
    }
}

/**
 * 抽象基类 — 动物
 * 定义了表演的基础接口
 */
abstract class Animal {
    abstract void appear();   // 出场
    abstract void perform();  // 表演
    abstract void leave();    // 退场
}

/**
 * 狗
 */
class Dog extends Animal {
    @Override
    void appear()  { System.out.println("🐕 狗狗摇着尾巴跑上来"); }

    @Override
    void perform() { System.out.println("🐕 狗狗算术：1+1=2！汪汪！"); }

    @Override
    void leave()   { System.out.println("🐕 狗狗叼着球跑下去"); }
}

/**
 * 猫
 */
class Cat extends Animal {
    @Override
    void appear()  { System.out.println("🐱 猫咪优雅地走上来"); }

    @Override
    void perform() { System.out.println("🐱 猫咪钻火圈：嗖！"); }

    @Override
    void leave()   { System.out.println("🐱 猫咪高傲地甩尾巴离开"); }
}

/**
 * 鸟
 */
class Bird extends Animal {
    @Override
    void appear()  { System.out.println("🐦 小鸟扑扇翅膀飞上来"); }

    @Override
    void perform() { System.out.println("🐦 小鸟唱了一首动听的歌🎵"); }

    @Override
    void leave()   { System.out.println("🐦 小鸟振翅飞走"); }
}

/**
 * 海豚 — 新增的动物种类
 *
 * 💡 关键点：我们新增了 Dolphin 类，但 performShow() 方法
 *    一行代码都没有修改！多态让程序具有了可扩展性。
 */
class Dolphin extends Animal {
    @Override
    void appear()  { System.out.println("🐬 海豚从水中一跃而出"); }

    @Override
    void perform() { System.out.println("🐬 海豚顶球：球在鼻尖旋转！"); }

    @Override
    void leave()   { System.out.println("🐬 海豚潜入水中，溅起一片水花"); }
}
/* 输出:
=== 动物园表演 ===

--- 表演开始 ---
🐕 狗狗摇着尾巴跑上来
🐕 狗狗算术：1+1=2！汪汪！
🐕 狗狗叼着球跑下去
---
🐱 猫咪优雅地走上来
🐱 猫咪钻火圈：嗖！
🐱 猫咪高傲地甩尾巴离开
---
🐦 小鸟扑扇翅膀飞上来
🐦 小鸟唱了一首动听的歌🎵
🐦 小鸟振翅飞走
---

--- 加场表演（新增海豚🐬）---
🐕 狗狗摇着尾巴跑上来
🐕 狗狗算术：1+1=2！汪汪！
🐕 狗狗叼着球跑下去
---
🐱 猫咪优雅地走上来
🐱 猫咪钻火圈：嗖！
🐱 猫咪高傲地甩尾巴离开
---
🐦 小鸟扑扇翅膀飞上来
🐦 小鸟唱了一首动听的歌🎵
🐦 小鸟振翅飞走
---
🐬 海豚从水中一跃而出
🐬 海豚顶球：球在鼻尖旋转！
🐬 海豚潜入水中，溅起一片水花
---
*/