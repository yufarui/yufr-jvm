package indi.yufr.jvm.share.constant.content;

import indi.yufr.jvm.share.vm.oops.ConstantPoolItem;
import indi.yufr.jvm.share.vm.oops.InstanceKlass;
import lombok.Builder;
import lombok.Data;

/**
 * @date: 2022/1/19 9:52
 * @author: farui.yu
 */
@Data
@Builder
public class SingleIndex implements ConstantContent {

    // 指向 utf8
    private short index;

    public Utf8Info getName(InstanceKlass klass) {
        ConstantPoolItem[] constantPoolItems = klass.getConstantPoolItems();
        ConstantPoolItem constantPoolItem = constantPoolItems[index - 1];
        return (Utf8Info) constantPoolItem.getContent();
    }
}
