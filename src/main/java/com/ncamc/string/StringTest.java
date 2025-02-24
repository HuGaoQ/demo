package com.ncamc.string;

import cn.hutool.core.util.StrUtil;

/**
 * javap -c StringTest(进入TestString.class的目录下，对TestString类进行反编译): 反编译测试
 * javap -verbose StringTest: 查看常量池的内容
 */
public class StringTest {

    /**
     * 颜色代码：
     *  30: 黑色
     *  31: 红色
     *  32: 绿色
     *  33: 黄色
     *  34: 蓝色
     *  35: 紫色
     *  36: 青色
     *  37: 白色
     * 注意：
     *  1.\033[0m 是用于重置颜色的代码，防止后续的输出继续使用已设置的颜色。
     *  2.并不是所有的终端都支持 ANSI 转义序列。它们通常在类 Unix 系统的终端（如 Linux、macOS）中有效。在 Windows 的命令提示符中，可能需要启用支持或者使用类似 Git Bash、PowerShell 等支持 ANSI 的终端。
     */
    public static void main(String[] args) {
        sing();
        System.out.println("\033[31m===============sing===============\033[0m");
        System.out.println();

        equalsHashCode();
        System.out.println("\033[31m===============equalsHashCode===============\033[0m");
        System.out.println();

        compareTo();
        System.out.println("\033[31m===============compareTo===============\033[0m");
        System.out.println();

        startWith();
        System.out.println("\033[31m===============startWith===============\033[0m");
        System.out.println();

        endsWith();
        System.out.println("\033[31m===============endsWith===============\033[0m");
        System.out.println();

        indexOf();
        System.out.println("\033[31m===============indexOf===============\033[0m");
        System.out.println();

        split();
        System.out.println("\033[31m===============split===============\033[0m");
        System.out.println();
    }

    /**
     * split() 方法有两个重载版本：
     *  版本 1：使用正则表达式作为分隔符: public String[] split(String regex)
     *      参数：
     *          1.regex：用于分割字符串的正则表达式。
     *      返回值：
     *          1.返回一个字符串数组，包含分割后的子字符串。
     *  版本 2：使用正则表达式作为分隔符，并限制分割次数: public String[] split(String regex, int limit)
     *      参数：
     *          1.regex：用于分割字符串的正则表达式。
     *          2.limit：控制分割的次数和结果数组的长度。
     *      返回值：
     *          1.返回一个字符串数组，包含分割后的子字符串。
     */
    private static void split() {
        String str = "apple,banana,grape";
        System.out.println("----------基本用法----------");
        String[] fruits = str.split(StrUtil.COMMA);
        for (String fruit : fruits) {
            System.out.print(fruit + StrUtil.SPACE);
        }
        System.out.println();
        System.out.println("----------使用正则表达式----------");
        // 使用正则表达式匹配一个或多个空格
        // split() 的参数是一个正则表达式，因此特殊字符（如 .、|、* 等）需要转义
        String[] fruits1 = str.split("\\s+");
        for (String fruit : fruits1) {
            System.out.print(fruit + StrUtil.SPACE);
        }
        System.out.println();
        System.out.println("----------限制分割次数----------");
        // 最多分割 2 次
        String[] fruits2 = str.split(StrUtil.COMMA, 2);
        for (String fruit : fruits2) {
            System.out.print(fruit + StrUtil.SPACE);
        }
        System.out.println();
        System.out.println("----------空字符串处理----------");
        String str1 = "apple,,banana,,grape";
        // 如果字符串以分隔符开头或结尾，split() 会返回空字符串。
        String[] fruits3 = str1.split(StrUtil.COMMA);
        for (String fruit : fruits3) {
            System.out.print(fruit + StrUtil.SPACE);
        }
        System.out.println();
        System.out.println("----------去除空字符串----------");
        String str2 = "apple,,banana,,grape";
        // 保留所有空字符串
        String[] fruits4 = str2.split(StrUtil.COMMA, -1);
        for (String fruit : fruits4) {
            System.out.println(fruit);
        }
        System.out.println();
    }

