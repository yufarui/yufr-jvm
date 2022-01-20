package indi.yufr.jvm.yuShare.constant.executor;

import indi.yufr.jvm.yuShare.constant.content.ConstantContent;
import indi.yufr.jvm.yuShare.constant.content.FloatInfo;
import indi.yufr.jvm.yuShare.tools.DataTranslate;
import indi.yufr.jvm.yuShare.tools.Stream;
import indi.yufr.jvm.yuShare.vm.classFile.ByteIndex;
import indi.yufr.jvm.yuShare.vm.utilities.ConstantTag;

/**
 * @date: 2022/1/18 14:23
 * @author: farui.yu
 */
public class ConstantFloatInfoExecutor extends ConstantInfoExecutor {

    @Override
    public boolean canSupport(ConstantTag tag) {

        if (ConstantTag.JVM_CONSTANT_Float.equals(tag)) {
            return true;
        }
        return false;
    }

    @Override
    public ConstantContent doParseInfo(byte[] content, ByteIndex index) {

        byte[] bytes = Stream.readBytes(content, index, 4);

        return FloatInfo.builder()
                .content(DataTranslate.byteToFloat(bytes))
                .build();
    }
}
