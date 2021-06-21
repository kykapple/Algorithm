import java.io.*;
import java.util.*;

public class Main {
    static String input;
    static int check[];
    static Stack<Integer> mystack = new Stack<>();
    static Set<String> set = new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        input = br.readLine();
        check = new int[input.length()];

        for(int i=0; i<input.length(); i++) {
            check[i] = -1;  // 초기화
            char ch = input.charAt(i);

            if(ch == '(') {
                if(!mystack.empty()) {
                    check[i] = mystack.peek();
                }
                mystack.push(i);
            }
            else if(ch == ')')
                check[i] = mystack.pop();
        }

        solve("", 0, mystack);

        ArrayList<String> list = new ArrayList<>(set);
        Collections.sort(list);
        for (String s : list) {
            bw.write(s + "\n");
        }

        br.close();
        bw.close();
    }

    static void solve(String str, int idx, Stack<Integer> s) {
        if(idx >= input.length()) {
            if (!str.equals(input))
                set.add(str);
            return;
        }

        char ch = input.charAt(idx);

        if(ch == '(') {
            Stack<Integer> temp = new Stack<>();
            for (Integer integer : s) {
                temp.push(integer);
            }
            temp.push(idx);

            solve(str + ch, idx+1, temp);
            solve(str, idx+1, s);
        } else if(ch == ')') {
            if(!s.isEmpty() && s.peek() == check[idx]) {
                s.pop();
                solve(str + ch, idx+1, s);
            } else {
                solve(str, idx + 1, s);
            }
        } else {
            solve(str + ch, idx+1, s);
        }
    }
}