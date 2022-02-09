package indi.yufr.jvm.share.tools;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @date: 2021/6/10 18:53
 * @author: farui.yu
 */
public class Tuple<T1, T2> implements Iterable<Object>, Serializable {

    private T1 t1;
    private T2 t2;

    public Tuple(T1 t1, T2 t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    public T1 getT1() {
        return t1;
    }

    public T2 getT2() {
        return t2;
    }


    public Object get(int index) {
        switch (index) {
            case 0:
                return t1;
            case 1:
                return t2;
            default:
                return null;
        }
    }

    public List<Object> toList() {
        return Arrays.asList(toArray());
    }


    public Object[] toArray() {
        return new Object[]{t1, t2};
    }

    @Override
    public Iterator<Object> iterator() {
        return Collections.unmodifiableList(toList()).iterator();
    }


    @Override
    public int hashCode() {
        int result = size();
        result = 31 * result + t1.hashCode();
        result = 31 * result + t2.hashCode();
        return result;
    }

    public int size() {
        return 2;
    }
}