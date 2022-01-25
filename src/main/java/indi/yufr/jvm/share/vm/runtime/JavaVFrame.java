package indi.yufr.jvm.share.vm.runtime;

import indi.yufr.jvm.share.vm.oops.MethodInfo;
import lombok.Data;

import java.util.Stack;

/**
 * 实际处理的栈帧
 */
@Data
public class JavaVFrame extends VFrame {

    /**
     * 模拟局部变量表
     */
    private Stack<StackValue> container = new Stack<>();

    /**
     * 模拟操作数栈
     */
    private StackValue[] locals;

    private MethodInfo ownerMethod;

    public JavaVFrame(short maxLocals, MethodInfo methodInfo) {
        locals = new StackValue[maxLocals];
        ownerMethod = methodInfo;
    }

    public void push(StackValue value) {
        getContainer().push(value);
    }

    public StackValue pop() {
        return getContainer().pop();
    }

    public StackValue peek() {
        return getContainer().peek();
    }

    public void add(int index, StackValue value) {
        getLocals()[index] = value;
    }

    public StackValue get(int index) {
        return getLocals()[index];
    }
}
