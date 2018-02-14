import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Boomerang {

    public static int numberOfBoomerangs(int[][] points) {
        int numBooms = 0;
        for(int[] p1:points) {
            HashMap<Double, Integer> dists = new HashMap<>();
            for(int[] p2:points) {
                double x_diff = (double) (p1[0] - p2[0]);
                double y_diff = (double) (p1[1] - p2[1]);
                double dist = Math.sqrt(x_diff*x_diff + y_diff*y_diff);
                if(dists.containsKey(dist)) {
                    dists.put(dist, dists.get(dist)+1);
                }
                else {
                    dists.put(dist, 1);
                }
            }
            for(Double key:dists.keySet()) {
                int n = dists.get(key);
                numBooms += n*(n-1);
            }
        }
        return numBooms;
    }
}

