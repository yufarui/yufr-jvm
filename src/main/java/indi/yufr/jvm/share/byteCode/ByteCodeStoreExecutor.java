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
 * 统一处理 store系 命令
 */
@Slf4j
public class ByteCodeStoreExecutor extends ByteCodeExecutor {

    @Override
    public boolean canSupport(Opcode opcode) {

        String name = opcode.name();

        if (name.contains("STORE")) {
            return true;
        }

        return false;
    }

    @Override
    public void doExecute(JavaThread thread, InstanceKlass belongKlass, ByteCode byteCode) {

        JavaVFrame frame = (JavaVFrame) thread.getStack().peek();

        Opcode opcode = byteCode.getOpcode();
        String name = opcode.name();
        int index = name.indexOf("STORE");
        String prefix = name.substring(0, index);
        int suffix = ByteCodeExecutorContext.parseByteCodeLastNum(byteCode.getOpcode());
        byte[] content = byteCode.getContent();

        // 暂时未处理A开头的数组
        StackValue pop = frame.pop();
        if (suffix == -1) {
            frame.add(content[0], pop);
        } else {
            frame.add(suffix, pop);
        }
    }

}
