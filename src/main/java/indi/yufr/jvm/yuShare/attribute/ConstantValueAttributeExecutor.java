package indi.yufr.jvm.yuShare.attribute;

import indi.yufr.jvm.yuShare.tools.Stream;
import indi.yufr.jvm.yuShare.vm.classFile.ByteIndex;
import indi.yufr.jvm.yuShare.vm.oops.attribute.AttributeInfo;
import indi.yufr.jvm.yuShare.vm.oops.attribute.ConstantValueAttribute;
import org.apache.commons.lang3.StringUtils;

/**
 * @date: 2022/1/19 11:15
 * @author: farui.yu
 */
public class ConstantValueAttributeExecutor extends AttributeInfoExecutor {

    @Override
    public boolean canSupport(String attributeName) {

        if (StringUtils.equals(attributeName, "ConstantValue")) {
            return true;
        }

        return false;
    }

    @Override
    public AttributeInfo doParseInfo(byte[] content, ByteIndex index) {

        // 到达此方法是 必定解析过 attrIndex
        // 字节码长度
        int length = Stream.readU4(content, index);

        ConstantValueAttribute attribute = new ConstantValueAttribute();
        attribute.setAttributeName("ConstantValue");
        attribute.setAttributeLength(length);
        attribute.setConstantValueIndex(Stream.readU2(content, index));

        return attribute;
    }

}
