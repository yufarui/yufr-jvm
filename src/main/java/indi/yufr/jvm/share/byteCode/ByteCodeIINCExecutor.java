package indi.yufr.jvm.share.byteCode;

import indi.yufr.jvm.share.vm.oops.InstanceKlass;
import indi.yufr.jvm.share.vm.runtime.JavaThread;
import indi.yufr.jvm.share.vm.runtime.JavaVFrame;
import indi.yufr.jvm.share.vm.runtime.StackValue;
import indi.yufr.jvm.share.vm.utilities.BasicType;

/**
 * @date: 2022/2/14 13:21
 * @author: farui.yu
 */
public class ByteCodeIINCExecutor extends ByteCodeExecutor {
    @Override
    public boolean canSupport(Opcode opcode) {
        if (opcode.equals(Opcode.IINC)) {
            return true;
        }

        return false;
    }

    // i++ 与 i--都是通过指令实现
    // ++i 指令执行过程
    // iinc $index by 1
    // iload $index
    @Override
    public void doExecute(JavaThread thread, InstanceKlass belongKlass, ByteCode byteCode) {

        JavaVFrame frame = (JavaVFrame) thread.getStack().peek();

        byte[] content = byteCode.getContent();

        byte index = content[0];
        byte step = content[1];

        int data = (int) frame.get(index).getData();
        data += step;

        frame.add(index, new StackValue(BasicType.T_INT, data));
    }
}
