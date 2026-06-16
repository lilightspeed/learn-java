package ch09_polymorphism;

/**
 * 构造器内部的多态 (9.6 节)
 *
 * ⚠️ 警告：在构造器中调用多态方法要格外小心！
 *
 * 问题根源：
 * - 如果在基类构造器中调用了某个方法
 * - 而该方法被子类覆盖了
 * - 那么在创建子类对象时，基类构造器调用的是子类的覆盖版本
 * - 但此时子类的成员变量可能还没有初始化！
 *
 * 执行顺序：
 * 1. 子类构造器被调用
 * 2. 子类构造器第一行隐式调用 super()（基类构造器）
 * 3. 基类构造器执行 — 内部调用了多态方法（→ 调用子类覆盖版本）
 * 4. 但此时子类成员变量还是默认值（null、0、false）
 * 5. 基类构造器执行完毕
 * 6. 子类成员变量初始化
 * 7. 子类构造器体执行
 *
 * 最佳实践：在构造器中只调用 private 或 final 方法，
 * 避免调用可被覆盖的方法。
 */
public class Demo0906_PolymorphismInConstructor {

    public static void main(String[] args) {
        System.out.println("=== 构造器内部的多态 ===\n");
        System.out.println("创建子类对象：");
        new Sub();

        System.out.println("\n--- 思考题 ---");
        System.out.println("draw() 被调用了两次：" +
            "一次在基类构造器中（看不到半径），" +
            "一次在子类构造器后（半径已初始化）");
    }
}

/**
 * 图形 — 基类
 */
class Shape {

    Shape() {
        // ❌ 危险：在基类构造器中调用可被覆盖的方法
        // 此时子类的成员变量还未初始化！
        System.out.println("Shape() 构造器 -> 调用 draw()");
        draw();     // 多态！会调用子类覆盖版本的 draw()

        System.out.println("Shape() 构造器结束");
    }

    /**
     * 绘制图形 — 可被子类覆盖
     */
    void draw() {
        System.out.println("Shape.draw()");
    }
}

/**
 * 圆形 — 子类
 */
class Sub extends Shape {
    /**
     * 圆形的半径 — 在构造器中初始化
     */
    private int radius = 10;

    Sub() {
        // 隐式 super() → 先调用 Shape() 构造器
        // 在 Shape() 执行期间，radius 还是默认值 0！
        System.out.println("Sub() 构造器 -> 此时 radius = " + radius);
        // 此时 radius 已经初始化了（= 10）
    }

    /**
     * 覆盖基类的 draw()
     */
    @Override
    void draw() {
        // 当 Shape() 构造器调用 draw() 时，
        // radius 还是 0（尚未初始化）
        System.out.println("Sub.draw()，半径 = " + radius);
    }
}
/* 输出:
=== 构造器内部的多态 ===

创建子类对象：
Shape() 构造器 -> 调用 draw()
Sub.draw()，半径 = 0        ← ⚠️ 半径还未初始化！
Shape() 构造器结束
Sub() 构造器 -> 此时 radius = 10  ← 现在才初始化

--- 思考题 ---
draw() 被调用了两次：一次在基类构造器中（看不到半径），一次在子类构造器后（半径已初始化）
*/