package indi.yufr.jvm.example;

/**
 * 此类 辅助查看 类的结构
 */
public class MainClass {

    public static final String HELLO_WORLD = "Hello World";

    public static void main(String[] args) {
        System.out.println(HELLO_WORLD);

        long l = 0x1111_f000_1111_f000L;
        int i = (int) l;

        System.out.println(i);
    }

}
