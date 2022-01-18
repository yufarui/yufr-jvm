package indi.yufr.jvm.yuShare.vm.classFile;

import indi.yufr.jvm.yuShare.constantTag.ConstantInfoExecutor;
import indi.yufr.jvm.yuShare.constantTag.ConstantInfoExecutorContext;
import indi.yufr.jvm.yuShare.vm.oops.ConstantPoolItem;
import indi.yufr.jvm.yuShare.vm.oops.InstanceKlass;
import indi.yufr.jvm.yuShare.vm.utilities.ConstantTag;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import static indi.yufr.jvm.yuShare.tools.Stream.*;

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
        klass.initConstantPool();

        // 解析常量池项
        for (int i = 0; i < klass.getConstantPoolCount(); i++) {
            byte tag = readU1(content, index);
            ConstantTag constantTag = ConstantTag.of(tag);
            ConstantPoolItem item = new ConstantPoolItem(constantTag);
            ConstantInfoExecutor executor = ConstantInfoExecutorContext.findExecutor(constantTag);
            Object constantContent = executor.doParseInfo(content, index);
            item.setContent(constantContent);
        }

        return klass;
    }


}
