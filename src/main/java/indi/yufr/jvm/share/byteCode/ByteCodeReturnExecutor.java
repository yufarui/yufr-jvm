package indi.yufr.jvm.share.byteCode;

/**
 * @date: 2022/1/20 10:37
 * @author: farui.yu
 */
public class ByteCodeReturnExecutor extends ByteCodeExecutor {

    @Override
    public boolean canSupport(Opcode opcode) {
        if (opcode.equals(Opcode.RETURN)) {
            return true;
        }

        return false;
    }
}
