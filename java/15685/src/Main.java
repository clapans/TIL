import java.util.Scanner;

public class Main {
    static int[] dx = {0,-1,0,1};
    static int[] dy = {1,0,-1,0};
    static int[][] square = new int[101][101];
    static int res = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            int[] start = {y,x};
            square[start[0]][start[1]] = 1;
            int d = sc.nextInt();
            int g = sc.nextInt();
            start[0] += dx[d];
            start[1] += dy[d];
            square[start[0]][start[1]] = 1;
            makeDragon(start,new int[] {d}, g , 0);
        }
        for (int i = 0; i < 100; i++){
            for (int j = 0; j < 100; j++){
                if (isDragon(i,j)) res += 1;
            }
        }
        System.out.println(res);
    }

    static void makeDragon(int[] start, int[] dirs, int g, int cnt){
        if (g > cnt){
            int[] new_dirs = new int[2*dirs.length];
            for (int i = dirs.length; i > 0; i--) {
                if (dirs[i - 1] == 3){
                    new_dirs[2*dirs.length - i] = 0;
                }else{
                    new_dirs[2*dirs.length - i] = dirs[i-1] + 1;
                }
                start[0] += dx[new_dirs[2*dirs.length - i]];
                start[1] += dy[new_dirs[2*dirs.length - i]];
                square[start[0]][start[1]] = 1;
            }
            for (int i = 0; i < dirs.length; i++) {
                new_dirs[i] = dirs[i];
            }
            makeDragon(start,new_dirs,g,cnt + 1);
        }
    }

    static boolean isDragon(int x, int y){
        for (int i = x; i <= x+1; i++){
            for (int j = y; j <= y+1; j++){
                if (square[i][j] == 0) return false;
            }
        }
        return true;
    }
}