import java.util.Scanner;

public class Main {
    static int n,m;
    static int[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        dp = new int[n+1][n+1];
        for (int i = 0; i < n+1; i++)
            for (int j = 0; j < n+1; j++)
                dp[i][j] = (int)1e9;
        int min = (int)1e9;
        int res = 0;
        for (int i = 0; i < m; i++)
            friend(sc.nextInt(),sc.nextInt());
        mapFriend();
        for (int i = 1; i < n+1; i++){
            int tmp = kevinNum(i);
            if (tmp < min){
                res = i;
                min = tmp;
            }
        }
        System.out.println(res);
    }

    static void friend(int a, int b){
        dp[a][b] = 1;
        dp[b][a] = 1;
    }

    static void mapFriend(){
        for (int m = 1; m < n+1; m++)
            for (int i = 1; i < n+1; i++)
                for (int j = 1; j < n+1; j++)
                    dp[i][j] = Math.min(dp[i][j],dp[i][m] + dp[m][j]);
    }

    static int kevinNum(int num){
        int tmp = 0;
        for (int i = 1; i < n+1; i++){
            if (i != num) tmp += dp[num][i];
        }
        return tmp;
    }
}
