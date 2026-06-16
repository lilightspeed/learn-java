# Learn Java — 《On Java 8》中文版·基础卷 教程项目

基于 **《On Java 8》中文版·基础卷**（Bruce Eckel 著）目录结构，逐章编写可运行的 Java 示例代码。适合 Java 初学者系统学习，也适合有经验者快速查阅知识点示例。

## 环境要求

| 工具 | 版本 |
|------|------|
| JDK | **Java 21**（由 Gradle toolchain 自动管理） |
| Gradle | 9.2+（如果本地没有 Gradle，可使用 `./gradlew` wrapper） |

## 快速开始

```bash
# 编译所有代码
./gradlew compileJava

# 运行测试
./gradlew test

# 运行某个具体 Demo 类（在 IDE 中直接点击 main 方法的 ▶ 按钮更便捷）
```

> 每个 Demo 类都自带 `main` 方法，可直接在 IDE 中右键运行。

## 项目结构

```
learn-java/
├── CLAUDE.md              # 项目说明 + Claude Code 指令（进度跟踪、代码规范）
├── README.md              # 本文件
├── settings.gradle.kts
├── gradle/
│   └── libs.versions.toml
├── app/
│   └── src/
│       ├── main/
│       │   └── java/
│       │       ├── ch08_reuse/          # 第8章 复用
│       │       │   └── ...
│       │       ├── ch09_polymorphism/   # 第9章 多态
│       │       │   └── ...
│       │       └── ch10_interfaces/     # 第10章 接口
│       │           └── ...
│       └── test/
│           └── java/
│               └── learn/java/
```

## 学习进度

| 章节 | 主题 | 包名 | 进度 |
|------|------|------|------|
| 第1章 | 什么是对象 | — | ✅ |
| 第2章 | 安装Java和本书示例 | — | ✅ |
| 第3章 | 对象无处不在 | — | ✅ |
| 第4章 | 操作符 | — | ✅ |
| 第5章 | 控制流 | — | ✅ |
| 第6章 | 初始化和清理 | — | ✅ |
| 第7章 | 实现隐藏 | — | ✅ |
| **第8章** | **复用** | `ch08_reuse` | ✅ |
| **第9章** | **多态** | `ch09_polymorphism` | ✅ |
| **第10章** | **接口** | `ch10_interfaces` | ✅ |
| **第11章** | **内部类** | — | 🟡 **当前** |
| 第12章 | 集合 | — | ⬜ |
| 第13章 | 函数式编程 | — | ⬜ |
| 第14章 | 流 | — | ⬜ |
| 第15章 | 异常 | — | ⬜ |
| 第16章 | 代码校验 | — | ⬜ |
| 第17章 | 文件 | — | ⬜ |
| 第18章 | 字符串 | — | ⬜ |
| 第19章 | 反射 | — | ⬜ |
| 第20章 | 泛型 | — | ⬜ |
| 第21章 | 数组 | — | ⬜ |

## 编码规范

### 包命名

```
ch{两位数字}_{英文关键词}
```

例如：`ch08_reuse` 表示第8章「复用」

### 类命名

| 分类 | 命名模式 | 示例 |
|------|---------|------|
| 演示代码 | `Demo{2位章号}{2位节号}_{关键词}.java` | `Demo0801_CompositionSyntax.java` |
| 练习代码 | `Exercise{编号}.java` | `Exercise01.java` |
| 辅助类 | 按功能英文命名 | `Engine.java`、`Guitar.java` |

### 注释

- 每个类头部写 Javadoc，说明对应的小节和知识点
- 关键代码行上方加中文注释
- 输出结果标注 `// 输出: xxx`

## IDE 推荐

- **VS Code** + Extension Pack for Java
- **IntelliJ IDEA** Community/Ultimate 版（推荐）

导入项目后，直接打开 `app/src/main/java/ch08_reuse/` 下的任一个 Demo 文件，点击 `main` 方法旁的 ▶ 即可运行。

## 参考资源

- 《On Java 8》中文版·基础卷 — Bruce Eckel 著
- [Java 21 官方文档](https://docs.oracle.com/en/java/javase/21/)
- [Gradle 用户手册](https://docs.gradle.org/current/userguide/)
