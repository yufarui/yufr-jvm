package indi.yufr.jvm.yuShare.vm.oops;

import lombok.Data;

/**
 * @date: 2022/1/17 15:52
 * @author: farui.yu
 */
@Data
public class ConstantPoolItem {

    private ConstantTag tag;
    private int length;
    private Object content;

    // todo 转换
    public String getAsString() {
        return (String)content;
    }

    public short getAsSingleIndex() {

        switch (tag) {
            case JVM_CONSTANT_Class:
            case JVM_CONSTANT_String:
                return (short) content;
        }

        throw new RuntimeException("不支持的转换项");
    }

    public int getAsDoubleIndex() {
        switch (tag) {
            case JVM_CONSTANT_Fieldref:
            case JVM_CONSTANT_Methodref:
            case JVM_CONSTANT_InterfaceMethodref:
            case JVM_CONSTANT_NameAndType:
                return (int) content;
        }
        throw new RuntimeException("不支持的转换项");
    }

}
