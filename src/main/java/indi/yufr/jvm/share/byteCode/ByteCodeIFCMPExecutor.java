package indi.yufr.jvm.share.byteCode;

import indi.yufr.jvm.share.vm.oops.InstanceKlass;
import indi.yufr.jvm.share.vm.runtime.JavaThread;
import indi.yufr.jvm.share.vm.runtime.JavaVFrame;
import lombok.extern.slf4j.Slf4j;

/**
 * @date: 2022/2/15 9:37
 * @author: farui.yu
 * <p>
 * 对 if_compare系命令解析
 * <p>
 * idea中快速替换正则
 * [ ]+(.*)\(\(byte\).*,
 * || opcode.equals(Opcode.$1)
 */
@Slf4j
public class ByteCodeIFCMPExecutor extends ByteCodeControlFlowExecutor {

    @Override
    public boolean canSupport(Opcode opcode) {

        if (opcode.equals(Opcode.IFEQ)
                || opcode.equals(Opcode.IFNE)
                || opcode.equals(Opcode.IFLT)
                || opcode.equals(Opcode.IFGE)
                || opcode.equals(Opcode.IFGT)
                || opcode.equals(Opcode.IFLE)
                || opcode.equals(Opcode.IF_ICMPEQ)
                || opcode.equals(Opcode.IF_ICMPNE)
                || opcode.equals(Opcode.IF_ICMPLT)
                || opcode.equals(Opcode.IF_ICMPGE)
                || opcode.equals(Opcode.IF_ICMPGT)
                || opcode.equals(Opcode.IF_ICMPLE)
                || opcode.equals(Opcode.IF_ACMPEQ)
                || opcode.equals(Opcode.IF_ACMPNE)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean doExecuteInControlFlow(JavaThread thread, InstanceKlass belongKlass, ByteCode byteCode) {

        JavaVFrame frame = (JavaVFrame) thread.getStack().peek();

        String opName = byteCode.getOpcode().name();

        Object value1;
        Object value2;
        if (opName.startsWith("IF_")) {
            value1 = frame.getStack().pop().getData();
            value2 = frame.getStack().pop().getData();
        } else {
            value1 = 0;
            value2 = frame.getStack().pop().getData();
        }

        String suffix2 = suffix2(byteCode.getOpcode());
        boolean result;

        switch (suffix2) {
            case "EQ":
                if (opName.contains("_A")) {
                    result = value2 == value1;
                } else {
                    result = (int) value2 == (int) value1;
                }
                break;
            case "NE":
                if (opName.contains("_A")) {
                    result = value2 != value1;
                } else {
                    result = (int) value2 != (int) value1;
                }
                break;
            case "LT":
                result = (int) value2 < (int) value1;
                break;
            case "LE":
                result = (int) value2 <= (int) value1;
                break;
            case "GT":
                result = (int) value2 > (int) value1;
                break;
            case "GE":
                result = (int) value2 >= (int) value1;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + suffix2);
        }

        return result;
    }

    private static String suffix2(Opcode opcode) {
        String name = opcode.name();
        return name.substring(name.length() - 2);
    }
}
