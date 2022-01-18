package indi.yufr.jvm.yuShare.vm.oops;

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
        private int startPc;
        private int lineNumber;
    }
}
