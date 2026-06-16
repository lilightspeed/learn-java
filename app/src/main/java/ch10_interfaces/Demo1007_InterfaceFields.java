package ch10_interfaces;

/**
 * 接口中的字段 (10.7 节)
 *
 * 接口中的字段有以下特征：
 * - 默认是 public static final（常量）
 * - 必须在定义时初始化（或使用静态初始化块）
 * - 可以通过"接口名.字段名"直接访问
 *
 * 接口常量的使用场景：
 * - 定义一组相关常量（类似枚举，但比枚举更轻量）
 * - 作为标记值、配置参数
 * - Java 早期版本的"常量接口"反模式（不推荐）
 *
 * 📌 注意：现代 Java 推荐用 enum 替代接口常量，
 * 用专门的常量类而不是"常量接口"。
 */
public class Demo1007_InterfaceFields {

    public static void main(String[] args) {
        System.out.println("=== 接口中的字段 ===\n");

        // 接口字段通过"接口名.字段名"访问
        System.out.println("KARMA_DAYS = " + Days.KARMA_DAYS + " 天");
        System.out.println("CALM_DAYS = " + Days.CALM_DAYS + " 天");

        System.out.println("\n--- 使用接口常量控制逻辑 ---");
        checkTaskPriority("紧急修复线上bug", Days.KARMA_DAYS);
        checkTaskPriority("整理代码注释", Days.CALM_DAYS);
    }

    static void checkTaskPriority(String task, int days) {
        System.out.printf("任务: %-20s 计划: %d 天 -> %s%n",
            task, days, days <= 1 ? "🔴 高优先级" : "🟢 常规任务");
    }
}

/**
 * 接口常量：定义项目周期相关的常量
 *
 * 这是接口的"常量"用法，但要注意：
 * - 字段自动是 public static final
 * - 初始化后不可修改
 */
interface Days {
    // 接口中的字段：默认是 public static final
    int KARMA_DAYS = 1;     // 紧急事件处理天数
    int CALM_DAYS = 3;      // 常规事件处理天数
    int SPRINT_DAYS = 14;   // 一个迭代的天数
    int DAILY_STANDUP_MINUTES = 15;  // 每日站会分钟数
}
/* 输出:
=== 接口中的字段 ===

KARMA_DAYS = 1 天
CALM_DAYS = 3 天

--- 使用接口常量控制逻辑 ---
任务: 紧急修复线上bug       计划: 1 天 -> 🔴 高优先级
任务: 整理代码注释          计划: 3 天 -> 🟢 常规任务
*/