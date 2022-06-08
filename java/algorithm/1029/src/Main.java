import java.util.*;

class Node {
    int x,y;
    public Node(){};

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int n;
    static int max = 0;
    static int min = (int)1e9;
    static String[][] arr;
    static int[][] altitude;
    static int houseCnt = 0;
    static int res = (int)1e9;
    static int[] dx = {1,-1,0,0,1,-1,1,-1};
    static int[] dy = {0,0,1,-1,-1,1,1,-1};
    static Node start = new Node();
    static Set<Integer> set = new HashSet<>();
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static ArrayList<Integer> lst = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = Integer.parseInt(sc.next());
        arr = new String[n][n];
        altitude = new int[n][n];

        for (int i = 0; i < n; i++){
            String[] tmp = sc.next().split("");
            for (int j = 0; j < n; j++){
                arr[i][j] = tmp[j];
                if (arr[i][j].equals("K")) houseCnt++;
            }
        }
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                altitude[i][j] = Integer.parseInt(sc.next());
                if (arr[i][j].equals("P")) start = new Node(i,j);
                if (arr[i][j].equals("K")){
                    max = Math.max(max,altitude[i][j]);
                    min = Math.min(min,altitude[i][j]);
                }
                set.add(altitude[i][j]);
            }
        }
        for (int i : set) lst.add(i);
        lst.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        int startIx = 0;
        int endIx = 0;

        while (startIx < lst.size() && endIx < lst.size()){
            int s = lst.get(startIx);
            int e = lst.get(endIx);
            if (altitude[start.x][start.y] > e || altitude[start.x][start.y] < s) {
                endIx++;
                continue;
            }
            if (bfs(s,e) == houseCnt){
                res = Math.min(res,e - s);
                startIx++;
            }else{
                endIx++;
            }
        }
        System.out.println(res);
    }

    static int bfs(int min, int max){
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        int cnt = 0;
        boolean[][] visit = new boolean[n][n];
        visit[start.x][start.y] = true;
        while (queue.size() > 0){
            if (cnt == houseCnt) return cnt;
            Node node = queue.poll();
            if (arr[node.x][node.y].equals("K")) cnt++;
            for (int t = 0; t < 8; t++){
                int x = node.x + dx[t];
                int y = node.y + dy[t];
                if (0 <= x && x < n && 0 <= y && y < n && !visit[x][y])
                    if (min <= altitude[x][y] && altitude[x][y] <= max){
                        queue.add(new Node(x,y));
                        visit[x][y] = true;
                    }
            }
        }
        return cnt;
    }
}