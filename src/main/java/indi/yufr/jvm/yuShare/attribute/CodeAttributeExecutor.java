package indi.yufr.jvm.yuShare.attribute;


import indi.yufr.jvm.yuShare.byteCode.ByteCode;
import indi.yufr.jvm.yuShare.byteCode.ByteCodeExecutor;
import indi.yufr.jvm.yuShare.byteCode.ByteCodeExecutorContext;
import indi.yufr.jvm.yuShare.byteCode.Opcode;
import indi.yufr.jvm.yuShare.tools.Stream;
import indi.yufr.jvm.yuShare.vm.classFile.ByteIndex;
import indi.yufr.jvm.yuShare.vm.classFile.ClassFileParser;
import indi.yufr.jvm.yuShare.vm.oops.ExceptionTableElement;
import indi.yufr.jvm.yuShare.vm.oops.InstanceKlass;
import indi.yufr.jvm.yuShare.vm.oops.attribute.AttributeInfo;
import indi.yufr.jvm.yuShare.vm.oops.attribute.CodeAttributeInfo;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static indi.yufr.jvm.yuShare.tools.Stream.readU2;

/**
 * @date: 2022/1/19 15:05
 * @author: farui.yu
 */
public class CodeAttributeExecutor extends AttributeInfoExecutor {
    @Override
    public boolean canSupport(String attributeName) {
        if (StringUtils.equals(attributeName, "Code")) {
            return true;
        }

        return false;
    }

    @Override
    public AttributeInfo doParse(byte[] content, ByteIndex index, InstanceKlass klass) {

        int length = Stream.readU4(content, index);

        CodeAttributeInfo attribute = new CodeAttributeInfo();
        attribute.setAttributeName("Code");
        attribute.setAttributeLength(length);

        attribute.setMaxStack(Stream.readU2(content, index));
        attribute.setMaxLocals(Stream.readU2(content, index));
        attribute.setCodeLength(Stream.readU4(content, index));

        byte[] codes = Stream.readBytes(content, index, attribute.getCodeLength());

        // 解析字节码信息
        List<ByteCode> byteCodes = parseByteCode(codes);
        attribute.setCodes(byteCodes);

        // 解析异常表
        attribute.setExceptionTableLength(Stream.readU2(content, index));
        ExceptionTableElement[] exceptions = parseExceptions(content, index, attribute.getExceptionTableLength());
        attribute.setExceptions(exceptions);

        // 解析其他属性
        attribute.setAttributesCount(readU2(content, index));
        ClassFileParser.parseAttribute(content, index, klass, attribute);

        return attribute;
    }

    private List<ByteCode> parseByteCode(byte[] codes) {

        List<ByteCode> result = new ArrayList<>();

        ByteIndex index = new ByteIndex();
        while (index.getIndex() < codes.length) {
            byte readU1 = Stream.readU1(codes, index);
            Opcode opcode = Opcode.of(readU1);
            ByteCodeExecutor executor = ByteCodeExecutorContext.findExecutor(opcode);

            if (executor == null) {
                result.add(new ByteCode(opcode));
                if (opcode.getOpNum() == -1) {
                    throw new RuntimeException("还未能解析的opcode: " + opcode.name());
                }
                Stream.skip(index, opcode.getOpNum());
                continue;
            }

            ByteCode byteCode = executor.doParse(codes, index, opcode);
            result.add(byteCode);
        }

        return result;
    }

    private ExceptionTableElement[] parseExceptions(byte[] content, ByteIndex index, short exceptionTableLength) {

        ExceptionTableElement[] elements = new ExceptionTableElement[exceptionTableLength];

        for (int i = 0; i < exceptionTableLength; i++) {
            ExceptionTableElement element = new ExceptionTableElement();
            element.setStartPc(Stream.readU2(content, index));
            element.setEndPc(Stream.readU2(content, index));
            element.setHandlerPc(Stream.readU2(content, index));
            element.setHandlerPc(Stream.readU2(content, index));
        }

        return elements;
    }
}
