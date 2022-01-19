package indi.yufr.jvm.yuShare.vm.oops.attribute;

import indi.yufr.jvm.yuShare.vm.oops.AttributeAble;
import lombok.Data;

@Data
public class CodeAttributeInfo extends AttributeInfo implements AttributeAble {

    private short maxStack;
    private short maxLocals;

    private int codeLength;
    private byte[] code;

    // 暂时不处理异常
    private short exceptionTableLength;

    // 如局部变量表、操作数栈
    private short attributesCount;
    private AttributeInfo[] attributes;

    @Override
    public void initAttributes() {
        this.attributes = new AttributeInfo[attributesCount];
    }

}
