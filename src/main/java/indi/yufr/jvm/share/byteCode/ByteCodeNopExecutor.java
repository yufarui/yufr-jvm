package indi.yufr.jvm.share.byteCode;

import indi.yufr.jvm.share.vm.oops.InstanceKlass;
import indi.yufr.jvm.share.vm.runtime.JavaThread;
import lombok.extern.slf4j.Slf4j;

/**
 * @date: 2022/2/15 15:02
 * @author: farui.yu
 */
@Slf4j
public class ByteCodeNopExecutor extends ByteCodeExecutor {
    @Override
    public boolean canSupport(Opcode opcode) {
        if (opcode.equals(Opcode.NOP)) {
            return true;
        }

        return false;
    }

    @Override
    public void doExecute(JavaThread thread, InstanceKlass belongKlass, ByteCode byteCode) {
        log.info("nop命令执行");
    }
}
