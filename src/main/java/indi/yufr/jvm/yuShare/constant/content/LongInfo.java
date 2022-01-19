package indi.yufr.jvm.yuShare.constant.content;

import lombok.Builder;
import lombok.Data;

/**
 * @date: 2022/1/19 9:57
 * @author: farui.yu
 */
@Data
@Builder
public class LongInfo implements ConstantContent {

    private long content;

}
