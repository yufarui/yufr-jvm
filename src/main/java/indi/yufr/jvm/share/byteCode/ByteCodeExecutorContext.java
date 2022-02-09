package indi.yufr.jvm.share.byteCode;

import indi.yufr.jvm.share.vm.oops.InstanceKlass;
import indi.yufr.jvm.share.vm.oops.MethodInfo;
import indi.yufr.jvm.share.vm.oops.attribute.CodeAttributeInfo;
import indi.yufr.jvm.share.vm.runtime.JavaThread;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

/**
 * @date: 2022/1/20 9:54
 * @author: farui.yu
 */
@Slf4j
public class ByteCodeExecutorContext {

    private static List<ByteCodeExecutor> byteCodeExecutors;

    static {
        byteCodeExecutors = Arrays.asList(
                new ByteCodeALoadExecutor(),
                new ByteCodeGetStaticExecutor(),
                new ByteCodeInvokeSpecialExecutor(),
                new ByteCodeInvokeVirtualExecutor(),
                new ByteCodeLdcExecutor(),
                new ByteCodeReturnExecutor()
        );
    }

    public static ByteCodeExecutor findExecutor(Opcode opcode) {
        for (ByteCodeExecutor executor : byteCodeExecutors) {

            if (executor.canSupport(opcode)) {
                return executor;
            }
        }

        log.warn("无法支持的字节码信息解析[{}]", opcode);
        return null;
    }

    public static void run(JavaThread thread, MethodInfo method) {

        CodeAttributeInfo attribute = (CodeAttributeInfo) method.getAttributes()[0];

        List<ByteCode> codes = attribute.getCodes();

        InstanceKlass belongKlass = method.getBelongKlass();

        for (ByteCode byteCode : codes) {
            ByteCodeExecutor executor = ByteCodeExecutorContext.findExecutor(byteCode.getOpcode());
            assert executor != null;
            executor.execute(thread, belongKlass, byteCode);
        }
    }

    protected static int parseByteCodeLastNum(Opcode opcode) {

        String name = opcode.name();
        int index = name.lastIndexOf("_");

        String opcodeEnd = name.substring(index + 1);
        return Integer.parseInt(opcodeEnd);
    }

}
