package indi.yufr.jvm.share.constant.content;

import indi.yufr.jvm.share.vm.oops.ConstantPoolItem;
import indi.yufr.jvm.share.vm.oops.InstanceKlass;
import lombok.Builder;
import lombok.Data;

/**
 * @date: 2022/1/19 9:51
 * @author: farui.yu
 */
@Data
@Builder
public class MetaRefInfo implements ConstantContent {

    private short classIndex;
    private short nameAndTypeIndex;

    public String getClassName(InstanceKlass klass) {
        ConstantPoolItem[] constantPoolItems = klass.getConstantPoolItems();
        ConstantPoolItem constantPoolItem = constantPoolItems[classIndex - 1];
        SingleIndex classNameIndex = (SingleIndex) constantPoolItem.getContent();
        Utf8Info utf8Info = classNameIndex.getName(klass);
        return utf8Info.getContent();
    }

    public String getName(InstanceKlass klass) {
        ConstantPoolItem[] constantPoolItems = klass.getConstantPoolItems();
        ConstantPoolItem constantPoolItem = constantPoolItems[nameAndTypeIndex - 1];
        NameAndTypeInfo nameAndTypeInfo = (NameAndTypeInfo) constantPoolItem.getContent();
        return nameAndTypeInfo.getName(klass);
    }

    public String getDescriptionName(InstanceKlass klass) {
        ConstantPoolItem[] constantPoolItems = klass.getConstantPoolItems();
        ConstantPoolItem constantPoolItem = constantPoolItems[nameAndTypeIndex - 1];
        NameAndTypeInfo nameAndTypeInfo = (NameAndTypeInfo) constantPoolItem.getContent();
        return nameAndTypeInfo.getDescription(klass);
    }

}
