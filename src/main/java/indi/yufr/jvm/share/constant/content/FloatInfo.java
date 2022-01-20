package indi.yufr.jvm.share.constant.content;

import lombok.Builder;
import lombok.Data;

/**
 * @date: 2022/1/19 9:58
 * @author: farui.yu
 */
@Data
@Builder
public class FloatInfo implements ConstantContent {

    private float content;

}
