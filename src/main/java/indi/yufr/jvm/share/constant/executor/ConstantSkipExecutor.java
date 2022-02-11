package indi.yufr.jvm.share.constant.executor;

import indi.yufr.jvm.share.constant.content.ConstantContent;
import indi.yufr.jvm.share.vm.classFile.ByteIndex;
import indi.yufr.jvm.share.vm.utilities.ConstantTag;

/**
 * @date: 2022/2/11 16:51
 * @author: farui.yu
 */
public class ConstantSkipExecutor extends ConstantInfoExecutor {

    @Override
    public boolean canSupport(ConstantTag tag) {
        return false;
    }

    @Override
    public ConstantContent doParseInfo(byte[] content, ByteIndex index) {
        return null;
    }
}
