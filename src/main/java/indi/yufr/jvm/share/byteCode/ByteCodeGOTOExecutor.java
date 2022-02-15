package indi.yufr.jvm.share.byteCode;

import indi.yufr.jvm.share.vm.oops.InstanceKlass;
import indi.yufr.jvm.share.vm.runtime.JavaThread;

/**
 * @date: 2022/2/15 11:10
 * @author: farui.yu
 */
public class ByteCodeGOTOExecutor extends ByteCodeControlFlowExecutor {

    @Override
    public boolean canSupport(Opcode opcode) {
        if (opcode.equals(Opcode.GOTO)) {
            return true;
        }

        return false;
    }

    @Override
    public boolean doExecuteInControlFlow(JavaThread thread, InstanceKlass belongKlass, ByteCode byteCode) {
        return true;
    }
}
