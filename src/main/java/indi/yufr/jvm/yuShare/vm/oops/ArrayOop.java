package indi.yufr.jvm.yuShare.vm.oops;

import lombok.Data;

@Data
public class ArrayOop {

    /**
     * 数组类型
     */
    private int type;

    /**
     * 如果是引用类型数组，数组元素对应的类名
     */
    private String className;

    /**
     * 数组大小
     */
    private int size;

    private Object[] data;
}
