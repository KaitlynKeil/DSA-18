package your_code;

import ADTs.QueueADT;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * An implementation of a priority Queue
 */
public class MyPriorityQueue {
    private LinkedList<Integer> ll;
    int size;
    int maxIndex;
    Integer max;

    public MyPriorityQueue() {
        ll = new LinkedList<>();
        maxIndex = 0;
    }

    public void enqueue(int item) {
        if(max == null) max = item;
        else if (max < item){
            maxIndex = ll.size();
            max = item;
        }
        ll.addLast(item);
    }

    public int dequeueMax() {
        Integer maxVal = ll.remove(maxIndex);
        if (ll.size() > 0) findMax();
        return maxVal;
    }

    public void findMax() {
        max = ll.getFirst();
        maxIndex = 0;
        int i = 0;
        int running = 1;
        ListIterator<Integer> iter = ll.listIterator(0);
        Integer current = iter.next();
        while(running == 1){
            if(max < current) {
                max = current;
                maxIndex = i;
            }
            if(iter.hasNext()) {
                current = iter.next();
                i++;
            }
            else running = 0;
        }
    }

}