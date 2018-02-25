public class LocksAndKeys {

    private static void swap(char[] A, int i, int j) {
        char t = A[i];
        A[i] = A[j];
        A[j] = t;
    }
    static char[][] locksAndKeys(char[] locks, char[] keys) {
        char[][] result = new char[2][];
        lockAndKeySort(keys, locks, 0, keys.length-1);
        lockAndKeySort(locks, keys, 0, locks.length-1);
        result[0] = locks;
        result[1] = keys;
        return result;
    }

    public static void lockAndKeySort(char[] a1, char[] a2, int lo, int hi) {
        if (lo < hi) {
            int p = pivot(a1, a2, lo, hi);
            lockAndKeySort(a1, a2, lo, p-1);
            lockAndKeySort(a1, a2, p+1, hi);
        }
    }

    public static int pivot(char[] a1, char[] a2, int lo, int hi) {
        if(a2.length <= 1) return lo;
        int pivot = a2[lo];
        int current_piv = lo;
        int end = hi;

        for(int i = lo; i <= end; i++){
            if(a1[i] > pivot) {
                swap(a1, i, end);
                end--;
                i--;
            }
            else if(a1[i] == pivot) {
                current_piv = i;
            }
            else{
                swap(a1, i, current_piv);
                current_piv = i;
            }
        }
        swap(a2, lo, current_piv);
        return current_piv;
    }
}




