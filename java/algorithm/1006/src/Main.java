import java.util.Scanner;

public class Main {
    static int n,w;
    static int[][] enemy;
    static int[] dp;
    static int res = (int)1e9;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for (int i = 0; i < tc; i++){
            n = sc.nextInt();
            w = sc.nextInt();
            enemy = new int[2][n];
            dp = new int[n+1];
            for (int j = 0; j < 2; j++)
                for (int k = 0; k < n; k++)
                    enemy[j][k] = sc.nextInt();
            dp[1] = 2;
            if (isCheck(1,0,1)) dp[1]--;
            for (int j = 2; j < n; j++){

            }
            dfs(0,arr,0);
            System.out.println(res);
        }
    }
    static boolean isCheck(int x,int y, int dir){
        if (dir == 0){
            return enemy[x][y] + enemy[x][y-1] <= w;
        }else{
            return enemy[x][y] + enemy[x-1][y] <= w;
        }
    }

    static void dfs(int v, boolean[] arr, int cnt){
        if (v == 2*n){
            res = Math.min(res,2*n-cnt);
        }else{
            if (arr[v]) {
                dfs(v + 1, arr, cnt);
            } else {
                int tmp = w - enemy[v];
                boolean check = true;
                int next = v + 1;
                if (v == n-1 || v == 2*n-1) next = v - (n - 1);
                if (tmp >= enemy[next]) {
                    check = false;
                    arr[next] = true;
                    arr[v] = true;
                    dfs(v + 1, arr, cnt + 1);
                    arr[next] = false;
                    arr[v] = false;
                }
                if (v < n && tmp >= enemy[v + n]) {
                    check = false;
                    arr[v + n] = true;
                    arr[v] = true;
                    dfs(v + 1, arr, cnt + 1);
                    arr[v + n] = false;
                    arr[v] = false;
                }
                if (check) dfs(v+1,arr,cnt);
            }
        }
    }
}