    /**
     * 版本 1：查找字符第一次出现的位置: public int indexOf(int ch)
     *  参数：
     *      1.ch：要查找的字符（以 int 形式表示，可以是 Unicode 码点）。
     *  返回值：
     *      1.返回字符在字符串中第一次出现的索引（从 0 开始）。
     *      2.如果未找到，返回 -1。
     * 版本 2：从指定位置开始查找字符: public int indexOf(int ch, int fromIndex)
     *  参数：
     *      1.ch：要查找的字符（以 int 形式表示）。
     *      2.fromIndex：从字符串的哪个索引位置开始查找。
     *  返回值：
     *      1.返回字符在字符串中第一次出现的索引（从 fromIndex 开始）。
     *      2.如果未找到，返回 -1。
     * 版本 3：查找子字符串第一次出现的位置: public int indexOf(String str)
     *  参数：
     *      1.str：要查找的子字符串。
     *  返回值：
     *      1.返回子字符串在字符串中第一次出现的索引（从 0 开始）。
     *      2.如果未找到，返回 -1。
     * 版本 4：从指定位置开始查找子字符串: public int indexOf(String str, int fromIndex)
     *  参数：
     *      1.str：要查找的子字符串。
     *      2.fromIndex：从字符串的哪个索引位置开始查找。
     *  返回值：
     *      1.返回子字符串在字符串中第一次出现的索引（从 fromIndex 开始）。
     *      2.如果未找到，返回 -1。
     */
    private static void indexOf() {
        String str = "Hello, World!";
        System.out.println("----------查找字符----------");
        // 查找字符 'o'
        int index1 = str.indexOf('o');
        // 查找字符 'z'
        int index2 = str.indexOf('z');
        System.out.printf("str.indexOf('o'): %s%n", index1);
        System.out.printf("str.indexOf('z'): %s%n", index2);
        System.out.println("----------从指定位置开始查找字符----------");
        // 从索引 5 开始查找字符 'o'
        int index3 = str.indexOf('o', 5);
        // 从索引 10 开始查找字符 'o'
        int index4 = str.indexOf('o', 10);
        System.out.printf("str.indexOf('o', 5): %s%n", index3);
        System.out.printf("str.indexOf('o', 10): %s%n", index4);
        System.out.println("----------查找子字符串----------");
        // 查找子字符串 "World"
        int index5 = str.indexOf("World");
        // 查找子字符串 "Java"
        int index6 = str.indexOf("Java");
        System.out.printf("str.indexOf(\"World\"): %s%n", index5);
        System.out.printf("str.indexOf(\"Java\"): %s%n", index6);
        System.out.println("----------从指定位置开始查找子字符串----------");
        // 从索引 5 开始查找子字符串 "o"
        int index7 = str.indexOf("o", 5);
        // 从索引 10 开始查找子字符串 "World"
        int index8 = str.indexOf("World", 10);
        System.out.printf("str.indexOf('o', 5): %s%n", index7);
        System.out.printf("str.indexOf('World', 10): %s%n", index8);
    }

    /**
     * endsWith()方法用于检查一个字符串是否以指定的后缀结尾: public boolean endsWith(String suffix)
     *  参数：
     *      1.suffix：要检查的后缀字符串。
     *  返回值：
     *      1.如果字符串以 suffix 结尾，返回 true。
     *      2.否则，返回 false
     */
    private static void endsWith() {
        String str = "Hello, World!";
        System.out.printf("str.endsWith(\"Hello\") %s%n", str.endsWith("Hello"));
        System.out.printf("str.endsWith(\"World\") %s%n", str.endsWith("World!"));
    }

    /**
     * startsWith() 方法有两个重载版本：
     * 版本 1：检查字符串是否以指定前缀开头: public boolean startsWith(String prefix)
     *   参数：
     *       1.prefix：要检查的前缀字符串。
     *   返回值：
     *       1.如果字符串以 prefix 开头，返回 true。
     *       2.否则，返回 false。
     * 版本 2：从指定位置开始检查前缀: public boolean startsWith(String prefix, int offset)
     *   参数：
     *       1.prefix：要检查的前缀字符串。
     *       2.offset：从字符串的哪个位置开始检查。
     *   返回值：
     *       1.如果从 offset 位置开始的子字符串以 prefix 开头，返回 true。
     *       2.否则，返回 false。
     */
    private static void startWith() {
        String str = "Hello, World!";
        System.out.println("----------版本01----------");
        System.out.printf("str.startsWith(\"Hello\") %s%n", str.startsWith("Hello"));
        System.out.printf("str.startsWith(\"World!\") %s%n", str.startsWith("World!"));
        System.out.println("----------版本02----------");
        System.out.printf("str.startsWith(\"Hello\") %s%n", str.startsWith("Hello", 7));
        System.out.printf("str.startsWith(\"World!\") %s%n", str.startsWith("World!", 7));
    }

    /**
     * public int compareTo(String anotherString)
     *   参数：
     *       1.anotherString 是要与当前字符串比较的另一个字符串。
     *   返回值：
     *       1.如果当前字符串小于参数字符串，返回一个负整数。
     *       2.如果当前字符串等于参数字符串，返回 0。
     *       3.如果当前字符串大于参数字符串，返回一个正整数。
     */
    private static void compareTo() {
        String a = "gdejicbegh";
        String b = "hgebcijedg";
        System.out.printf("a.compareTo() %s%n", a.compareTo(b));
        System.out.printf("g - h: %s j - j: %s h - g: %s%n", ('g' - 'h'), ('j' - 'j'), ('h' - 'g'));
        // 转换码值
        System.out.printf("g: %s j: %s h: %s%n", (int) 'g', (int) 'j', (int) 'h');
    }

