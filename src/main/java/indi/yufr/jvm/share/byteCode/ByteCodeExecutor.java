package indi.yufr.jvm.share.byteCode;

import indi.yufr.jvm.share.tools.Stream;
import indi.yufr.jvm.share.vm.classFile.ByteIndex;

/**
 * 字节码相关操作
 * <p>
 * 涉及栈帧内操作
 *
 * @date: 2022/1/20 9:36
 * @author: farui.yu
 */
public abstract class ByteCodeExecutor {

    public abstract boolean canSupport(Opcode opcode);

    public ByteCode doParse(byte[] content, ByteIndex index, Opcode opcode) {

        if (opcode.getOpNum() == -1) {
            throw new RuntimeException("还未能解析的opcode: " + opcode.name());
        }

        return ByteCode.builder()
                .opcode(opcode)
                .content(Stream.readBytes(content, index, opcode.getOpNum()))
                .build();
    }

    // todo
    // public abstract void doExecute(ByteCodeStream codeStream);

}
