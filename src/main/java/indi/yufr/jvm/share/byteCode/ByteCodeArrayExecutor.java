package indi.yufr.jvm.share.byteCode;

import indi.yufr.jvm.share.constant.content.ClassInfo;
import indi.yufr.jvm.share.constant.content.ConstantContent;
import indi.yufr.jvm.share.constant.content.Utf8Info;
import indi.yufr.jvm.share.tools.Stream;
import indi.yufr.jvm.share.vm.oops.ArrayOop;
import indi.yufr.jvm.share.vm.oops.ConstantPoolContext;
import indi.yufr.jvm.share.vm.oops.InstanceKlass;
import indi.yufr.jvm.share.vm.runtime.JavaThread;
import indi.yufr.jvm.share.vm.runtime.JavaVFrame;
import indi.yufr.jvm.share.vm.runtime.StackValue;
import indi.yufr.jvm.share.vm.utilities.BasicType;

/**
 * @date: 2022/2/15 13:27
 * @author: farui.yu
 * <p>
 * 创建数组对象命令
 */
public class ByteCodeArrayExecutor extends ByteCodeExecutor {

    @Override
    public boolean canSupport(Opcode opcode) {
        if (opcode.equals(Opcode.NEWARRAY)
                || opcode.equals(Opcode.ANEWARRAY)
        ) {
            return true;
        }

        return false;
    }

    @Override
    public void doExecute(JavaThread thread, InstanceKlass belongKlass, ByteCode byteCode) {

        JavaVFrame frame = (JavaVFrame) thread.getStack().peek();

        int arraySize = (int) frame.pop().getData();

        BasicType basicType;
        String className = null;
        switch (byteCode.getOpcode()) {
            case NEWARRAY:
                byte[] content1 = byteCode.getContent();
                byte arrayType = content1[0];
                basicType = BasicType.intToBasicType(arrayType);
                break;
            case ANEWARRAY:
                byte[] content2 = byteCode.getContent();
                short index = (short) Stream.readInOrder(content2);
                ConstantContent constantContent = ConstantPoolContext.getConstantContent(belongKlass, index);

                Utf8Info name = ((ClassInfo) constantContent).getName(belongKlass);
                className = name.getContent();
                basicType = BasicType.T_OBJECT;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + byteCode.getOpcode());
        }

        frame.push(new StackValue(BasicType.T_ARRAY, new ArrayOop(basicType, className, arraySize)));
    }
}
