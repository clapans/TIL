import java.util.ArrayList;
import java.util.Scanner;

public class Main{
  static int n;
  static ArrayList<int[]> able;
  static int[] dx = {1,-1,1,-1};
  static int[] dy = {-1,1,1,-1};
  static int res = 0;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    int[][] arr = new int[n][n];
    for (int i = 0; i < n; i++){
      for (int j = 0; j < n; j++){
        int tmp = sc.nextInt();
        arr[i][j] = tmp;
        if (tmp == 1){
          able.add(new int[]{i,j});
        }
      }
    }
    bishop(0, arr, 0);
    sc.close();
  }

  static boolean check(int x, int y, int[][] arr){
    for (int i = 0; i < 4; i++){
      int cnt = 1;
      while(true){
        int nx = x + cnt * dx[i];
        int ny = y + cnt * dy[i];
        if (nx >= 0 && nx < n && ny >= 0 && ny < n){
          if (arr[nx][ny] == 2){
            return false;
          }
        }
        else{
          break;
        }
        cnt += 1;
      }
    }
    return true;
  }

  static void bishop(int x, int[][] arr, int cnt){
    if (x == n){
      res = Math.max(res,cnt);
    }
    else{
      int[] pos = able.get(x);
      if (check(pos[0],pos[1],arr)){
        arr[pos[0]][pos[1]] = 2;
        bishop(x+1, arr, cnt+1);
        arr[pos[0]][pos[1]] = 1;
      }
      bishop(x+1, arr, cnt+1);
    }
  }
}