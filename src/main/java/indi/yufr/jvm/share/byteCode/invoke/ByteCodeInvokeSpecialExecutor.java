package indi.yufr.jvm.share.byteCode.invoke;

import indi.yufr.jvm.share.byteCode.ByteCode;
import indi.yufr.jvm.share.byteCode.ByteCodeExecutor;
import indi.yufr.jvm.share.byteCode.Opcode;
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
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * @date: 2022/1/20 10:30
 * @author: farui.yu
 */
@Slf4j
public class ByteCodeInvokeSpecialExecutor extends ByteCodeExecutor {

    @Override
    public boolean canSupport(Opcode opcode) {
        if (opcode.equals(Opcode.INVOKESPECIAL)) {
            return true;
        }

        return false;
    }

    @SneakyThrows
    @Override
    public void doExecute(JavaThread thread, InstanceKlass belongKlass, ByteCode byteCode) {
        JavaVFrame frame = (JavaVFrame) thread.getStack().peek();

        byte[] content = byteCode.getContent();

        int methodRefIndex = DataTranslate.byteToUnsignedShort(content);

        ConstantContent constantContent = ConstantPoolContext.getConstantContent(belongKlass, methodRefIndex);
        MetaRefInfo metaRefInfo = (MetaRefInfo) constantContent;

        String className = metaRefInfo.getClassName(belongKlass);
        String methodName = metaRefInfo.getName(belongKlass);
        String descriptorName = metaRefInfo.getDescriptionName(belongKlass);

        if (className.startsWith("java")) {

            List<DescriptorInfo> methodParamsDes = MethodInfo.parseMethodParams(descriptorName);

            Object[] params = DescriptorInfo.parseMethodParams(methodParamsDes, frame);
            Class<?>[] paramsClass = DescriptorInfo.getParamsType(methodParamsDes);

            Object stackValueData = frame.pop().getData();

            if (methodName.equals("<init>")) {

                if (null == stackValueData || stackValueData.equals("")) {
                    log.info("\t NEW????????????????????????????????????????????????");

                    Class<?> clazz = Class.forName(className.replace('/', '.'));
                    Constructor<?> constructor = clazz.getConstructor(paramsClass);

                    stackValueData = constructor.newInstance(params);
                }

                if (!className.equals("java/lang/Object")) {
                    // ??????????????????????????????????????????StackValue????????????????????????????????????
                    frame.getStack().peek().setData(stackValueData);
                }
            } else {
                throw new Error("java???????????????????????????????????????");
            }
        } else {
            InstanceKlass klass = BootClassLoader.findLoadedKlass(className.replace('/', '.'));
            if (null == klass) {
                log.info("\t ???????????????????????????:" + className);

                klass = BootClassLoader.loadKlass(className.replace('/', '.'));
                if (null == klass) {
                    throw new Error("???????????????: " + className);
                }
            }
            MethodInfo methodID = JavaNativeInterface.getMethodID(klass, methodName, descriptorName);
            JavaNativeInterface.callMethod(methodID);
        }
    }
}
