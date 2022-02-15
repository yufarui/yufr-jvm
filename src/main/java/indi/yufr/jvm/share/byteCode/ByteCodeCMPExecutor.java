package indi.yufr.jvm.share.byteCode;

import indi.yufr.jvm.share.vm.oops.InstanceKlass;
import indi.yufr.jvm.share.vm.runtime.JavaThread;
import indi.yufr.jvm.share.vm.runtime.JavaVFrame;
import indi.yufr.jvm.share.vm.runtime.StackValue;
import indi.yufr.jvm.share.vm.utilities.BasicType;

/**
 * @date: 2022/2/15 9:14
 * @author: farui.yu
 * <p>
 * CMP 系命令
 */
public class ByteCodeCMPExecutor extends ByteCodeExecutor {

    @Override
    public boolean canSupport(Opcode opcode) {

        if (opcode.equals(Opcode.LCMP)
                || opcode.equals(Opcode.FCMPL)
                || opcode.equals(Opcode.FCMPG)
                || opcode.equals(Opcode.DCMPL)
                || opcode.equals(Opcode.DCMPG)) {
            return true;
        }

        return false;
    }

    @Override
    public void doExecute(JavaThread thread, InstanceKlass belongKlass, ByteCode byteCode) {
        JavaVFrame frame = (JavaVFrame) thread.getStack().peek();

        Opcode opcode = byteCode.getOpcode();
        String prefix = ByteCodeExecutorContext.parseByteCodePrefix(opcode, "CMP");
        String suffix = ByteCodeExecutorContext.parseByteCodeSuffix(opcode, "CMP");

        BasicType basicType = ByteCodeExecutorContext.cast(prefix.charAt(0));

        if (suffix.equals("G")) {
            throw new RuntimeException("不支持对NAN操作和比较");
        }

        int result = compareResult(frame, basicType);

        frame.push(new StackValue(BasicType.T_INT, result));
    }

    private int compareResult(JavaVFrame frame, BasicType basicType) {
        int result = 0;
        switch (basicType) {
            case T_LONG:
                long operand1 = (long) frame.pop().getData();
                long operand2 = (long) frame.pop().getData();
                if (operand1 > operand2) {
                    result = 1;
                } else if (operand1 == operand2) {
                    result = 0;
                } else {
                    result = -1;
                }
                break;
            case T_FLOAT:
                float f1 = (float) frame.pop().getData();
                float f2 = (float) frame.pop().getData();
                if (f1 > f2) {
                    result = 1;
                } else if (f1 == f2) {
                    result = 0;
                } else {
                    result = -1;
                }
                break;
            case T_DOUBLE:
                double d1 = (double) frame.pop().getData();
                double d2 = (double) frame.pop().getData();
                if (d1 > d2) {
                    result = 1;
                } else if (d1 == d2) {
                    result = 0;
                } else {
                    result = -1;
                }
                break;
        }
        return result;
    }


}
