import java.util.ArrayList;
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

public class Main {
  static int n;
  static int m;
  static int[][] arr;
  static ArrayList<int[]> virus = new ArrayList<>();
  static ArrayList<int[]> combi = new ArrayList<>();
  static int[] dx = {1,-1,0,0};
  static int[] dy = {0,0,1,-1};
  static int result = (int)1e9;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    m = sc.nextInt();
    arr = new int[n][n];
    for (int i = 0; i < n; i++){
      for (int j = 0; j < n; j++){
        arr[i][j] = sc.nextInt();
        if (arr[i][j] == 2){
          virus.add(new int[] {i,j});
        }
      }
    }
    comb(0);
    if (result == (int)1e9){
      System.out.println(-1);
    }
    else{
      System.out.println(result);
    }
    sc.close();
  }

  static void comb(int x){
    if (combi.size() == m){
      result = Math.min(result,bfs(combi));
    }
    else{
      if (x < virus.size()){
        combi.add(virus.get(x));
        comb(x+1);
        combi.remove(combi.size()-1);
        comb(x+1);
      }
    }
  }

  static int bfs(ArrayList<int[]> lst){
    int res = 0;
    int[][] visit = new int[n][n];
    for (int[] i : visit){
      Arrays.fill(i, (int)1e9);
    }
    Queue<Node> queue = new LinkedList<>();
    for (int[] i : lst){
      queue.offer(new Node(i[0],i[1],0));
      visit[i[0]][i[1]] = 0;
    }
    while (queue.size() > 0){
      Node tmp = queue.poll();
      for (int i = 0; i < 4; i++){
        int nx = tmp.x + dx[i];
        int ny = tmp.y + dy[i];
        if (0 <= nx && nx < n && 0 <= ny && ny < n && visit[nx][ny] > tmp.cnt+1){
          if (arr[nx][ny] != 1){
            visit[nx][ny] = tmp.cnt+1;
            queue.add(new Node(nx,ny,tmp.cnt+1));
          }
        }
      }
    }
    for (int i = 0; i < n; i++){
      for (int j = 0; j < n; j++){
        if (visit[i][j] == (int)1e9){
          if (arr[i][j] != 1){
            return (int)1e9;
          }
        }
        else {
          res = Math.max(res,visit[i][j]);
        }
      }
    }
    return res;
  }
}