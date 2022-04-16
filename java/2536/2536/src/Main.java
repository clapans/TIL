import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

class position{
  int x,y;
  public position(int x, int y){
    this.x = x;
    this.y = y;
  }
}

public class Main {
  static int m,n,k;
  static position start,end;
  static HashMap<Integer, ArrayList<int[]>> row = new HashMap<>();
  static HashMap<Integer, ArrayList<int[]>> column = new HashMap<>();
  static Queue<position> queue = new LinkedList<>();

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    m = sc.nextInt();
    n = sc.nextInt();
    k = sc.nextInt();
    for (int i = 0; i < k; i++){
      sc.nextInt();
      int[] tmp = new int[4];
      for (int j = 0; j < 4; j++){
        tmp[j] = sc.nextInt();
      }
      if (tmp[0] == tmp[2]){
        arrange_map(tmp[1], tmp[3], tmp[0], 'c');
      }
      else{
        arrange_map(tmp[0], tmp[2], tmp[1], 'r');
      }
    }
    start = new position(sc.nextInt(), sc.nextInt());
    end = new position(sc.nextInt(), sc.nextInt());

    sc.close();
  }

  static void arrange_map(int x, int y, int value, char r_or_c){
    if (r_or_c == 'r'){
      if (row.containsKey(value)){
        row.get(value).add(new int[] {x,y});
      }
      else{
        ArrayList<int[]> tmp = new ArrayList<>();
        tmp.add(new int[] {x,y});
        row.put(value, tmp);
      }
    }
    else{
      if (column.containsKey(value)){
        column.get(value).add(new int[] {x,y});
      }
      else{
        ArrayList<int[]> tmp = new ArrayList<>();
        tmp.add(new int[] {x,y});
        column.put(value, tmp);
      }
    }
  }

  static void bfs(){
    queue.offer(start);
    while (queue.size() > 0){
      position tmp = queue.poll();
      if (row.containsKey(tmp.y)){
        for (int[] i : row.get(tmp.y)){
          
        }
      }
    }
  }
}
