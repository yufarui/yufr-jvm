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
    private StackValue[] locals;

    /**
     * 模拟操作数栈
     */
    private Stack<StackValue> stack = new Stack<>();

    private MethodInfo ownerMethod;

    public JavaVFrame(short maxLocals, MethodInfo methodInfo) {
        locals = new StackValue[maxLocals];
        ownerMethod = methodInfo;
    }

    public void push(StackValue value) {
        getStack().push(value);
    }

    public StackValue pop() {
        return getStack().pop();
    }

    public StackValue peek() {
        return getStack().peek();
    }

    public void add(int index, StackValue value) {
        getLocals()[index] = value;
    }

    public StackValue get(int index) {
        return getLocals()[index];
    }
}
