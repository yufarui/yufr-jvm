package indi.yufr.jvm.share.byteCode;

import indi.yufr.jvm.share.tools.Stream;
import indi.yufr.jvm.share.vm.oops.InstanceKlass;
import indi.yufr.jvm.share.vm.runtime.JavaThread;
import indi.yufr.jvm.share.vm.runtime.JavaVFrame;
import indi.yufr.jvm.share.vm.runtime.StackValue;
import indi.yufr.jvm.share.vm.utilities.BasicType;

/**
 * @date: 2022/2/14 16:51
 * @author: farui.yu
 */
public class ByteCodeSiPushExecutor extends ByteCodeExecutor {

    @Override
    public boolean canSupport(Opcode opcode) {
        if (opcode.equals(Opcode.SIPUSH)) {
            return true;
        }

        return false;
    }

    @Override
    public void doExecute(JavaThread thread, InstanceKlass belongKlass, ByteCode byteCode) {
        JavaVFrame frame = (JavaVFrame) thread.getStack().peek();

        byte[] content = byteCode.getContent();
        int val = (short) Stream.readInOrder(content);

        frame.push(new StackValue(BasicType.T_INT, val));
    }
}
