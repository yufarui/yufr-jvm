package indi.yufr.jvm.share.byteCode;

import indi.yufr.jvm.share.vm.oops.ArrayOop;
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
        String prefix = ByteCodeExecutorContext.parseByteCodePrefix(opcode, "LOAD");

        // 处理 IA FA ... AA命令
        if (prefix.length() == 2) {

            int index = (int) frame.pop().getData();
            ArrayOop arrayOop = (ArrayOop) frame.pop().getData();

            if (index > arrayOop.getSize() - 1) {
                throw new Error("数组访问越界");
            }

            frame.push(new StackValue(arrayOop.getType(), arrayOop.getData()[index]));
            return;
        }

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
