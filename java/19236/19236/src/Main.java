import java.util.Scanner;

class Fish{
  int num,dir;
  public Fish(int num, int dir){
    this.dir = dir;
    this.num = num;
  }
}

public class Main{
  static Fish[][] arr = new Fish[4][4];
  static Fish[] num_arr = new Fish[16];
  static int[] dx = {-1,-1,0,1,1,1,0,-1};
  static int[] dy = {0,-1,-1,-1,0,1,1,1};
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    for (int i = 0; i < 4; i++){
      for (int j = 0; j < 4; j++){
        int num = sc.nextInt();
        int dir = sc.nextInt();
        arr[i][j] = new Fish(num, dir);
      }
    }

    sc.close();
  }

  static void move(int[] pos, int dir){
    int nx = pos[0] + dx[dir];
    int ny = pos[1] + dy[dir];
    if (0 <= nx && nx < 4 && 0 <= ny && ny < 4){
      Fish tmp = arr[]
    }
  }
}