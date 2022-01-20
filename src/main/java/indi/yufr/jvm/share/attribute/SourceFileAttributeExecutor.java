package indi.yufr.jvm.share.attribute;

import indi.yufr.jvm.share.tools.Stream;
import indi.yufr.jvm.share.vm.classFile.ByteIndex;
import indi.yufr.jvm.share.vm.oops.InstanceKlass;
import indi.yufr.jvm.share.vm.oops.attribute.AttributeInfo;
import indi.yufr.jvm.share.vm.oops.attribute.SourceFileAttribute;
import org.apache.commons.lang3.StringUtils;

/**
 * @date: 2022/1/19 11:15
 * @author: farui.yu
 */
public class SourceFileAttributeExecutor extends AttributeInfoExecutor {

    @Override
    public boolean canSupport(String attributeName) {

        if (StringUtils.equals(attributeName, "SourceFile")) {
            return true;
        }

        return false;
    }

    @Override
    public AttributeInfo doParse(byte[] content, ByteIndex index, InstanceKlass klass) {

        int length = Stream.readU4(content, index);
        SourceFileAttribute attribute = new SourceFileAttribute();
        attribute.setAttributeName("SourceFile");
        attribute.setAttributeLength(length);

        attribute.setSourceFileIndex(Stream.readU2(content, index));

        return attribute;
    }

}
