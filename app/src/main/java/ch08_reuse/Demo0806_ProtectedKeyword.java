package ch08_reuse;

/**
 * protected 关键字 (8.6 节)
 *
 * protected 是 Java 四种访问权限修饰符之一。
 * 它提供了「继承访问权限」：
 * - 对包内其他类：可见（等同于包访问权限）
 * - 对子类（即使在不同包中）：可见
 * - 对非子类且不同包的类：不可见
 *
 * 注意：此处所有类在同一包中，为了更好地演示 protected 的"继承"特性，
 * 我们在不同包的子类中访问 protected 成员的效果，
 * 本示例将通过同包子类的角度来演示。
 */
public class Demo0806_ProtectedKeyword {

    public static void main(String[] args) {
        System.out.println("=== 同包访问 ===");
        OrganicFood food = new OrganicFood("有机苹果", 15.0);
        // 同包中可以直接访问 protected 方法（因同包可见）
        food.displayInfo();
        // 同包中可以访问 public 方法
        food.showPrice();

        System.out.println("\n=== 子类访问 ===");
        ImportedFood imported = new ImportedFood("进口车厘子", 68.0, "智利");
        imported.displayInfo();    // 继承自 OrganicFood
        imported.showOrigin();     // ImportedFood 自己的方法中调用了父类的 protected 字段
    }
}

/**
 * 食品基类 — 演示 protected 的用法
 */
class Food {
    public String name;          // public: 任何人都能访问
    private double price;        // private: 只有本类能访问
    protected String category;   // protected: 子类 + 同包可访问

    Food(String name, double price) {
        this.name = name;
        this.price = price;
        this.category = "普通食品";
    }

    // protected 方法：子类可以调用，同包可以调用
    protected String getPriceInfo() {
        return "¥" + price;   // private 字段只能在本类内部访问
    }

    // public 方法：对外公开
    public void showPrice() {
        System.out.println(name + " 价格: " + getPriceInfo());
    }
}

/**
 * OrganicFood 类 — 继承自 Food，同包
 *
 * 子类中可以访问基类的 protected 成员
 */
class OrganicFood extends Food {

    OrganicFood(String name, double price) {
        super(name, price);
        this.category = "有机食品";   // 访问基类的 protected 字段
    }

    // 调用基类的 protected 方法
    protected void displayInfo() {
        System.out.println("[" + category + "] " + name + ", " + getPriceInfo());
    }
}

/**
 * ImportedFood 类 — 进一步继承
 *
 * protected 成员可以一直沿继承链传递下去
 */
class ImportedFood extends OrganicFood {
    private String origin;

    ImportedFood(String name, double price, String origin) {
        super(name, price);
        this.origin = origin;
        this.category = "进口食品";   // 再次访问 protected 字段（两级继承后仍可访问）
    }

    void showOrigin() {
        // protected 方法在子类中仍然可以调用
        System.out.println("[" + category + "] " + name + ", 产地: " + origin + ", " + getPriceInfo());
    }
}
/* 输出:
=== 同包访问 ===
[有机食品] 有机苹果, ¥15.0
有机苹果 价格: ¥15.0

=== 子类访问 ===
[进口食品] 进口车厘子, ¥68.0
[进口食品] 进口车厘子, 产地: 智利, ¥68.0
*/