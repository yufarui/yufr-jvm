package indi.yufr.jvm.yuShare.byteCode;

import lombok.extern.slf4j.Slf4j;

/**
 * @date: 2022/1/20 10:30
 * @author: farui.yu
 */
@Slf4j
public class ByteCodeInvokeSpecialExecutor extends ByteCodeExecutor {

    private short methodRefIndex;

    @Override
    public boolean canSupport(Opcode opcode) {
        if (opcode.equals(Opcode.INVOKESPECIAL)) {
            return true;
        }

        return false;
    }

}
