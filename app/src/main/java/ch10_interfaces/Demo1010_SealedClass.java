package ch10_interfaces;

/**
 * 密封类 (10.10 节)
 *
 * sealed 类（Java 17 正式引入）：
 * 限制哪些类可以继承或实现它。
 *
 * 关键词：
 * - sealed：声明一个类是密封的
 * - permits：列出允许继承的子类
 * - non-sealed：允许子类开放继承
 * - final：终止继承链
 *
 * 接口也可以密封！限制哪些类可以实现该接口。
 *
 * 有了密封类，可以精确控制系统中的类型层次结构。
 */
public class Demo1010_SealedClass {

    public static void main(String[] args) {
        System.out.println("=== 密封类 ===\n");

        // 密封类允许创建有限类型的子类
        Shape[] shapes = {
            new Circle(5.0),
            new Rectangle(3.0, 4.0),
            new Triangle(3.0, 4.0, 5.0)
        };

        for (Shape s : shapes) {
            System.out.printf("%s 面积: %.2f%n",
                s.getClass().getSimpleName(), s.area());
        }

        System.out.println("\n💡 注意：Shape sealed 接口只允许" +
            "Circle、Rectangle、Triangle 三种实现，" +
            "其他类无法继承 Shape！");
    }
}

/**
 * 密封接口（sealed interface）：
 * 只允许指定的 3 个类实现该接口
 */
sealed interface Shape permits Circle, Rectangle, Triangle {
    double area();
}

/**
 * 圆形 — 允许（在 permits 列表中）
 *
 * final：此类的继承链终止，不能再有子类
 */
final class Circle implements Shape {
    private final double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}

/**
 * 矩形 — 允许
 *
 * non-sealed：允许任何人继续继承 Rectangle
 */
non-sealed class Rectangle implements Shape {
    private final double width;
    private final double height;

    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return width * height;
    }
}

/**
 * 三角形 — 允许
 *
 * 使用 record 实现 sealed 接口也是可以的
 */
record Triangle(double a, double b, double c) implements Shape {
    @Override
    public double area() {
        // 海伦公式
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }
}

// 以下代码去掉注释将编译错误：
// class Square implements Shape { }   // ❌ 不在 permits 列表中
/* 输出:
=== 密封类 ===

Circle 面积: 78.54
Rectangle 面积: 12.00
Triangle 面积: 6.00

💡 注意：Shape sealed 接口只允许 Circle、Rectangle、Triangle 三种实现，其他类无法继承 Shape！
*/