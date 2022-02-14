package indi.yufr.jvm.share.util;

import indi.yufr.jvm.share.attribute.AttributeInfoExecutor;
import indi.yufr.jvm.share.byteCode.ByteCodeExecutor;
import indi.yufr.jvm.share.constant.executor.ConstantInfoExecutorContext;
import lombok.SneakyThrows;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @date: 2022/1/19 17:47
 * @author: farui.yu
 */
public class FindAllSubClass {

    public static void main(String[] args) {
//        listExecutor(ConstantInfoExecutor.class);

        listExecutor(ByteCodeExecutor.class);

        listExecutor(AttributeInfoExecutor.class);
    }

    @SneakyThrows
    private static void listExecutor(Class<?> superClazz) {

        String name = superClazz.getPackage().getName();

        String pathName = name.replace(".", "/");
        URL resource = ConstantInfoExecutorContext.class.getResource("/" + pathName);

        List<String> result = new ArrayList<>();

        File file = new File(resource.getFile());
        parse(superClazz, name, result, file);

        String join = String.join(",\n", result);
        System.out.println("Arrays.asList(\n" + join + "\n)");

    }

    private static void parse(Class<?> superClazz, String name, List<String> result, File file) {

        if (file.isDirectory()) {
            for (File f : Objects.requireNonNull(file.listFiles())) {
                parse(superClazz, name, result, f);
            }
        } else {
            doParse(superClazz, name, result, file);
        }

    }

    @SneakyThrows
    private static void doParse(Class<?> superClazz, String name, List<String> result, File f) {
        String className = f.getName();
        String substring = className.substring(0, className.length() - ".class".length());
        String realClassName = name + "." + substring;

        if (realClassName.contains("$")) {
            return;
        }

        Class<?> clazz = null;
        try {
            clazz = Class.forName(realClassName);
        } catch (ClassNotFoundException e) {
            result.add("new " + substring + "()");
            return;
        }

        if (clazz.getSuperclass() == superClazz) {
            result.add("new " + substring + "()");
        }
    }
}
