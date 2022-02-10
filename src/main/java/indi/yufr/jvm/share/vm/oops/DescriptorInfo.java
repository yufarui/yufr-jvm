package indi.yufr.jvm.share.vm.oops;

import indi.yufr.jvm.share.vm.runtime.JavaVFrame;
import indi.yufr.jvm.share.vm.utilities.BasicType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Data
public class DescriptorInfo {

//    暂未开启的属性
//    private boolean isResolved = false;

    // 类型
    private BasicType type;

    // 针对数组类型,添加的额外维度
    private int arrayDimension;

    // 针对数组 或 L对象的描述
    private String typeDesc;

    public void incArrayDimension() {
        arrayDimension++;
    }

    public static DescriptorInfo objectDesc(BasicType basicType, String typeDesc) {
        DescriptorInfo descriptorInfo = new DescriptorInfo();
        descriptorInfo.setType(basicType);
        descriptorInfo.setTypeDesc(typeDesc);
        return descriptorInfo;
    }

    public static DescriptorInfo simple(BasicType basicType) {

        DescriptorInfo descriptorInfo = new DescriptorInfo();
        descriptorInfo.setType(basicType);
        return descriptorInfo;
    }

    public static Object[] parseMethodParams(List<DescriptorInfo> methodParamsDes, JavaVFrame frame) {

        Object[] params = new Object[methodParamsDes.size()];

        for (int i = 0; i < methodParamsDes.size(); i++) {
            params[i] = frame.pop().getData();
        }

        return params;
    }

    public static Class<?>[] getParamsType(List<DescriptorInfo> methodParamsDes) {
        Class<?>[] types = new Class[methodParamsDes.size()];

        for (int i = 0; i < methodParamsDes.size(); i++) {
            DescriptorInfo info = methodParamsDes.get(i);

            switch (info.getType()) {
                case T_BOOLEAN:
                    types[i] = boolean.class;

                    break;
                case T_CHAR:
                    types[i] = char.class;
                    break;
                case T_INT:
                    types[i] = int.class;
                    break;
                case T_OBJECT:
                    try {
                        types[i] = Class.forName(info.getTypeDesc().replace('/', '.'));
                    } catch (ClassNotFoundException e) {
                        log.error("未能处理的类");
                    }
                    break;
                case T_LONG:
                    types[i] = long.class;
                    break;
                case T_DOUBLE:
                    types[i] = double.class;
                    break;
                case T_FLOAT:
                    types[i] = float.class;
                    break;
                case T_ARRAY:
                    throw new Error("数组类型，未作处理");
                default:
                    throw new Error("无法识别的参数类型");
            }
        }

        return types;
    }
}
