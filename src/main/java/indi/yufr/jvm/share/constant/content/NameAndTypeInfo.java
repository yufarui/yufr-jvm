package indi.yufr.jvm.share.constant.content;

import indi.yufr.jvm.share.vm.oops.ConstantPoolItem;
import indi.yufr.jvm.share.vm.oops.InstanceKlass;
import lombok.Builder;
import lombok.Data;

/**
 * @date: 2022/1/19 16:12
 * @author: farui.yu
 */
@Data
@Builder
public class NameAndTypeInfo implements ConstantContent {

    private short nameIndex;
    private short descriptionIndex;

    public String getName(InstanceKlass klass) {
        ConstantPoolItem[] constantPoolItems = klass.getConstantPoolItems();
        ConstantPoolItem constantPoolItem = constantPoolItems[nameIndex - 1];
        Utf8Info utf8Info = (Utf8Info) constantPoolItem.getContent();
        return utf8Info.getContent();
    }

    public String getDescription(InstanceKlass klass) {
        ConstantPoolItem[] constantPoolItems = klass.getConstantPoolItems();
        ConstantPoolItem constantPoolItem = constantPoolItems[descriptionIndex - 1];
        Utf8Info utf8Info = (Utf8Info) constantPoolItem.getContent();
        return utf8Info.getContent();
    }

}
