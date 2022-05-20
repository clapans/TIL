import java.util.Scanner;

public class Main{
  static int[][] arr = new int[10][10];
  static int res = (int)1e9;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    for (int i = 0; i < 10; i++){
      for (int j = 0; j < 10; j++){
        arr[i][j] = sc.nextInt();
      }
    }
    dfs(0,0,new int[] {5,5,5,5,5},0);
    if (res == (int)1e9){
      System.out.println(-1);
    }
    else{
      System.out.println(res);
    }
    sc.close();
  }

  static boolean is_check(int x, int y, int n, int[][] arr){
    if (x+n > 10 || y+n > 10){
      return false;
    }
    for (int i = x; i < x+n; i++){
      for (int j = y; j < y+n; j++){
        if (arr[i][j] == 0){
          return false;
        }
      }
    }
    return true;
  }

  static void fill_arr(int x, int y, int n, int value){
    for (int i = x; i < x+n; i++){
      for (int j = y; j < y+n; j++){
        arr[i][j] = value;
      }
    }
  }

  static void dfs(int x, int y, int[] use, int cnt){
    while (x < 10 && arr[x][y] == 0){
      if (y == 9){
        x += 1;
        y = 0;
      }
      else{
        y += 1;
      }
    }
    if (x == 10){
      res = Math.min(res,cnt);
    }
    else{
      for (int t = 1; t < 6; t++){
        if (use[t-1] > 0 && is_check(x,y,t,arr)){
          fill_arr(x,y,t,0);
          use[t-1] -= 1;
          if (y == 9){
            dfs(x+1,0,use,cnt+1);
          }
          else{
            dfs(x,y+1,use,cnt+1);
          }
          use[t-1] += 1;
          fill_arr(x,y,t,1);
        }
      }
    }
  }
}