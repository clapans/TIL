import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int n,m;
    static int res;
    static String[][] arr;
    static int[][] dp;
    static int[][] trans;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = Integer.parseInt(sc.next());
        for (int t = 0; t < tc; t++){
            n = Integer.parseInt(sc.next());
            m = Integer.parseInt(sc.next());
            arr = new String[n][m];
            dp = new int[n+1][(int)Math.pow(2,m)];
            trans = new int[(int)Math.pow(2,m)][m];
            res = 0;
            for (int i = 0; i < n; i++){
                String[] tmp = sc.next().split("");
                for (int j = 0; j < m; j++) arr[i][j] = tmp[j];
            }
            for (int i = 0; i < trans.length; i++) trans[i] = transBit(i);
            assign();
            for (int i : dp[n]) res = Math.max(res,i);
            System.out.println(res);
            //for (int i = 0; i < n+1; i++)
            //    System.out.println(Arrays.toString(dp[i]));
        }
    }

    static void assign(){
        for (int i = 1; i < n+1; i++)
            for (int j = 0; j < Math.pow(2,m); j++){
                if (!isCheck(i,trans[j])) continue;
                int tmp = sum(trans[j]);
                for (int k = 0; k < Math.pow(2,m); k++)
                    if (compare(trans[j],trans[k])) dp[i][j] = Math.max(dp[i][j],dp[i-1][k] + tmp);
            }
    }

    static int[] transBit(int num){
        int[] bit = new int[m];
        for (int i = 0; i < m; i++) bit[m-i-1] = Math.min(num & (int)Math.pow(2,i),1);
        return bit;
    }

    static boolean compare(int[] bit1, int[] bit2){
        for (int i = 0; i < m; i++)
            if (bit1[i] == 1) {
                int left = Math.max(i - 1, 0);
                int right = Math.min(i + 1, m - 1);
                if (left != i)
                    if (bit1[left] == 1 || bit2[left] == 1) return false;
                if (right != i)
                    if (bit1[right] == 1 || bit2[right] == 1) return false;
            }
        return true;
    }

    static boolean isCheck(int t, int[] bit){
        for (int i = 0; i < m; i++)
            if (bit[i] == 1 && arr[t-1][i].equals("x")) return false;
        return true;
    }
    static int sum(int[] bit){
        int tmp = 0;
        for (int i : bit) tmp += i;
        return tmp;
    }
}
