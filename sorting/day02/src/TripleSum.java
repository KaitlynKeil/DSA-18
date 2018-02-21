import static java.util.Arrays.sort;

public class TripleSum {

    static int tripleSum(int arr[], int sum) {
        sort(arr); // Sort first so you can check to see when you no longer have a chance
        int count = 0;
        int i, j, k; // Make your indeces
        for(i = 0; i < arr.length - 2; i++) {
            j = arr.length - 1;
            k = i+1;
            while (k < j) {
                int k_val = sum - (arr[i] + arr[j]);
                if(k_val > arr[j]) { // if the value you need for k is greater than the one you have for j, break
                    break;
                }
                while (arr[k] < k_val && k < j - 1) { // as long as k is less than j and less than the k_val, increase k
                    k++;
                }
                if (arr[k] == k_val) { // if you found it, increase count
                    count++;
                }
                k = i + 1; // reset k...
                j--; // decrement j
            }
        }
        return count;
    }
}
