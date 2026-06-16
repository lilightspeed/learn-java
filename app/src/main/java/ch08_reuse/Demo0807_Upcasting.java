package ch08_reuse;

/**
 * 向上转型 (8.7 节)
 *
 * 向上转型（Upcasting）：将子类引用转为基类引用。
 * 之所以叫"向上"，是因为在继承图中，基类在上，子类在下。
 *
 * 关键点：
 * - 向上转型是安全的（子类一定包含基类的全部接口）
 * - 向上转型会"丢失"子类特有的方法
 * - 向上转型后调用方法，表现的是子类的行为（多态的基础）
 * - "再论组合与继承"：向上转型是区分组合与继承的关键特征
 */
public class Demo0807_Upcasting {

    public static void main(String[] args) {
        System.out.println("=== 直接使用子类 ===");
        Guitar guitar = new Guitar();
        guitar.play();          // 子类重写的方法
        guitar.tune();          // 继承自基类的方法
        guitar.strum();         // 子类特有的方法

        System.out.println("\n=== 向上转型：把 Guitar 当作 Instrument 使用 ===");
        Instrument instrument = guitar;  // 向上转型：自动发生
        instrument.play();      // 仍然调用 Guitar 的 play()（多态）
        instrument.tune();     // 可以调用基类的方法
        // instrument.strum(); // ❌ 编译错误！向上转型后丢失了子类特有方法

        System.out.println("\n=== 方法参数中的向上转型 ===");
        // 向上转型的最大优势：可以编写只操作基类的代码
        tuneInstrument(new Guitar());   // Guitar → Instrument
        tuneInstrument(new Piano());    // Piano → Instrument
        tuneInstrument(new Violin());   // Violin → Instrument

        System.out.println("\n=== 结合数组的向上转型 ===");
        // 用基类数组容纳各种子类对象
        Instrument[] orchestra = {
            new Guitar(),
            new Piano(),
            new Violin()
        };
        for (Instrument ins : orchestra) {
            ins.play();   // 多态：每个乐器发出自己的声音
        }
    }

    /**
     * 这个方法接受 Instrument 引用，而不是具体的子类
     * 这就是"再论组合与继承"中强调的：
     * 继承允许我们向上转型，而组合不行！
     * 这是选择继承而非组合的最重要原因。
     */
    static void tuneInstrument(Instrument ins) {
        System.out.print("调音: ");
        ins.play();
    }
}

/**
 * 基类 — 乐器
 */
class Instrument {
    void play() {
        System.out.println("演奏乐器");
    }

    void tune() {
        System.out.println("调音乐器");
    }
}

/**
 * 吉他 — 继承自 Instrument
 */
class Guitar extends Instrument {
    @Override
    void play() {
        System.out.println("🎸 弹奏吉他: 叮咚铮铮");
    }

    // 子类特有方法
    void strum() {
        System.out.println("扫弦");
    }
}

/**
 * 钢琴 — 继承自 Instrument
 */
class Piano extends Instrument {
    @Override
    void play() {
        System.out.println("🎹 弹奏钢琴: 哆来咪发");
    }
}

/**
 * 小提琴 — 继承自 Instrument
 */
class Violin extends Instrument {
    @Override
    void play() {
        System.out.println("🎻 拉奏小提琴: 悠扬婉转");
    }
}
/* 输出:
=== 直接使用子类 ===
🎸 弹奏吉他: 叮咚铮铮
调音乐器
扫弦

=== 向上转型：把 Guitar 当作 Instrument 使用 ===
🎸 弹奏吉他: 叮咚铮铮
调音乐器

=== 方法参数中的向上转型 ===
调音: 🎸 弹奏吉他: 叮咚铮铮
调音: 🎹 弹奏钢琴: 哆来咪发
调音: 🎻 拉奏小提琴: 悠扬婉转

=== 结合数组的向上转型 ===
🎸 弹奏吉他: 叮咚铮铮
🎹 弹奏钢琴: 哆来咪发
🎻 拉奏小提琴: 悠扬婉转
*/