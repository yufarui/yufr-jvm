package indi.yufr.jvm.yuShare.attribute;


import indi.yufr.jvm.yuShare.constant.content.Utf8Info;
import indi.yufr.jvm.yuShare.vm.oops.ConstantPoolItem;
import indi.yufr.jvm.yuShare.vm.oops.InstanceKlass;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

/**
 * @date: 2022/1/19 11:05
 * @author: farui.yu
 */
@Slf4j
public class AttributeInfoExecutorContext {

    private static List<AttributeInfoExecutor> attrExecutors;

    static {
        attrExecutors = Arrays.asList(
                new CodeAttributeExecutor(),
                new ConstantValueAttributeExecutor(),
                new LineNumberTableAttributeExecutor(),
                new LocalVariableTableAttributeExecutor(),
                new SourceFileAttributeExecutor()
        );
    }

    public static String parseAttrName(short attrNameIndex, InstanceKlass klass) {
        ConstantPoolItem[] constantPoolItems = klass.getConstantPoolItems();
        ConstantPoolItem constantPoolItem = constantPoolItems[attrNameIndex - 1];
        return ((Utf8Info) constantPoolItem.getContent()).getContent();
    }

    public static AttributeInfoExecutor findExecutor(short attrNameIndex, InstanceKlass klass) {
        String attrName = parseAttrName(attrNameIndex, klass);

        for (AttributeInfoExecutor executor : attrExecutors) {

            if (executor.canSupport(attrName)) {
                return executor;
            }
        }

        log.warn("无法支持的属性解析[{}]", attrName);
        return null;
    }

}
