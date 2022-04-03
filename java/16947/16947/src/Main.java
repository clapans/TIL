import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main{
  static int n;
  static ArrayList<Integer>[] arr;
  static Queue<Integer> queue = new LinkedList<>();
  static boolean[] visit;
  static ArrayList<Integer> cycle = new ArrayList<>();
  static boolean[] isCycle;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    arr = new ArrayList[n+1];
    visit = new boolean[n+1];
    isCycle = new boolean[n+1];
    Arrays.fill(isCycle, true);
    for (int i = 0; i < n; i++) {
      arr[i] = new ArrayList<>();
    }

    for (int i = 0; i < n; i++) {
      int a = sc.nextInt();
      int b = sc.nextInt();
      arr[a].add(b);
      arr[b].add(a);
    }
    for (int i = 0; i < n; i++) {
      check_cycle(i, i);
    }  
  }

  static void check_cycle(int x, int start){
    if (isCycle[start]){
      for (int i : arr[x]){
        if (!visit[i]){
          check_cycle(i,start);
        }
        else if (i == start){
          isCycle[start] = false;
        }
      }
    }
  }
}