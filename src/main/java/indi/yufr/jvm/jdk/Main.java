package indi.yufr.jvm.jdk;


import indi.yufr.jvm.share.vm.classFile.BootClassLoader;
import indi.yufr.jvm.share.vm.oops.InstanceKlass;
import indi.yufr.jvm.share.vm.oops.MethodInfo;
import indi.yufr.jvm.share.vm.prims.JavaNativeInterface;
import indi.yufr.jvm.share.vm.runtime.JavaThread;
import indi.yufr.jvm.share.vm.runtime.Threads;

public class Main {

    public static void main(String[] args) {
        startJVM();
    }

    public static void startJVM() {
        // 通过AppClassLoader加载main函数所在的类
        InstanceKlass mainKlass = BootClassLoader.loadMainKlass("indi.yufr.jvm.example.MainClass");

        // 找到main方法
        MethodInfo mainMethod = JavaNativeInterface.getMethodID(mainKlass, "main", "([Ljava/lang/String;)V");

        // 创建线程
        JavaThread thread = new JavaThread();

        Threads.getThreadList().add(thread);
        Threads.setCurrentThread(thread);

        // 执行main方法
        JavaNativeInterface.callMethod(mainMethod);
    }
}
