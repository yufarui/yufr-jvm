package indi.yufr.jvm.yuShare.vm.oops;

/**
 * @date: 2022/1/17 16:54
 * @author: farui.yu
 */
public class InterfaceInfo {

    private short index;

    public String getName(InstanceKlass instanceKlass) {

        ConstantPoolItem[] allItems = instanceKlass.getConstantPoolItems();
        ConstantPoolItem item = allItems[index];

        short classInfoIndex = item.getAsSingleIndex();
        ConstantPoolItem utf8Info = allItems[classInfoIndex];
        return utf8Info.getAsString();
    }
}
