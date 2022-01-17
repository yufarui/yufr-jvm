package indi.yufr.jvm.share.vm.oops;

import lombok.Data;

/**
 * @Author: ziya
 * @Date: 2021/3/7 10:23
 */
@Data
public class LineNumberTable extends AttributeInfo {

    private int tableLength;
    private Item[] table;

    public void initTable() {
        table = new Item[tableLength];
    }

    @Data
    public class Item {
        private int startPc;
        private int lineNumber;
    }
}
