import java.util.ArrayList;
import java.util.Scanner;

public class Main{
  static int n;
  static int[][] arr;
  static int res = (int)1e9;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    arr = new int[n][n];
    for (int i = 0;i < n;i++){
      for (int j = 0;j < n;j++){
        arr[i][j] = sc.nextInt();
      }
    }
    ArrayList<Integer> home = new ArrayList<>();
    ArrayList<Integer> away = new ArrayList<>();
    dfs(0,home,away);
    System.out.println(res);
    sc.close();
  }

  static int synergy(ArrayList<Integer> lst){
    int total = 0;
    for (int i = 0;i < lst.size(); i++){
      for (int j = 0; j < lst.size(); j++){
        if (i != j){
          total += arr[lst.get(i)][lst.get(j)];
        }
      }
    }
    return total;
  }

  static void dfs(int x, ArrayList<Integer> home, ArrayList<Integer> away){
    if (x == n){
      res = Math.min(res,Math.abs(synergy(home)-synergy(away)));
    }
    else{
      if (home.size() < n/2){
        home.add(x);
        dfs(x+1,home,away);
        home.remove(home.size()-1);
      }
      if (away.size() < n/2){
        away.add(x);
        dfs(x+1,home,away);
        away.remove(away.size()-1);
      }
    }
  }
}