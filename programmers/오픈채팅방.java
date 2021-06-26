import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        List<String> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        
        for(int i=0; i<record.length; i++) {
            String[] str = record[i].split(" ");
            
            String behavior = str[0];
            String uid = str[1];
            
            switch(behavior) {
                case "Enter":
                    list.add(uid + "님이 들어왔습니다.");
                    break;
                case "Leave":
                    list.add(uid + "님이 나갔습니다.");
                    break;
            }
            
            if(behavior.equals("Leave")) continue;
                
            String nickName = str[2];

            if(map.get(uid) == null) {
                map.put(uid, nickName);
            } else if(!map.get(uid).equals(nickName)) {
                map.put(uid, nickName);
            }
        }
        
        String[] answer = new String[list.size()];
        for(int i=0; i<list.size(); i++) {
            String str = list.get(i);
            String uid = str.substring(0, str.indexOf('님'));
            String nickName = map.get(uid);
            
            answer[i] = nickName + str.substring(str.indexOf('님'));
        }
        
        return answer;
    }
}