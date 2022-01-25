package indi.yufr.jvm.share.vm.runtime;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ziya
 * @Date: 2021/3/26 14:12
 */
@Data
public class Threads {

    /**
     * 所有的Java基本线程全部存储在这个list中
     */
    private static List<Thread> threadList;

    private static Thread currentThread;

    static {
        threadList = new ArrayList<>();
    }

    public static List<Thread> getThreadList() {
        return threadList;
    }

    public static JavaThread currentThread() {
        return (JavaThread) currentThread;
    }

    public static void setCurrentThread(Thread thread) {
        currentThread = thread;
    }

}
