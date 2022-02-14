package indi.yufr.jvm.share.byteCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @date: 2022/1/20 9:39
 * @author: farui.yu
 */
@Data
@Builder
@AllArgsConstructor
public class ByteCode {

    // 不开启的属性
    // private CodeAttributeInfo belongAttribute;

    // 当前byteCode所在游标
    private int cursor;
    private Opcode opcode;

    // 指令 对应的实际 操作数
    private byte[] content;

    public ByteCode() {
    }

    public ByteCode(int cursor, Opcode opcode) {
        this.cursor = cursor;
        this.opcode = opcode;
    }
}
