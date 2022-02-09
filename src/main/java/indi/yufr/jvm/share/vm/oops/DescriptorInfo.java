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

    // 针对数组 或 L对象的描述
    private String typeDesc;

    public void incArrayDimension() {
        arrayDimension++;
    }

    public static DescriptorInfo objectDesc(BasicType basicType, String typeDesc) {
        DescriptorInfo descriptorInfo = new DescriptorInfo();
        descriptorInfo.setType(basicType);
        descriptorInfo.setTypeDesc(typeDesc);
        return descriptorInfo;
    }

    public static DescriptorInfo simple(BasicType basicType) {

        DescriptorInfo descriptorInfo = new DescriptorInfo();
        descriptorInfo.setType(basicType);
        return descriptorInfo;
    }
}
