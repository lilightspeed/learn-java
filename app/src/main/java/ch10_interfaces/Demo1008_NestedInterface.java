package ch10_interfaces;

/**
 * 接口嵌套 (10.8 节)
 *
 * 接口可以嵌套在类或其他接口中：
 * - 类内部嵌套接口：相当于一个"接口类"，用于对接口分类
 * - 接口内部嵌套接口：自动是 public 的
 * - 实现嵌套接口时用 外部类名.接口名 引用
 *
 * 嵌套接口的主要用途：命名空间管理，将相关的接口组织在一起。
 */
public class Demo1008_NestedInterface {

    public static void main(String[] args) {
        System.out.println("=== 接口嵌套 ===\n");

        // 通过具体类调用嵌套接口的实现
        Chef chef = new Chef();
        chef.cook();
        chef.clean();

        System.out.println();

        // 通过嵌套接口的引用调用
        Kitchen.Cook cookRef = new Chef();
        Kitchen.Clean cleanRef = new Chef();

        cookRef.cook();
        cleanRef.clean();
    }
}

/**
 * 厨房 — 外部类，内部嵌套了接口
 *
 * Kitchen 像一个"命名空间"，把相关的接口组织在一起
 */
class Kitchen {

    /**
     * 烹饪接口 — 嵌套在类中
     */
    interface Cook {
        void cook();
    }

    /**
     * 清洁接口 — 嵌套在类中
     */
    interface Clean {
        void clean();
    }
}

/**
 * 厨师 — 实现了 Kitchen 中的两个嵌套接口
 *
 * 实现类使用 Kitchen.Cook 和 Kitchen.Clean 来引用嵌套接口
 */
class Chef implements Kitchen.Cook, Kitchen.Clean {
    @Override
    public void cook() {
        System.out.println("👨‍🍳 厨师烹饪美味佳肴");
    }

    @Override
    public void clean() {
        System.out.println("🧹 厨师收拾厨房卫生");
    }
}
/* 输出:
=== 接口嵌套 ===

👨‍🍳 厨师烹饪美味佳肴
🧹 厨师收拾厨房卫生

👨‍🍳 厨师烹饪美味佳肴
🧹 厨师收拾厨房卫生
*/