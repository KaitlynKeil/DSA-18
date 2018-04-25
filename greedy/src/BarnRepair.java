import java.util.*;

public class BarnRepair {

//    public static int solve(int M, int[] occupied) {
//        HashMap<Integer, Integer> boards = new HashMap<>();
//        for(int stall:occupied) {
//            boards.put(stall, 1);
//        }
//
//        for(Map.Entry<Integer, Integer> board:boards.entrySet()) {
//            int start = board.getKey();
//            int len = board.getValue();
//            if(boards.containsKey(start+len)) {
//                int stall_to_merge = start+len;
//                boards.put(start, len+boards.get(stall_to_merge));
//                boards.remove(stall_to_merge);
//            }
//        }
//
//        while(boards.size() > M) {
//            merge_closest(boards);
//        }
//
//        return 0;
//    }
//
//    static void merge_closest(HashMap<Integer, Integer> boards) {
//        for(Map.Entry<Integer, Integer> board:boards.entrySet()) {
//            int start = board.getKey();
//            int len = board.getValue();
//            if(boards.containsKey(start+len)) {
//                int stall_to_merge = start+len;
//                boards.put(start, len+boards.get(stall_to_merge));
//                boards.remove(stall_to_merge);
//            }
//        }
//
//    }
    public static int solve(int M, int[] occupied) {
        if(occupied.length < 1) {return 0;}

        Arrays.sort(occupied);
        int num_boards = 1;
        int min_gap = occupied[occupied.length-1] - occupied[0];
        int gap_start = occupied[0];

        for(int i = 1; i < occupied.length; i++) {
            int gap = occupied[i] - occupied[i-1];
            if(gap > 1){
                num_boards++;
                if(gap <= min_gap) {
                    min_gap = gap;
                    gap_start = occupied[i-1];
                }
            }
        }

        if(num_boards <= M) {return occupied.length;}

        PriorityQueue<Integer> stalls = new PriorityQueue<>();
        for(int stall:occupied) {
            stalls.offer(stall);
        }
        for(int i = 1; i < min_gap; i++) {
            stalls.offer(gap_start+i);
        }

        int[] blocked = new int[stalls.size()];
        for(int i = 0; i < blocked.length; i++) {
            blocked[i] = stalls.poll();
        }

        return solve(M, blocked);
    }

}