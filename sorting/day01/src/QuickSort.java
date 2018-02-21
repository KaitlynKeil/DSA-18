import java.util.concurrent.ThreadLocalRandom;

public class QuickSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;
    private void shuffleArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int randIndex = ThreadLocalRandom.current().nextInt(i+1);
            swap(array, i, randIndex);
        }
    }

    /**
     * Best-case runtime: O(n log(n))
     * Worst-case runtime: O(n^2), but shuffling it makes that INCREDIBLY unlikely. O(n log(n))
     * Average-case runtime: O(n log(n))
     *
     * Space-complexity: O(log(n))
     */
    @Override
    public int[] sort(int[] array) {
        shuffleArray(array);
        quickSort(array, 0, array.length-1);
        return array;
    }

    /**
     * Partition the array around a pivot, then recursively sort the left and right
     * portions of the array. A test for this method is provided in `SortTest.java`
     * Optional: use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * @param lo The beginning index of the subarray being considered (inclusive)
     * @param hi The ending index of the subarray being considered (inclusive)
     */
    public void quickSort(int[] a, int lo, int hi) {
        if (lo < hi) {
            int p = partition(a, lo, hi);
            quickSort(a, lo, p-1);
            quickSort(a, p+1, hi);
        }
    }


    /**
     * Given an array, choose the array[low] element as the "pivot" element.
     * Place all elements smaller than "pivot" on "pivot"'s left, and all others
     * on its right. Return the final position of "pivot" in the partitioned array.
     *
     * @param lo The beginning index of the subarray being considered (inclusive)
     * @param hi The ending index of the subarray being considered (inclusive)
     */
    public int partition(int[] array, int lo, int hi) {
        if(array.length <= 1) return lo;
        int pivot = array[lo];
        int current_piv = lo;
        int end = hi;

        for(int i = lo+1; i <= end; i++){
            if(array[i] >= pivot) {
                swap(array, i, end);
                end--;
                i--;
            }
            else{
                swap(array, i, current_piv);
                current_piv = i;
            }
        }
        return current_piv;
    }

}
