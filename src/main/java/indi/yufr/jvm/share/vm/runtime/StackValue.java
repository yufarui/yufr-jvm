package indi.yufr.jvm.share.vm.runtime;

import indi.yufr.jvm.share.vm.utilities.BasicType;
import indi.yufr.jvm.share.tools.DataTranslate;
import lombok.Data;

/**
 * Created By ziya
 * QQ: 3039277701
 * 2021/3/29
 */
@Data
public class StackValue {

    private int type;

    /**
     * 数据存储在这里的情况：
     *  1、float
     *  2、long
     */
    private byte[] data;

    /**
     * 数据
     */
    private int val;

    private Object object;

    public StackValue(int type, int val) {
        this.type = type;
        this.val = val;
    }

    public StackValue(int type, Object val) {
        this.type = type;
        this.object = val;
    }

    public StackValue(int type, float v) {
        this.type = type;
        this.data = DataTranslate.floatToByte(v);
    }

    public StackValue(int type, long v) {
        this.type = type;
        this.data = DataTranslate.longToBytes(v);
    }

    public Object getData() {
        switch (type) {

            case BasicType.T_FLOAT:
                return DataTranslate.byteToFloat(data);
            case BasicType.T_LONG:
                return DataTranslate.bytesToLong(data);
            case BasicType.T_INT:
                return val;
        }

        return null;
    }
}
