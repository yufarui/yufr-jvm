package indi.yufr.jvm.share.vm.oops;

import indi.yufr.jvm.share.tools.Tuple;
import indi.yufr.jvm.share.vm.classFile.MethodDescriptor;
import indi.yufr.jvm.share.vm.utilities.BasicType;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@ToString(callSuper = true)
public class MethodInfo extends FieldAndMethodAttribute {

    public MethodDescriptor methodDescriptorHandler() {
        MethodDescriptor descriptor = new MethodDescriptor();

        String descriptorName = descriptorName();
        descriptor.setDescriptor(descriptorName);
        descriptor.setParamsInfo(parseMethodParams(descriptorName));
        descriptor.setReturnInfo(parseMethodReturn(descriptorName));
        return descriptor;
    }

    public List<DescriptorInfo> parseMethodParams(String descriptor) {
        int paramStartIndex = descriptor.indexOf('(');
        int paramEndIndex = descriptor.indexOf(')');

        String paramsDescriptor = descriptor.substring(paramStartIndex + 1, paramEndIndex);

        return doParseDescriptor(paramsDescriptor);
    }

    public DescriptorInfo parseMethodReturn(String descriptor) {
        int paramEndIndex = descriptor.indexOf(')');

        String paramsDescriptor = descriptor.substring(paramEndIndex + 1);
        List<DescriptorInfo> descriptorInfos = doParseDescriptor(paramsDescriptor);

        return descriptorInfos.get(0);
    }

    private List<DescriptorInfo> doParseDescriptor(String descriptor) {

        int index = 0;
        int length = descriptor.length();
        List<DescriptorInfo> descriptorInfos = new ArrayList<>();

        while (index < length) {

            // 数组 或是 对象是特殊的
            char initial = descriptor.charAt(index);
            BasicType basicType = BasicType.charToBasicType(initial);

            if (basicType == BasicType.T_OBJECT) {
                int objectEnd = descriptor.indexOf(";", index);

                String objectDesc = descriptor.substring(index + 1, objectEnd);
                descriptorInfos.add(DescriptorInfo.objectDesc(basicType, objectDesc));
                index = objectEnd + 1;
            } else if (basicType == BasicType.T_ARRAY) {
                Tuple<DescriptorInfo, Integer> tuple = parseArrayTypeDesc(descriptor, index);
                descriptorInfos.add(tuple.getT1());
                index = tuple.getT2() + 1;
            } else {
                descriptorInfos.add(DescriptorInfo.simple(basicType));
                index++;
            }
        }

        return descriptorInfos;
    }

    private Tuple<DescriptorInfo, Integer> parseArrayTypeDesc(String paramsDescriptor, int startIndex) {

        DescriptorInfo arrayDescriptor = new DescriptorInfo();
        arrayDescriptor.setType(BasicType.T_ARRAY);
        arrayDescriptor.setArrayDimension(1);

        int componentIndex = startIndex + 1;
        int componentEnd;

        while (true) {
            char componentChar = paramsDescriptor.charAt(componentIndex);
            BasicType componentType = BasicType.charToBasicType(componentChar);
            if (componentType == BasicType.T_ARRAY) {
                arrayDescriptor.incArrayDimension();
                componentIndex++;
                continue;
            }

            if (componentType == BasicType.T_OBJECT) {
                int objectEnd = paramsDescriptor.indexOf(";", componentIndex);
                String objectDesc = paramsDescriptor.substring(componentIndex + 1, objectEnd);
                arrayDescriptor.setTypeDesc(objectDesc);
                componentEnd = objectEnd;
            } else {
                arrayDescriptor.setTypeDesc(componentChar + "");
                componentEnd = componentIndex;
            }
            break;
        }
        return new Tuple<>(arrayDescriptor, componentEnd);
    }

}
