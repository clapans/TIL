import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Node{
  int x,y,cnt;
  public Node(int x,int y,int cnt){
    this.x = x;
    this.y = y;
    this.cnt = cnt;
  }
}

public class Main {
  static int n;
  static int[][] arr;
  static int[] dx = {-1,0,0,1};
  static int[] dy = {0,-1,1,0};
  static int[] shark = new int[2];
  static int shark_size = 2;
  static boolean[][] visit;
  static int cnt = 0;
  static int time_cnt = 0;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    arr = new int[n][n];
    visit = new boolean[n][n];
    for (int i = 0; i < n; i++){
      for (int j = 0; j < n; j++){
        arr[i][j] = sc.nextInt();
        if (arr[i][j] == 9){
          shark[0] = i;
          shark[1] = j;
          arr[i][j] = 0;
        }
      }
    }
    while (true){
      if(bfs()){
        System.out.println(time_cnt);
        break;
      }
    }
    sc.close();
  }
  
  static boolean bfs(){
    Queue<Node> queue = new LinkedList<>();
    queue.offer(new Node(shark[0],shark[1],0));
    int[] eat = {(int)1e9,(int)1e9};
    int limit = (int)1e9;
    for (boolean[] i : visit){
      Arrays.fill(i, false);
    }
    visit[shark[0]][shark[1]] = true;
    while (queue.size() >= 1){
      Node tmp = queue.poll();
      for (int i = 0; i < 4; i++){
        int nx = tmp.x + dx[i];
        int ny = tmp.y + dy[i];
        if (0 <= nx && nx < n && 0 <= ny && ny < n && !visit[nx][ny]){
          if (arr[nx][ny] <= shark_size){
            queue.offer(new Node(nx,ny,tmp.cnt+1));
            visit[nx][ny] = true;
            if (arr[nx][ny] != 0 && arr[nx][ny] < shark_size && tmp.cnt < limit){
              if (nx <eat[0]){
                eat = new int[] {nx,ny};
              }
              else if (nx == eat[0] && ny < eat[1]){
                eat = new int[] {nx,ny};
              }
              limit = tmp.cnt + 1;
            }
          }
        }
      }
    }

    if (eat[0] != (int)1e9 && eat[1] != (int)1e9){
      shark[0] = eat[0];
      shark[1] = eat[1];
      arr[eat[0]][eat[1]] = 0;
      cnt += 1;
      time_cnt += limit;
      if (cnt == shark_size){
        shark_size += 1;
        cnt = 0;
      }
      return false;
    }
    return true;
  }
}