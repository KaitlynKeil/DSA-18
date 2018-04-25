import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class MCCR {
        public static int MCCR(EdgeWeightedGraph G) {
            int sum = 0;
            IndexPQ<Integer> vert_queue = new IndexPQ<>(G.numberOfV());

            // Build the queue
            for(Integer i = 0; i < G.numberOfV(); i++) {
                vert_queue.insert(i, Integer.MAX_VALUE);
            }
            vert_queue.changeKey(0, 0);

            while(!vert_queue.isEmpty()) {
                sum += vert_queue.minKey();
                Integer v = vert_queue.delMin();
                for(Edge edge:G.edges(v)) {
                    Integer u = edge.other(v);
                    if(vert_queue.contains(u) && edge.weight() < vert_queue.getKey(u)) { //< vert_keys.get(u)) {
                        Integer new_key = edge.weight();
                        vert_queue.changeKey(u, new_key);
                    }
                }

            }
            return sum;
        }

    }

