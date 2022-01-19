package indi.yufr.jvm.yuShare.vm.oops;

import indi.yufr.jvm.yuShare.constant.content.ConstantContent;
import indi.yufr.jvm.yuShare.vm.utilities.ConstantTag;
import lombok.Data;

/**
 * @date: 2022/1/17 15:52
 * @author: farui.yu
 */
@Data
public class ConstantPoolItem {

    protected ConstantTag tag;
    // 忽略了length属性
    private ConstantContent content;

    public ConstantPoolItem() {
    }

    public ConstantPoolItem(ConstantTag tag) {
        this.tag = tag;
    }

}
