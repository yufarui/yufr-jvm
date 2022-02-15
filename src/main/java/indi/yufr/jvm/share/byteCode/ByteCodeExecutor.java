package indi.yufr.jvm.share.byteCode;

import indi.yufr.jvm.share.tools.Stream;
import indi.yufr.jvm.share.vm.classFile.ByteIndex;
import indi.yufr.jvm.share.vm.oops.InstanceKlass;
import indi.yufr.jvm.share.vm.runtime.JavaThread;
import lombok.extern.slf4j.Slf4j;

/**
 * 字节码相关操作
 * <p>
 * 涉及栈帧内操作
 *
 * @date: 2022/1/20 9:36
 * @author: farui.yu
 */
@Slf4j
public abstract class ByteCodeExecutor {

    public boolean ifControlFlow() {
        return false;
    }

    public abstract boolean canSupport(Opcode opcode);

    public final ByteCode doParse(byte[] content, ByteIndex index, Opcode opcode) {

        if (opcode.getOpNum() == -1) {
            throw new RuntimeException("还未能解析的opcode: " + opcode.name());
        }

        // -1 是因为 之前正好根据 u1 解析过opcode
        return ByteCode.builder()
                .cursor(index.getIndex() - 1)
                .opcode(opcode)
                .content(Stream.readBytes(content, index, opcode.getOpNum()))
                .build();
    }

    public final void execute(JavaThread thread, InstanceKlass belongKlass, ByteCode byteCode, ByteIndex byteIndex) {
        log.info("开始执行指令:{},指令所在游标:{}", byteCode.getOpcode().name(), byteCode.getCursor());

        if (ifControlFlow()) {
            executeInControlFlow(thread, belongKlass, byteCode, byteIndex);
            log.info("指令进行跳转,跳转到游标{}", byteIndex.getIndex());
        } else {
            doExecute(thread, belongKlass, byteCode);
            byteIndex.plus(1 + byteCode.getContent().length);
        }

    }

    public void doExecute(JavaThread thread, InstanceKlass belongKlass, ByteCode byteCode) {
        log.info("暂未支持的指令");
    }

    public void executeInControlFlow(JavaThread thread, InstanceKlass belongKlass, ByteCode byteCode, ByteIndex byteIndex) {
        log.info("暂未支持的指令");
    }

}
