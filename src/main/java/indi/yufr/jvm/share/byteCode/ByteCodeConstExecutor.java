package indi.yufr.jvm.share.byteCode;

import indi.yufr.jvm.share.vm.oops.InstanceKlass;
import indi.yufr.jvm.share.vm.runtime.JavaThread;
import indi.yufr.jvm.share.vm.runtime.JavaVFrame;
import indi.yufr.jvm.share.vm.runtime.StackValue;
import indi.yufr.jvm.share.vm.utilities.BasicType;

/**
 * @date: 2022/2/14 14:15
 * @author: farui.yu
 * <p>
 * const系列命令
 */
public class ByteCodeConstExecutor extends ByteCodeExecutor {

    @Override
    public boolean canSupport(Opcode opcode) {
        String name = opcode.name();

        if (name.contains("CONST")) {
            return true;
        }

        return false;
    }

    @Override
    public void doExecute(JavaThread thread, InstanceKlass belongKlass, ByteCode byteCode) {
        JavaVFrame frame = (JavaVFrame) thread.getStack().peek();

        Opcode opcode = byteCode.getOpcode();
        String prefix = ByteCodeExecutorContext.parseByteCodePrefix(opcode, "CONST");

        BasicType basicType = ByteCodeExecutorContext.cast(prefix.charAt(0));

        int suffix = ByteCodeExecutorContext.parseByteCodeLastNum(byteCode.getOpcode());

        StackValue operand;

        switch (basicType) {
            case T_INT:
                operand = new StackValue(basicType, (int) suffix);
                break;
            case T_FLOAT:
                operand = new StackValue(basicType, (float) suffix);
                break;
            case T_DOUBLE:
                operand = new StackValue(basicType, (double) suffix);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + basicType);
        }
        frame.push(operand);
    }

}
