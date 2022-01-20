package indi.yufr.jvm.yuShare.vm.oops;

import lombok.Data;

/**
 * @date: 2022/1/20 13:34
 * @author: farui.yu
 */
@Data
public class ExceptionTableElement {

    private short startPc;
    private short endPc;
    private short handlerPc;
    private short catchType;

}
