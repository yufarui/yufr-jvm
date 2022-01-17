package indi.yufr.jvm.share.vm.oops;

import lombok.Data;


import java.util.ArrayList;
import java.util.List;

@Data
public class InstanceKlass {

    private byte[] magic;
    private byte[] minorVersion;
    private byte[] majorVersion;

    private ConstantPool constantPool;

    private int accessFlag;
    private int thisClass;
    private int superClass;

    private int interfacesLength;
    private List<InterfaceInfo> interfaceInfos;

    private int fieldsLength;
    private List<FieldInfo> fields;

    private int methodLength;
    private MethodInfo[] methods;

    private int attributeLength;
    private List<AttributeInfo> attributeInfos;

    public InstanceKlass() {
        magic = new byte[4];
        minorVersion = new byte[2];
        majorVersion = new byte[2];

        constantPool = new ConstantPool();
        constantPool.setPoolHolder(this);

        interfaceInfos = new ArrayList<>();
        fields = new ArrayList<>();
        attributeInfos = new ArrayList<>();
    }

    public void initMethodsContainer() {
        methods = new MethodInfo[methodLength];
    }
}
