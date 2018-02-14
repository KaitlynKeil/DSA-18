import java.util.Arrays;

public class MergeSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;
    InsertionSort smallSorter = new InsertionSort();

    /**
     * This is the recursive step in which you split the array up into
     * a left and a right portion, sort them, and then merge them together.
     * Use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * Best-case runtime: O(n log n)
     * Worst-case runtime: O(n log n)
     * Average-case runtime: O(n log n)
     *
     * Space-complexity: O(n)
     */
    @Override
    public int[] sort(int[] array) {
        if (array.length <= INSERTION_THRESHOLD) return smallSorter.sort(array);
        int[] left = Arrays.copyOfRange(array, 0, array.length/2);
        int[] right = Arrays.copyOfRange(array, array.length/2, array.length);
        left = sort(left);
        right = sort(right);
        return merge(left, right);
    }

    /**
     * Given two sorted arrays a and b, return a new sorted array containing
     * all elements in a and b. A test for this method is provided in `SortTest.java`
     */
    public int[] merge(int[] a, int[] b) {
        int s, i = 0, j = 0;
        int[] sorted = new int[a.length + b.length];
        for(s = 0; s < sorted.length && i < a.length && j < b.length; s++){
            if(a[i] < b[j]) {
                sorted[s] = a[i];
                i++;
            }
            else {
                sorted[s] = b[j];
                j++;
            }
        }
        if(i != a.length) System.arraycopy(a, i, sorted, s, a.length-i);
        else if (j != b.length) System.arraycopy(b, j, sorted, s, b.length-j);
        return sorted;
    }

}
