package indi.yufr.jvm.share.byteCode;

import indi.yufr.jvm.share.vm.oops.InstanceKlass;
import indi.yufr.jvm.share.vm.runtime.JavaThread;
import indi.yufr.jvm.share.vm.runtime.JavaVFrame;
import indi.yufr.jvm.share.vm.runtime.StackValue;
import indi.yufr.jvm.share.vm.utilities.BasicType;

/**
 * @date: 2022/2/14 8:48
 * @author: farui.yu
 * <p>
 * 加法
 */
public abstract class ByteCodeArithmeticExecutor extends ByteCodeExecutor {

    @Override
    public boolean canSupport(Opcode opcode) {
        String name = opcode.name();

        if (name.contains(getKeyword())) {
            return true;
        }

        return false;
    }

    @Override
    public void doExecute(JavaThread thread, InstanceKlass belongKlass, ByteCode byteCode) {
        JavaVFrame frame = (JavaVFrame) thread.getStack().peek();

        StackValue value1 = frame.pop();
        StackValue value2 = frame.pop();

        // 检查类型
        Opcode opcode = byteCode.getOpcode();
        String prefix = ByteCodeExecutorContext.parseByteCodePrefix(opcode, getKeyword());
        BasicType basicType = ByteCodeExecutorContext.cast(prefix.charAt(0));

        if (value1.getBasicType() != basicType || value2.getBasicType() != basicType) {
            throw new RuntimeException("不匹配的数据类型");
        }

        // 计算
        Object result = doArithmetic(basicType, value1, value2);

        frame.push(new StackValue(basicType, result));
    }

    public abstract String getKeyword();

    public abstract Object doArithmetic(BasicType basicType, StackValue value1, StackValue value2);

}
