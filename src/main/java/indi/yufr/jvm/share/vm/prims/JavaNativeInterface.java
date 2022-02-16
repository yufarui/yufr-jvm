package indi.yufr.jvm.share.vm.prims;

import indi.yufr.jvm.share.byteCode.ByteCodeExecutorContext;
import indi.yufr.jvm.share.vm.classFile.MethodDescriptor;
import indi.yufr.jvm.share.vm.oops.InstanceKlass;
import indi.yufr.jvm.share.vm.oops.MethodInfo;
import indi.yufr.jvm.share.vm.oops.attribute.CodeAttributeInfo;
import indi.yufr.jvm.share.vm.runtime.JavaThread;
import indi.yufr.jvm.share.vm.runtime.JavaVFrame;
import indi.yufr.jvm.share.vm.runtime.Threads;
import indi.yufr.jvm.share.vm.utilities.AccessFlags;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Stack;

@Slf4j
public class JavaNativeInterface {

    public static MethodInfo getMethodID(InstanceKlass klass, String methodName, String paramsDescriptor) {
        MethodInfo[] methods = klass.getMethods();

        for (MethodInfo method : methods) {
            String name = method.name();
            String descriptorName = method.descriptorName();

            if (StringUtils.equals(name, methodName) && StringUtils.equals(descriptorName, paramsDescriptor)) {
                return method;
            }

        }

        log.error("没有找到方法 methodName[{}], params Descriptor[{}]", methodName, paramsDescriptor);
        return null;
    }

    public static void callMethod(MethodInfo methodInfo) {

        JavaVFrame prevFrame = null;
        JavaThread thread = Threads.currentThread();

        short accessFlags = methodInfo.getAccessFlags();
        AccessFlags realAccessFlags = new AccessFlags(accessFlags);

        // 获取参数信息
        MethodDescriptor methodDescriptor = methodInfo.methodDescriptorHandler();

        if (!realAccessFlags.isStatic() || methodDescriptor.getParamsInfo().size() != 0) {

            if (0 != thread.getStack().size()) {
                log.info("\t 从上一个栈帧取参数值");

                prevFrame = (JavaVFrame) thread.getStack().peek();
            }

        } else {
            log.info("\t 方法 [ " + methodInfo.name() + " ] 没有参数");
        }

        // methodInfo 第一个attribute必定是 codeAttributeInfo
        CodeAttributeInfo codeAttributeInfo = (CodeAttributeInfo) methodInfo.getAttributes()[0];

        JavaVFrame frame = new JavaVFrame(codeAttributeInfo.getMaxLocals(), methodInfo);

        // 静态方法实际上 和 非静态保持了一致
        // 非静态方法 多压入了一个this引用
        if (null != prevFrame) {

            if (realAccessFlags.isStatic()) {
                for (int i = 0; i < methodDescriptor.getParamsInfo().size(); i++) {
                    frame.add(i, prevFrame.getStack().pop());
                }
            } else {
                for (int i = 0; i < methodDescriptor.getParamsInfo().size(); i++) {
                    frame.add(i, prevFrame.getStack().pop());
                }
                frame.add(0, prevFrame.pop());
            }
        }

        thread.getStack().push(frame);

        // 执行任务交给字节码解释器
        ByteCodeExecutorContext.run(thread, methodInfo);
    }

}
