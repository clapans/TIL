import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int r,c,t;
    static int[][] dustArr;
    static ArrayList<int[]> dustList = new ArrayList<>();
    static ArrayList<int[]> inverseCycle = new ArrayList<>();
    static ArrayList<int[]> normalCycle = new ArrayList<>();
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        r = sc.nextInt();
        c = sc.nextInt();
        t = sc.nextInt();
        dustArr = new int[r][c];
        boolean isInverse = true;
        for (int i = 0; i < r; i++){
            for (int j = 0; j < c; j++){
                dustArr[i][j] = sc.nextInt();
                if (dustArr[i][j] == -1){
                    if (isInverse) {
                        isInverse = false;
                        for (int k = i-1; k > 0; k--) inverseCycle.add(new int[] {k,j});
                        for (int k = 0; k < c-1; k++) inverseCycle.add(new int[] {0,k});
                        for (int k = 0; k < i; k++) inverseCycle.add(new int[] {k,c-1});
                        for (int k = c-1; k > 0; k--) inverseCycle.add(new int[] {i,k});
                    } else {
                        for (int k = i+1; k < r-1; k++) normalCycle.add(new int[] {k,j});
                        for (int k = 0; k < c-1; k++) normalCycle.add(new int[] {r-1,k});
                        for (int k = r-1; k > i; k--) normalCycle.add(new int[] {k,c-1});
                        for (int k = c-1; k > 0; k--) normalCycle.add(new int[] {i,k});
                    }
                }
            }
        }

        for (int i = 0; i < t; i++){
            checkDust();
            spread();
            clean(inverseCycle);
            clean(normalCycle);
        }

        int cnt = 0;
        for (int i = 0; i < r; i++){
            for (int j = 0; j < c; j++){
                if (dustArr[i][j] > 0) cnt += dustArr[i][j];
            }
        }

        System.out.println(cnt);
    }

    static void checkDust(){
        for (int i = 0; i < r; i++){
            for (int j = 0; j < c; j++){
                if (dustArr[i][j] > 0) dustList.add(new int[] {i,j,dustArr[i][j]/5});
            }
        }
    }

    static void spread(){
        for (int[] dust: dustList){
            int cnt = 0;
            for (int i = 0; i < 4; i++) {
                int x = dust[0] + dx[i];
                int y = dust[1] + dy[i];
                if (0 <= x && x < r && 0 <= y && y < c && dustArr[x][y] > -1){
                    dustArr[x][y] += dust[2];
                    cnt++;
                }
            }
            dustArr[dust[0]][dust[1]] -= dust[2]*cnt;
        }
        dustList.clear();
    }

    static void clean(ArrayList<int[]> lst){
        for (int i = 1; i < lst.size(); i++){
            int pre_x = lst.get(i-1)[0];
            int pre_y = lst.get(i-1)[1];
            int x = lst.get(i)[0];
            int y = lst.get(i)[1];
            dustArr[pre_x][pre_y] = dustArr[x][y];
            if (i == lst.size()-1) dustArr[x][y] = 0;
        }
    }
}