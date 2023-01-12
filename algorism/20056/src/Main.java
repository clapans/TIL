import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Fireball {
    int r,c,m,s,d;

    public Fireball(int r, int c, int m, int s, int d) {
        this.r = r;
        this.c = c;
        this.m = m;
        this.s = s;
        this.d = d;
    }
}

public class Main {
    static int n,m,k;
    static int[] dx = {-1,-1,0,1,1,1,0,-1};
    static int[] dy = {0,1,1,1,0,-1,-1,-1};
    static int[][] cnt;
    static List<Fireball> fireballList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        cnt = new int[n][n];

        for (int i = 0; i < m; i++) {
            Fireball fireball = new Fireball(sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt(), sc.nextInt(), sc.nextInt());
            cnt[fireball.r][fireball.c]++;
            fireballList.add(fireball);
        }

        for (int c = 0; c < k; c++) {
            for (Fireball fireball : fireballList) {
                cnt[fireball.r][fireball.c]--;
                fireball.r = move(fireball)[0];
                fireball.c = move(fireball)[1];
                cnt[fireball.r][fireball.c]++;
            }

            List<Fireball> newFireballList = new ArrayList<>();

            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++) {
                    if (cnt[i][j] == 0) continue;
                    List<Fireball> fireballTmp = findFireball(i, j);
                    if (fireballTmp.size() > 1) {
                        cnt[i][j] = 4;
                        int[] mv = sum(fireballTmp);
                        int[] dir = direction(fireballTmp);
                        if (mv[0] == 0) {
                            cnt[i][j] = 0;
                            continue;
                        }
                        for (int t = 0; t < 4; t++)
                            newFireballList.add(new Fireball(i, j, mv[0], mv[1], dir[t]));
                    } else if (fireballTmp.size() == 1)
                        newFireballList.add(fireballTmp.get(0));
                }
            fireballList = new ArrayList<>(newFireballList);
        }

        int res = 0;
        for (Fireball fireball : fireballList) {
            res += fireball.m;
        }
        System.out.println(res);
    }

    static int[] move(Fireball fireball) {
        int nx = isEscape(fireball.r + fireball.s * dx[fireball.d]);
        int ny = isEscape(fireball.c + fireball.s * dy[fireball.d]);
        return new int[] {nx,ny};
    }

    static int isEscape(int x) {
        while (x > n - 1) x -= n;
        while (x < 0) x += n;
        return x;
    }

    static List<Fireball> findFireball(int x, int y) {
        List<Fireball> fireballTmp = new ArrayList<>();
        for (Fireball fireball : fireballList)
            if (fireball.r == x && fireball.c == y) fireballTmp.add(fireball);
        return fireballTmp;
    }

    static int[] sum(List<Fireball> fireballList) {
        int m = 0;
        int s = 0;
        for (Fireball fireball : fireballList) {
            m += fireball.m;
            s += fireball.s;
        }
        return new int[] {m / 5, s / fireballList.size()};
    }

    static int[] direction(List<Fireball> fireballList) {
        int isCheck = fireballList.get(0).d % 2;
        for (int i = 1; i < fireballList.size(); i++)
            if (fireballList.get(i).d % 2 != isCheck) return new int[] {1,3,5,7};
        return new int[] {0,2,4,6};
    }
}
