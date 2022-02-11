package indi.yufr.jvm.share.byteCode;

import indi.yufr.jvm.share.vm.oops.InstanceKlass;
import indi.yufr.jvm.share.vm.runtime.JavaThread;
import indi.yufr.jvm.share.vm.runtime.JavaVFrame;
import indi.yufr.jvm.share.vm.runtime.StackValue;
import lombok.extern.slf4j.Slf4j;

/**
 * @date: 2022/2/11 17:39
 * @author: farui.yu
 * <p>
 * 统一处理 load系 命令
 */
@Slf4j
public class ByteCodeLoadExecutor extends ByteCodeExecutor {

    @Override
    public boolean canSupport(Opcode opcode) {
        String name = opcode.name();

        if (name.contains("LOAD")) {
            return true;
        }

        return false;
    }

    @Override
    public void doExecute(JavaThread thread, InstanceKlass belongKlass, ByteCode byteCode) {
        JavaVFrame frame = (JavaVFrame) thread.getStack().peek();

        Opcode opcode = byteCode.getOpcode();
        String name = opcode.name();
        int index = name.indexOf("LOAD");
        String prefix = name.substring(0, index);
        int suffix = ByteCodeExecutorContext.parseByteCodeLastNum(byteCode.getOpcode());

        StackValue local;
        if (suffix == -1) {
            byte[] content = byteCode.getContent();
            local = frame.get(content[0]);
        } else {
            local = frame.get(suffix);
        }

        frame.push(local);
    }
}
