package indi.yufr.jvm.yuShare.constant.content;

import lombok.Builder;
import lombok.Data;

/**
 * @date: 2022/1/19 9:52
 * @author: farui.yu
 */
@Data
@Builder
public class SingleIndex implements ConstantContent {

    // 指向 utf8
    private short index;

}
