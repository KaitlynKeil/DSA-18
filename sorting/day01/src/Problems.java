import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Problems {

    private static PriorityQueue<Integer> minPQ() {
        return new PriorityQueue<>(11);
    }

    private static PriorityQueue<Integer> maxPQ() {
        return new PriorityQueue<>(11, Collections.reverseOrder());
    }

    private static double getMedian(List<Integer> A) {
        double median = (double) A.get(A.size() / 2);
        if (A.size() % 2 == 0)
            median = (median + A.get(A.size() / 2 - 1)) / 2.0;
        return median;
    }

    // Runtime of this algorithm is O(N^2). Sad! We provide it here for testing purposes
    public static double[] runningMedianReallySlow(int[] A) {
        double[] out = new double[A.length];
        List<Integer> seen = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            int j = 0;
            while (j < seen.size() && seen.get(j) < A[i])
                j++;
            seen.add(j, A[i]);
            out[i] = getMedian(seen);
        }
        return out;
    }

    /**
     *
     * @param inputStream an input stream of integers
     * @return the median of the stream, after each element has been added
     */
    public static double[] runningMedian(int[] inputStream) {
        double[] runningMedian = new double[inputStream.length];
        if(inputStream.length < 1) return runningMedian;
        runningMedian[0] = inputStream[0];
        if(inputStream.length == 1) return runningMedian;
        PriorityQueue below_med = maxPQ();
        PriorityQueue above_med = minPQ();
        for(int i = 1; i < inputStream.length; i++){
            if(inputStream[i] > runningMedian[i-1]){
                if((i+1) %2 == 0) {
                    above_med.offer((double) inputStream[i]);
                    below_med.offer(runningMedian[i - 1]);
                    runningMedian[i] = ((double) above_med.peek() + (double) below_med.peek()) / 2.0;
                }
                else {
                    if(((double)above_med.peek() < (double)inputStream[i])) {
                        runningMedian[i] = (double) above_med.poll();
                        above_med.offer((double) inputStream[i]);
                    }
                    else {
                        runningMedian[i] = (double) inputStream[i];
                    }
                }
            }
            else{
                if((i+1) %2 == 0) {
                    below_med.offer((double) inputStream[i]);
                    above_med.offer(runningMedian[i - 1]);
                    runningMedian[i] = ((double) above_med.peek() + (double) below_med.peek()) / 2.0;
                }
                else {
                    if(((double)below_med.peek() > (double)inputStream[i])) {
                        runningMedian[i] = (double) below_med.poll();
                        below_med.offer((double) inputStream[i]);
                    }
                    else {
                        runningMedian[i] = (double) inputStream[i];
                    }
                }
            }
        }
        return runningMedian;
    }


}
