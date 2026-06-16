# Learn Java — 《On Java 8》中文版·基础卷 教程项目

## 📖 项目说明

本项目基于 **《On Java 8》中文版·基础卷**（Bruce Eckel 著）的目录结构，逐章编写可运行的 Java 示例代码，作为 Java 学习教程。

- **JDK 版本:** Java 21 (Gradle toolchain)
- **构建工具:** Gradle (Kotlin DSL)
- **项目结构:** 单模块 `app/`，每章独立包

---

## 📊 进度跟踪

| 状态 | 章节 | 主题 | 包名 | 关键知识点 |
|------|------|------|------|-----------|
| ✅ 已完成 | 第1章 | 什么是对象 | `ch01_what_is_object` | 抽象、接口、隐藏、复用、继承、多态、集合、泛型、异常 |
| ✅ 已完成 | 第2章 | 安装Java和本书示例 | `ch02_install` | 编辑器、shell、Java安装、Gradle基础任务 |
| ✅ 已完成 | 第3章 | 对象无处不在 | `ch03_objects_everywhere` | 引用、堆/栈、基本类型、作用域、static、第一个Java程序 |
| ✅ 已完成 | 第4章 | 操作符 | `ch04_operators` | 赋值、算术、关系、逻辑、按位、移位、三元、类型转换 |
| ✅ 已完成 | 第5章 | 控制流 | `ch05_control_flow` | if-else、for/while、do-while、for-in、return、break/continue、switch |
| ✅ 已完成 | 第6章 | 初始化和清理 | `ch06_init_cleanup` | 构造器、重载、this、垃圾回收、finalize()、枚举、var |
| ✅ 已完成 | 第7章 | 实现隐藏 | `ch07_hiding_implementation` | package、public/private/protected、模块系统 |
| ✅ 已完成 | 第8章 | 复用 | `ch08_reuse` | 组合、继承、委托、final、代理、初始化顺序 |
| ✅ 已完成 | 第9章 | 多态 | `ch09_polymorphism` | 向上转型、绑定、协变返回、构造器内多态、向下转型 |
| 🟡 **当前** | 第10章 | 接口 | `ch10_interfaces` | 抽象类、接口定义、default方法、密封类、工厂模式 |
| ⬜ 待开始 | 第11章 | 内部类 | `ch11_inner_classes` | 匿名内部类、嵌套类、闭包、回调、控制框架 |
| ⬜ 待开始 | 第12章 | 集合 | `ch12_collections` | List、Set、Map、Queue、Iterator、record类型 |
| ⬜ 待开始 | 第13章 | 函数式编程 | `ch13_functional` | lambda、方法引用、函数式接口、闭包、柯里化 |
| ⬜ 待开始 | 第14章 | 流 | `ch14_streams` | 流创建、中间操作、Optional、收集器、并行流 |
| ⬜ 待开始 | 第15章 | 异常 | `ch15_exceptions` | try/catch/finally、自定义异常、异常链、try-with-resources |
| ⬜ 待开始 | 第16章 | 代码校验 | `ch16_code_validation` | 单元测试、TDD、日志、调试、JMH、重构 |
| ⬜ 待开始 | 第17章 | 文件 | `ch17_files` | Path、目录、文件系统、文件监听、文件查找读写 |
| ⬜ 待开始 | 第18章 | 字符串 | `ch18_strings` | StringBuilder、格式化、Formatter、文本块、正则表达式、Scanner |
| ⬜ 待开始 | 第19章 | 反射 | `ch19_reflection` | Class对象、instanceof、动态代理、Optional、类型信息 |
| ⬜ 待开始 | 第20章 | 泛型 | `ch20_generics` | 简单泛型、泛型方法、类型擦除、通配符、边界、自限定 |
| ⬜ 待开始 | 第21章 | 数组 | `ch21_arrays` | 一等对象、多维数组、Arrays工具、并行排序、二分查找 |

### 状态图标说明

| 图标 | 含义 |
|------|------|
| 🟡 **当前** | 正在编写的章节 |
| ✅ 已完成 | 章节代码已全部提交 |
| ⬜ 待开始 | 尚未开始编写 |

---

## 📁 代码规范

### 包命名

```
ch{两位数字}_{英文关键词}
```

例如：`ch01_what_is_object`、`ch06_init_cleanup`

### 类命名

| 分类 | 命名模式 | 说明 |
|------|---------|------|
| **演示代码** | `Demo{2位章号}{2位节号}_{关键词}.java` | 例如第8章第1节 → `Demo0801_CompositionSyntax.java` |
| **练习代码** | `Exercise{编号}.java` | 章节练习参考实现 |
| **辅助类** | 按功能英文命名 | 被 Demo 引用的工具类 |

### 注释要求

- 每个类文件头部写 Javadoc 注释，说明演示的小节和知识点
- 关键代码行上方加中文注释解释原理
- 输出结果标注 `// 输出: xxx`

```java
package ch06_init_cleanup;

/**
 * 构造器 (6.1 节)
 *
 * 构造器保证每个对象在使用前都被正确初始化。
 * - 构造器名称与类名相同
 * - 没有返回值
 * - 可以有多个重载版本
 */
public class Demo0601_Constructor {

    public Demo0601_Constructor() {
        System.out.println("构造器被调用");
    }

    public static void main(String[] args) {
        new Demo0601_Constructor();
    }
}
```

### 源文件路径

```
app/src/main/java/ch{XX}_{topic}/Demo{XX}{YY}_{Name}.java
app/src/test/java/ch{XX}_{topic}/Demo{XX}{YY}_{Name}Test.java    // 需要测试时
```

---

## 🤖 对 Claude Code 的指令

### 编写某章示例代码

请直接说 **"帮我写第X章的示例代码"**，我会：
1. 读取 `CLAUDE.md` 中的进度表确认当前章节
2. 根据 `目录.txt` 中的该章节目录创建对应的 Demo 类和练习类
3. 文件写入到 `app/src/main/java/ch{XX}_{topic}/` 下
4. 更新 CLAUDE.md 的进度状态

### 更新进度

完成一章后，我会将进度表中的状态从 **"当前"** 改为 **"✅ 已完成"**，并将下一章设为 **"🟡 当前"**

### 编码风格

- Java 21 语法（sealed class、record、pattern matching 等可合理使用）
- 类名、方法名、变量名使用英文
- 注释使用中文
- 每个 Demo 类自带 `main` 方法，可直接 `./gradlew run` 或右键运行
