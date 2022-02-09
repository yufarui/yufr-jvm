package indi.yufr.jvm.share.vm.runtime;

import indi.yufr.jvm.share.vm.utilities.BasicType;
import lombok.Data;

@Data
public class StackValue {

    private BasicType basicType;

    private Object data;

    public StackValue() {
    }

    public StackValue(BasicType basicType, Object data) {
        this.basicType = basicType;
        this.data = data;
    }
}
