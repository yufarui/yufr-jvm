package indi.yufr.jvm.share.byteCode;

import lombok.Getter;

/**
 * 字节码操作的助记符
 * <p>
 * idea中快手替换的正则记录
 * public static final int (.*) = (.*);\s+// (.*)
 * $1\(\(byte\) $3, 0\),
 *
 * @date: 2022/1/20 9:28
 * @author: farui.yu
 */
public enum Opcode {

    NOP((byte) 0x00, -1),
    ACONST_NULL((byte) 0x01, -1),
    ICONST_0((byte) 0x03, 0),
    ICONST_1((byte) 0x04, 0),
    ICONST_2((byte) 0x05, 0),
    ICONST_3((byte) 0x06, 0),
    ICONST_4((byte) 0x07, 0),
    ICONST_5((byte) 0x08, 0),

    LCONST_0((byte) 0x09, -1),
    LCONST_1((byte) 0x0a, -1),

    FCONST_0((byte) 0x0b, 0),
    FCONST_1((byte) 0x0c, 0),
    FCONST_2((byte) 0x0d, 0),

    DCONST_0((byte) 0x0e, -1),
    DCONST_1((byte) 0x0f, -1),

    BIPUSH((byte) 0x10, 1),
    SIPUSH((byte) 0x11, 1),

    LDC((byte) 0x12, 1),
    LDC_W((byte) 0x13, 2),
    LDC2_W((byte) 0x14, 2),

    ILOAD((byte) 0x15, 1),
    LLOAD((byte) 0x16, 1),
    FLOAD((byte) 0x17, 1),
    DLOAD((byte) 0x18, 1),
    ALOAD((byte) 0x19, 1),

    ILOAD_0((byte) 0x1a, 0),
    ILOAD_1((byte) 0x1b, 0),
    ILOAD_2((byte) 0x1c, 0),
    ILOAD_3((byte) 0x1d, 0),

    LLOAD_0((byte) 0x1e, 0),
    LLOAD_1((byte) 0x1f, 0),
    LLOAD_2((byte) 0x20, 0),
    LLOAD_3((byte) 0x21, 0),

    FLOAD_0((byte) 0x22, 0),
    FLOAD_1((byte) 0x23, 0),
    FLOAD_2((byte) 0x24, 0),
    FLOAD_3((byte) 0x25, 0),

    DLOAD_0((byte) 0x26, 0),
    DLOAD_1((byte) 0x27, 0),
    DLOAD_2((byte) 0x28, 0),
    DLOAD_3((byte) 0x29, 0),

    ALOAD_0((byte) 0x2a, 0),
    ALOAD_1((byte) 0x2b, 0),
    ALOAD_2((byte) 0x2c, 0),
    ALOAD_3((byte) 0x2d, 0),

    IALOAD((byte) 0x2e, 1),
    LALOAD((byte) 0x2f, 1),
    FALOAD((byte) 0x30, 1),
    DALOAD((byte) 0x31, 1),
    AALOAD((byte) 0x32, 1),
    BALOAD((byte) 0x33, 1),
    CALOAD((byte) 0x34, 1),
    SALOAD((byte) 0x35, 1),

    ISTORE((byte) 0x36, 1),
    LSTORE((byte) 0x37, 1),
    FSTORE((byte) 0x38, 1),
    DSTORE((byte) 0x39, 1),
    ASTORE((byte) 0x3a, 1),

    ISTORE_0((byte) 0x3b, 0),
    ISTORE_1((byte) 0x3c, 0),
    ISTORE_2((byte) 0x3d, 0),
    ISTORE_3((byte) 0x3e, 0),

    LSTORE_0((byte) 0x3f, 0),
    LSTORE_1((byte) 0x40, 0),
    LSTORE_2((byte) 0x41, 0),
    LSTORE_3((byte) 0x42, 0),

    FSTORE_0((byte) 0x43, 0),
    FSTORE_1((byte) 0x44, 0),
    FSTORE_2((byte) 0x45, 0),
    FSTORE_3((byte) 0x46, 0),

    DSTORE_0((byte) 0x47, 0),
    DSTORE_1((byte) 0x48, 0),
    DSTORE_2((byte) 0x49, 0),
    DSTORE_3((byte) 0x4a, 0),

    ASTORE_0((byte) 0x4b, 0),
    ASTORE_1((byte) 0x4c, 0),
    ASTORE_2((byte) 0x4d, 0),
    ASTORE_3((byte) 0x4e, 0),
    IASTORE((byte) 0x4f, 0),
    LASTORE((byte) 0x50, 0),
    FASTORE((byte) 0x51, 0),
    DASTORE((byte) 0x52, 0),
    AASTORE((byte) 0x53, 0),

