package ch10_interfaces;

/**
 * 抽象类与抽象方法 (10.1 节)
 *
 * 抽象方法：只有声明没有实现的方法（用 abstract 修饰）
 * 抽象类：包含抽象方法的类（必须也用 abstract 修饰）
 *
 * 特点：
 * - 抽象类不能直接实例化（不能 new）
 * - 子类必须实现所有抽象方法，除非子类也是抽象类
 * - 抽象类可以包含非抽象方法、成员变量、构造器
 * - 抽象方法类似于"接口"的雏形，只定义规范不定义实现
 *
 * 抽象类 vs 普通类：抽象类强制子类实现某些行为，提供了更高的设计约束。
 */
public class Demo1001_AbstractClass {

    public static void main(String[] args) {
        System.out.println("=== 抽象类与抽象方法 ===\n");

        // Instrument 是抽象的，不能直接创建：
        // new Instrument();  // ❌ 编译错误

        Instrument[] orchestra = {
            new Wind(),
            new Percussion(),
            new Stringed()
        };

        for (Instrument ins : orchestra) {
            ins.play(Note.MIDDLE_C);   // 抽象方法的实现
            ins.adjust();               // 非抽象方法
            System.out.println("---");
        }
    }
}

/**
 * 音符枚举
 */
enum Note {
    MIDDLE_C, C_SHARP, B_FLAT
}

/**
 * 抽象类 — 乐器
 *
 * 抽象类可以有：
 * - 抽象方法（子类必须实现）
 * - 普通方法（子类可以继承或覆盖）
 * - 成员变量、构造器
 */
abstract class Instrument {
    // 抽象方法：没有方法体，子类必须实现
    abstract void play(Note n);

    // 抽象类可以包含非抽象方法
    void adjust() {
        System.out.println("调音中...");
    }
}

/**
 * 管乐器
 */
class Wind extends Instrument {
    @Override
    void play(Note n) {
        System.out.println("Wind.play() " + n);
    }
}

/**
 * 打击乐器
 */
class Percussion extends Instrument {
    @Override
    void play(Note n) {
        System.out.println("Percussion.play() " + n);
    }
}

/**
 * 弦乐器
 */
class Stringed extends Instrument {
    @Override
    void play(Note n) {
        System.out.println("Stringed.play() " + n);
    }
}
/* 输出:
=== 抽象类与抽象方法 ===

Wind.play() MIDDLE_C
调音中...
---
Percussion.play() MIDDLE_C
调音中...
---
Stringed.play() MIDDLE_C
调音中...
*/