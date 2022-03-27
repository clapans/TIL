import java.util.ArrayList;
import java.util.Scanner;

public class Main{
  public static int[] find(int[] x, int[][][] parent){
    int a = x[0];
    int b = x[1];
    if (x != parent[a][b]){
      return find(parent[a][b],parent);
    }
    return x;
  }

  public static void union(int[] x, int[] y, int[][][] parent){
    int[] a = find(x,parent);
    int[] b = find(y,parent);
    if (a != b){
      parent[b[0]][b[1]] = a;
    }
  }
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n,l,r;
    n = sc.nextInt();
    l = sc.nextInt();
    r = sc.nextInt();
    ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
    for (int i = 0;i < n;i++){
      ArrayList<Integer> tmp = new ArrayList<>();
      for (int j = 0;j < n;j++){
        tmp.add(sc.nextInt());
      }
      arr.add(tmp);
    }
    int [][][] parent = new int[n][n][2];
    for (int i = 0;i < n;i++){
      for (int j = 0;j < n;j++){
        parent[i][j][0] = i;
        parent[i][j][1] = j;
      }
    }

    for (int i = 0;i < n;i++){
      for (int j = 0;j < n;j++){
        if (j < n-1){
          int tmp1 = Math.abs(arr.get(i).get(j) - arr.get(i).get(j+1));
          if (l <= tmp1 & tmp1 <= r){
            int[] x = {i,j};
            int[] y = {i,j+1};
            union(x,y,parent);
          }
        }
        if (i < n-1){
          int tmp2 = Math.abs(arr.get(i+1).get(j) - arr.get(i).get(j));     
          if (l <= tmp2 & tmp2 <= r){
            int[] x = {i,j};
            int[] y = {i+1,j};
            union(x,y,parent);
          }
        }
      }
    }
  }
}