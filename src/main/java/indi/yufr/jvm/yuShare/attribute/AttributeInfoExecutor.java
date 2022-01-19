package indi.yufr.jvm.yuShare.attribute;

import indi.yufr.jvm.yuShare.vm.classFile.ByteIndex;
import indi.yufr.jvm.yuShare.vm.oops.attribute.AttributeInfo;


/**
 * @date: 2022/1/19 11:01
 * @author: farui.yu
 */
public abstract class AttributeInfoExecutor {

    public abstract boolean canSupport(String attributeName);

    public abstract AttributeInfo doParseInfo(byte[] content, ByteIndex index);

}
