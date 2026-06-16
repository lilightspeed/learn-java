package ch10_interfaces;

/**
 * 通过继承扩展接口 (10.5 节)
 *
 * 接口之间可以通过 extends 继承，而且支持多继承！
 * - 一个接口可以 extends 多个父接口
 * - 接口继承接口使用 extends 关键字（不是 implements）
 * - 实现该接口的类必须实现所有父接口中的方法
 *
 * 这种机制让我们可以逐步构建更具体的接口层次结构。
 */
public class Demo1005_InterfaceInheritance {

    public static void main(String[] args) {
        System.out.println("=== 通过继承扩展接口 ===\n");

        // 可以直接实例化实现类并使用
        DragonZilla dz = new DragonZilla();
        dz.menace();
        dz.destroy();
        dz.lethal();

        System.out.println("\n--- 向上转型展示 ---");
        // 一个实现类可以向上转型到任意父接口
        Monster m1 = new DragonZilla();            // 向上转型到 Monster
        DangerousMonster m2 = new DragonZilla();    // 向上转型到 DangerousMonster
        Lethal m3 = new DragonZilla();              // 向上转型到 Lethal

        m1.menace();
        // m2 可以看到 menace() 和 destroy()
        m2.menace();
        m2.destroy();
        // m3 可以看到所有方法
        m3.menace();
        m3.destroy();
        m3.lethal();
    }
}

// === 接口层次结构 ===

interface Monster {
    void menace();
}

/**
 * DangerousMonster 继承 Monster 接口
 * — 扩展了一个新方法 destroy()
 */
interface DangerousMonster extends Monster {
    void destroy();
}

/**
 * Lethal 接口 — 继承了 Monster 和 DangerousMonster
 * — 一个接口 extends 多个父接口（接口的多继承）
 */
interface Lethal extends Monster, DangerousMonster {
    void lethal();
}

// === 实现类 ===

/**
 * DragonZilla 实现了 Lethal 接口
 * Lethal → Monster + DangerousMonster → 共3个方法需要实现
 */
class DragonZilla implements Lethal {
    @Override public void menace()  { System.out.println("DragonZilla.menace() — 怪物咆哮👹"); }
    @Override public void destroy() { System.out.println("DragonZilla.destroy() — 猛烈破坏💥"); }
    @Override public void lethal()  { System.out.println("DragonZilla.lethal() — 致命一击⚡"); }
}
/* 输出:
=== 通过继承扩展接口 ===

DragonZilla.menace() — 怪物咆哮👹
DragonZilla.destroy() — 猛烈破坏💥
DragonZilla.lethal() — 致命一击⚡

--- 向上转型展示 ---
DragonZilla.menace() — 怪物咆哮👹
DragonZilla.menace() — 怪物咆哮👹
DragonZilla.destroy() — 猛烈破坏💥
DragonZilla.menace() — 怪物咆哮👹
DragonZilla.destroy() — 猛烈破坏💥
DragonZilla.lethal() — 致命一击⚡
*/