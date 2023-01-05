import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

class Homework implements Comparable<Homework>{
    int d,w;

    public Homework(int d, int w) {
        this.d = d;
        this.w = w;
    }

    @Override
    public int compareTo(Homework o) {
        if (o.w > this.w) return 1;
        else return -1;
    }
}

public class Main {
    static int n;
    static Map<Integer, Boolean> map = new HashMap<>();
    static PriorityQueue<Homework> homeworks = new PriorityQueue<>();
    static int score = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) homeworks.add(new Homework(sc.nextInt(),sc.nextInt()));
        greedySelect();
        System.out.println(score);
    }

    static void greedySelect() {
        while (homeworks.size() > 0) {
            Homework homework = homeworks.poll();
            int d = homework.d;
            while (d > 0) {
                if (map.containsKey(d)) d--;
                else {
                    map.put(d,true);
                    score += homework.w;
                    break;
                }
            }
        }
    }
}
