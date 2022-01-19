package indi.yufr.jvm.yuShare.vm.oops;

import indi.yufr.jvm.yuShare.constant.content.Utf8Info;
import lombok.Data;

/**
 * @date: 2022/1/19 14:19
 * @author: farui.yu
 */
@Data
public class FieldAndMethodAttribute extends AbstractAttributeObject {

    private short accessFlags;
    private short nameIndex;
    private short descriptorIndex;

    // nameIndex 直接映射到 utf8
    public String methodName(InstanceKlass instanceKlass) {
        ConstantPoolItem[] allItems = instanceKlass.getConstantPoolItems();
        ConstantPoolItem item = allItems[nameIndex - 1];
        return ((Utf8Info) item.getContent()).getContent();
    }

    // descriptorIndex 同样映射到 utf8
    public String descriptorName(InstanceKlass instanceKlass) {
        ConstantPoolItem[] allItems = instanceKlass.getConstantPoolItems();
        ConstantPoolItem item = allItems[descriptorIndex - 1];
        return ((Utf8Info) item.getContent()).getContent();
    }

}
