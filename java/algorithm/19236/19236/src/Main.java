import java.util.ArrayList;
import java.util.Scanner;

class Fish{
  int dir;
  int[] pos;
  public Fish(int[] pos, int dir){
    this.dir = dir;
    this.pos = pos;
  }
}

public class Main{
  static int[][] arr = new int[4][4];
  static Fish[] num_arr = new Fish[16];
  static int res;
  static Fish shark;
  static int[] dx = {-1,-1,0,1,1,1,0,-1};
  static int[] dy = {0,-1,-1,-1,0,1,1,1};
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    for (int i = 0; i < 4; i++){
      for (int j = 0; j < 4; j++){
        int num = sc.nextInt();
        int dir = sc.nextInt();
        arr[i][j] = num;
        num_arr[num-1] = new Fish(new int[] {i,j}, dir);
      }
    }
    res += arr[0][0];
    shark = new Fish(new int[] {0,0}, num_arr[arr[0][0]].dir);
    remove(0,0);
    dfs(shark,0);
    System.out.println(res);
    sc.close();
  }

  static void remove(int x, int y){
    num_arr[arr[x][y]].pos = new int[] {-1,-1};
    num_arr[arr[x][y]].dir = -1; 
  }

  static void move(Fish fish){
    int[] pos = fish.pos;
    while (true){
      int nx = pos[0] + dx[fish.dir];
      int ny = pos[1] + dy[fish.dir];
      if (0 <= nx && nx < 4 && 0 <= ny && ny < 4 && (shark.pos[0] != pos[0] || shark.pos[1] != pos[1])){
        fish.pos = new int[] {nx,ny};
        num_arr[arr[nx][ny]].pos = pos;
        break;
      }
      if (fish.dir < 7){
        fish.dir += 1;
      }
      else{
        fish.dir = 0;
      }
    }
  }

  static ArrayList<Fish> find_fish(int[] pos, int dir){
    ArrayList<Fish> lst = new ArrayList<>();
    int cnt = 1;
    pos[0] += dx[cnt];
    pos[1] += dy[cnt];
    while (0 <= pos[0] && pos[0] < 4 && 0 <= pos[1] && pos[1] < 4){
      Fish tmp = num_arr[arr[pos[0]][pos[1]]];
      if (tmp.dir != -1){
        lst.add(tmp);
      }
    }
    return lst;
  }

  static void dfs(Fish shark, int part_sum){
    ArrayList<Fish> fishes = new ArrayList<>();
    Fish[] move_save = new Fish[16]; 
    for (int i = 0; i < 16; i++){
        move_save[i] = new Fish(num_arr[i].pos, num_arr[i].dir);
    }
    for (int i = 0; i < 16; i++){
      if (num_arr[i].dir != -1){
        move(num_arr[i]);
      }
    }
    fishes = find_fish(shark.pos, shark.dir);
    if (!fishes.isEmpty()){
      for (Fish fish : fishes){
        part_sum += arr[fish.pos[0]][fish.pos[1]];
        remove(fish.pos[0],fish.pos[1]);
        dfs(fish,part_sum);
        num_arr[arr[fish.pos[0]][fish.pos[1]]] = new Fish(shark.pos, shark.dir);
        part_sum -= arr[fish.pos[0]][fish.pos[1]];
      }
      for (int i = 0;i < 16; i++){
        num_arr[i] = new Fish(move_save[i].pos, move_save[i].dir);
      }
    }
    else{
      res = Math.max(res,part_sum);
    }
  }
}