package indi.yufr.jvm.yuShare.byteCode;

/**
 * @date: 2022/1/20 15:25
 * @author: farui.yu
 */
public class ByteCodeGetStaticExecutor extends ByteCodeExecutor {

    @Override
    public boolean canSupport(Opcode opcode) {

        if (opcode.equals(Opcode.GETSTATIC)) {
            return true;
        }

        return false;
    }
}
