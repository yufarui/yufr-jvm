package indi.yufr.jvm.share.byteCode;

import indi.yufr.jvm.share.byteCode.arithmethic.*;
import indi.yufr.jvm.share.vm.oops.InstanceKlass;
import indi.yufr.jvm.share.vm.oops.MethodInfo;
import indi.yufr.jvm.share.vm.oops.attribute.CodeAttributeInfo;
import indi.yufr.jvm.share.vm.runtime.JavaThread;
import indi.yufr.jvm.share.vm.utilities.BasicType;
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
                new ByteCodeAddExecutor(),
                new ByteCodeDivExecutor(),
                new ByteCodeMulExecutor(),
                new ByteCodeRemExecutor(),
                new ByteCodeSubExecutor(),
                new ByteCodeBiPushExecutor(),
                new ByteCodeClassCastExecutor(),
                new ByteCodeGetStaticExecutor(),
                new ByteCodeInvokeSpecialExecutor(),
                new ByteCodeInvokeVirtualExecutor(),
                new ByteCodeIINCExecutor(),
                new ByteCodeConstExecutor(),
                new ByteCodeLdcExecutor(),
                new ByteCodeLoadExecutor(),
                new ByteCodeReturnExecutor(),
                new ByteCodeStoreExecutor()
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

    protected static String parseByteCodePrefix(Opcode opcode, String keyword) {

        String name = opcode.name();

        int index = name.indexOf(keyword);
        return name.substring(0, index);
    }

    protected static int parseByteCodeLastNum(Opcode opcode) {

        String name = opcode.name();
        int index = name.lastIndexOf("_");

        if (index == -1) {
            return -1;
        }

        String opcodeEnd = name.substring(index + 1);
        return Integer.parseInt(opcodeEnd);
    }

    public static BasicType cast(char type) {
        if (type == 'L') {
            type = 'J';
        }
        return BasicType.charToBasicType(type);
    }

}
