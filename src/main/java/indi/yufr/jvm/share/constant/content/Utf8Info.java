package indi.yufr.jvm.share.constant.content;

import lombok.Builder;
import lombok.Data;

/**
 * @date: 2022/1/19 9:52
 * @author: farui.yu
 */
@Data
@Builder
public class Utf8Info implements ConstantContent {

    private String content;

}
