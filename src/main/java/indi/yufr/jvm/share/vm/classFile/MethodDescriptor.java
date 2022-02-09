package indi.yufr.jvm.share.vm.classFile;

import indi.yufr.jvm.share.vm.oops.DescriptorInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @date: 2022/1/25 11:04
 * @author: farui.yu
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MethodDescriptor {

    // 整体的描述符 如:([Ljava/lang/String;)V
    private String descriptor;

    private DescriptorInfo returnInfo;

    private List<DescriptorInfo> paramsInfo;

}
