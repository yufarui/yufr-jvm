package indi.yufr.jvm.share.constant.executor;

import indi.yufr.jvm.share.constant.content.ConstantContent;
import indi.yufr.jvm.share.constant.content.SingleIndex;
import indi.yufr.jvm.share.tools.Stream;
import indi.yufr.jvm.share.vm.classFile.ByteIndex;
import indi.yufr.jvm.share.vm.utilities.ConstantTag;

/**
 * @date: 2022/1/18 14:23
 * @author: farui.yu
 */
public class ConstantSingleIndexExecutor extends ConstantInfoExecutor {

    @Override
    public boolean canSupport(ConstantTag tag) {

        if (ConstantTag.JVM_CONSTANT_Class.equals(tag)
                || ConstantTag.JVM_CONSTANT_String.equals(tag)
        ) {
            return true;
        }
        return false;
    }

    @Override
    public ConstantContent doParseInfo(byte[] content, ByteIndex index) {

        return SingleIndex.builder()
                .index(Stream.readU2(content, index))
                .build();
    }
}
