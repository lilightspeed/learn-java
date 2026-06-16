package ch10_interfaces;

/**
 * 适配接口 / 适配器模式 (10.6 节)
 *
 * 适配器模式（Adapter Pattern）：
 * 将一个类的接口转换成客户端期望的另一个接口。
 *
 * 在接口的语境中，适配器模式就是：
 * 让一个已有的类通过实现目标接口，来适配新的使用场景。
 *
 * 本例演示：有一个 Filter 接口（过滤器），
 * 但我们想把"波形处理"应用到过滤器场景中。
 * 通过 WaveformAdapter 适配器实现两者的对接。
 */
public class Demo1006_AdapterPattern {

    public static void main(String[] args) {
        System.out.println("=== 适配器模式 ===\n");

        Processor p = new WaveformAdapter();
        // WaveformAdapter 通过实现 Processor 接口，
        // 让 Waveform 能用在需要 Processor 的场景中

        String result = p.process("Hello World");
        System.out.println("处理结果: \"" + result + "\"");
    }
}

/**
 * 已有类 — 波形处理器
 * 它的接口不兼容 Processor，需要适配
 */
class Waveform {
    /**
     * 已有的处理方法，但方法签名不同
     */
    static String transform(String input) {
        StringBuilder sb = new StringBuilder();
        for (char c : input.toCharArray()) {
            // 简单的"波形"转换：大小写交替
            sb.append(Character.isUpperCase(c)
                ? Character.toLowerCase(c)
                : Character.toUpperCase(c));
        }
        return sb.toString();
    }
}

/**
 * 适配器 — 让 Waveform 适配 Processor 接口
 *
 * 适配器模式三要素：
 * 1. 目标接口（Processor）— 期待什么
 * 2. 被适配者（Waveform）— 已有的类
 * 3. 适配器（WaveformAdapter）— 中间的转换层
 */
class WaveformAdapter implements Processor {
    @Override
    public String name() {
        return "波形变换适配器";
    }

    @Override
    public String process(String input) {
        // 适配：调用 Waveform 的 transform() 方法
        return Waveform.transform(input);
    }
}
/* 输出:
=== 适配器模式 ===

处理结果: "hELLO wORLD"
*/