package indi.yufr.jvm.share.byteCode;

import indi.yufr.jvm.share.tools.Tuple;
import indi.yufr.jvm.share.vm.oops.InstanceKlass;
import indi.yufr.jvm.share.vm.runtime.JavaThread;
import indi.yufr.jvm.share.vm.runtime.JavaVFrame;
import indi.yufr.jvm.share.vm.runtime.StackValue;
import indi.yufr.jvm.share.vm.utilities.BasicType;
import lombok.extern.slf4j.Slf4j;

/**
 * @date: 2022/2/11 16:00
 * @author: farui.yu
 * <p>
 * 此类是对类型转换的统一处理器
 */
@Slf4j
public class ByteCodeClassCastExecutor extends ByteCodeExecutor {

    @Override
    public boolean canSupport(Opcode opcode) {
        if (opcode.equals(Opcode.I2D)
                || opcode.equals(Opcode.I2L)
                || opcode.equals(Opcode.I2F)
                || opcode.equals(Opcode.L2I)
                || opcode.equals(Opcode.L2F)
                || opcode.equals(Opcode.L2D)
                || opcode.equals(Opcode.F2I)
                || opcode.equals(Opcode.F2L)
                || opcode.equals(Opcode.F2D)
                || opcode.equals(Opcode.D2I)
                || opcode.equals(Opcode.D2L)
                || opcode.equals(Opcode.D2F)
                || opcode.equals(Opcode.I2B)
                || opcode.equals(Opcode.I2C)
                || opcode.equals(Opcode.I2S)
        ) {
            return true;
        }

        return false;
    }

    @Override
    public void doExecute(JavaThread thread, InstanceKlass belongKlass, ByteCode byteCode) {
        JavaVFrame peek = (JavaVFrame) thread.getStack().peek();

        Tuple<BasicType, BasicType> parse = parse(byteCode.getOpcode());

        BasicType t1 = parse.getT1();
        BasicType t2 = parse.getT2();

        // 此处强制转换,我们暂时不用特意思考
        // 留在cpp部分我们再来一遍
        if (t1.equals(BasicType.T_LONG) && t2.equals(BasicType.T_INT)) {
            Long data = (Long) peek.pop().getData();
            peek.push(new StackValue(t2, data.intValue()));
        } else {
            peek.push(new StackValue(t2, peek.pop().getData()));
        }
    }

    private static Tuple<BasicType, BasicType> parse(Opcode opcode) {
        String name = opcode.name();
        char first = name.substring(0, 1).charAt(0);
        char second = name.substring(2).charAt(0);

        if (first == 'L') {
            first = 'J';
        }

        if (second == 'L') {
            second = 'J';
        }

        return new Tuple<>(BasicType.charToBasicType(first),
                BasicType.charToBasicType(second));
    }

}
