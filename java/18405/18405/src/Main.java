import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Node{
  int x,y,cnt;
  public Node(int x, int y, int cnt){
    this.x = x;
    this.y = y;
    this.cnt = cnt;
  }
}

public class Main{
  static int n,k;
  static int[][] arr;
  static int[] dx = {1,-1,0,0};
  static int[] dy = {0,0,1,-1};
  static int[][] visit;
  static int limit;
  static Queue<Node> queue = new LinkedList<>();

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    k = sc.nextInt();
    arr = new int[n][n];
    visit = new int[n][n];
    for (int[] i : visit){
      Arrays.fill(i, (int)1e9);
    }
    for (int i = 0; i < n; i++){
      for (int j = 0; j < n; j++){
        arr[i][j] = sc.nextInt();
        if (arr[i][j] > 0){
          queue.offer(new Node(i,j,1));
          visit[i][j] = 1;
        }
      }
    }
    limit = sc.nextInt();
    int[] pos = {sc.nextInt(),sc.nextInt()};
    bfs();
    System.out.println(arr[pos[0]-1][pos[1]-1]);
    sc.close();
  }

  static void bfs(){
    while (queue.size() > 0){
      Node tmp = queue.poll();
      if (tmp.cnt <= limit){
        for (int t = 0; t < 4; t++){
          int nx = tmp.x + dx[t];
          int ny = tmp.y + dy[t];
          if (0 <= nx && nx < n && 0 <= ny && ny < n){
            if (visit[nx][ny] == tmp.cnt+1){
              if (arr[tmp.x][tmp.y] < arr[nx][ny]){
                arr[nx][ny] = arr[tmp.x][tmp.y];
                visit[nx][ny] = tmp.cnt+1; 
                queue.offer(new Node(nx,ny,tmp.cnt + 1));
              }
            }
            else if (visit[nx][ny] > tmp.cnt+1){
              arr[nx][ny] = arr[tmp.x][tmp.y];
              visit[nx][ny] = tmp.cnt+1; 
              queue.offer(new Node(nx,ny,tmp.cnt + 1));
            } 
          }
        }
      }
    }
  }
}