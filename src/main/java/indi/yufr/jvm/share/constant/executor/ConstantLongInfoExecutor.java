package indi.yufr.jvm.share.constant.executor;

import indi.yufr.jvm.share.constant.content.ConstantContent;
import indi.yufr.jvm.share.constant.content.LongInfo;
import indi.yufr.jvm.share.tools.DataTranslate;
import indi.yufr.jvm.share.tools.Stream;
import indi.yufr.jvm.share.vm.classFile.ByteIndex;
import indi.yufr.jvm.share.vm.utilities.ConstantTag;

/**
 * @date: 2022/1/18 14:23
 * @author: farui.yu
 */
public class ConstantLongInfoExecutor extends ConstantInfoExecutor {

    @Override
    public boolean canSupport(ConstantTag tag) {

        if (ConstantTag.JVM_CONSTANT_Long.equals(tag)) {
            return true;
        }
        return false;
    }

    @Override
    public ConstantContent doParseInfo(byte[] content, ByteIndex index) {

        byte[] bytes = Stream.readBytes(content, index, 8);

        return LongInfo.builder()
                .content(DataTranslate.bytesToLong(bytes))
                .build();

    }

}
