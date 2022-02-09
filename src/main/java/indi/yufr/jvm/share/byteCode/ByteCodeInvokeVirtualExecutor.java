package indi.yufr.jvm.share.byteCode;

import indi.yufr.jvm.share.constant.content.ConstantContent;
import indi.yufr.jvm.share.constant.content.MetaRefInfo;
import indi.yufr.jvm.share.tools.DataTranslate;
import indi.yufr.jvm.share.vm.oops.ConstantPoolContext;
import indi.yufr.jvm.share.vm.oops.InstanceKlass;
import indi.yufr.jvm.share.vm.runtime.JavaThread;
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

    @Override
    public void doExecute(JavaThread thread, InstanceKlass belongKlass, ByteCode byteCode) {
        byte[] content = byteCode.getContent();

        int methodRefIndex = DataTranslate.byteToUnsignedShort(content);

        ConstantContent constantContent = ConstantPoolContext.getConstantContent(belongKlass, methodRefIndex);
        MetaRefInfo metaRefInfo = (MetaRefInfo) constantContent;

        String className = metaRefInfo.getClassName(belongKlass);
        String methodName = metaRefInfo.getName(belongKlass);
        String descriptionName = metaRefInfo.getDescriptionName(belongKlass);

        if (className.startsWith("java")) {

        }

    }
}
