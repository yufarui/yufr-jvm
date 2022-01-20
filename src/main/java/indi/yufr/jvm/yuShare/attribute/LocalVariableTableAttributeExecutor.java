package indi.yufr.jvm.yuShare.attribute;

import indi.yufr.jvm.yuShare.tools.Stream;
import indi.yufr.jvm.yuShare.vm.classFile.ByteIndex;
import indi.yufr.jvm.yuShare.vm.oops.InstanceKlass;
import indi.yufr.jvm.yuShare.vm.oops.attribute.AttributeInfo;
import indi.yufr.jvm.yuShare.vm.oops.attribute.LocalVariableTableAttribute;
import org.apache.commons.lang3.StringUtils;

/**
 * @date: 2022/1/19 11:15
 * @author: farui.yu
 */
public class LocalVariableTableAttributeExecutor extends AttributeInfoExecutor {

    @Override
    public boolean canSupport(String attributeName) {

        if (StringUtils.equals(attributeName, "LocalVariableTable")) {
            return true;
        }

        return false;
    }

    @Override
    public AttributeInfo doParse(byte[] content, ByteIndex index, InstanceKlass klass) {

        // 到达此方法是 必定解析过 attrIndex
        // 字节码长度
        int length = Stream.readU4(content, index);
        LocalVariableTableAttribute attribute = new LocalVariableTableAttribute();
        attribute.setAttributeName("LocalVariableTable");
        attribute.setAttributeLength(length);

        attribute.setTableLength(Stream.readU2(content, index));

        LocalVariableTableAttribute.Item[] items =
                parseTableItem(content, index, attribute.getTableLength());
        attribute.setTable(items);

        return attribute;
    }

    private LocalVariableTableAttribute.Item[] parseTableItem(byte[] content, ByteIndex index, short tableLength) {

        LocalVariableTableAttribute.Item[] items = new LocalVariableTableAttribute.Item[tableLength];

        for (int i = 0; i < tableLength; i++) {
            LocalVariableTableAttribute.Item item = new LocalVariableTableAttribute.Item();

            item.setStartPc(Stream.readU2(content, index));
            item.setLength(Stream.readU2(content, index));
            item.setNameIndex(Stream.readU2(content, index));
            item.setDescriptorIndex(Stream.readU2(content, index));
            item.setIndex(Stream.readU2(content, index));

            items[i] = item;
        }

        return items;
    }

}
