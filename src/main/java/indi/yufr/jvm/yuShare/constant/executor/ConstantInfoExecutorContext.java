package indi.yufr.jvm.yuShare.constant.executor;

import indi.yufr.jvm.yuShare.vm.utilities.ConstantTag;
import lombok.SneakyThrows;
import lombok.val;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @date: 2022/1/18 13:53
 * @author: farui.yu
 */
public class ConstantInfoExecutorContext {

    private static List<ConstantInfoExecutor> constantInfoExecutors;

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
