package your_code;

public class MyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        Chicken val;
        Node prev;
        Node next;

        private Node(Chicken d, Node prev, Node next) {
            this.val = d;
            this.prev = prev;
            this.next = next;
        }

        private Node(Chicken d) {
            this.val = d;
            prev = null;
            next = null;
        }
    }

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(Chicken c) {
        addLast(c);
    }

    public Chicken pop() {
        return removeLast();
    }

    public void addLast(Chicken c) {
        Node n = new Node(c, null, null);
        if (isEmpty()) {
            head = n;
        }
        else {
            n.prev = tail;
        	tail.next = n;
        }
        tail = n;
        size++;
    }

    public void addFirst(Chicken c) {
        Node n = new Node(c, null, null);
        if (isEmpty()) {
            tail = n;
        }
        else {
            n.next = head;
            head.prev = n;
        }
        head = n;
        size++;
    }

    public Chicken get(int index) {
        if (head != null) {
            Node n = head;
            int i = 0;
            while (n != null && i < index) {
                n = n.next;
                i++;
            }
            return n.val;
        }
        return null;
    }

    public Chicken remove(int index) {
        Chicken chick_return;
        if (index == 0) return removeFirst();
        else if (index == size - 1) return removeLast();
        else if (index < size - 1 && index > 0){
            Node n = head;
            int i = 0;
            while (n != null && i < index) {
                n = n.next;
                i++;
            }
            n.prev.next = n.next;
            n.next.prev = n.prev;
            size--;
            return n.val;
        }
        else throw new IndexOutOfBoundsException();
    }

    public Chicken removeFirst() {
        if(size > 0) {
            Node temp = head;
            head = temp.next;
            size--;
            return temp.val;
        }
        return null;
    }

    public Chicken removeLast() {
        if(size > 0) {
            Node temp = tail;
            tail = temp.prev;
            size--;
            return temp.val;
        }
        return null;
    }
}