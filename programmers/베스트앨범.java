import java.util.*;

class Solution {
    Map<String, Integer> totalPlayCnt = new HashMap<>();
    Map<String, ArrayList<Pair>> playCntPerGenre = new HashMap<>();
    List<Integer> answer = new ArrayList<>();
    
    class Pair implements Comparable<Pair> {
        int idx;
        int play;
        
        public Pair(int idx, int play) {
            this.idx = idx;
            this.play = play;
        }
        
        @Override
        public int compareTo(Pair p) {
            if(this.play == p.play) {
                return this.idx - p.idx;
            }
            
            return p.play - this.play;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        
        for(int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int play = plays[i];
            
            if(totalPlayCnt.get(genre) == null) {
                playCntPerGenre.put(genre, new ArrayList<>());
                totalPlayCnt.put(genre, play);
            } else {
                totalPlayCnt.put(genre, play + totalPlayCnt.get(genre));
            }
            
            playCntPerGenre
                .get(genre)
                .add(new Pair(i, play));
        }
        
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(totalPlayCnt.entrySet());
        Collections.sort(entries, 
                         (a, b) -> b.getValue() - a.getValue());
        
        for(int i = 0; i < entries.size(); i++) {
            Map.Entry<String, Integer> entry = entries.get(i);
            ArrayList<Pair> list = playCntPerGenre.get(entry.getKey());
            
            Collections.sort(list);
            
            for(int j = 0; j < Math.min(list.size(), 2); j++) {
                Pair p = list.get(j);
                answer.add(p.idx);
            }
        }
        
        int[] ans = new int[answer.size()];
        for(int i = 0; i < answer.size(); i++) {
            ans[i] = answer.get(i);
        }
        
        return ans;
    }
    
}
