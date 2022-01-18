package indi.yufr.jvm.yuShare.constantTag;

import indi.yufr.jvm.yuShare.vm.utilities.ConstantTag;
import lombok.val;

import java.util.ArrayList;
import java.util.List;

/**
 * @date: 2022/1/18 13:53
 * @author: farui.yu
 */
public class ConstantInfoExecutorContext {

    private static List<ConstantInfoExecutor> constantInfoExecutors = new ArrayList<>();

    static {
        constantInfoExecutors.add(new ConstantDoubleIndexExecutor());
        constantInfoExecutors.add(new ConstantSingleIndexExecutor());

        constantInfoExecutors.add(new ConstantUtf8InfoExecutor());

        constantInfoExecutors.add(new ConstantIntegerInfoExecutor());
        constantInfoExecutors.add(new ConstantFloatInfoExecutor());

        constantInfoExecutors.add(new ConstantLongInfoExecutor());
        constantInfoExecutors.add(new ConstantDoubleInfoExecutor());
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
