import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int n;
    static int[] counter = {0,0,0,0,0,0,0,0,0,0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        ArrayList<Integer> numList= seperate(n);

    }

    static ArrayList<Integer> seperate(int n){
        ArrayList<Integer> lst = new ArrayList<>();
        while (n / 10 != 0){
            lst.add(n / 10);
            n = n % 10;
        }
        lst.add(n);
        return lst;
    }

    static void count(ArrayList<Integer> numList){
        int cnt = 0;
        while (numList.size()-cnt-2 >= 0){
            double tmp = Math.pow(10,numList.size()-cnt-2);
            for (int j = 0; j < 10; j++) counter[j] += tmp * (numList.get(cnt) - 1);

            cnt += 1;
        }

    }
}
