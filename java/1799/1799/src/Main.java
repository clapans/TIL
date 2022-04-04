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
  static int tmp_res = 0;
  static int res = 0;
  static int[][] arr;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    arr = new int[n][n];
    ArrayList<Bishop> black = new ArrayList<>();
    ArrayList<Bishop> white = new ArrayList<>();
    boolean isBlack = true;
    for (int i = 0; i < n; i++){
      for (int j = 0; j < n; j++){
        int tmp = sc.nextInt();
        arr[i][j] = tmp;
        if (tmp == 1){
          if (isBlack){
            black.add(new Bishop(i, j));
          }
          else{
            white.add(new Bishop(i, j));
          }
        }
        if (isBlack){
          isBlack = false;
        }
        else{
          isBlack = true;
        }
      }
    }
    bishop(0, 0, black);
    res += tmp_res;
    tmp_res = 0;
    bishop(0, 0, white);
    res += tmp_res;
    System.out.println(res);
    sc.close();
  }

  static boolean check(int x, int y){
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

  static void bishop(int t, int cnt, ArrayList<Bishop> able){
    if (t == able.size()){
      tmp_res = Math.max(tmp_res,cnt);
    }
    else{
      Bishop pos = able.get(t);
      if (check(pos.x,pos.y)){
        arr[pos.x][pos.y] = 2;
        bishop(t+1, cnt+1, able);
        arr[pos.x][pos.y] = 1;
      }
      bishop(t+1, cnt, able);
    }
  }
}