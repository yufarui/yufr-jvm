package indi.yufr.jvm.share.attribute;

import indi.yufr.jvm.share.tools.Stream;
import indi.yufr.jvm.share.vm.classFile.ByteIndex;
import indi.yufr.jvm.share.vm.oops.InstanceKlass;
import indi.yufr.jvm.share.vm.oops.attribute.AttributeInfo;
import indi.yufr.jvm.share.vm.oops.attribute.LineNumberTableAttribute;
import org.apache.commons.lang3.StringUtils;

/**
 * @date: 2022/1/19 11:15
 * @author: farui.yu
 */
public class LineNumberTableAttributeExecutor extends AttributeInfoExecutor {

    @Override
    public boolean canSupport(String attributeName) {

        if (StringUtils.equals(attributeName, "LineNumberTable")) {
            return true;
        }

        return false;
    }

    @Override
    public AttributeInfo doParse(byte[] content, ByteIndex index, InstanceKlass klass) {

        // 到达此方法是 必定解析过 attrIndex
        // 字节码长度
        int length = Stream.readU4(content, index);
        LineNumberTableAttribute attribute = new LineNumberTableAttribute();
        attribute.setAttributeName("LineNumberTable");
        attribute.setAttributeLength(length);

        attribute.setTableLength(Stream.readU2(content, index));

        LineNumberTableAttribute.Item[] items =
                parseTableItem(content, index, attribute.getTableLength());
        attribute.setTable(items);

        return attribute;
    }

    private LineNumberTableAttribute.Item[] parseTableItem(byte[] content, ByteIndex index, short tableLength) {

        LineNumberTableAttribute.Item[] items = new LineNumberTableAttribute.Item[tableLength];

        for (int i = 0; i < tableLength; i++) {
            LineNumberTableAttribute.Item item = new LineNumberTableAttribute.Item();
            item.setStartPc(Stream.readU2(content, index));
            item.setLineNumber(Stream.readU2(content, index));
            items[i] = item;
        }

        return items;
    }

}
