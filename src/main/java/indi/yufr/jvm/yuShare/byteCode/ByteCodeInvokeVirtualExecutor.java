package indi.yufr.jvm.yuShare.byteCode;

import lombok.extern.slf4j.Slf4j;

/**
 * @date: 2022/1/20 16:10
 * @author: farui.yu
 */
@Slf4j
public class ByteCodeInvokeVirtualExecutor extends ByteCodeExecutor {
    @Override
    public boolean canSupport(Opcode opcode) {

        if (opcode.equals(Opcode.INVOKEVIRTUAL)) {
            return true;
        }

        return false;
    }
}