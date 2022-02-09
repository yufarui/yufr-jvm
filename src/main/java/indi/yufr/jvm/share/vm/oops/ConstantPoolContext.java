package indi.yufr.jvm.share.vm.oops;

import indi.yufr.jvm.share.constant.content.ConstantContent;
import lombok.extern.slf4j.Slf4j;

/**
 * @date: 2022/2/9 18:27
 * @author: farui.yu
 */
@Slf4j
public class ConstantPoolContext {

    public static ConstantContent getConstantContent(InstanceKlass klass, int index) {

        ConstantPoolItem[] constantPoolItems = klass.getConstantPoolItems();
        ConstantPoolItem constantPoolItem = constantPoolItems[index - 1];
        return constantPoolItem.getContent();
    }

}
