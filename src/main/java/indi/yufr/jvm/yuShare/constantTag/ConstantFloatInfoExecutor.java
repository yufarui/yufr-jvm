package indi.yufr.jvm.yuShare.constantTag;

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

        if (ConstantTag.JVM_CONSTANT_Utf8.equals(tag)) {
            return true;
        }
        return false;
    }

    @Override
    public Object doParseInfo(byte[] content, ByteIndex index) {

        byte[] bytes = Stream.readBytes(content, index, 4);
        return DataTranslate.byteToFloat(bytes);
    }
}
