import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static String s;
    static Map<String,Integer> map = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        s = sc.next();
        String t = sc.next();
        map.put(s,1);
        System.out.println(dfs(t));
    }

    static int dfs(String str) {
        if (map.containsKey(str)) return map.get(str);
        int res;
        if (str.length() <= s.length()) {
            map.put(str,0);
            return 0;
        }
        if (str.charAt(str.length() - 1) == 'A') res = dfs(addA(str));
        else res = dfs(reverseAddB(str));
        map.put(str,res);
        return res;
    }

    static String addA(String s) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < s.length() - 1; i++)
            str.append(s.charAt(i));
        return str.toString();
    }

    static String reverseAddB(String s) {
        StringBuilder str = new StringBuilder();
        for (int i = s.length() - 2; i >= 0; i--)
            str.append(s.charAt(i));
        return str.toString();
    }
}
