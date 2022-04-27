import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;

class Position{
  int x,y;
  public Position(int x, int y){
    this.x = x;
    this.y = y;
  }

  @Override
  public boolean equals(Object o) {
      if(this == o) return true;    
      if(!(o instanceof Position)) return false;      
      Position pos = (Position) o;
      return pos.x == x && pos.y == y;
  }

  @Override
  public int hashCode(){
    return Objects.hash(x,y);
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
  static int n,m,fuel;
  static int[][] map;
  static Position taxi;
  static HashMap<Position, Position> customer = new HashMap<>();
  static boolean[][] visit;
  static int[] dx = {1,-1,0,0};
  static int[] dy = {0,0,1,-1};

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    m = sc.nextInt();
    fuel = sc.nextInt();
    map = new int[n][n];
    visit = new boolean[n][n];
    for (int i = 0; i < n; i++){
      for (int j = 0; j < n; j++){
        map[i][j] = sc.nextInt();
      }
    }
    taxi = new Position(sc.nextInt()-1, sc.nextInt()-1);
    for (int i = 0; i < m; i++){
      Position start = new Position(sc.nextInt()-1, sc.nextInt()-1);
      Position end = new Position(sc.nextInt()-1, sc.nextInt()-1);
      customer.put(start,end);
    }
    while (true){
      if (customer.size() == 0){
        System.out.println(fuel);
        break;
      }
      if (!bfs(taxi)){
        System.out.println(-1);
        break;
      }
    }
    sc.close();
  }

  static Position is_close(ArrayList<Position> lst){
    Position pos = new Position((int)1e9, (int)1e9);
    for (Position i : lst){
      if (i.x < pos.x){
        pos.x = i.x;
        pos.y = i.y;
      }
      else if (i.x == pos.x){
        if (i.y < pos.y){
          pos.x = i.x;
          pos.y = i.y;
        }
      }
    }
    return pos;
  }

  static boolean contain_check(Position pos, ArrayList<Position> lst){
    for (Position i : lst){
      if (i.equals(pos)){
        return true;
      }
    }
    return false;
  }

  static boolean bfs(Position taxi){
    Queue<Node> queue = new LinkedList<>();
    queue.offer(new Node(taxi,0));
    for (boolean[] i : visit){
      Arrays.fill(i, false);
    }
    int limit = (int)1e9;
    ArrayList<Position> candidate = new ArrayList<>();
    while (queue.size() > 0){
      Node node = queue.poll();
      if (customer.containsKey(node.pos)){
        candidate.add(node.pos);
        limit = node.cnt;
      }
      if (node.cnt < limit){
        visit[node.pos.x][node.pos.y] = true;
        for (int t = 0; t < 4; t++){
          int nx = node.pos.x + dx[t];
          int ny = node.pos.y + dy[t];
          if (0 <= nx && nx < n && 0 <= ny && ny < n && !visit[nx][ny] && map[nx][ny] == 0){
            Position position = new Position(nx, ny);
            queue.add(new Node(position, node.cnt + 1));
          }
        }
      }
    }
    if (candidate.isEmpty()){
      return false;
    }
    Position start = is_close(candidate);
    Position destination = customer.get(start);
    customer.remove(start);
    queue.offer(new Node(start, 0));
    for (boolean[] i : visit){
      Arrays.fill(i, false);
    }
    while (queue.size() > 0){
      Node node = queue.poll();
      if (node.pos.x == destination.x && node.pos.y == destination.y){
        limit += node.cnt + 1;
        if (limit <= fuel){
          fuel -= limit;
          fuel += 2*(node.cnt + 1);
          taxi.x = destination.x;
          taxi.y = destination.y;
          return true;
        }
      }
      for (int t = 0; t < 4; t++){
        int nx = node.pos.x + dx[t];
        int ny = node.pos.y + dy[t];
        if (0 <= nx && nx < n && 0 <= ny && ny < n && !visit[nx][ny] && map[nx][ny] == 0){
          visit[nx][ny] = true;
          Position position = new Position(nx, ny);
          queue.add(new Node(position, node.cnt + 1));
        }
      }
    }
    return false;
  }
}