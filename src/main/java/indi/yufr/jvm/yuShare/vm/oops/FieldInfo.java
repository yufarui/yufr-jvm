package indi.yufr.jvm.yuShare.vm.oops;

import indi.yufr.jvm.yuShare.vm.utilities.AccessFlags;
import lombok.Data;

/**
 * @date: 2022/1/17 18:00
 * @author: farui.yu
 */
@Data
public class FieldInfo {

    private AccessFlags accessFlags;
    private short nameIndex;
    private short descriptorIndex;
    private short attributesCount;
    private AttributeInfo[] attributes;

}
