package indi.yufr.jvm.yuShare.vm.oops.attribute;

import lombok.Data;

@Data
public abstract class AttributeInfo {

    protected short attributeNameIndex;
    protected int attributeLength;

    protected String attributeName;
}
