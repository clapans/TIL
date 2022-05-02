import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Node{
    int x,y,use;
    Node(int x, int y, int use){
        this.x = x;
        this.y = y;
        this.use = use;
    }
}

public class Main {
    static int m,n;
    static int[][] arr;
    static int[][][] visit;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        sc.nextLine();
        arr = new int[m][n];
        visit = new int[m][n][2];
        for (int i = 0; i < m; i++){
            String[] tmp = sc.nextLine().split("");
            for (int j = 0; j < n; j++){
                int k = Integer.parseInt(tmp[j]);
                arr[i][j] = k;
                Arrays.fill(visit[i][j],(int)1e9);
            }
        }
        bfs();
        int tmp = Math.min(visit[m-1][n-1][0], visit[m-1][n-1][1]);
        if (tmp == (int)1e9){
            System.out.println(-1);
        }else {
            System.out.println(tmp);
        }
        sc.close();
    }

    static void bfs(){
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0,0,0));
        visit[0][0][0] = 1;
        while (queue.size() > 0){
            Node node = queue.poll();
            if (node.x == m-1 && node.y == n-1){
                break;
            }
            for (int t = 0; t < 4; t++){
                int nx = node.x + dx[t];
                int ny = node.y + dy[t];
                if (0 <= nx && nx < m && 0 <= ny && ny < n){
                    if (arr[nx][ny] == 0 && visit[nx][ny][node.use] == (int)1e9){
                        visit[nx][ny][node.use] = visit[node.x][node.y][node.use] + 1;
                        queue.add(new Node(nx,ny,node.use));
                    }else if (arr[nx][ny] == 1 && node.use == 0 && visit[nx][ny][1] == (int)1e9){
                        visit[nx][ny][1] = visit[node.x][node.y][node.use] + 1;
                        queue.add(new Node(nx,ny,1));
                    }
                }
            }
        }
    }
}
