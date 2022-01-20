package indi.yufr.jvm.yuShare.byteCode;

import indi.yufr.jvm.yuShare.vm.classFile.ByteIndex;

/**
 * @date: 2022/1/20 10:25
 * @author: farui.yu
 */
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
}
