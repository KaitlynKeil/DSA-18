import java.util.Arrays;
import java.util.Collections;

public class LongestIncreasingSubsequence {

    private static int LISrecurs(int[] A, int[] DP, int i) {
        if(i >= A.length) return 0; // i has gotten past the end
        if(DP[i] != -1) return DP[i];
        int ans = 1;
        for(int j = i+1; j < A.length; j++) {
            int recurs = LISrecurs(A, DP, j);
            if(A[i] < A[j]) {
                ans = Math.max(recurs + 1, ans);
            }
        }
        DP[i] = ans;
        return ans;
    }
    // Runtime: O(N^2)
    // Space: O(N)
    public static int LIS(int[] A) {
        int[] DP = new int[A.length];
        for (int i = 0; i < DP.length; i++) {
            DP[i] = -1;
        }
        int max = 0;
        LISrecurs(A, DP, 0);
        for (int iss:DP) {
            max = Math.max(max, iss);
        }
        return max;
    }
}