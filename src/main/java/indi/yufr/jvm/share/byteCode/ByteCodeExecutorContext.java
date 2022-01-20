package indi.yufr.jvm.share.byteCode;

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
}
