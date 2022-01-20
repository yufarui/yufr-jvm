package indi.yufr.jvm.yuShare.constant.executor;

import indi.yufr.jvm.yuShare.constant.content.ConstantContent;
import indi.yufr.jvm.yuShare.constant.content.NameAndTypeInfo;
import indi.yufr.jvm.yuShare.tools.Stream;
import indi.yufr.jvm.yuShare.vm.classFile.ByteIndex;
import indi.yufr.jvm.yuShare.vm.utilities.ConstantTag;

/**
 * @date: 2022/1/19 16:15
 * @author: farui.yu
 */
public class ConstantNameAndTypeIndex extends ConstantInfoExecutor {

    @Override
    public boolean canSupport(ConstantTag tag) {

        if (ConstantTag.JVM_CONSTANT_NameAndType.equals(tag)) {
            return true;
        }
        return false;
    }

    @Override
    public ConstantContent doParseInfo(byte[] content, ByteIndex index) {

        short firstIndex = Stream.readU2(content, index);
        short secondIndex = Stream.readU2(content, index);

        return NameAndTypeInfo.builder()
                .nameIndex(firstIndex)
                .descriptionIndex(secondIndex)
                .build();
    }
}

