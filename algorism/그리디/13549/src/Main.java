import java.util.*;

class Node implements Comparable<Node>{
    int num, cost;

    public Node(int num, int cost) {
        this.num = num;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.cost, o.cost);
    }
}

public class Main {
    static int n;
    static int k;
    static int[] dp = new int[100001];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        if (n >= k) System.out.println(n - k);
        else {
            Arrays.fill(dp,(int)1e9);
            dp[n] = 0;
            System.out.println(dijkstra());
        }
    }

    static int dijkstra() {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(n,0));
        while (queue.size() > 0) {
            Node node = queue.poll();
            if (node.num == k) return node.cost;
            if (node.cost > dp[node.num]) continue;
            if (node.num < 100000 && node.cost + 1 < dp[node.num + 1]) {
                dp[node.num + 1] = node.cost + 1;
                queue.add(new Node(node.num + 1, node.cost + 1));
            }
            if (node.num > 0 && node.cost + 1 < dp[node.num - 1]) {
                dp[node.num - 1] = node.cost + 1;
                queue.add(new Node(node.num - 1, node.cost + 1));
            }
            if (node.num <= 50000 && node.cost < dp[node.num * 2]) {
                dp[node.num * 2] = node.cost;
                queue.add(new Node(node.num * 2, node.cost));
            }
        }
        return 0;
    }
}
