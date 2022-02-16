package indi.yufr.jvm.example;

/**
 * 此类 辅助查看 类的结构
 */
public class MainClass {

    public static final String HELLO_WORLD = "Hello World";

    public static void main(String[] args) {
//        System.out.println(HELLO_WORLD);
//
//        long l = 0x1111_f000_1111_f000L;
//        int i = (int) l;
//
//        System.out.println(i);
//
//        int num1 = 10;
//        int num2 = 12;
//
//        System.out.println(num1 + num2);
//        System.out.println(num2 - num1);
//        System.out.println(num2 * num1);
//        System.out.println(num2 / num1);
//
//        System.out.println(num1++);
//        System.out.println(--num1);
//
//        System.out.println(num1 += 0x7f00_0000);
//
//        if (num2 == 10 || num2 == 12) {
//            System.out.println(1);
//        }
//
//        byte[] arr = {1, 2};
//
//        for (byte b : arr) {
//            System.out.println(b);
//        }

        new MainClass().show();
    }

    public void show() {
        System.out.println("show");
    }

}
