package ch08_reuse;

/**
 * 委托 (8.3 节)
 *
 * 委托（Delegation）：介于组合和继承之间的复用方式。
 * 我们在新类中持有被委托类的引用（像组合），
 * 但主动在新类的方法中调用被委托对象的方法（像继承的转发）。
 *
 * Java 没有对委托的原生语言支持，需要手动编写转发方法。
 * 委托的优势：可以精确控制暴露哪些方法，比继承更灵活。
 */
public class Demo0803_Delegation {

    public static void main(String[] args) {
        // 使用委托的打印机
        System.out.println("=== 使用委托的 Printer ===");
        Printer printer = new Printer("HP LaserJet");
        printer.print("Hello World");        // 委托给 RealPrinter.print()
        printer.printLine("Java 委托示例");   // 另一种委托方式
        printer.feedPaper();                  // Printer 自己的行为

        System.out.println();

        // 对比：不使用委托直接调用
        System.out.println("=== 直接使用 RealPrinter ===");
        RealPrinter real = new RealPrinter("Epson");
        real.print("直接调用");
    }
}

/**
 * 真正的打印机类 — 负责底层打印操作
 */
class RealPrinter {
    private String model;

    RealPrinter(String model) {
        this.model = model;
    }

    void print(String text) {
        System.out.println("[" + model + "] 打印: " + text);
    }

    void printLine(String text) {
        System.out.println("[" + model + "] 逐行打印: " + text);
    }

    void checkInk() {
        System.out.println("[" + model + "] 检查墨水");
    }

    void powerOff() {
        System.out.println("[" + model + "] 关机");
    }
}

/**
 * Printer 类 — 通过委托复用 RealPrinter 的功能
 *
 * 与继承不同：Printer 不是 RealPrinter（没有 is-a 关系）
 * 与纯组合不同：Printer 暴露了 RealPrinter 的部分方法（转发调用）
 */
class Printer {
    private String brand;
    // 组合：持有被委托者的引用
    private RealPrinter realPrinter;

    Printer(String brand) {
        this.brand = brand;
        // 创建被委托的对象
        this.realPrinter = new RealPrinter(brand);
    }

    // 委托方法：将打印任务委托给 RealPrinter
    void print(String text) {
        // 委托前可以加自己的逻辑
        System.out.println("[" + brand + " 委托] 准备打印...");
        realPrinter.print(text);  // 关键：转发调用
        // 委托后也可以加逻辑
        System.out.println("[" + brand + " 委托] 打印完成");
    }

    // 另一种委托方式：不同风格的方法名映射
    void printLine(String text) {
        realPrinter.printLine(text);
    }

    // Printer 独有的行为（不在 RealPrinter 中）
    void feedPaper() {
        System.out.println("[" + brand + "] 自动进纸");
    }

    // 可以选择不暴露某些方法给外部
    // 例如，不提供 checkInk() 和 powerOff() 的委托
    // 这就是委托比继承更精确控制的地方
}
/* 输出:
=== 使用委托的 Printer ===
[HP LaserJet 委托] 准备打印...
[HP LaserJet] 打印: Hello World
[HP LaserJet 委托] 打印完成
[HP LaserJet] 逐行打印: Java 委托示例
[HP LaserJet] 自动进纸

=== 直接使用 RealPrinter ===
[Epson] 打印: 直接调用
*/