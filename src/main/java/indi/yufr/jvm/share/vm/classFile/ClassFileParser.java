package indi.yufr.jvm.share.vm.classFile;

import indi.yufr.jvm.share.attribute.AttributeInfoExecutor;
import indi.yufr.jvm.share.attribute.AttributeInfoExecutorContext;
import indi.yufr.jvm.share.constant.content.ConstantContent;
import indi.yufr.jvm.share.constant.executor.ConstantDoubleInfoExecutor;
import indi.yufr.jvm.share.constant.executor.ConstantInfoExecutor;
import indi.yufr.jvm.share.constant.executor.ConstantInfoExecutorContext;
import indi.yufr.jvm.share.constant.executor.ConstantLongInfoExecutor;
import indi.yufr.jvm.share.tools.Stream;
import indi.yufr.jvm.share.vm.oops.*;
import indi.yufr.jvm.share.vm.oops.attribute.AttributeInfo;
import indi.yufr.jvm.share.vm.utilities.ConstantTag;
import lombok.extern.slf4j.Slf4j;

import static indi.yufr.jvm.share.tools.Stream.*;

/**
 * @date: 2022/1/18 10:33
 * @author: farui.yu
 */
@Slf4j
public class ClassFileParser {

    /**
     * 将字节编码按顺序解析,最后组装成内存实体对象
     *
     * @param content
     * @return
     */
    public static InstanceKlass parseClassFile(byte[] content) {

        ByteIndex index = new ByteIndex();
        InstanceKlass klass = new InstanceKlass();

        klass.setMagic(readU4(content, index));

        log.info("magic:[{}]", klass.getMagic());
        klass.setMajorVersion(readU2(content, index));
        klass.setMinorVersion(readU2(content, index));

        klass.setConstantPoolCount(readU2(content, index));
        parseConstantPool(content, index, klass);

        // 解析本类的基础信息
        klass.setAccessFlags(readU2(content, index));
        klass.setThisClass(readU2(content, index));
        klass.setSuperClass(readU2(content, index));

        // 接口信息
        klass.setInterfacesLength(readU2(content, index));
        klass.initInterfaceInfos();

        // 暂无接口信息,跳过
        Stream.skip(index, klass.getFieldsLength());

        // 解析字段信息
        klass.setFieldsLength(readU2(content, index));

        parseFieldInfo(content, index, klass);

        klass.setMethodLength(readU2(content, index));
        parseMethodInfo(content, index, klass);

        klass.setAttributesCount(readU2(content, index));

        log.info("开始解析类本身的属性");
        for (int i = 0; i < klass.getAttributesCount(); i++) {
            parseAttribute(content, index, klass, klass);
        }

        return klass;
    }

    private static void parseConstantPool(byte[] content, ByteIndex index, InstanceKlass klass) {

        klass.initConstantPool();

        log.info("开始解析常量池");

        // 解析常量池项, 注意长度
        // 注意 常量池的 index 实际上以1开始计算
        ConstantInfoExecutor lastExecutor = null;
        for (int i = 0; i < klass.getConstantPoolCount() - 1; i++) {

            if (lastExecutor instanceof ConstantLongInfoExecutor
                    || lastExecutor instanceof ConstantDoubleInfoExecutor) {
                // 不解析直接跳过
                klass.getConstantPoolItems()[i] = null;
                lastExecutor = ConstantInfoExecutorContext.skipExecutor;
                continue;
            }

            byte tag = readU1(content, index);
            ConstantTag constantTag = ConstantTag.of(tag);
            ConstantPoolItem constantPoolItem = new ConstantPoolItem(constantTag);
            lastExecutor = ConstantInfoExecutorContext.findExecutor(constantTag);

            ConstantContent constantContent = lastExecutor.doParseInfo(content, index);
            constantPoolItem.setContent(constantContent);

            klass.getConstantPoolItems()[i] = constantPoolItem;
            log.info("第[{}]个常量,类型为[{}],内容为[{}]", i + 1, constantTag.name(), constantContent);
        }
    }

    private static void parseFieldInfo(byte[] content, ByteIndex index, InstanceKlass klass) {

        klass.initFieldInfos();

        log.info("开始解析字段信息");

        for (int i = 0; i < klass.getFieldsLength(); i++) {

            FieldAndMethodAttribute fieldInfo = parseFieldAndMethodAttribute(content, index, new FieldInfo());
            fieldInfo.setBelongKlass(klass);
            log.info("第[{}]个字段,信息{}", i, fieldInfo);
            parseAttribute(content, index, klass, fieldInfo);

            klass.getFields()[i] = (FieldInfo) fieldInfo;
        }
    }

    private static void parseMethodInfo(byte[] content, ByteIndex index, InstanceKlass klass) {
        klass.initMethods();
        log.info("开始解析方法信息");

        for (int i = 0; i < klass.getMethodLength(); i++) {

            FieldAndMethodAttribute methodInfo = parseFieldAndMethodAttribute(content, index, new MethodInfo());
            methodInfo.setBelongKlass(klass);

            log.info("第[{}]个方法,信息{}", i, methodInfo);
            parseAttribute(content, index, klass, methodInfo);

            klass.getMethods()[i] = (MethodInfo) methodInfo;
        }
    }

    public static void parseAttribute(byte[] content, ByteIndex index, InstanceKlass klass, AttributeAble attributeAble) {

        // 防止重复加载
        if (attributeAble.getAttributes() == null) {
            attributeAble.initAttributes();
        }

        for (int j = 0; j < attributeAble.getAttributesCount(); j++) {

            short attrNameIndex = readU2(content, index);
            AttributeInfoExecutor executor = AttributeInfoExecutorContext.findExecutor(attrNameIndex, klass);

            // 测试时,暂时将不支持的attr跳过
            if (executor == null) {
                int attrLength = readU4(content, index);
                skip(index, attrLength);
                continue;
            }

            AttributeInfo attributeInfo = executor.doParse(content, index, klass);
            attributeAble.getAttributes()[j] = attributeInfo;

            log.info("第[{}]个属性,属性信息[{}]", j, attributeInfo);
        }
    }

    private static FieldAndMethodAttribute parseFieldAndMethodAttribute(byte[] content, ByteIndex index,
                                                                        FieldAndMethodAttribute fieldAndMethodAttribute) {
        fieldAndMethodAttribute.setAccessFlags(readU2(content, index));
        fieldAndMethodAttribute.setNameIndex(readU2(content, index));
        fieldAndMethodAttribute.setDescriptorIndex(readU2(content, index));
        fieldAndMethodAttribute.setAttributesCount(readU2(content, index));

        return fieldAndMethodAttribute;
    }
}
