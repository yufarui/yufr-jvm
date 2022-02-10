package indi.yufr.jvm.share.tools;

import indi.yufr.jvm.share.vm.classFile.ByteIndex;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Stream {

    public static void skip(ByteIndex byteIndex, int length) {
        byteIndex.plus(length);
    }

    public static byte[] readOnly(byte[] content, int startIndex, int length) {
        return readByte(content, startIndex, length);
    }

    public static byte[] readBytes(byte[] content, ByteIndex byteIndex, int length) {

        // 自适应方法
        if (length <= 0) {
            return new byte[0];
        }

        byte[] bytes = readByte(content, byteIndex.getIndex(), length);
        byteIndex.plus(length);
        return bytes;
    }

    public static int readU4(byte[] content, ByteIndex byteIndex) {
        int u4Simple = readU4Simple(content, byteIndex.getIndex());
        byteIndex.plus(4);
        return u4Simple;
    }

    public static short readU2(byte[] content, ByteIndex byteIndex) {
        short u2Simple = readU2Simple(content, byteIndex.getIndex());
        byteIndex.plus(2);
        return u2Simple;
    }

    public static byte readU1(byte[] content, ByteIndex byteIndex) {
        byte u1Simple = readU1Simple(content, byteIndex.getIndex());
        byteIndex.plus(1);
        return u1Simple;
    }

    private static byte readU1Simple(byte[] content, int from) {
        byte[] bytes = readByte(content, from, 1);
        return (byte) readInOrder(bytes);
    }

    private static short readU2Simple(byte[] content, int from) {
        byte[] bytes = readByte(content, from, 2);
        return (short) readInOrder(bytes);
    }

    private static int readU4Simple(byte[] content, int from) {
        byte[] bytes = readByte(content, from, 4);
        return (int) readInOrder(bytes);
    }

    private static byte[] readByte(byte[] content, int from, int length) {

        byte[] result = new byte[length];

        System.arraycopy(content, from, result, 0, length);

        return result;
    }

    private static Object readInOrder(byte[] bytes) {

        if (bytes == null || bytes.length == 0) {
            throw new RuntimeException("数组长度错误");
        }

        int length = bytes.length;

        ByteBuffer buffer;
        switch (length) {
            case 1:
                return bytes[0];
            case 2:
                buffer = ByteBuffer.wrap(bytes, 0, 2);
                buffer.order(ByteOrder.BIG_ENDIAN);
                return buffer.getShort();
            case 4:
                buffer = ByteBuffer.wrap(bytes, 0, 4);
                buffer.order(ByteOrder.BIG_ENDIAN);
                return buffer.getInt();
        }

        throw new RuntimeException("数组长度错误");
    }

    // 大端序 和 小端序的测试
    public static void main(String[] args) {
        byte[] bytes = new byte[4];
        bytes[3] = (byte) 0xca;
        bytes[2] = (byte) 0xfe;
        bytes[1] = (byte) 0xba;
        bytes[0] = (byte) 0xbe;
        int x = 0xca_fe_ba_be;
        int y = 0xbe_ba_fe_ca;
        System.out.println(x);
        System.out.println(y);
        System.out.println(readInOrder(bytes));

        System.out.println(0xca);
        System.out.println((byte) 0xca);
    }

}
