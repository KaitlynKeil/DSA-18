
public class InsertionSort extends SortAlgorithm {
    /**
     * Use the insertion sort algorithm to sort the array
     *
     * Best-case runtime: O(n)
     * Worst-case runtime: O(n^2)
     * Average-case runtime: O(n^2)
     *
     * Space-complexity: O(1)
     */
    @Override
    public int[] sort(int[] array) {
        // TODO get while loop or w/e
        int i = 1;
        while(i < array.length) {
            if (array[i] < array[i - 1]) {
                swap(array, i, i - 1);
                if (i > 1) i--;
                continue;
            }
            i++;
        }
        return array;
    }
}
