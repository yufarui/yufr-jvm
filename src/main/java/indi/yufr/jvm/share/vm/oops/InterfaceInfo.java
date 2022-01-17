package indi.yufr.jvm.share.vm.oops;

import lombok.Data;

/**
 * Created By ziya
 * QQ: 3039277701
 * 2021/4/4
 */
@Data
public class InterfaceInfo {

    private int constantPoolIndex;

    private String interfaceName;

    public InterfaceInfo(int index, String name) {
        this.constantPoolIndex = index;
        this.interfaceName = name;
    }
}