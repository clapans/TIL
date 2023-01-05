import java.util.Scanner;

public class Main {
    static int n,m;
    static boolean[][] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new boolean[n+1][n+1];
        for (int i = 0; i < m; i++)
            arr[sc.nextInt()][sc.nextInt()] = true;
        for (int k = 1; k < n+1; k++)
            for (int i = 1; i < n+1; i++)
                for (int j = 1; j < n+1; j++)
                    if (arr[i][k] && arr[k][j]) arr[i][j] = true;
        for (int i = 1; i < n+1; i++)
            System.out.println(notInTree(i));
    }

    public static int notInTree(int x) {
        int cnt = 0;
        for (int i = 1; i < n+1; i++) {
            if (i == x) continue;
            if (!arr[x][i] && !arr[i][x]) cnt++;
        }
        return cnt;
    }
}
