public class DiceRollSum {
    static int[] rolls = {1,2,3,4,5,6};

    private static int diceRecurs(int i, int[] DP) {
        if (i < 0) return 0;
        if (i == 0) return 1; // THIS IS INCORRECT
        if (DP[i] != -1) return DP[i];
        int combos = 0;
//        if(i < 7) combos++;
        for(int roll:rolls) {
            combos += diceRecurs(i-roll, DP);
        }
        DP[i] = combos;
        return combos;
    }

    // Runtime: O(NC)
    // Space: O(N)
    public static int diceRollSum(int N) {
        int[] DP = new int[N + 1];
        for (int i = 0; i < DP.length; i++) {
            DP[i] = -1;
        }
        return diceRecurs(N, DP);
    }

}
