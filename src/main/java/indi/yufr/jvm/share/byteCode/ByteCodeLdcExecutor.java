package indi.yufr.jvm.share.byteCode;

import indi.yufr.jvm.share.constant.content.*;
import indi.yufr.jvm.share.vm.oops.ConstantPoolContext;
import indi.yufr.jvm.share.vm.oops.InstanceKlass;
import indi.yufr.jvm.share.vm.runtime.JavaThread;
import indi.yufr.jvm.share.vm.runtime.JavaVFrame;
import indi.yufr.jvm.share.vm.runtime.StackValue;
import indi.yufr.jvm.share.vm.utilities.BasicType;
import lombok.extern.slf4j.Slf4j;

/**
 * @date: 2022/1/20 16:10
 * @author: farui.yu
 */
@Slf4j
public class ByteCodeLdcExecutor extends ByteCodeExecutor {
    @Override
    public boolean canSupport(Opcode opcode) {

        if (opcode.equals(Opcode.LDC)) {
            return true;
        }

        return false;
    }

    @Override
    public void doExecute(JavaThread thread, InstanceKlass belongKlass, ByteCode byteCode) {
        byte[] content = byteCode.getContent();

        byte operand = content[0];

        ConstantContent constantContent = ConstantPoolContext.getConstantContent(belongKlass, operand);

        JavaVFrame frame = (JavaVFrame) thread.getStack().peek();

        if (constantContent instanceof FloatInfo) {
            float f = ((FloatInfo) constantContent).getContent();
            frame.push(new StackValue(BasicType.T_FLOAT, f));
        } else if (constantContent instanceof StringInfo) {
            Utf8Info name = ((SingleIndex) constantContent).getName(belongKlass);
            frame.push(new StackValue(BasicType.T_OBJECT, name.getContent()));
        } else {
            log.error("未知类型");
        }
    }
}
