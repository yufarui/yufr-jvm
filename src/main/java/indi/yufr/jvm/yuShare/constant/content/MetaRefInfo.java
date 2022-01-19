package indi.yufr.jvm.yuShare.constant.content;

import lombok.Builder;
import lombok.Data;

/**
 * @date: 2022/1/19 9:51
 * @author: farui.yu
 */
@Data
@Builder
public class MetaRefInfo implements ConstantContent {

    private short classIndex;
    private short nameAndTypeIndex;

}
