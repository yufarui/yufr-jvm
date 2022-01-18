package indi.yufr.jvm.yuShare.vm.oops;

import lombok.Data;

/**
 * @date: 2022/1/17 15:00
 * @author: farui.yu
 */
@Data
public class InstanceKlass {

    private int magic;
    private short minorVersion;
    private short majorVersion;

    private short constantPoolCount;
    private ConstantPoolItem[] constantPoolItems;

    private short accessFlag;
    private short thisClass;
    private short superClass;

    private short interfacesLength;
    private InterfaceInfo[] interfaceInfos;

    private short fieldsLength;
    private FieldInfo[] fields;

    private short methodLength;
    private MethodInfo[] methods;

    private short attributeLength;
    private AttributeInfo[] attributeInfos;

    public void initConstantPool() {
        this.constantPoolItems = new ConstantPoolItem[constantPoolCount];
    }

}
