package indi.yufr.jvm.yuShare.constant.content;

import lombok.Builder;
import lombok.Data;

/**
 * @date: 2022/1/19 9:58
 * @author: farui.yu
 */
@Data
@Builder
public class DoubleInfo implements ConstantContent {

    private double content;

}
