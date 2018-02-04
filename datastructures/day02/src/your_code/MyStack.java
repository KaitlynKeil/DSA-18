package your_code;
import ADTs.StackADT;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Stack;

/**
 * An implementation of the Stack interface.
 */
public class MyStack implements StackADT<Integer> {

    private LinkedList<Integer> ll;
    private Stack<Integer> max;

    public MyStack() {
        ll = new LinkedList<>();
        max = new Stack<>();
    }

    @Override
    public void push(Integer e) {
        if(max.size() == 0) max.push(e);
        else if (max.peek() < e) max.push(e);
        ll.addFirst(e);
    }

    @Override
    public Integer pop() {
        Integer pop = ll.removeFirst();
        if (pop==max.peek() && ll.size()!=0) max.pop();
        return pop;
    }

    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }

    @Override
    public Integer peek() {
        return ll.getFirst();
    }

    public Integer maxElement() {
        return max.peek();
    }
}
