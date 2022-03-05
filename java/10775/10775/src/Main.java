import java.util.Scanner;

public class Main {
  public static int find(int[] parent, int x){
    if (parent[x] == x){
      return x;
    }
    parent[x] = find(parent,parent[x]);
    return parent[x];
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int[] gate = new int[n+1];
    for (int i=0;i<n+1;i++){
      gate[i] = i;
    }
    int plane = sc.nextInt();
    int[] dock = new int[plane];
    for (int i=0;i<plane;i++){
      dock[i] = sc.nextInt();
    }
    int cnt = 0;
    for (int i : dock){
      int tmp = find(gate,i);
      if (tmp==0){
        break;
      }
      gate[tmp] = gate[tmp-1];
      cnt += 1;
    }
    System.out.printf("%d",cnt);
    for (int i : gate){
      System.out.printf("%d",i);
    }
  }
}

