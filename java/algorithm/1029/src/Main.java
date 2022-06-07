import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Node {
    int x,y,max,min;
    public Node(){};

    public Node(int x, int y, int max, int min) {
        this.x = x;
        this.y = y;
        this.max = max;
        this.min = min;
    }

    public boolean inside(int[] node){
        for (int[] n : this.visit){
            if (n[0] == node[0] && n[1] == node[1]) return false;
        }
        return true;
    }
}

public class Main {
    static int n;
    static String[][] arr;
    static int[][] altitude;

    static boolean[][] visit;
    static int[] dx = {1,-1,0,0,1,-1,1,-1};
    static int[] dy = {0,0,1,-1,-1,1,1,-1};
    static int res = (int)1e9;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = Integer.parseInt(sc.next());
        arr = new String[n][n];
        altitude = new int[n][n];
        visit = new boolean[n][n];
        Node node = new Node();
        for (int i = 0; i < n; i++){
            String[] tmp = sc.next().split("");
            for (int j = 0; j < n; j++) arr[i][j] = tmp[j];
        }
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                altitude[i][j] = Integer.parseInt(sc.next());
                if (arr[i][j].equals("P")) node = new Node(i,j,altitude[i][j],altitude[i][j]);
            }
        }
        bfs(node);
        System.out.println(res);
    }

    static void bfs(Node start){
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        while (queue.size() > 0){
            Node node = queue.poll();

            //visit[node.x][node.y][node.visit.size()] = node.max - node.min;
            //System.out.println(Integer.toString(node.x)+" "+Integer.toString(node.y)+" "+
            //        Integer.toString(node.visit.size())+" "+
            //        Integer.toString(node.max)+" "+Integer.toString(node.min));

            for (int t = 0; t < 8; t++){
                int x = node.x + dx[t];
                int y = node.y + dy[t];
                if (0 <= x && x < n && 0 <= y && y < n && !visit[x][y]){
                    int max = Math.max(node.max,altitude[x][y]);
                    int min = Math.min(node.min,altitude[x][y]);
                    if (max - min < visit[x][y][node.visit.size()]){
                        Node tmp = new Node(x,y,max,min);
                        if (arr[x][y].equals("K") && tmp.inside(new int[] {x,y})) tmp.visit.add(new int[] {x,y});
                        queue.add(tmp);
                        visit[x][y] = true;
                    }
                }
            }
        }
    }
}