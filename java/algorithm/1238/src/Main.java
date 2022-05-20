import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.lang.Comparable;

class Cost implements Comparable<Cost>{
    int desti,cost;
    public Cost(int x, int y){
        desti = x;
        cost = y;
    }

    @Override
    public int compareTo(Cost other){
        if (this.cost < other.cost){
            return 1;
        }
        return -1;
    }
}

public class Main {
    static int n,m,x;
    static int[] res;
    static ArrayList<Cost>[] cost;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        x = sc.nextInt();
        cost = new ArrayList[n];
        res = new int[n];
        for (int i = 0; i < n; i++){
            cost[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++){
            cost[sc.nextInt()-1].add(new Cost(sc.nextInt()-1, sc.nextInt()));
        }

        for (int i = 0; i < n; i++){
            if (i != x) {
                res[i] = shortWay(i, x) + shortWay(x, i);
            }
        }
        int last = 0;
        for (int i : res){
            last = Math.max(last,i);
        }
        System.out.println(last);
        sc.close();
    }

    static int shortWay(int start, int end){
        PriorityQueue<Cost> queue = new PriorityQueue<>();
        int[] distance = new int[n];
        Arrays.fill(distance,(int)1e9);
        queue.add(new Cost(start,0));
        distance[start] = 0;
        while (queue.size() > 0){
            Cost tmp = queue.poll();
            if (distance[tmp.desti] < tmp.cost){
                continue;
            }
            for (Cost i : cost[tmp.desti]) {
                if (distance[i.desti] > i.cost + tmp.cost) {
                    distance[i.desti] = i.cost + tmp.cost;
                    queue.add(new Cost(i.desti, distance[i.desti]));
                }
            }
        }
        return distance[end];
    }
}
