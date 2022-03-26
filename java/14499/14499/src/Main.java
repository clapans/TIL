import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main{
  static int[] dx = {0,0,0,-1,1};
  static int[] dy = {0,1,-1,0,0};
  static int[] dice = {0,0,0,0,0,0};
  static int[][] arr = {{-1,5,4,1,3},{-1,5,4,2,0},{-1,5,4,3,1},
                        {-1,5,4,0,2},{-1,};


  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();
    int x = sc.nextInt();
    int y = sc.nextInt();
    int k = sc.nextInt();
    ArrayList<ArrayList<Integer>> map = new ArrayList<>();
    for (int i = 0;i < n; i++){
      ArrayList<Integer> tmp = new ArrayList<>();
      for (int j = 0;j < m; j++){
        tmp.add(sc.nextInt());
      }
      map.add(tmp);
    }
    ArrayList<Integer> command = new ArrayList<>();
    for (int i = 0;i < k; i++){
      command.add(sc.nextInt());
    }
    for (int i:command){
      if (0 < i & i < 5){
        x += dx[i];
        y += dy[i];
        
      }
      
    }
    HashMap<Integer,Integer> maps = new HashMap<>();
    maps.put(0,1);

  }
}
