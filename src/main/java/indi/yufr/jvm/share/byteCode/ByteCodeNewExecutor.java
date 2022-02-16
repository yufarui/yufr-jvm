package indi.yufr.jvm.share.byteCode;

import indi.yufr.jvm.share.constant.content.ClassInfo;
import indi.yufr.jvm.share.constant.content.ConstantContent;
import indi.yufr.jvm.share.constant.content.Utf8Info;
import indi.yufr.jvm.share.tools.DataTranslate;
import indi.yufr.jvm.share.vm.oops.ArrayOop;
import indi.yufr.jvm.share.vm.oops.ConstantPoolContext;
import indi.yufr.jvm.share.vm.oops.InstanceKlass;
import indi.yufr.jvm.share.vm.runtime.JavaThread;
import indi.yufr.jvm.share.vm.runtime.JavaVFrame;
import indi.yufr.jvm.share.vm.runtime.StackValue;
import indi.yufr.jvm.share.vm.utilities.BasicType;
import lombok.SneakyThrows;

import java.lang.reflect.Constructor;

/**
 * @date: 2022/2/15 13:27
 * @author: farui.yu
 * <p>
 * 创建数组对象命令
 */
public class ByteCodeNewExecutor extends ByteCodeExecutor {

    @Override
    public boolean canSupport(Opcode opcode) {
        if (opcode.equals(Opcode.NEW)
                || opcode.equals(Opcode.NEWARRAY)
                || opcode.equals(Opcode.ANEWARRAY)
        ) {
            return true;
        }

        return false;
    }

    @SneakyThrows
    @Override
    public void doExecute(JavaThread thread, InstanceKlass belongKlass, ByteCode byteCode) {

        JavaVFrame frame = (JavaVFrame) thread.getStack().peek();

        if (byteCode.getOpcode().equals(Opcode.NEW)) {
            byte[] content = byteCode.getContent();
            int index = DataTranslate.byteToUnsignedShort(content);
            ConstantContent constantContent = ConstantPoolContext.getConstantContent(belongKlass, index);
            String className = ((ClassInfo) constantContent).getName(belongKlass).getContent();

            Class<?> clazz = Class.forName(className.replace('/', '.'));
            Constructor<?> constructor = clazz.getConstructor();

            Object o = constructor.newInstance();
            if (o instanceof Throwable) {
                frame.getStack().push(new StackValue(BasicType.T_Throwable, o));
            } else {
                frame.getStack().push(new StackValue(BasicType.T_OBJECT, o));
            }
        } else if (byteCode.getOpcode().equals(Opcode.NEWARRAY)) {
            int arraySize = (int) frame.pop().getData();
            byte[] content = byteCode.getContent();
            byte arrayType = content[0];
            BasicType basicType = BasicType.intToBasicType(arrayType);
            frame.push(new StackValue(BasicType.T_ARRAY, new ArrayOop(basicType, arraySize)));
        } else if (byteCode.getOpcode().equals(Opcode.ANEWARRAY)) {
            int arraySize = (int) frame.pop().getData();
            byte[] content = byteCode.getContent();
            int index = DataTranslate.byteToUnsignedShort(content);
            ConstantContent constantContent = ConstantPoolContext.getConstantContent(belongKlass, index);
            Utf8Info name = ((ClassInfo) constantContent).getName(belongKlass);
            String className = name.getContent();
            BasicType basicType = BasicType.T_OBJECT;
            frame.push(new StackValue(BasicType.T_ARRAY, new ArrayOop(basicType, className, arraySize)));
        }

    }
}
