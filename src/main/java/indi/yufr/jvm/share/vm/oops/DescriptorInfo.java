package indi.yufr.jvm.share.vm.oops;

import indi.yufr.jvm.share.vm.utilities.BasicType;
import lombok.Data;

@Data
public class DescriptorInfo {

//    暂未开启的属性
//    private boolean isResolved = false;

    // 类型
    private BasicType type;

    // 针对数组类型,添加的额外维度
    private int arrayDimension;

    public void incArrayDimension() {
        arrayDimension++;
    }

}
