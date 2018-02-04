public class MyArrayList {
    private Cow[] elems;
    private int size;

    // TODO: Runtime: O(1)
    public MyArrayList() {
        // Initialize array with capacity 10 cows
        //  and set initial size to 0
        elems = new Cow[10];
        size = 0;
    }

    // TODO: Runtime: O(1)
    public MyArrayList(int capacity) {
        // Initialize array with capacity cows
        //  and set initial size to 0
        elems = new Cow[capacity];
        size = 0;
    }

    // TODO: Runtime: O(1)*
    public void add(Cow c) {
        // Add a cow to the array at the last
        //  index. If no more space in array,
        //  double array size.
        if(size == elems.length) grow();
        elems[size] = c;
        size++;
    }

    // TODO: Runtime: O(1)
    public int size() {
        // Returns the current size
        return size;
    }

    // TODO: Runtime: O(1)
    public Cow get(int index) {
        // Returns the cow at the given index
        //  without removing said cow
        if(index > size) throw new IndexOutOfBoundsException();
        return elems[index];
    }

    // TODO: Runtime: O(n)
    public Cow remove(int index) {
        // Removes a cow and shifts all the other
        //  cows because no one likes the space
        //  in between us
        if(index >= size) throw new IndexOutOfBoundsException();
        Cow temp = elems[index];
        Cow [] tempArray = new Cow[elems.length];
        for(int i = 0; i < elems.length-1; i++){
            if (i < index) tempArray[i] = elems[i];
            else if (i >= index) {tempArray[i] = elems[i+1];}
        }
        if(size <= elems.length/4) shrink();
        elems = tempArray;
        size--;
        return temp;
    }

    // TODO: Runtime: O(n)
    public void add(int index, Cow c) {
        if(index > size) throw new IndexOutOfBoundsException();
        if(size == elems.length) grow();
        Cow[] temp = new Cow[elems.length];
        for(int i = 0; i < size+1; i++) {
            if (i == index) temp[i] = c;
            else if (i < index) {
                temp[i] = elems[i];
            } else if (i > index){
                temp[i] = elems[i - 1];
            }
        }
        size++;
        elems = temp;
    }

    // O(n)
    public void grow() {
        // Double the array size
        Cow[] temp = new Cow[elems.length * 2];
        System.arraycopy(elems, 0, temp, 0, elems.length);
        elems = temp;
    }

    // O(n)
    public void shrink() {
        // Halves the size of the array
        if(size > 2) {
            Cow[] temp = new Cow[elems.length / 2];
            System.arraycopy(elems, 0, temp, 0, elems.length);
            elems = temp;
        }

    }
}
