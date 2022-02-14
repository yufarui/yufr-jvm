package indi.yufr.jvm.share.byteCode;

import indi.yufr.jvm.share.vm.oops.InstanceKlass;
import indi.yufr.jvm.share.vm.runtime.JavaThread;

/**
 * @date: 2022/2/14 8:48
 * @author: farui.yu
 * <p>
 * 加法
 */
public class ByteCodeMinusExecutor extends ByteCodeExecutor {

    @Override
    public boolean canSupport(Opcode opcode) {
        String name = opcode.name();

        if (name.contains("ADD")) {
            return true;
        }

        return false;
    }

    @Override
    public void doExecute(JavaThread thread, InstanceKlass belongKlass, ByteCode byteCode) {

    }
}
