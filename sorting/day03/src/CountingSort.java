public class CountingSort {

    /**
     * Use counting sort to sort positive integer array A.
     * Runtime: O(N+k)
     * If k is significantly bigger than N, k dominates. Otherwise, O(N).
     * <p>
     * k: maximum element in array A
     */
    static void countingSort(int[] A) {
        int[] histArray = new int[getMax(A)+1]; // O(N): finding max by iterating A
        for(int i = 0; i < A.length; i++) {// O(N): iterating all of A...
            histArray[A[i]]++; // ...but indexing to histArray is O(1)
        }
        int i = 0;
        for(int j = 0; j < histArray.length; j++) { // O(k) to iterate the k elements of histArray...
            while (histArray[j] > 0) { // ...but goes to O(N) because you have to access each place
                A[i] = j;
                i++;
                histArray[j]--;
            }
        }
    }

    /* Gets the max of an array by iterating over all of it.
    * Is there a built in command for this? I didn't see it...
    * Runtime: O(N)
    */
    static int getMax(int[] A) {

        int k = A[0];
        for (int i = 1; i < A.length; i++) // O(N)
            k = (A[i] > k) ? A[i] : k;

        return k;
    }

}
