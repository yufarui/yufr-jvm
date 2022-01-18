package indi.yufr.jvm.yuShare.constantTag;

import indi.yufr.jvm.yuShare.vm.classFile.ByteIndex;
import indi.yufr.jvm.yuShare.vm.utilities.ConstantTag;

/**
 * @date: 2022/1/18 13:57
 * @author: farui.yu
 */
public abstract class ConstantInfoExecutor {

    public abstract boolean canSupport(ConstantTag tag);

    /**
     * 当前已解析完毕tag,此index已经移动到常量content上
     *
     * @param content
     * @param index
     * @return
     */
    public abstract Object doParseInfo(byte[] content, ByteIndex index);

}
