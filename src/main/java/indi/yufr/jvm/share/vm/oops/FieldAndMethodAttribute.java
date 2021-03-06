package indi.yufr.jvm.share.vm.oops;

import indi.yufr.jvm.share.constant.content.Utf8Info;
import lombok.Data;

/**
 * @date: 2022/1/19 14:19
 * @author: farui.yu
 */
@Data
public class FieldAndMethodAttribute extends AbstractAttributeObject {

    private InstanceKlass belongKlass;

    private short accessFlags;
    private short nameIndex;
    private short descriptorIndex;

    // nameIndex 直接映射到 utf8
    public String name() {
        ConstantPoolItem[] allItems = belongKlass.getConstantPoolItems();
        ConstantPoolItem item = allItems[nameIndex - 1];
        return ((Utf8Info) item.getContent()).getContent();
    }

    // descriptorIndex 同样映射到 utf8
    public String descriptorName() {
        ConstantPoolItem[] allItems = belongKlass.getConstantPoolItems();
        ConstantPoolItem item = allItems[descriptorIndex - 1];
        return ((Utf8Info) item.getContent()).getContent();
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "accessFlags=" + accessFlags +
                ", nameIndex=" + nameIndex +
                ", descriptorIndex=" + descriptorIndex +
                '}';
    }
}
