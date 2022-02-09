package indi.yufr.jvm.share.byteCode;

import indi.yufr.jvm.share.vm.oops.InstanceKlass;
import indi.yufr.jvm.share.vm.runtime.JavaThread;
import indi.yufr.jvm.share.vm.runtime.JavaVFrame;
import indi.yufr.jvm.share.vm.runtime.StackValue;
import indi.yufr.jvm.share.vm.runtime.VFrame;
import lombok.extern.slf4j.Slf4j;

/**
 * @date: 2022/1/20 10:25
 * @author: farui.yu
 */
@Slf4j
public class ByteCodeALoadExecutor extends ByteCodeExecutor {

    @Override
    public boolean canSupport(Opcode opcode) {

        if (opcode.equals(Opcode.ALOAD_0)
                || opcode.equals(Opcode.ALOAD_1)
                || opcode.equals(Opcode.ALOAD_2)
                || opcode.equals(Opcode.ALOAD_3)
        ) {
            return true;
        }

        return false;
    }

    @Override
    public void doExecute(JavaThread thread, InstanceKlass belongKlass, ByteCode byteCode) {

        JavaVFrame peek = (JavaVFrame)thread.getStack().peek();
        int byteCodeNum = ByteCodeExecutorContext.parseByteCodeLastNum(byteCode.getOpcode());

        // 从局部变量表取出数据
        StackValue stackValue = peek.get(byteCodeNum);

        peek.push(stackValue);
    }

}
