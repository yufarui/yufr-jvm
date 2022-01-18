package indi.yufr.jvm.yuShare.vm.oops;

import indi.yufr.jvm.share.vm.utilities.AccessFlags;
import lombok.Data;

@Data
public class MethodInfo {

    private AccessFlags accessFlags;

    private short nameIndex;
    private short descriptorIndex;
    private short attributesCount;

    private AttributeInfo[] attributes;

    // nameIndex 直接映射到 utf8
    public String methodName(InstanceKlass instanceKlass) {
        ConstantPoolItem[] allItems = instanceKlass.getConstantPoolItems();
        ConstantPoolItem item = allItems[nameIndex];
        return item.getAsString();
    }

    // descriptorIndex 同样映射到 utf8
    public String descriptorName(InstanceKlass instanceKlass) {
        ConstantPoolItem[] allItems = instanceKlass.getConstantPoolItems();
        ConstantPoolItem item = allItems[descriptorIndex];
        return item.getAsString();
    }

}
