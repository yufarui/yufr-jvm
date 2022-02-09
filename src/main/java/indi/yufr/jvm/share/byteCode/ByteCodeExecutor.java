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

    public final void execute(JavaThread thread, InstanceKlass belongKlass, ByteCode byteCode) {
        log.info("开始执行指令:{}", byteCode.getOpcode().name());
        doExecute(thread, belongKlass, byteCode);
    }

    public void doExecute(JavaThread thread, InstanceKlass belongKlass, ByteCode byteCode) {
        log.info("暂未支持的指令");
    }

}
