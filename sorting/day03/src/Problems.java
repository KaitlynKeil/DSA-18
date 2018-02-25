import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

public class Problems {

    static int swap_counts = 0;

    static void sortNumsBetween100s(int[] A) {
        // Option #1, which just adds to all of them first. This adds 2O(N) runtime.
        for (int i = 0; i < A.length; i++) {
            A[i] += 101;
        }
        RadixSort.radixSort(A, 10);
        for (int i = 0; i < A.length; i++) {
            A[i] -= 101;
        }
    }

    static void sortNumsBetween100s2(int[] A) {
        // Option #2, which sorts them by ignoring the sign first, then by sign alone
        RadixSort.radixSort(A,10); // Base ten seems safe for this range
        LinkedList<Integer>[] L = new LinkedList[2]; // After sorting with ignoring sign, create 2 lists: neg and pos
        for(int i = 0;i< 2;i++)
            L[i]=new LinkedList<>();
        for(int i :A){ // O(N)
            int sign = (i > 0) ? 1 : 0; // If negative, put in L[0], else L[1]
            L[sign].add(i);
        }
        int j = 0; // All the below should be O(N)
        ListIterator<Integer> it = L[0].listIterator(L[0].size()); // Make an iterator to traverse L[0] backwards
        while(it.hasPrevious()) { // Because the negatives are sorted by absolute value, we must go backwards
            A[j] = it.previous();
            j++;
        }
        while(!L[1].isEmpty()){ // Then forwards for positives!
            A[j] = L[1].poll();
            j++;
        }
    }

    /**
     * @param n the character number, 0 is the rightmost character
     * @return
     */
    private static int getNthCharacter(String s, int n) {
        return s.charAt(s.length() - 1 - n) - 'a';
    }


    /**
     * Use counting sort to sort the String array according to a character
     *
     * @param n The digit number (where 0 is the least significant digit)
     */
    static void countingSortByCharacter(String[] A, int n) {
        int b = 26; // Make an easily changable number of buckets
        LinkedList<String>[] L = new LinkedList[b];
        for (int i = 0; i < b; i++) // O(b)
            L[i] = new LinkedList<>(); // O(1)
        for (String i : A) { // O(N)
            int digit = getNthCharacter(i, n); // O(1)
            L[digit].add(i); // O(1) b/c linked list
        }
        int j = 0; // index in A to place numbers
        for (LinkedList<String> list : L) { // O(b)
            while(!list.isEmpty()){ // O(N), in essence
                A[j] = list.poll();
                j++;
            }
        }
    }

    /**
     * @param stringLength The length of each of the strings in S
     */
    static void sortStrings(String[] S, int stringLength) {
        for(int i = 0; i < stringLength; i++){ // Runs O(w) times
            countingSortByCharacter(S, i); // O(N + b)
        }
    }

    /**
     * @param A The array to count swaps in
     */

    public static int countSwaps(int[] A) {
        swap_counts = 0;
        A = sortCount(A);
        return swap_counts;
    }

    public static int[] sortCount(int[] array) {
        if(array.length < 2) return array;
        int[] left = Arrays.copyOfRange(array, 0, array.length/2);
        int[] right = Arrays.copyOfRange(array, array.length/2, array.length);
        left = sortCount(left);
        right = sortCount(right);
        return merge(left, right);
    }

    public static int[] merge(int[] a, int[] b) {
        int s, i = 0, j = 0;
        int[] sorted = new int[a.length + b.length];
        for(s = 0; s < sorted.length && i < a.length && j < b.length; s++){
            if(a[i] < b[j]) {
                sorted[s] = a[i];
                i++;
            }
            else {
                swap_counts+=(a.length-i);
                sorted[s] = b[j];
                j++;
            }
        }
        if(i != a.length){
            System.arraycopy(a, i, sorted, s, a.length-i);
        }
        else if (j != b.length) {
            System.arraycopy(b, j, sorted, s, b.length-j);
        }
        return sorted;
    }
}
