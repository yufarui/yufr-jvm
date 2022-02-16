package indi.yufr.jvm.example;

/**
 * 此类 辅助查看 类的结构
 */
public class MainClass {

    public static final String HELLO_WORLD = "Hello World";

    public static void main(String[] args) {
        step1();

        step2();

        int num1 = 10;
        int num2 = 12;

        step3(num1, num2);

        step4(num2);

        step5();

    }

    private static void step1() {
        System.out.println(HELLO_WORLD);
    }

    private static void step2() {
        long l = 0x1111_f000_1111_f000L;
        int i = (int) l;

        System.out.println(i);
    }

    private static void step3(int num1, int num2) {
        System.out.println(num1 + num2);
        System.out.println(num2 - num1);
        System.out.println(num2 * num1);
        System.out.println(num2 / num1);

        System.out.println(num1++);
        System.out.println(--num1);

        System.out.println(num1 += 0x7f00_0000);
    }

    private static void step4(int num2) {
        if (num2 == 10 || num2 == 12) {
            System.out.println("当前值为" + num2);
        }
    }

    private static void step5() {
        byte[] arr = {1, 2};

        for (byte b : arr) {
            System.out.println(b);
        }
    }
}
