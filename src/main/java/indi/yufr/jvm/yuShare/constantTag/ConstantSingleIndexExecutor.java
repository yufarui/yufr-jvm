package indi.yufr.jvm.yuShare.constantTag;

import indi.yufr.jvm.yuShare.tools.Stream;
import indi.yufr.jvm.yuShare.vm.classFile.ByteIndex;
import indi.yufr.jvm.yuShare.vm.utilities.ConstantTag;

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
    public Object doParseInfo(byte[] content, ByteIndex index) {

        return Stream.readU2(content, index);
    }
}
