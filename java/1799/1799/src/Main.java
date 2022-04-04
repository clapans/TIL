import java.util.ArrayList;
import java.util.Scanner;

class Bishop{
  int x,y;
  public Bishop(int x, int y){
    this.x = x;
    this.y = y;
  }
}

public class Main{
  static int n;
  static int[] dx = {-1,-1};
  static int[] dy = {1,-1};
  static int res = 0;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    int[][] arr = new int[n][n];
    ArrayList<Bishop> able = new ArrayList<>();
    for (int i = 0; i < n; i++){
      for (int j = 0; j < n; j++){
        int tmp = sc.nextInt();
        arr[i][j] = tmp;
        if (tmp == 1){
          able.add(new Bishop(i, j));
        }
      }
    }
    bishop(0, arr, 0, able);
    System.out.println(res);
    sc.close();
  }

  static boolean check(int x, int y, int[][] arr){
    for (int i = 0; i < 2; i++){
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

  static void bishop(int t, int[][] arr, int cnt, ArrayList<Bishop> able){
    if (t == able.size()){
      res = Math.max(res,cnt);
    }
    else{
      Bishop pos = able.get(t);
      if (check(pos.x,pos.y,arr)){
        arr[pos.x][pos.y] = 2;
        bishop(t+1, arr, cnt+1, able);
        arr[pos.x][pos.y] = 1;
      }else{
        bishop(t+1, arr, cnt, able);
      }
    }
  }
}