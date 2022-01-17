package indi.yufr.jvm.share.vm.oops;

import indi.yufr.jvm.share.vm.classfile.DescriptorStream2;
import indi.yufr.jvm.share.vm.utilities.AccessFlags;
import lombok.Data;


@Data
public class MethodInfo {

    private InstanceKlass belongKlass;

    private AccessFlags accessFlags;

    private int nameIndex;
    private int descriptorIndex;
    private int attributesCount;

    private CodeAttributeInfo[] attributes;

    private String methodName;
    private DescriptorStream2 descriptor;

    public void initAttributeContainer() {
        attributes = new CodeAttributeInfo[attributesCount];
    }

    @Override
    public String toString() {
        return "MethodInfo{ }";
    }
}
