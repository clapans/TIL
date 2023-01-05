import java.util.*;

public class Main {
    static int n;
    static int[][] position;
    static int[][] favorite;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[] exchange = {0,1,10,100,1000};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        position = new int[n][n];
        favorite = new int[n*n+1][4];
        for (int i = 0; i < n*n; i++) {
            int studentNum = sc.nextInt();
            for (int j = 0; j < 4; j++)
                favorite[studentNum][j] = sc.nextInt();
            List<int[]> bestPosition = findBestPosition(studentNum);
            if (bestPosition.size() == 1) {
                position[bestPosition.get(0)[0]][bestPosition.get(0)[1]] = studentNum;
                continue;
            }
            List<int[]> mostEmpty = findMostEmpty(bestPosition);
            if (mostEmpty.size() == 1) {
                position[mostEmpty.get(0)[0]][mostEmpty.get(0)[1]] = studentNum;
                continue;
            }
            int[] pos = getMoreTopMoreLeft(mostEmpty);
            position[pos[0]][pos[1]] = studentNum;
        }
        int res = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                res += exchange[getFavoriteScore(i,j,position[i][j])];
        System.out.println(res);
    }

    static List<int[]> findBestPosition(int student) {
        List<int[]> res = new ArrayList<>();
        int score = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                if (position[i][j] != 0) continue;
                int nowScore = getFavoriteScore(i, j, student);
                if (nowScore > score) {
                    res = new ArrayList<>();
                    score = nowScore;
                }
                if (nowScore >= score)
                    res.add(new int[] {i,j});
            }
        return res;
    }

    static int getFavoriteScore(int x, int y, int student) {
        int cnt = 0;
        for (int t = 0; t < 4; t++) {
            int nx = x + dx[t];
            int ny = y + dy[t];
            if (0 <= nx && nx < n && 0 <= ny && ny < n && position[nx][ny] != 0)
                if (isFavorite(position[nx][ny], student)) cnt++;
        }
        return cnt;
    }

    static boolean isFavorite(int s1, int s2) {
        for (int s : favorite[s2])
            if (s == s1) return true;
        return false;
    }

    static List<int[]> findMostEmpty(List<int[]> candidates) {
        List<int[]> res = new ArrayList<>();
        int score = 0;
        for (int[] candidate : candidates) {
            int nowScore = isEmptyScore(candidate[0],candidate[1]);
            if (nowScore > score) {
                res = new ArrayList<>();
                score = nowScore;
            }
            if (nowScore >= score)
                res.add(new int[] {candidate[0],candidate[1]});
        }
        return res;
    }

    static int isEmptyScore(int x, int y) {
        int cnt = 0;
        for (int t = 0; t < 4; t++) {
            int nx = x + dx[t];
            int ny = y + dy[t];
            if (0 <= nx && nx < n && 0 <= ny && ny < n && position[nx][ny] == 0) cnt++;
        }
        return cnt;
    }

    static int[] getMoreTopMoreLeft(List<int[]> candidates) {
        int[] res = new int[] {(int)1e9,(int)1e9};
        for (int[] candidate : candidates) {
            if (candidate[0] < res[0]) {
                res[0] = candidate[0];
                res[1] = candidate[1];
            } else if (candidate[0] == res[0] && candidate[1] < res[1])
                res[1] = candidate[1];
        }
        return res;
    }
}