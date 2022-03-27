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
      a = parent[b[0]][b[1]];
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
    int [][] parent = new int[n][n];
    for (int i = 0;i < n;i++){
      for (int j = 0;j < n;j++){
        int tmp1 = Math.abs(arr.get(i).get(j) - arr.get(i).get(j+1));
        int tmp2 = Math.abs(arr.get(i+1).get(j) - arr.get(i).get(j));
        if (l <= tmp1 & tmp1 <= r){
          
        }
      }
    }
  }
}