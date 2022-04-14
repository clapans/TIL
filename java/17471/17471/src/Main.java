import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main{
  static int n;
  static int[] population;
  static ArrayList<Integer>[] map;
  static Stack<Integer> red = new Stack<>();
  static Stack<Integer> blue = new Stack<>();
  static Queue<Integer> queue = new LinkedList<>();
  static boolean[] visit;
  static int res = (int)1e9;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    population = new int[n];
    map = new ArrayList[n];
    visit = new boolean[n];
    for (int i = 0; i < n; i++){
      population[i] = sc.nextInt();
    }
    for (int i = 0; i < n; i++){
      int tmp = sc.nextInt();
      map[i] = new ArrayList<>();
      for (int j = 0; j < tmp; j++){
        map[i].add(sc.nextInt()-1);
      }
    }
    combi(0);
    if (res == (int)1e9){
      System.out.println(-1);
    }
    else{
      System.out.println(res);
    }
    sc.close();
  }

  static void combi(int x){
    if (x == n){
      if (red.size() < n && blue.size() < n){
        res = Math.min(res,bfs(red,blue));
      }
    }
    else{
      red.push(x);
      combi(x+1);
      red.pop();
      blue.push(x);
      combi(x+1);
      blue.pop();
    }
  }

  static boolean contain_check(int x, Stack<Integer> lst){
    for (int i: lst){
      if (x == i){
        return true;
      }
    }
    return false;
  }

  static int bfs(Stack<Integer> red, Stack<Integer> blue){
    queue.offer(red.peek());
    Arrays.fill(visit, false);
    while (queue.size() > 0){
      int tmp = queue.poll();
      visit[tmp] = true;
      for (int i : map[tmp]){
        if (!visit[i] && contain_check(i, red)){
          queue.add(i);
        }
      }
    }
    for (int i : red){
      if (!visit[i]){
        return (int)1e9;
      }
    }
    Arrays.fill(visit, false);
    queue.offer(blue.peek());
    while (queue.size() > 0){
      int tmp = queue.poll();
      visit[tmp] = true;
      for (int i : map[tmp]){
        if (!visit[i] && contain_check(i, blue)){
          queue.add(i);
        }
      }
    }
    for (int i : blue){
      if (!visit[i]){
        return (int)1e9;
      }
    }
    int red_sum = 0;
    int blue_sum = 0;
    for (int i = 0; i < n; i++){
      if (contain_check(i, red)){
        red_sum += population[i];
      }
      else{
        blue_sum += population[i];
      }
    }
    return Math.abs(red_sum - blue_sum);
  }
}