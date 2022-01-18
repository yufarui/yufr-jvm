package indi.yufr.jvm.yuShare.vm.utilities;

import lombok.Getter;

public enum ConstantTag {

    // These replicated from the VM to save space
    JVM_CONSTANT_Utf8(1),
    JVM_CONSTANT_Unicode(2), // unused
    JVM_CONSTANT_Integer(3),
    JVM_CONSTANT_Float(4),
    JVM_CONSTANT_Long(5),
    JVM_CONSTANT_Double(6),
    JVM_CONSTANT_Class(7),
    JVM_CONSTANT_String(8),
    JVM_CONSTANT_Fieldref(9),
    JVM_CONSTANT_Methodref(10),
    JVM_CONSTANT_InterfaceMethodref(11),
    JVM_CONSTANT_NameAndType(12),
    JVM_CONSTANT_MethodHandle(15),  // JSR 292
    JVM_CONSTANT_MethodType(16),  // JSR 292
    // static final int JVM_CONSTANT_(unused)(17), // JSR 292 early drafts only
    JVM_CONSTANT_InvokeDynamic(18),  // JSR 292
    ;

    @Getter
    private byte tag;

    ConstantTag(int tag) {
        this.tag = (byte) tag;
    }

    public static BasicType basicType(ConstantTag tag) {
        switch (tag) {
            case JVM_CONSTANT_Integer:
                return BasicType.T_INT;
            case JVM_CONSTANT_Float:
                return BasicType.T_FLOAT;
            case JVM_CONSTANT_Long:
                return BasicType.T_LONG;
            case JVM_CONSTANT_Double:
                return BasicType.T_DOUBLE;

            case JVM_CONSTANT_Class:
            case JVM_CONSTANT_String:
            case JVM_CONSTANT_MethodHandle:
            case JVM_CONSTANT_MethodType:
                return BasicType.T_OBJECT;
            default:
                throw new InternalError("unexpected tag: " + tag);
        }
    }

    public static ConstantTag of(byte tag) {

        ConstantTag[] values = ConstantTag.values();
        for (int i = 0; i < values.length; i++) {
            ConstantTag value = values[i];
            if (value.tag == tag) {
                return value;
            }
        }

        throw new RuntimeException("无法解析tag");
    }




}
