import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main{
  static int n;
  static ArrayList<Integer>[] arr;
  static Queue<Integer> queue = new LinkedList<>();
  static boolean[] visit;
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

  static void bfs(){
    queue.offer(1);
    while (queue.size() >= 1){
      int x = queue.poll();
      for (int i:arr[x]){
        if (!visit[i]){
          visit[i] = true;
          queue.offer(i);
        }
        else {
          
        }
      }
    }
  }
}