package indi.yufr.jvm.share.vm.oops;

import indi.yufr.jvm.share.vm.oops.attribute.AttributeInfo;
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
        // 防止重复加载
        this.attributes = new AttributeInfo[attributesCount];
    }

}
