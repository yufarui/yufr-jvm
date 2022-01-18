package indi.yufr.jvm.yuShare.vm.classFile;

import lombok.Data;

/**
 * @date: 2022/1/18 11:16
 * @author: farui.yu
 */
@Data
public class ByteIndex {

    private int index = 0;

    public void plus(int num) {
        this.index += num;
    }

}
