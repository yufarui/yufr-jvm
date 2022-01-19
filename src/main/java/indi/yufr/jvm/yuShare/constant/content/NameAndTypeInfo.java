package indi.yufr.jvm.yuShare.constant.content;

import lombok.Builder;
import lombok.Data;

/**
 * @date: 2022/1/19 16:12
 * @author: farui.yu
 */
@Data
@Builder
public class NameAndTypeInfo implements ConstantContent {

    private short nameIndex;
    private short descriptionIndex;

}
