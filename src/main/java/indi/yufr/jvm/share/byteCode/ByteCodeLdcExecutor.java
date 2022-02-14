package indi.yufr.jvm.share.byteCode;

import indi.yufr.jvm.share.constant.content.*;
import indi.yufr.jvm.share.tools.DataTranslate;
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

        if (opcode.equals(Opcode.LDC)
                || opcode.equals(Opcode.LDC2_W)
        ) {
            return true;
        }

        return false;
    }

    @Override
    public void doExecute(JavaThread thread, InstanceKlass belongKlass, ByteCode byteCode) {
        byte[] content = byteCode.getContent();

        int operand = -1;

        switch (byteCode.getOpcode()) {
            case LDC:
                operand = content[0];
                break;
            case LDC2_W:
                operand = DataTranslate.byteToUnsignedShort(content);
                break;
        }

        ConstantContent constantContent = ConstantPoolContext.getConstantContent(belongKlass, operand);

        JavaVFrame frame = (JavaVFrame) thread.getStack().peek();

        if (constantContent instanceof IntegerInfo) {
            int n = ((IntegerInfo) constantContent).getContent();
            frame.push(new StackValue(BasicType.T_INT, n));
        } else if (constantContent instanceof FloatInfo) {
            float f = ((FloatInfo) constantContent).getContent();
            frame.push(new StackValue(BasicType.T_FLOAT, f));
        } else if (constantContent instanceof StringInfo) {
            Utf8Info name = ((SingleIndex) constantContent).getName(belongKlass);
            frame.push(new StackValue(BasicType.T_OBJECT, name.getContent()));
        } else if (constantContent instanceof LongInfo) {
            long n = ((LongInfo) constantContent).getContent();
            frame.push(new StackValue(BasicType.T_LONG, n));
        } else if (constantContent instanceof DoubleInfo) {
            double n = ((DoubleInfo) constantContent).getContent();
            frame.push(new StackValue(BasicType.T_DOUBLE, n));
        } else {
            log.error("未知类型");
        }
    }
}
