package indi.yufr.jvm.share.vm.prims;

import indi.yufr.jvm.share.vm.oops.InstanceKlass;
import indi.yufr.jvm.share.vm.oops.MethodInfo;
import indi.yufr.jvm.share.vm.runtime.JavaThread;
import indi.yufr.jvm.share.vm.runtime.JavaVFrame;
import indi.yufr.jvm.share.vm.runtime.Threads;
import indi.yufr.jvm.share.vm.utilities.AccessFlags;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author: ziya
 * @Date: 2021/3/26 07:34
 */
@Slf4j
public class JavaNativeInterface {

    public static MethodInfo getMethodID(InstanceKlass klass, String methodName, String paramsDescriptor) {
        MethodInfo[] methods = klass.getMethods();

        for (MethodInfo method : methods) {
            String name = method.name(klass);
            String descriptorName = method.descriptorName(klass);

            if (StringUtils.equals(name, methodName) && StringUtils.equals(descriptorName, paramsDescriptor)) {
                return method;
            }

        }

        log.error("没有找到方法 methodName[{}], paramsDescriptor[{}]", methodName, paramsDescriptor);
        return null;
    }

    public static void callStaticMethod(MethodInfo methodInfo) {

        JavaVFrame prevFrame = null;
        JavaThread thread = Threads.currentThread();

        short accessFlags = methodInfo.getAccessFlags();
        AccessFlags realAccessFlags = new AccessFlags(accessFlags);
        if (!realAccessFlags.isStatic()) {
            throw new RuntimeException("非静态方法,调用失败");
        }




    }
}
