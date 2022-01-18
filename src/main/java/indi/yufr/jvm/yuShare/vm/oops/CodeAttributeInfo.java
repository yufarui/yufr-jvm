package indi.yufr.jvm.yuShare.vm.oops;

import lombok.Data;

@Data
public class CodeAttributeInfo extends AttributeInfo {

    private short maxStack;
    private short maxLocals;

    private int codeLength;
    private byte[] code;

    // 暂时不处理异常
    private short exceptionTableLength;

    // 如局部变量表、操作数栈
    private int attributesCount;
    private AttributeInfo[] attributes;

}
