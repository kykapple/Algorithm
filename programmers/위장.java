import java.util.*;

class Solution {
    int ans = 0;
    List<String> typeList = new ArrayList<>();
    Map<String, ArrayList<String>> map = new HashMap<>();
    
    public int solution(String[][] clothes) {
        
        for(int i = 0; i < clothes.length; i++) {
            String name = clothes[i][0];
            String type = clothes[i][1];

            if(map.get(type) == null) {
                typeList.add(type);
                map.put(type, new ArrayList<>());
            }

            map.get(type).add(name);
        }
        
        tracking(0);
        
        return ans;
    }
    
    public void tracking(int idx) {
        if(idx != 0) {
            ans++;
        }
        
        for(int i = idx; i < typeList.size(); i++) {
            String type = typeList.get(i);
            ArrayList<String> list = map.get(type);
            
            for(int j = 0; j < list.size(); j++) {
                tracking(i + 1);
            }
        }
    }
    
}
