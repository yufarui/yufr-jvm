package indi.yufr.jvm.share.vm.oops;

import lombok.Data;

/**
 * @Author: ziya
 * @Date: 2021/3/6 16:56
 */
@Data
public class FieldInfo {

    private int accessFlags;
    private int nameIndex;
    private int descriptorIndex;
    private int attributesCount;

    private CodeAttributeInfo[] attributes;

}
