package indi.yufr.jvm.share.byteCode.arithmethic;

import indi.yufr.jvm.share.byteCode.ByteCodeArithmeticExecutor;
import indi.yufr.jvm.share.vm.runtime.StackValue;
import indi.yufr.jvm.share.vm.utilities.BasicType;

/**
 * @date: 2022/2/14 10:00
 * @author: farui.yu
 */
public class ByteCodeDivExecutor extends ByteCodeArithmeticExecutor {

    @Override
    public String getKeyword() {
        return "DIV";
    }

    @Override
    public Object doArithmetic(BasicType basicType, StackValue value1, StackValue value2) {
        Object result = null;
        switch (basicType) {
            case T_BYTE:
                result = (byte) value1.getData() / (byte) value2.getData();
                break;
            case T_INT:
                result = (int) value1.getData() / (int) value2.getData();
                break;
            case T_LONG:
                result = (long) value1.getData() / (long) value2.getData();
                break;
            case T_DOUBLE:
                result = (double) value1.getData() / (double) value2.getData();
                break;
            case T_FLOAT:
                result = (float) value1.getData() / (float) value2.getData();
                break;
        }

        return result;
    }
}
