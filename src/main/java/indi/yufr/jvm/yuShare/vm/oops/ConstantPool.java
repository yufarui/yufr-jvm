package indi.yufr.jvm.yuShare.vm.oops;

import lombok.Data;

/**
 * @date: 2022/1/17 15:52
 * @author: farui.yu
 */
@Data
public class ConstantPool {

    private InstanceKlass poolHolder;
    private int length;
    private ConstantPoolItem[] items;

}
