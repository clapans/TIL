import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int[] longer;
    static int n;
    static int[] numbers;
    static List<int[]> candidates = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        longer = new int[n];
        numbers = new int[n];
        for (int i = 0; i < n; i++)
            longer[i] = sc.nextInt();
        getPermutations(0,0);
        for (int[] candi : candidates) {
            if (isCheck(candi)) {
                Arrays.stream(candi).forEach(c -> {
                    System.out.printf("%d ",c + 1);
                });
            }
        }
    }

    static void getPermutations(int cnt, int use) {
        if (cnt == n) {
            int[] shallowArr = new int[n];
            System.arraycopy(numbers, 0, shallowArr, 0, n);
            candidates.add(shallowArr);
        }
        else
            for (int i = 0; i < n; i++) {
                if ((use & 1 << i) != 0) continue;
                numbers[cnt] = i;
                getPermutations(cnt + 1, use | 1 << i);
            }
    }

    static boolean isCheck(int[] candidate) {
        for (int i = 0; i < n; i++)
            if (longer[candidate[i]] != bigCount(i, candidate)) return false;
        return true;
    }

    static int bigCount(int num, int[] candidate) {
        int cnt = 0;
        for (int i = 0; i < num; i++)
            if (candidate[i] > candidate[num]) cnt++;
        return cnt;
    }
}
