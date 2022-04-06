import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Node{
  int x,y;
  public Node(int x, int y){
    this.x = x;
    this.y = y;
  }
}

public class Main{
  static int n,l,r;
  static int[][] arr;
  static boolean[][] visit;
  static Queue<Node> queue = new LinkedList<>();
  static int[] dx = {1,-1,0,0};
  static int[] dy = {0,0,1,-1};

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    n = sc.nextInt();
    l = sc.nextInt();
    r = sc.nextInt();
    arr = new int[n][n];
    visit = new boolean[n][n];

    for (int i = 0;i < n;i++){
      for (int j = 0;j < n;j++){
        arr[i][j] = sc.nextInt();
      }
    }

    for (int i = 0;i < n;i++){
      for (int j = 0; j< n;j++){
        if (!visit[i][j]){
          visit[i][j] = true;
          bfs(i,j);
        }
      }
    }
    sc.close();
  }

  static void bfs(int x,int y){
    queue.offer(new Node(x, y));
    while (queue.size() >= 1){
      Node tmp = queue.poll();
      for (int i = 0;i < 4;i++){
        int nx = tmp.x + dx[i];
        int ny = tmp.y + dy[i];
        if (0 <= nx && nx < n && 0 <= ny && ny < n && !visit[ny][ny]){
          int distance = Math.abs(arr[x][y] - arr[nx][ny])
          if (l <= distance && distance <= r){
            
          }
        }
      }
    }
  }
}