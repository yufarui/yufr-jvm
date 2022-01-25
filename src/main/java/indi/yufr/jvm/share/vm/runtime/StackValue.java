package indi.yufr.jvm.share.vm.runtime;

import indi.yufr.jvm.share.vm.utilities.BasicType;
import lombok.Data;

@Data
public class StackValue {

    private BasicType basicType;

    private Object data;

}
