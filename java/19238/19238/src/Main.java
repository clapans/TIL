import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Position{
  int x,y;
  public Position(int x, int y){
    this.x = x;
    this.y = y;
  }
}

class Customer{
  Position start,end;
  public Customer(Position x, Position y){
    this.start = x;
    this.end = y;
  }
}

class Node{
  Position pos;
  int cnt;
  public Node(Position pos, int cnt){
    this.pos = pos;
    this.cnt = cnt;
  }
}

public class Main{
  static int n,m,gas;
  static int[][] map;
  static Position taxi;
  static Customer[] customer;
  static boolean[][] visit;
  static int[] dx = {1,-1,0,0};
  static int[] dy = {0,0,1,-1};

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    m = sc.nextInt();
    gas = sc.nextInt();
    map = new int[n][n];
    customer = new Customer[m];
    for (int i = 0; i < n; i++){
      for (int j = 0; j < n; j++){
        map[i][j] = sc.nextInt();
      }
    }
    taxi = new Position(sc.nextInt(), sc.nextInt());
    for (int i = 0; i < m; i++){
      Position start = new Position(sc.nextInt(), sc.nextInt());
      Position end = new Position(sc.nextInt(), sc.nextInt());
      customer[i] = new Customer(start, end);
    }
    sc.close();
  }

  static void bfs(Position taxi){
    Queue<Node> queue = new LinkedList<>();
    queue.offer(new Node(taxi,0));
    for (boolean[] i : visit){
      Arrays.fill(i, false);
    }
    while (queue.size() > 0){
      Node node = queue.poll();
      for (int t = 0; t < 4; t++){
        int nx = node.pos.x + dx[t];
        int ny = node.pos.y + dy[t];
        if (0 <= nx && nx < n && 0 <= ny && ny < n && !visit[nx][ny]){
          visit[nx][ny] = true;
          queue.add(new Node(new Position(nx, ny), node.cnt + 1));
          
        }
      }
    }
  }
}