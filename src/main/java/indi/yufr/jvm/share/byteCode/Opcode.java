package indi.yufr.jvm.share.byteCode;

import lombok.Getter;

/**
 * 字节码操作的助记符
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

    DCONST_0((byte) 0x0e, -1),
    DCONST_1((byte) 0x0f, -1),

    BIPUSH((byte) 0x10, -1),
    SIPUSH((byte) 0x11, -1),

    LDC((byte) 0x12, 1),
    LDC_W((byte) 0x13, -1),
    LDC2_W((byte) 0x14, -1),

    ILOAD((byte) 0x15, -1),

    ILOAD_0((byte) 0x1a, -1),
    ILOAD_1((byte) 0x1b, -1),
    ILOAD_2((byte) 0x1c, -1),
    ILOAD_3((byte) 0x1d, -1),

    FLOAD_0((byte) 0x22, -1),
    FLOAD_1((byte) 0x23, -1),
    FLOAD_2((byte) 0x24, -1),
    FLOAD_3((byte) 0x25, -1),

    DLOAD_0((byte) 0x26, -1),
    DLOAD_1((byte) 0x27, -1),
    DLOAD_2((byte) 0x28, -1),
    DLOAD_3((byte) 0x29, -1),

    ALOAD_0((byte) 0x2a, 0),
    ALOAD_1((byte) 0x2b, 0),
    ALOAD_2((byte) 0x2c, 0),
    ALOAD_3((byte) 0x2d, 0),

    IALOAD((byte) 0x2e, -1),
    LALOAD((byte) 0x2f, -1),
    FALOAD((byte) 0x30, -1),
    DALOAD((byte) 0x31, -1),
    AALOAD((byte) 0x32, -1),
    BALOAD((byte) 0x33, -1),
    CALOAD((byte) 0x34, -1),
    SALOAD((byte) 0x35, -1),

    DSTORE((byte) 0x39, -1),

    ISTORE_0((byte) 0x3b, -1),
    ISTORE_1((byte) 0x3c, -1),
    ISTORE_2((byte) 0x3d, -1),
    ISTORE_3((byte) 0x3e, -1),

    FSTORE_0((byte) 0x43, -1),
    FSTORE_1((byte) 0x44, -1),
    FSTORE_2((byte) 0x45, -1),
    FSTORE_3((byte) 0x46, -1),

    DSTORE_0((byte) 0x47, -1),
    DSTORE_1((byte) 0x48, -1),
    DSTORE_2((byte) 0x49, -1),
    DSTORE_3((byte) 0x4a, -1),

    ASTORE_0((byte) 0x4b, -1),
    ASTORE_1((byte) 0x4c, -1),
    ASTORE_2((byte) 0x4d, -1),
    ASTORE_3((byte) 0x4e, -1),
    IASTORE((byte) 0x4f, -1),
    LASTORE((byte) 0x50, -1),
    FASTORE((byte) 0x51, -1),
    DASTORE((byte) 0x52, -1),
    AASTORE((byte) 0x53, -1),

    DUP((byte) 0x59, -1),

    IADD((byte) 0x60, -1),
    LADD((byte) 0x61, -1),
    FADD((byte) 0x62, -1),
    DADD((byte) 0x63, -1),

    IINC((byte) 0x84, -1),

    /**
     * 将栈顶short类型强制转为double类型，并将结果压入栈
     */
    I2D((byte) 0x87, -1),

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

        throw new RuntimeException("无法解析tag");
    }

}
