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
        String prefix = ByteCodeExecutorContext.parseByteCodePrefix(opcode, "STORE");

        // 处理 IA FA ... AA命令
        if (prefix.length() == 2) {
            StackValue stackValue = frame.pop();
            int index = (int) frame.pop().getData();
            ArrayOop arrayOop = (ArrayOop) frame.pop().getData();

            if (index > arrayOop.getSize() - 1) {
                throw new Error("数组访问越界");
            }
            arrayOop.getData()[index] = stackValue.getData();
            return;
        }

        // 处理剩余Store系命令
        int suffix = ByteCodeExecutorContext.parseByteCodeLastNum(byteCode.getOpcode());
        byte[] content = byteCode.getContent();

        StackValue pop = frame.pop();
        if (suffix == -1) {
            frame.add(content[0], pop);
        } else {
            frame.add(suffix, pop);
        }

    }

}
