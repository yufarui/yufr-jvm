package indi.yufr.jvm.share.vm.oops;

import indi.yufr.jvm.share.vm.utilities.BasicType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

public class ArrayOop {

    /**
     * 数组类型
     */
    private BasicType type;

    /**
     * 如果是引用类型数组，数组元素对应的类名
     */
    private String className;

    /**
     * 数组大小
     */
    private int size;

    private Object[] data;

    public ArrayOop(BasicType type, String className, int size) {
        this.type = type;
        this.className = className;
        this.size = size;

        this.data = new Object[size];
    }
}
