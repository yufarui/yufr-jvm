package indi.yufr.jvm.share.vm.oops.attribute;

import lombok.Data;

/**
 * @date: 2022/1/18 10:22
 * @author: farui.yu
 */
@Data
public class LocalVariableTableAttribute extends AttributeInfo {

    private short tableLength;

    private Item[] table;

    @Data
    public static class Item {
        private int startPc;
        private int length;
        private int nameIndex;
        private int descriptorIndex;
        private int index;
    }

}
