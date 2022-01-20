package indi.yufr.jvm.yuShare.byteCode;

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

    private Opcode opcode;

    // 操作数对应常量池中index
    // 统一使用short 表示index, 但是此处的index 只读取一位Stream.readU1
    private byte[] content;

    public ByteCode() {
    }

    public ByteCode(Opcode opcode) {
        this.opcode = opcode;
    }
}
