package indi.yufr.jvm.share.byteCode;

import indi.yufr.jvm.share.vm.oops.InstanceKlass;
import indi.yufr.jvm.share.vm.runtime.JavaThread;
import indi.yufr.jvm.share.vm.runtime.JavaVFrame;
import indi.yufr.jvm.share.vm.runtime.StackValue;

/**
 * @date: 2022/2/15 13:53
 * @author: farui.yu
 */
public class ByteCodeDupExecutor extends ByteCodeExecutor {

    @Override
    public boolean canSupport(Opcode opcode) {
        if (opcode.equals(Opcode.DUP)
                || opcode.equals(Opcode.DUP2)) {
            return true;
        }

        return false;
    }

    // 复制操作数,并再次压入操作数栈
    @Override
    public void doExecute(JavaThread thread, InstanceKlass belongKlass, ByteCode byteCode) {
        JavaVFrame frame = (JavaVFrame) thread.getStack().peek();

        StackValue peek = frame.peek();
        frame.push(peek);
    }
}
