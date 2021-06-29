import java.util.*;

class Solution {
    ArrayList<String> links = new ArrayList<>();
    Map<String, WebPage> map = new HashMap<>();
    
    class WebPage {
        int base_score;
        int link;
        ArrayList<String> external;     // 외부 링크 저장
        
        public WebPage(int base_score, int link, ArrayList<String> external) {
            this.base_score = base_score;
            this.link = link;
            this.external = external;
        }
        
        public String toString() {
            return base_score + " " + link;
        }
    }
    
    public int solution(String word, String[] pages) {
        int answer = 0;
        word = word.toLowerCase();
        
        for(int i=0; i<pages.length; i++) {
            int score = 0;
            int link = 0;
            ArrayList<String> external = new ArrayList<>();    
            
            // 웹 페이지 URL 파악
            String tagInfo = "<meta property=\"og:url\" content=\"https://";
            String str = pages[i].substring(pages[i].indexOf(tagInfo)).toLowerCase();
            str = str.substring(str.indexOf("https://"));
            
            String pagelink = "";
            int j = 0;
            while(true) {
                char ch = str.charAt(j++);
                if(ch == '\"') break;
                
                pagelink += ch;
            }
            
            // word 개수 파악
            String words[] = str.split("\\W+");		// 문자, 숫자만 포함
            for(String w : words) {
                String keywords[] = w.split("[0-9]");		// 숫자 제외
            
                for(String keyword : keywords) {
                    if(keyword.equals(word)) 
                        score++;
                }
            }
            
            // 외부 링크 파악
            String ext = "";
            str = str.substring(str.indexOf("<a href") + "<a href=\"".length());
            j = 0;
            while(true) {
                char ch = str.charAt(j++);
                if(ch == '\"') {
                    external.add(ext);
                    
                    // 외부 링크가 또 있다면
                    if(str.indexOf("<a href=\"") != -1) {
                        str = str.substring(str.indexOf("<a href") + "<a href=\"".length());
                        ext = "";
                        j = 0;
                        continue;
                    } 
                    
                    break;
                }
                
                ext += ch;
            }
            
            // 웹 페이지 정보 저장
            WebPage web = new WebPage(score, external.size(), external);
            links.add(pagelink);
            map.put(pagelink, web);
        }
        
        // 매칭 점수 계산
        ArrayList<Double> ans = new ArrayList<>();
        for(String str : links) {
            WebPage webPage = map.get(str);
            double matching_score = webPage.base_score;
            
            for(String external_link : links) {
                if(str.equals(external_link)) continue;

                WebPage extern = map.get(external_link);
                if(extern == null) continue;
                
                ArrayList<String> list = extern.external;
                if(list.contains(str)) {
                    matching_score += (extern.base_score / (double)extern.link);
                }
                
            }
            
            ans.add(matching_score);
        }
        
        for(int i=1; i<ans.size(); i++) {
            if(ans.get(answer) < ans.get(i))
                answer = i;
        }
        
        return answer;
    }
}