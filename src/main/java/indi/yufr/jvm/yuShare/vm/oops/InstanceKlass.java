package indi.yufr.jvm.yuShare.vm.oops;

import lombok.Data;

/**
 * @date: 2022/1/17 15:00
 * @author: farui.yu
 */
@Data
public class InstanceKlass extends AbstractAttributeObject {

    private int magic;
    private short minorVersion;
    private short majorVersion;

    private short constantPoolCount;
    private ConstantPoolItem[] constantPoolItems;

    private short accessFlags;
    private short thisClass;
    private short superClass;

    private short interfacesLength;
    private InterfaceInfo[] interfaceInfos;

    private short fieldsLength;
    private FieldInfo[] fields;

    private short methodLength;
    private MethodInfo[] methods;

    public void initConstantPool() {
        this.constantPoolItems = new ConstantPoolItem[constantPoolCount - 1];
    }

    public void initInterfaceInfos() {
        this.interfaceInfos = new InterfaceInfo[interfacesLength];
    }

    public void initFieldInfos() {
        this.fields = new FieldInfo[fieldsLength];
    }

    public void initMethods() {
        this.methods = new MethodInfo[methodLength];
    }

}