    /**
     * String的equals方法
     * 1.如果两个对象equals()，则它们的hashcode一定相等。
     *   解释：
     *       1.equals() 方法用于判断两个对象是否逻辑相等。
     *       2.hashCode() 方法用于返回对象的哈希值，哈希值是一个整数，通常用于哈希表等数据结构中快速定位对象。
     *       3.根据 Java 规范，如果两个对象通过 equals() 方法比较返回 true，那么它们的 hashCode() 必须返回相同的值。
     *   原因：
     *       1.这是为了确保在哈希表（如 HashMap、HashSet）中，相等的对象能够被正确地存储和检索。
     *       2.如果两个对象 equals() 但 hashCode() 不同，哈希表可能会将它们存储在不同的位置，导致无法正确检索。
     * 2.如果两个对象不equals()，它们的hashcode可能相等。
     *   解释：
     *       1.即使两个对象不相等（equals() 返回 false），它们的 hashCode() 仍然可能相同。
     *       2.这种情况称为哈希冲突。
     *   原因：
     *       1.hashCode() 是一个整数，而对象的状态可能是无限的，因此不同的对象可能会产生相同的哈希值。
     *       2.哈希冲突是正常的，哈希表（如 HashMap）会通过链表或红黑树等方式处理冲突。
     * 3.如果两个对象的hashcode相等，则它们不一定equals。
     *   解释：
     *       1.两个对象的 hashCode() 相同，并不意味着它们一定是相等的。
     *       2.哈希冲突的存在使得不同的对象可能具有相同的哈希值。
     *   原因：
     *       1.hashCode() 是一个哈希函数，它的设计目标是尽可能均匀地分布哈希值，但无法完全避免冲突。
     * 4.如果两个对象的hashcode不相等，则它们一定不equals。
     *   解释：
     *       1.如果两个对象的 hashCode() 不同，那么它们一定不相等（equals() 返回 false）。
     *   原因：
     *       1.根据第一条规则，如果两个对象 equals()，则它们的 hashCode() 必须相等。
     *       2.反过来，如果 hashCode() 不相等，则它们不可能 equals()。
     */
    private static void equalsHashCode() {
        String a = "gdejicbegh";
        String b = "hgebcijedg";
        System.out.printf("a.address(): %s b.address(): %s%n", System.identityHashCode(a), System.identityHashCode(b));
        System.out.printf("a.hashcode(): %s b.hashcode(): %s%n", a.hashCode(), b.hashCode());
        System.out.println("a.hashcode() == b.hashcode() " + (a.hashCode() == b.hashCode()));
        System.out.println("a.equals(b) " + StrUtil.equals(a, b));
    }

    /**
     * == 用法
     */
    private static void sing() {
        String name1 = "bruis";
        String name2 = "bruis";
        String name3 = new String("bruis");
        String name4 = "a" + "b";
        String name5 = new StringBuilder().append("a").append("b").toString();
        // true
        System.out.println("name == name2 : " + (name1 == name2));
        // false
        System.out.println("name == name3 : " + (name1 == name3));

        String a1 = new String("AA") + new String("BB");
        String a2 = new String("ABAB") + new String("CDCD");
        String a3 = "ABAB" + "CDCD";

        System.out.printf("a1: %s a1.intern(): %s%n", a1, a1.intern());
        System.out.printf("a1.address: %d a1.intern().address: %d%n", System.identityHashCode(a1), System.identityHashCode(a1.intern()));
        System.out.printf("a1 == a1.intern() %s%n", a1 == a1.intern());

        System.out.println();
        System.out.printf("a2: %s a2.intern(): %s%n", a2, a2.intern());
        System.out.printf("a2.address: %d a2.intern().address: %d%n", System.identityHashCode(a2), System.identityHashCode(a2.intern()));
        System.out.printf("a2 == a2.intern() %s%n", a2 == a2.intern());

        System.out.println();
        System.out.printf("a2: %s a3: %s%n", a2, a3);
        System.out.printf("a2.address: %d a3.address: %d%n", System.identityHashCode(a2), System.identityHashCode(a3));
        System.out.printf("a2 == a3 %s%n", a2 == a3);

        System.out.println();
        System.out.printf("a3: %s a2.intern(): %s%n", a3, a2.intern());
        System.out.printf("a3.address: %d a2.intern().address: %d%n", System.identityHashCode(a3), System.identityHashCode(a2.intern()));
        System.out.printf("a3 == a2.intern() %s%n", a3 == a2.intern());

        System.out.printf("a1: %s a2: %s a3: %s%n", a1.hashCode(), a2.hashCode(), a3.hashCode());
        System.out.printf("a1.intern(): %s a2.intern(): %s a3.intern(): %s%n", a1.intern().hashCode(), a2.intern().hashCode(), a3.intern().hashCode());
    }
}
