package indi.yufr.jvm.share.vm.oops;

import indi.yufr.jvm.share.vm.oops.attribute.AttributeInfo;

/**
 * @date: 2022/1/19 14:35
 * @author: farui.yu
 */
public interface AttributeAble {

    short getAttributesCount();

    AttributeInfo[] getAttributes();

    void initAttributes();

}
