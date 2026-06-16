package ch08_reuse;

/**
 * 演示组合语法 (8.1 节)
 *
 * 组合（Composition）：在新类中创建已有类的对象作为成员变量。
 * 这是一种"has-a"（有一个）关系。
 * 组合是 Java 代码复用的最基本方式。
 */
public class DemoComposition {

    // 组合：将已有的类作为成员变量
    private Engine engine;
    private Wheels wheels;
    private Stereo stereo;

    public DemoComposition() {
        // 构造器中初始化组合的对象
        engine = new Engine("V6");
        wheels = new Wheels(4);
        stereo = new Stereo("Bose");
    }

    /**
     * 通过组合，Car 暴露了 Engine/Wheels/Stereo 的功能
     */
    public void startCar() {
        engine.start();
        wheels.roll();
        stereo.play();
        System.out.println("汽车已启动！");
    }

    public void stopCar() {
        engine.stop();
        stereo.off();
        System.out.println("汽车已熄火。");
    }

    public static void main(String[] args) {
        DemoComposition car = new DemoComposition();
        car.startCar();
        System.out.println("————行驶中————");
        car.stopCar();
    }
}

/**
 * 引擎类 — 被组合的类之一
 */
class Engine {
    private String type;

    Engine(String type) {
        this.type = type;
    }

    void start() {
        System.out.println(type + " 引擎启动");
    }

    void stop() {
        System.out.println(type + " 引擎熄火");
    }
}

/**
 * 车轮类 — 被组合的类之二
 */
class Wheels {
    private int count;

    Wheels(int count) {
        this.count = count;
    }

    void roll() {
        System.out.println(count + " 个车轮开始滚动");
    }
}

/**
 * 音响类 — 被组合的类之三
 */
class Stereo {
    private String brand;

    Stereo(String brand) {
        this.brand = brand;
    }

    void play() {
        System.out.println(brand + " 音响播放音乐");
    }

    void off() {
        System.out.println(brand + " 音响关闭");
    }
}
/* 输出:
V6 引擎启动
4 个车轮开始滚动
Bose 音响播放音乐
汽车已启动！
————行驶中————
V6 引擎熄火
Bose 音响关闭
汽车已熄火。
*/