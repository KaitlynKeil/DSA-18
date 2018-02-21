import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class FrequencyPrint {

    static String frequencyPrint(String s) {
        String res = "";
        HashMap<String, Integer> hist = new HashMap();
        String[] split_string = s.split("\\s");
        for(String key:split_string){
            hist.put(key, hist.getOrDefault(key, 0) + 1);
        }
        PriorityQueue<Map.Entry<String, Integer>> queue =
                new PriorityQueue<Map.Entry<String, Integer>>(new Comparator<Map.Entry<String, Integer>>()
                {
                    @Override
                    public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2)
                    {
                        return entry1.getValue() - entry2.getValue();
                    }
                });

        for(Map.Entry<String, Integer> entry : hist.entrySet()){
            queue.offer(entry);
        }
        while(!queue.isEmpty()) {
            Map.Entry<String, Integer> next = queue.poll();
            for(int i = 0; i < next.getValue(); i++) {
                res = res.concat(next.getKey());
                res = res.concat(" ");
            }
        }
        return res;
    }

}
