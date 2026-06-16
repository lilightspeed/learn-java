package ch10_interfaces;

/**
 * 接口与工厂模式 (10.9 节)
 *
 * 工厂模式（Factory Pattern）：
 * 用接口定义工厂，工厂负责创建实现了某个接口的具体对象。
 *
 * 优势：
 * - 客户端代码只依赖接口和工厂接口，不依赖具体类
 * - 替换产品族时，只需更换工厂实现
 * - 完全符合"开闭原则"
 *
 * 本例：模拟一个"造型工厂"——
 * 可以生产不同的发型（短发、长发），
 * 每种发型由相应的工厂生产。
 */
public class Demo1009_FactoryPattern {

    public static void main(String[] args) {
        System.out.println("=== 工厂模式 ===\n");

        // 通过工厂创建产品，完全不依赖具体实现类
        System.out.println("--- 短发造型 ---");
        createStyle(new ShortHairFactory());

        System.out.println("--- 长发造型 ---");
        createStyle(new LongHairFactory());
    }

    /**
     * 客户端代码只依赖 HairStyle 和 HairStyleFactory 接口！
     * 更换产品系列时只需传入不同的工厂。
     */
    static void createStyle(HairStyleFactory factory) {
        HairStyle style = factory.create();
        style.cut();
        style.style();
    }
}

/**
 * 产品接口 — 发型
 */
interface HairStyle {
    void cut();     // 剪发
    void style();   // 造型
}

/**
 * 工厂接口 — 负责创建发型对象
 */
interface HairStyleFactory {
    HairStyle create();
}

// === 短发产品族 ===

/**
 * 短发
 */
class ShortHair implements HairStyle {
    @Override
    public void cut() {
        System.out.println("✂️ 修剪短发：整齐利落");
    }

    @Override
    public void style() {
        System.out.println("💇 短碎发造型：蓬松有型");
    }
}

/**
 * 短发工厂
 */
class ShortHairFactory implements HairStyleFactory {
    @Override
    public HairStyle create() {
        System.out.println("🏭 短发工厂：准备工具");
        return new ShortHair();
    }
}

// === 长发产品族 ===

/**
 * 长发
 */
class LongHair implements HairStyle {
    @Override
    public void cut() {
        System.out.println("✂️ 修剪长发：去分叉");
    }

    @Override
    public void style() {
        System.out.println("💇 长发波浪卷：优雅动人");
    }
}

/**
 * 长发工厂
 */
class LongHairFactory implements HairStyleFactory {
    @Override
    public HairStyle create() {
        System.out.println("🏭 长发工厂：准备卷发棒");
        return new LongHair();
    }
}
/* 输出:
=== 工厂模式 ===

--- 短发造型 ---
🏭 短发工厂：准备工具
✂️ 修剪短发：整齐利落
💇 短碎发造型：蓬松有型
--- 长发造型 ---
🏭 长发工厂：准备卷发棒
✂️ 修剪长发：去分叉
💇 长发波浪卷：优雅动人
*/