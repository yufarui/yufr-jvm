package indi.yufr.jvm.share.byteCode;

import indi.yufr.jvm.share.vm.oops.InstanceKlass;
import indi.yufr.jvm.share.vm.runtime.JavaThread;
import indi.yufr.jvm.share.vm.runtime.JavaVFrame;
import indi.yufr.jvm.share.vm.runtime.StackValue;
import lombok.extern.slf4j.Slf4j;

/**
 * @date: 2022/1/20 10:37
 * @author: farui.yu
 */
@Slf4j
public class ByteCodeReturnExecutor extends ByteCodeExecutor {

    @Override
    public boolean canSupport(Opcode opcode) {
        String name = opcode.name();

        if (name.contains("RETURN")) {
            return true;
        }

        return false;
    }

    @Override
    public void doExecute(JavaThread thread, InstanceKlass belongKlass, ByteCode byteCode) {

        JavaVFrame frame = (JavaVFrame) thread.getStack().pop();

        log.info("\t 剩余栈帧数量: {}", thread.getStack().size());
        if (!frame.getStack().isEmpty()) {
            StackValue value = frame.getStack().pop();
            JavaVFrame newFrame = (JavaVFrame) thread.getStack().peek();
            newFrame.push(value);
        }
    }
}