    DUP((byte) 0x59, -1),

    IADD((byte) 0x60, 0),
    LADD((byte) 0x61, 0),
    FADD((byte) 0x62, 0),
    DADD((byte) 0x63, 0),

    ISUB((byte) 0x64, 0),
    LSUB((byte) 0x65, 0),
    FSUB((byte) 0x66, 0),
    DSUB((byte) 0x67, 0),

    IMUL((byte) 0x68, 0),
    LMUL((byte) 0x69, 0),
    FMUL((byte) 0x6a, 0),
    DMUL((byte) 0x6b, 0),

    IDIV((byte) 0x6c, 0),
    LDIV((byte) 0x6d, 0),
    FDIV((byte) 0x6e, 0),
    DDIV((byte) 0x6f, 0),

    IREM((byte) 0x70, 0),
    LREM((byte) 0x71, 0),
    FREM((byte) 0x72, 0),
    DREM((byte) 0x73, 0),
    
    IINC((byte) 0x84, 2),

    /**
     * 类型转换
     */
    I2D((byte) 0x87, 0),
    I2L((byte) 0x85, 0),
    I2F((byte) 0x86, 0),
    L2I((byte) 0x88, 0),
    L2F((byte) 0x89, 0),
    L2D((byte) 0x8a, 0),
    F2I((byte) 0x8b, 0),
    F2L((byte) 0x8c, 0),
    F2D((byte) 0x8d, 0),
    D2I((byte) 0x8e, 0),
    D2L((byte) 0x8f, 0),
    D2F((byte) 0x90, 0),
    I2B((byte) 0x91, 0),
    I2C((byte) 0x92, 0),
    I2S((byte) 0x93, 0),

    LCMP((byte) 0x94, 0),
    FCMPL((byte) 0x95, 0),
    FCMPG((byte) 0x96, 0),
    DCMPL((byte) 0x97, 0),
    DCMPG((byte) 0x98, 0),
    
    IFEQ((byte) 0x99, 0),
    IFNE((byte) 0x9a, 0),
    IFLT((byte) 0x9b, 0),
    IFGE((byte) 0x9c, 0),
    IFGT((byte) 0x9d, 0),
    IFLE((byte) 0x9e, 0),

    IF_ICMPEQ((byte) 0x9f, -1),
    IF_ICMPNE((byte) 0xa0, -1),
    IF_ICMPLT((byte) 0xa1, -1),
    IF_ICMPGE((byte) 0xa2, -1),
    IF_ICMPGT((byte) 0xa3, -1),
    IF_ICMPLE((byte) 0xa4, -1),
    IF_ACMPEQ((byte) 0xa5, -1),
    IF_ACMPNE((byte) 0xa6, -1),

    GOTO((byte) 0xa7, -1),

    IRETURN((byte) 0xac, -1),

    ARETURN((byte) 0xb0, -1),
    RETURN((byte) 0xb1, 0),

    GETSTATIC((byte) 0xb2, 2),
    PUTSTATIC((byte) 0xb3, -1),
    GETFIELD((byte) 0xb4, -1),
    PUTFIELD((byte) 0xb5, -1),

    INVOKEVIRTUAL((byte) 0xb6, 2),
    INVOKESPECIAL((byte) 0xb7, 2),
    INVOKESTATIC((byte) 0xb8, -1),
    INVOKEshortERFACE((byte) 0xb9, -1),
    INVOKEDYNAMIC((byte) 0xba, -1),

    NEW((byte) 0xbb, -1),
    NEWARRAY((byte) 0xbc, -1),
    ANEWARRAY((byte) 0xbd, -1),
    ARRAYLENGTH((byte) 0xbe, -1),

    ATHROW((byte) 0xbf, -1),
    CHECKCAST((byte) 0xc0, -1),
    ;

    @Getter
    private byte opcode;

    /**
     * 操作数的个数
     * -1 表示个数未知,作者还未更新
     */
    @Getter
    private int opNum;

    Opcode(byte opcode, int opNum) {
        this.opcode = opcode;
        this.opNum = opNum;
    }

    public static Opcode of(byte opcode) {

        Opcode[] values = Opcode.values();
        for (int i = 0; i < values.length; i++) {
            Opcode value = values[i];
            if (value.opcode == opcode) {
                return value;
            }
        }

        throw new RuntimeException("无法解析opcode" + opcode);
    }

}
