package indi.yufr.jvm.yuShare.util;

import indi.yufr.jvm.yuShare.constant.executor.ConstantInfoExecutor;
import indi.yufr.jvm.yuShare.constant.executor.ConstantInfoExecutorContext;
import lombok.SneakyThrows;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @date: 2022/1/19 17:47
 * @author: farui.yu
 */
public class FindAllSubClass {

    public static void main(String[] args) {
        listExecutor(ConstantInfoExecutor.class);
    }

    @SneakyThrows
    private static void listExecutor(Class<?> superClazz) {

        String name = superClazz.getPackage().getName();

        String pathName = name.replace(".", "/");
        URL resource = ConstantInfoExecutorContext.class.getResource("/" + pathName);

        File file = new File(resource.getFile());
        File[] files = file.listFiles();

        List<String> result = new ArrayList<>();
        for (File f : files) {
            String className = f.getName();
            String substring = className.substring(0, className.length() - ".class".length());
            String realClassName = name + "." + substring;

            Class<?> clazz = Class.forName(realClassName);

            if (clazz.getSuperclass() == superClazz) {
                result.add("new " + substring + "()");
            }
        }
        String join = String.join(",\n", result);
        System.out.println("Arrays.asList(" + join + ")");

    }
}
