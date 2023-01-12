import java.util.*;

class Enemie {
    int x,y;

    public Enemie(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Enemie() {
        this.x = -1;
        this.y = -1;
    }

}

public class Main {
    static int n,m,d;
    static int[][] arr;
    static int res = 0;
    static List<Enemie> enemies = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        d = sc.nextInt();
        arr = new int[n][m];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                arr[i][j] = sc.nextInt();
                if (arr[i][j] == 1) enemies.add(new Enemie(i,j));
            }

        combination(0,0, new int[m]);
        System.out.println(res);
    }

    public static void combination(int x, int cnt, int[] archer) {
        if (cnt == 3 || x == m) {
            if (cnt == 3) {
                int tmp = game(archer,enemies);
                if (tmp > res) res = tmp;
            }
        } else {
            combination(x + 1, cnt, archer);
            archer[x] = 1;
            combination(x + 1, cnt + 1, archer);
            archer[x] = 0;
        }
    }

    public static int game(int[] archer, List<Enemie> enemieList) {
        int score = 0;
        List<Enemie> enemies = new ArrayList<>();
        for (Enemie e : enemieList)
            enemies.add(new Enemie(e.x,e.y));
        while (enemies.size() > 0) {
            Set<Enemie> enemieSet = new HashSet<>();
            for (int i = 0; i < m; i++)
                if (archer[i] == 1) {
                    Enemie enemie = attack(i, enemies);
                    if (enemie.x != -1) enemieSet.add(enemie);
                }
            score += enemieSet.size();
            enemies.removeAll(enemieSet);
            enemies.removeAll(moveEnemie(enemies));
            for (Enemie e : enemies)
                e.x++;
        }
        return score;
    }

    public static Enemie attack(int x, List<Enemie> enemies) {
        int leastDistance = (int)1e9;
        Enemie leastEnemie = new Enemie();
        for (Enemie e : enemies) {
            int distance = (n - e.x) + Math.abs(x - e.y);
            if (distance <= d)
                if (distance < leastDistance) {
                    leastDistance = distance;
                    leastEnemie = e;
                } else if (distance == leastDistance)
                    if (e.y < leastEnemie.y)
                        leastEnemie = e;
        }
        return leastEnemie;
    }

    public static List<Enemie> moveEnemie(List<Enemie> enemies) {
        List<Enemie> removeList = new ArrayList<>();
        for (Enemie e : enemies)
            if (e.x == n - 1) removeList.add(e);
        return removeList;
    }
}