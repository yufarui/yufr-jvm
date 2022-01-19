package indi.yufr.jvm.yuShare.vm.oops;

import indi.yufr.jvm.yuShare.constant.content.SingleIndex;
import indi.yufr.jvm.yuShare.constant.content.Utf8Info;

/**
 * @date: 2022/1/17 16:54
 * @author: farui.yu
 */
public class InterfaceInfo {

    private short index;

    public String getName(InstanceKlass instanceKlass) {

        ConstantPoolItem[] allItems = instanceKlass.getConstantPoolItems();
        ConstantPoolItem item = allItems[index];

        short classInfoIndex = ((SingleIndex) item.getContent()).getIndex();
        ConstantPoolItem utf8Info = allItems[classInfoIndex];
        return ((Utf8Info) utf8Info.getContent()).getContent();
    }
}
