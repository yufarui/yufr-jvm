package indi.yufr.jvm.share.byteCode;

import indi.yufr.jvm.share.constant.content.ConstantContent;
import indi.yufr.jvm.share.constant.content.MetaRefInfo;
import indi.yufr.jvm.share.tools.DataTranslate;
import indi.yufr.jvm.share.vm.oops.ConstantPoolContext;
import indi.yufr.jvm.share.vm.oops.InstanceKlass;
import indi.yufr.jvm.share.vm.runtime.JavaThread;
import indi.yufr.jvm.share.vm.runtime.JavaVFrame;
import indi.yufr.jvm.share.vm.runtime.StackValue;
import indi.yufr.jvm.share.vm.utilities.BasicType;
import lombok.SneakyThrows;

import java.lang.reflect.Field;

/**
 * @date: 2022/1/20 15:25
 * @author: farui.yu
 */
public class ByteCodeGetStaticExecutor extends ByteCodeExecutor {

    @Override
    public boolean canSupport(Opcode opcode) {

        if (opcode.equals(Opcode.GETSTATIC)) {
            return true;
        }

        return false;
    }

    @SneakyThrows
    @Override
    public void doExecute(JavaThread thread, InstanceKlass belongKlass, ByteCode byteCode) {

        byte[] content = byteCode.getContent();

        int fieldRefIndex = DataTranslate.byteToUnsignedShort(content);
        ConstantContent constantContent = ConstantPoolContext.getConstantContent(belongKlass, fieldRefIndex);

        MetaRefInfo metaRefInfo = (MetaRefInfo) constantContent;

        String className = metaRefInfo.getClassName(belongKlass);
        String fieldName = metaRefInfo.getName(belongKlass);

        Class<?> clazz = Class.forName(className.replace('/', '.'));
        Field field = clazz.getField(fieldName);

        JavaVFrame frame = (JavaVFrame) thread.getStack().peek();
        frame.push(new StackValue(BasicType.T_OBJECT, field.get(null)));
    }
}
