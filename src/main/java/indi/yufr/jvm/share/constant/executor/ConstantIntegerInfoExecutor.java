package indi.yufr.jvm.share.constant.executor;

import indi.yufr.jvm.share.constant.content.ConstantContent;
import indi.yufr.jvm.share.constant.content.IntegerInfo;
import indi.yufr.jvm.share.tools.Stream;
import indi.yufr.jvm.share.vm.classFile.ByteIndex;
import indi.yufr.jvm.share.vm.utilities.ConstantTag;

/**
 * @date: 2022/1/18 14:23
 * @author: farui.yu
 */
public class ConstantIntegerInfoExecutor extends ConstantInfoExecutor {

    @Override
    public boolean canSupport(ConstantTag tag) {

        if (ConstantTag.JVM_CONSTANT_Integer.equals(tag)) {
            return true;
        }
        return false;
    }

    @Override
    public ConstantContent doParseInfo(byte[] content, ByteIndex index) {

        return IntegerInfo.builder()
                .content(Stream.readU4(content, index))
                .build();
    }
}
