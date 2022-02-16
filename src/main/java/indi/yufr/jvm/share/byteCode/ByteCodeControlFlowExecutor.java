package indi.yufr.jvm.share.byteCode;

import indi.yufr.jvm.share.tools.DataTranslate;
import indi.yufr.jvm.share.tools.Stream;
import indi.yufr.jvm.share.vm.classFile.ByteIndex;
import indi.yufr.jvm.share.vm.oops.InstanceKlass;
import indi.yufr.jvm.share.vm.runtime.JavaThread;
import lombok.extern.slf4j.Slf4j;

/**
 * @date: 2022/2/15 11:17
 * @author: farui.yu
 */
@Slf4j
public abstract class ByteCodeControlFlowExecutor extends ByteCodeExecutor {

    @Override
    public final boolean ifControlFlow() {
        return true;
    }

    public final void executeInControlFlow(JavaThread thread, InstanceKlass belongKlass, ByteCode byteCode, ByteIndex byteIndex) {

        boolean result =  doExecuteInControlFlow(thread, belongKlass, byteCode);

        if (result) {
            byte[] content = byteCode.getContent();
            int cursor = DataTranslate.byteToUnsignedShort(content);
            byteIndex.plus(cursor);
        } else {
            // 没有执行挑战,按顺序执行下一个指令
            byteIndex.plus(1 + byteCode.getOpcode().getOpNum());
        }
    }

    public abstract boolean doExecuteInControlFlow(JavaThread thread, InstanceKlass belongKlass, ByteCode byteCode);

}
