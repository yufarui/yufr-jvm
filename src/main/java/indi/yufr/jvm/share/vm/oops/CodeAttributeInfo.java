package indi.yufr.jvm.share.vm.oops;

import indi.yufr.jvm.share.vm.intepreter.BytecodeStream;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ziya
 * @Date: 2021/3/6 17:55
 */
@Data
public class CodeAttributeInfo {

    private int attrNameIndex;
    private int attrLength;

    private int maxStack;
    private int maxLocals;

    private int codeLength;
    private BytecodeStream code;

    private int exceptionTableLength;

    // 如局部变量表、操作数栈
    private int attributesCount;

    private Map<String, AttributeInfo> attributes = new HashMap<>();

    @Override
    public String toString() {
        return "CodeAttributeInfo{}";
    }
}
