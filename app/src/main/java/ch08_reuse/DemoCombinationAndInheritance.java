package ch08_reuse;

/**
 * 演示组合与继承相结合 (8.4 节)
 *
 * 在实际开发中，组合和继承往往同时使用。
 * 关键原则：组合用于"has-a"关系，继承用于"is-a"关系。
 *
 * 本示例还演示了：
 * - 8.4.1 确保正确的清理
 * - 8.4.2 名称隐藏（子类覆盖基类方法）
 */
public class DemoCombinationAndInheritance {

    public static void main(String[] args) {
        // 创建一个 Home 对象
        Home home = new Home("梦想家园");
        home.live();
        System.out.println("————清理前————");
        // 显式清理（书中 8.4.1 节强调：清理顺序与初始化顺序相反）
        home.dispose();

        System.out.println("\n==================\n");

        // 演示名称隐藏
        System.out.println("=== 名称隐藏 ===");
        Child child = new Child();
        child.f();     // 调用 Child 的 f()
        child.f(42);   // 继承自 Parent 的 f(int)
        // child.f("hello"); // 基类的 f(String) 被彻底隐藏了！编译错误
    }
}

// ====== 8.4 组合与继承结合 ======

/** 门 — 被组合的类 */
class Door {
    void open() { System.out.println("门打开"); }
    void close() { System.out.println("门关闭"); }
    void dispose() { System.out.println("Door.dispose(): 清理门"); }
}

/** 窗户 — 被组合的类 */
class Window {
    void rollUp() { System.out.println("窗户摇起"); }
    void rollDown() { System.out.println("窗户摇下"); }
    void dispose() { System.out.println("Window.dispose(): 清理窗户"); }
}

/** 房间 — 基类 */
class Room {
    Room(String name) { System.out.println("Room 构造器: " + name); }
    void clean() { System.out.println("Room.clean(): 打扫房间"); }
    // 基类的清理方法
    void dispose() { System.out.println("Room.dispose(): 清理房间"); }
}

/**
 * Home 类 — 同时使用组合和继承
 *
 * 继承自 Room（is-a Room）
 * 组合了 Door 和 Window（has-a Door, has-a Window）
 *
 * 8.4.1 确保正确的清理：
 * 如果类中有特殊的清理需求，必须自己编写 dispose() 方法。
 * 清理顺序 与 初始化顺序 相反。
 * 初始化顺序：基类 → 组合成员（按声明顺序）→ 构造器体
 * 清理顺序：构造器体中的资源 → 组合成员（反向）→ 基类
 */
class Home extends Room {
    private Door door;
    private Window window;

    Home(String name) {
        super(name);                     // 1. 基类构造器
        door = new Door();               // 2. 组合成员初始化
        window = new Window();           // 3. 另一个组合成员
        System.out.println("Home 构造器完成");
    }

    void live() {
        door.open();
        window.rollDown();
        System.out.println("住在 " + getClass().getSimpleName() + " 中...");
    }

    /**
     * 自定义清理方法 — 清理顺序与初始化顺序相反
     * 初始化: Room → Door → Window → Home 构造器体
     * 清理:   Home 的构造器体分配的资源 → Window → Door → Room
     */
    void dispose() {
        System.out.println("Home.dispose(): 开始清理...");
        // 1. 先清理当前类可能分配的资源...

        // 2. 再清理组合成员（反向声明顺序）
        window.dispose();   // Window 后声明，先清理
        door.dispose();     // Door 先声明，后清理

        // 3. 最后调用基类的清理
        super.dispose();
        System.out.println("Home.dispose(): 清理完成");
    }
}

// ====== 8.4.2 名称隐藏 ======

/**
 * 基类 Parent
 * 定义了多个重载的 f() 方法
 */
class Parent {
    void f() { System.out.println("Parent.f()"); }
    void f(int i) { System.out.println("Parent.f(int): " + i); }
    void f(String s) { System.out.println("Parent.f(String): " + s); }
}

/**
 * 子类 Child
 *
 * 在 Java 中，子类重写基类的某个重载方法时，
 * 该基类的其他重载版本会被隐藏！
 * （这一点与 C++ 不同，后者是重载解析）
 */
class Child extends Parent {

    // 重写了基类的 f()
    @Override
    void f() { System.out.println("Child.f()"); }

    // 新增了一个重载 f(double)
    void f(double d) { System.out.println("Child.f(double): " + d); }

    // ⚠️ 注意：基类的 f(int) 和 f(String) 仍然可用
    // 但如果想调用 f(String)，这里会被隐藏
    // 因为 Java 的方法查找规则是：先在子类中找，找不到再找基类
    // 但实际上 f(int) 和 f(String) 因为签名不同，并不会被完全隐藏
    // 只是 f() 被重写了。f(int) 和 f(String) 继承自基类，仍然可用。
    // 真正被"隐藏"的是：如果你在子类中重新定义了同名方法
    // 而签名不同，那基类的同名方法就被"覆盖"了
}
/* 输出:
Room 构造器: 梦想家园
Home 构造器完成
门打开
窗户摇下
住在 Home 中...
————清理前————
Home.dispose(): 开始清理...
Window.dispose(): 清理窗户
Door.dispose(): 清理门
Room.dispose(): 清理房间
Home.dispose(): 清理完成

==================

=== 名称隐藏 ===
Child.f()
Parent.f(int): 42
*/