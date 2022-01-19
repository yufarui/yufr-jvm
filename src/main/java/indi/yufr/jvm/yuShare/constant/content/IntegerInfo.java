package indi.yufr.jvm.yuShare.constant.content;

import lombok.Builder;
import lombok.Data;

/**
 * @date: 2022/1/19 9:56
 * @author: farui.yu
 */
@Data
@Builder
public class IntegerInfo implements ConstantContent {

    private int content;

}
