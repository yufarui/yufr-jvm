package indi.yufr.jvm.yuShare.constant.executor;

import indi.yufr.jvm.yuShare.constant.content.ConstantContent;
import indi.yufr.jvm.yuShare.constant.content.MetaRefInfo;
import indi.yufr.jvm.yuShare.tools.Stream;
import indi.yufr.jvm.yuShare.vm.classFile.ByteIndex;
import indi.yufr.jvm.yuShare.vm.utilities.ConstantTag;

/**
 * @date: 2022/1/18 14:23
 * @author: farui.yu
 */
public class ConstantMetaRefExecutor extends ConstantInfoExecutor {

    @Override
    public boolean canSupport(ConstantTag tag) {

        if (ConstantTag.JVM_CONSTANT_Fieldref.equals(tag)
                || ConstantTag.JVM_CONSTANT_Methodref.equals(tag)
                || ConstantTag.JVM_CONSTANT_InterfaceMethodref.equals(tag)
                || ConstantTag.JVM_CONSTANT_NameAndType.equals(tag)
        ) {
            return true;
        }
        return false;
    }

    @Override
    public ConstantContent doParseInfo(byte[] content, ByteIndex index) {

        short firstIndex = Stream.readU2(content, index);
        short secondIndex = Stream.readU2(content, index);



        return MetaRefInfo.builder()
                .classIndex(firstIndex)
                .nameAndTypeIndex(secondIndex)
                .build();
    }
}
