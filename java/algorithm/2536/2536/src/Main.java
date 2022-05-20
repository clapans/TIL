import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Node{
  int x,y;
  Node(int x, int y){
    this.x = x;
    this.y = y;
  }
}

public class Main {
  static int m,n,k;
  static HashMap<String,ArrayList<Integer>> map = new HashMap<>();
  static Node[][] buses;
  static HashMap<String,Integer> visit = new HashMap<>(); 
  static Node depart, desti;
  static int[] dx = {1,-1,0,0};
  static int[] dy = {0,0,1,-1};
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    m = sc.nextInt();
    n = sc.nextInt();
    k = sc.nextInt();
    buses = new Node[k][2];
    for (int i = 0; i < k; i++){
      int num = sc.nextInt();
      Node start = new Node(sc.nextInt(), sc.nextInt());
      Node end = new Node(sc.nextInt(), sc.nextInt());
      bus(start,end,num);
      buses[i][0] = start;
      buses[i][1] = end;
    }
    depart = new Node(sc.nextInt(),sc.nextInt());
    desti = new Node(sc.nextInt(), sc.nextInt());
    for (String i : map.keySet()){
      System.out.println(i);
    }
    sc.close();
  }

  static void bus(Node start, Node end, int num){
    if (start.x == end.x){
      for (int i = Math.min(start.y,end.y); i <= Math.max(start.y,end.y); i++){
        String tmp = Arrays.toString(new int[] {start.x,i});
        if (map.containsKey(tmp)){
          map.get(tmp).add(num);
        }
        else{
          ArrayList<Integer> lst = new ArrayList<>();
          lst.add(num);
          map.put(tmp,lst);
        }
      }
    }
    else {
      for (int i = Math.min(start.x,end.x); i <= Math.max(start.x,end.x); i++){
        String tmp = Arrays.toString(new int[] {i, start.y});
        if (map.containsKey(tmp)){
          map.get(tmp).add(num);
        }
        else{
          ArrayList<Integer> lst = new ArrayList<>();
          lst.add(num);
          map.put(tmp,lst);
        }
      }
    }
  }

  static void bfs(){
    Queue<Node> queue = new LinkedList<>();
    queue.offer(depart);
    while (queue.size() > 0){
      Node node = queue.poll();
      String str = new int[] {node.x, node.y}.toString();
      if (map.containsKey(str)){
        for (int i : map.get(str)){
          Node start = buses[i][0];
          Node end = buses[i][1];
          if (start.x == end.x){
            start.x;
          }
        }
      }
    }
  }
}