package indi.yufr.jvm.yuShare.constant.executor;

import indi.yufr.jvm.yuShare.constant.content.ConstantContent;
import indi.yufr.jvm.yuShare.constant.content.Utf8Info;
import indi.yufr.jvm.yuShare.tools.Stream;
import indi.yufr.jvm.yuShare.vm.classFile.ByteIndex;
import indi.yufr.jvm.yuShare.vm.utilities.ConstantTag;

/**
 * @date: 2022/1/18 14:23
 * @author: farui.yu
 */
public class ConstantUtf8InfoExecutor extends ConstantInfoExecutor {

    @Override
    public boolean canSupport(ConstantTag tag) {

        if (ConstantTag.JVM_CONSTANT_Utf8.equals(tag)) {
            return true;
        }
        return false;
    }

    @Override
    public ConstantContent doParseInfo(byte[] content, ByteIndex index) {
        short length = Stream.readU2(content, index);

        // 原本是根据读取到的first,来处理字符串
        // 当前直接使用 new String 方法处理
        byte[] bytes = Stream.readBytes(content, index, length);

        return Utf8Info.builder()
                .content(new String(bytes))
                .build();
    }
}
