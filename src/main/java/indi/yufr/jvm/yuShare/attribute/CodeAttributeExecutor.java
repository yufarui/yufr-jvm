package indi.yufr.jvm.yuShare.attribute;

import indi.yufr.jvm.yuShare.tools.Stream;
import indi.yufr.jvm.yuShare.vm.classFile.ByteIndex;
import indi.yufr.jvm.yuShare.vm.oops.attribute.AttributeInfo;
import indi.yufr.jvm.yuShare.vm.oops.attribute.CodeAttributeInfo;
import indi.yufr.jvm.yuShare.vm.oops.attribute.ConstantValueAttribute;
import org.apache.commons.lang3.StringUtils;

/**
 * @date: 2022/1/19 15:05
 * @author: farui.yu
 */
public class CodeAttributeExecutor extends AttributeInfoExecutor {
    @Override
    public boolean canSupport(String attributeName) {
        if (StringUtils.equals(attributeName, "Code")) {
            return true;
        }

        return false;
    }

    @Override
    public AttributeInfo doParseInfo(byte[] content, ByteIndex index) {

        int length = Stream.readU4(content, index);

        CodeAttributeInfo attribute = new CodeAttributeInfo();
        attribute.setAttributeName("Code");
        attribute.setAttributeLength(length);

        attribute.setMaxStack(Stream.readU2(content, index));
        attribute.setMaxLocals(Stream.readU2(content, index));
        attribute.setCodeLength(Stream.readU4(content, index));


        return null;
    }
}
