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
  static int r,c;
  static String[][] arr;
  static int[] dx = {1,-1,0,0};
  static int[] dy = {0,0,1,-1};
  static boolean[][] visit;
  static Queue<Node> gos = new LinkedList<>();
  static Queue<Node> water = new LinkedList<>();
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    r = sc.nextInt();
    c = sc.nextInt();
    sc.nextLine();
    arr = new String[r][c];
    visit = new boolean[r][c];
    for (int i = 0; i < r; i++){
      String[] tmp = sc.nextLine().split("");
      for (int j = 0; j < c; j++){
        arr[i][j] = tmp[j];
        if (tmp[j].equals("*")){
          water.offer(new Node(i,j));
        }
        else if (tmp[j].equals("S")){
          gos.offer(new Node(i,j));
        }
      }
    }
    bfs();
    sc.close();
  }
  
  static Integer bfs(){
    int time = 0;
    while (true){
      Queue<Node> water_tmp = new LinkedList<>();
      Queue<Node> gos_tmp = new LinkedList<>();
      while (water.size() >= 1){
        Node tmp = water.poll();
        for (int t = 0; t < 4; t++) {
          int nx = tmp.x + dx[t];
          int ny = tmp.y + dy[t];
          if (0 <= nx && nx < r && 0 <= ny && ny < c && visit[nx][ny] == false && arr[nx][ny].equals(".")){
            visit[nx][ny] = true;
            water_tmp.offer(new Node(nx,ny));
          } 
        }
      }
      
      while (gos.size() >= 1){
        Node tmp = gos.poll();
        for (int t = 0; t < 4; t++) {
          int nx = tmp.x + dx[t];
          int ny = tmp.y + dy[t];
          if (0 <= nx && nx < r && 0 <= ny && ny < c && visit[nx][ny] == false){
            if (arr[nx][ny].equals(".")){
              visit[nx][ny] = true;
              gos_tmp.offer(new Node(nx,ny));
            }
            else if (arr[nx][ny].equals("D")){
              System.out.println(time+1);
              return 0;
            }
          } 
        }
      }
      time += 1;
      for (Node i:water_tmp){
        water.offer(i);
      }
      for (Node i:gos_tmp){
        gos.offer(i);
      }
      if (gos.size() == 0){
        System.out.println("KAKTUS");
      return 0; 
      }  
    }
  } 
}