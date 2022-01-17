package indi.yufr.jvm.yuShare.vm.oops;

import indi.yufr.jvm.share.vm.oops.CodeAttributeInfo;
import lombok.Data;

/**
 * @date: 2022/1/17 18:00
 * @author: farui.yu
 */
@Data
public class FieldInfo {

    private int accessFlags;
    private int nameIndex;
    private int descriptorIndex;
    private int attributesCount;

    private CodeAttributeInfo[] attributes;
}
