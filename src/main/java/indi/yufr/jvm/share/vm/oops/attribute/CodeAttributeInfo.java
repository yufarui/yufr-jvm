package indi.yufr.jvm.share.vm.oops.attribute;

import indi.yufr.jvm.share.byteCode.ByteCode;
import indi.yufr.jvm.share.vm.oops.AttributeAble;
import indi.yufr.jvm.share.vm.oops.ExceptionTableElement;
import lombok.Data;

import java.util.List;

@Data
public class CodeAttributeInfo extends AttributeInfo implements AttributeAble {

    private short maxStack;
    private short maxLocals;

    private int codeLength;

    // 由于不确定个数,故使用list代替数组
    private List<ByteCode> codes;

    // 暂时不处理异常
    private short exceptionTableLength;

    private ExceptionTableElement[] exceptions;

    // 如局部变量表、操作数栈
    private short attributesCount;
    private AttributeInfo[] attributes;

    @Override
    public void initAttributes() {
        this.attributes = new AttributeInfo[attributesCount];
    }

}
