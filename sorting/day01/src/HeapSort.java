import java.util.Arrays;

public class HeapSort extends SortAlgorithm {
    int size;
    int[] heap;

    private int parent(int i) {
        return (i-1) / 2;
    }

    private int leftChild(int i) {
        return 2*i + 1;
    }

    private int rightChild(int i) {
        return 2 * (i + 1);
    }

    // Check children, and swap with larger child if necessary.
    // Corrects the position of element indexed i by sinking it.
    // Use either recursion or a loop to then sink the child
    public void sink(int i) {
        // find the larger child
        int left_i = leftChild(i);
        if(left_i < size) {
            int right_i = rightChild(i);
            if (right_i >= size || heap[left_i] >= heap[right_i]) {
                if (heap[left_i] > heap[i]) {
                    swap(heap, left_i, i);
                    sink(left_i);
                }
            } else {
                if (heap[right_i] > heap[i]) {
                    swap(heap, right_i, i);
                    sink(right_i);
                }
            }
        }
    }

    // Given the array, build a heap by correcting every non-leaf's position, starting from the bottom, then
    // progressing upward
    public void heapify(int[] array) {
        this.heap = array;
        this.size = array.length;

        for (int i=this.size / 2 - 1; i>=0; i--) {
            sink(i);
        }
    }

    /**
     * Best-case runtime: O(n log(n))
     * Worst-case runtime: O(n log(n))
     * Average-case runtime: O(n log(n))
     *
     * Space-complexity: O(1)
     */
    @Override
    public int[] sort(int[] array) {
        heapify(array);

        for (int i=size-1; i>0; i--) {
            array[i] = heap[0];
            int[] t_array = Arrays.copyOfRange(heap, 0, i);
            heapify(t_array);
        }
        heap = array;
        return heap;
    }
}
