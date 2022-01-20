package indi.yufr.jvm.share.constant.executor;

import indi.yufr.jvm.share.constant.content.ConstantContent;
import indi.yufr.jvm.share.vm.classFile.ByteIndex;
import indi.yufr.jvm.share.vm.utilities.ConstantTag;

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
    public abstract ConstantContent doParseInfo(byte[] content, ByteIndex index);

}
