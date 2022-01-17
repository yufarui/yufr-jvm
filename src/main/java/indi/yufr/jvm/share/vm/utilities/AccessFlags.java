package indi.yufr.jvm.share.vm.utilities;

import lombok.Data;

/**
 * Created By ziya
 * QQ: 3039277701
 * 2021/3/31
 */
@Data
public class AccessFlags {

    private int flag;

    public AccessFlags(int flag) {
        this.flag = flag;
    }

    public boolean isStatic() {
        return (flag & BasicType.JVM_ACC_STATIC) != 0;
    }
}
