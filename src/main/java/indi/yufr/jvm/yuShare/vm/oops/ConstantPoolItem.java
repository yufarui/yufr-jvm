package indi.yufr.jvm.yuShare.vm.oops;

import indi.yufr.jvm.yuShare.vm.utilities.ConstantTag;
import lombok.Data;

/**
 * @date: 2022/1/17 15:52
 * @author: farui.yu
 */
@Data
public class ConstantPoolItem {

    protected ConstantTag tag;
    // 忽略了length属性
    private Object content;

    public ConstantPoolItem() {
    }

    public ConstantPoolItem(ConstantTag tag) {
        this.tag = tag;
    }

    public String getAsString() {
        switch (tag) {
            case JVM_CONSTANT_Utf8:
                return (String) content;
        }
        throw new RuntimeException("不支持的转换项");
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
