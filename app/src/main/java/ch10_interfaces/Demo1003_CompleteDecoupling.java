package ch10_interfaces;

/**
 * 完全解耦 (10.3 节)
 *
 * 接口是实现"解耦"的关键工具：
 * - 策略模式（Strategy Pattern）的典型应用
 * - 使用者只依赖接口，不依赖具体实现
 * - 可以随时切换实现类，无需修改使用者代码
 *
 * 本例演示：一个文本处理器（Processor）——
 * 接口定义了处理规范，不同策略提供不同实现。
 */
public class Demo1003_CompleteDecoupling {

    public static void main(String[] args) {
        System.out.println("=== 完全解耦：策略模式 ===\n");

        String input = "Hello Java Interface";

        System.out.println("原始字符串: \"" + input + "\"\n");

        // 使用不同策略处理同一个字符串
        process(input, new UpperCaseProcessor());
        process(input, new LowerCaseProcessor());
        process(input, new ReverseProcessor());
    }

    /**
     * 这个方法完全依赖于 Processor 接口，
     * 根本不关心具体实现！这就是"解耦"。
     *
     * 无论新增多少种 Processor，此方法都不需要修改。
     */
    static void process(String input, Processor p) {
        System.out.println("策略: " + p.name());
        System.out.println("结果: " + p.process(input));
        System.out.println();
    }
}

/**
 * 处理器接口 — 定义了处理规范
 *
 * "完全解耦"的思路：
 * 1. 调用者只依赖这个接口
 * 2. 可以替换任意实现类
 * 3. 新增实现类不需要修改调用代码
 */
interface Processor {
    /** 返回策略名称 */
    String name();

    /** 处理输入的字符串 */
    String process(String input);
}

/**
 * 转大写处理器
 */
class UpperCaseProcessor implements Processor {
    @Override
    public String name() {
        return "转大写";
    }

    @Override
    public String process(String input) {
        return input.toUpperCase();
    }
}

/**
 * 转小写处理器
 */
class LowerCaseProcessor implements Processor {
    @Override
    public String name() {
        return "转小写";
    }

    @Override
    public String process(String input) {
        return input.toLowerCase();
    }
}

/**
 * 反转处理器
 */
class ReverseProcessor implements Processor {
    @Override
    public String name() {
        return "反转";
    }

    @Override
    public String process(String input) {
        return new StringBuilder(input).reverse().toString();
    }
}
/* 输出:
=== 完全解耦：策略模式 ===

原始字符串: "Hello Java Interface"

策略: 转大写
结果: HELLO JAVA INTERFACE

策略: 转小写
结果: hello java interface

策略: 反转
结果: ecafretnI avaJ olleH
*/