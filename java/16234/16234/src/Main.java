import java.util.ArrayList;
import java.util.Arrays;
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
  static int check;

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

    int cnt = 0;

    while (true){
      check = 0;
      for (boolean[] i : visit){
        Arrays.fill(i,false);
      }
      for (int i = 0;i < n;i++){
        for (int j = 0; j< n;j++){
          if (!visit[i][j]){
            visit[i][j] = true;
            bfs(i,j);
          }
        }
      }
      if (check == 0){
        break;
      }
      else{
        cnt += 1;
      }
    }
    System.out.println(cnt);
    sc.close();
  }

  static void bfs(int x,int y){
    queue.offer(new Node(x, y));
    ArrayList<Node> union = new ArrayList<>();
    union.add(new Node(x,y));
    int sum = arr[x][y];
    while (queue.size() >= 1){
      Node tmp = queue.poll();
      for (int i = 0;i < 4;i++){
        int nx = tmp.x + dx[i];
        int ny = tmp.y + dy[i];
        if (0 <= nx && nx < n && 0 <= ny && ny < n && !visit[nx][ny]){
          int distance = Math.abs(arr[tmp.x][tmp.y] - arr[nx][ny]);
          if (l <= distance && distance <= r){
            check = 1;
            visit[nx][ny] = true;
            queue.add(new Node(nx,ny));
            union.add(new Node(nx,ny));
            sum += arr[nx][ny];
          }
        }
      }
    }
    int avg = sum/union.size();
    for (Node i : union){
      arr[i.x][i.y] = avg;
    }
  }
}