package indi.yufr.jvm.yuShare.attribute;

import indi.yufr.jvm.yuShare.vm.classFile.ByteIndex;
import indi.yufr.jvm.yuShare.vm.oops.InstanceKlass;
import indi.yufr.jvm.yuShare.vm.oops.attribute.AttributeInfo;


/**
 * @date: 2022/1/19 11:01
 * @author: farui.yu
 */
public abstract class AttributeInfoExecutor {

    public abstract boolean canSupport(String attributeName);

    /**
     * @param content
     * @param index
     * @param klass 部分属性解析时,需要查询常量池,故引入klass
     * @return
     */
    public abstract AttributeInfo doParse(byte[] content, ByteIndex index, InstanceKlass klass);

}
