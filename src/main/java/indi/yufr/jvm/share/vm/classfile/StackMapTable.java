package indi.yufr.jvm.share.vm.classfile;

import indi.yufr.jvm.share.vm.oops.AttributeInfo;
import lombok.Data;

/**
 * @Author: ziya
 * @Date: 2021/3/28 11:00
 */
@Data
public class StackMapTable extends AttributeInfo {

    private int attrNameIndex;
    private int attrLength;

}
