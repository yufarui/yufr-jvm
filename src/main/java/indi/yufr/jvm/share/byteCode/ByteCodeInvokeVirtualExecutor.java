package indi.yufr.jvm.share.byteCode;

import indi.yufr.jvm.share.constant.content.ConstantContent;
import indi.yufr.jvm.share.constant.content.MetaRefInfo;
import indi.yufr.jvm.share.tools.DataTranslate;
import indi.yufr.jvm.share.vm.classFile.BootClassLoader;
import indi.yufr.jvm.share.vm.oops.ConstantPoolContext;
import indi.yufr.jvm.share.vm.oops.DescriptorInfo;
import indi.yufr.jvm.share.vm.oops.InstanceKlass;
import indi.yufr.jvm.share.vm.oops.MethodInfo;
import indi.yufr.jvm.share.vm.prims.JavaNativeInterface;
import indi.yufr.jvm.share.vm.runtime.JavaThread;
import indi.yufr.jvm.share.vm.runtime.JavaVFrame;
import indi.yufr.jvm.share.vm.runtime.StackValue;
import indi.yufr.jvm.share.vm.utilities.BasicType;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.List;

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

    @SneakyThrows
    @Override
    public void doExecute(JavaThread thread, InstanceKlass belongKlass, ByteCode byteCode) {
        byte[] content = byteCode.getContent();

        int methodRefIndex = DataTranslate.byteToUnsignedShort(content);

        ConstantContent constantContent = ConstantPoolContext.getConstantContent(belongKlass, methodRefIndex);
        MetaRefInfo metaRefInfo = (MetaRefInfo) constantContent;

        String className = metaRefInfo.getClassName(belongKlass);
        String methodName = metaRefInfo.getName(belongKlass);
        String descriptorName = metaRefInfo.getDescriptionName(belongKlass);

        JavaVFrame frame = (JavaVFrame) thread.getStack().peek();

        if (className.startsWith("java")) {

            List<DescriptorInfo> methodParamsDes = MethodInfo.parseMethodParams(descriptorName);

            DescriptorInfo methodReturnDes = MethodInfo.parseMethodReturn(descriptorName);

            Object[] params = DescriptorInfo.parseMethodParams(methodParamsDes, frame);
            Class[] paramsClass = DescriptorInfo.getParamsType(methodParamsDes);

            Object stackValueData = frame.pop().getData();

            Method fun = stackValueData.getClass().getMethod(methodName, paramsClass);

            if (BasicType.T_VOID == methodReturnDes.getType()) {
                fun.invoke(stackValueData, params);
            } else {
                frame.push(new StackValue(methodReturnDes.getType(), fun.invoke(stackValueData, params)));
            }
        } else {
            InstanceKlass klass = BootClassLoader.findLoadedKlass(className.replace('/', '.'));
            if (null == klass) {
                throw new Error("类还未加载: " + className);
            }

            MethodInfo methodID = JavaNativeInterface.getMethodID(klass, methodName, descriptorName);
            if (null == methodID) {
                throw new Error("不存在的方法: " + methodName + "#" + descriptorName);
            }

            JavaNativeInterface.callMethod(methodID);
        }

    }


}
