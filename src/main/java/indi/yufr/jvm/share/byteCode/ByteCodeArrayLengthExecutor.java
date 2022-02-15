package indi.yufr.jvm.share.byteCode;

import indi.yufr.jvm.share.vm.oops.ArrayOop;
import indi.yufr.jvm.share.vm.oops.InstanceKlass;
import indi.yufr.jvm.share.vm.runtime.JavaThread;
import indi.yufr.jvm.share.vm.runtime.JavaVFrame;
import indi.yufr.jvm.share.vm.runtime.StackValue;
import indi.yufr.jvm.share.vm.utilities.BasicType;

/**
 * @date: 2022/2/15 13:48
 * @author: farui.yu
 */
public class ByteCodeArrayLengthExecutor extends ByteCodeExecutor {
    @Override
    public boolean canSupport(Opcode opcode) {
        if (opcode.equals(Opcode.ARRAYLENGTH)) {
            return true;
        }

        return false;
    }

    @Override
    public void doExecute(JavaThread thread, InstanceKlass belongKlass, ByteCode byteCode) {
        JavaVFrame frame = (JavaVFrame) thread.getStack().peek();

        ArrayOop data = (ArrayOop) frame.pop().getData();

        frame.push(new StackValue(BasicType.T_INT, data.getSize()));
    }
}
