import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main{
  static int n;
  static ArrayList<Integer>[] arr;
  static Queue<Integer> queue = new LinkedList<>();
  static boolean[] visit;
  static ArrayList<Integer> cycle = new ArrayList<>();

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    arr = new ArrayList[n+1];
    visit = new boolean[n+1];
    for (int i = 0; i < n; i++) {
      arr[i] = new ArrayList<>();
    }

    for (int i = 0; i < n; i++) {
      int a = sc.nextInt();
      int b = sc.nextInt();
      arr[a].add(b);
      arr[b].add(a);
    }  
  }

  static void check_cycle(int x){
    for (int i : arr[x]){
      
    }
  }
}