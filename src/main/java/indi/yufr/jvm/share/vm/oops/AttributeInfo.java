package indi.yufr.jvm.share.vm.oops;

import lombok.Data;

@Data
public class AttributeInfo {

    private int attrNameIndex;
    private int attrLength;

    // 用于存储class的attribute
    private byte[] container;

    public void initContainer() {
        container = new byte[attrLength];
    }

}
