package indi.yufr.jvm.share.constant.executor;

import indi.yufr.jvm.share.vm.utilities.ConstantTag;
import lombok.val;

import java.util.Arrays;
import java.util.List;

/**
 * @date: 2022/1/18 13:53
 * @author: farui.yu
 */
public class ConstantInfoExecutorContext {

    private static List<ConstantInfoExecutor> constantInfoExecutors;

    public final static ConstantSkipExecutor skipExecutor = new ConstantSkipExecutor();

    static {
        constantInfoExecutors = Arrays.asList(
                new ConstantDoubleInfoExecutor(),
                new ConstantFloatInfoExecutor(),
                new ConstantIntegerInfoExecutor(),
                new ConstantLongInfoExecutor(),
                new ConstantMetaRefExecutor(),
                new ConstantNameAndTypeIndex(),
                new ConstantSingleIndexExecutor(),
                new ConstantUtf8InfoExecutor()
        );
    }

    public static ConstantInfoExecutor findExecutor(ConstantTag tag) {

        for (val executor : constantInfoExecutors) {
            if (executor.canSupport(tag)) {
                return executor;
            }
        }
        throw new RuntimeException("未找到合适的处理器");
    }

}
