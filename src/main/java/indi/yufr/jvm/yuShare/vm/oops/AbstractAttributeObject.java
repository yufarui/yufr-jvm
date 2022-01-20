package indi.yufr.jvm.yuShare.vm.oops;

import indi.yufr.jvm.yuShare.vm.oops.attribute.AttributeInfo;
import lombok.Data;

/**
 * @date: 2022/1/19 14:46
 * @author: farui.yu
 */
@Data
public abstract class AbstractAttributeObject implements AttributeAble {

    private short attributesCount;
    private AttributeInfo[] attributes;

    public void initAttributes() {
        this.attributes = new AttributeInfo[attributesCount];
    }

}