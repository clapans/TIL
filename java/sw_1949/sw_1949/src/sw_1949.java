import java.util.*;

public class sw_1949 {
  static int res = 0;
  static int[] dx = {1,-1,0,0};
  static int[] dy = {0,0,1,-1};

  public static void dfs(int x, int y){
    for (int i=0;i<4;i++){
      int nx = x + dx[i];
      int ny = y + dy[i];
      if (0 <= nx < n && 0 <= ny < n){
        
      }
    }  
  }
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int t_case = sc.nextInt();
    for (int i=0;i<t_case;i++){
      int n = sc.nextInt();
      int k = sc.nextInt();
      int max = 0;
      int[][] arr = new int[n][n];
      for (int j=0;j<n;j++){
        for (int t=0;t<n;t++){
          int tmp = sc.nextInt();
          arr[j][t] = tmp;
          if (tmp > max){
            max = tmp;
          }
        }
      }
      for (int j=0;j<n;j++){
        for (int t=0;t<n;t++){
          if (arr[j][t] == max){
            int[][] visit = new int[n][n];
            dfs(j,t);
          }
        }
      }
    }
  }
  
}
