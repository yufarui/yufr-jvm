package indi.yufr.jvm.share.vm.oops.attribute;

import lombok.Data;

/**
 * @date: 2022/1/18 10:20
 * @author: farui.yu
 */
@Data
public class LineNumberTableAttribute extends AttributeInfo {

    private short tableLength;

    private Item[] table;

    @Data
    public static class Item {
        private short startPc;
        private short lineNumber;
    }
}
