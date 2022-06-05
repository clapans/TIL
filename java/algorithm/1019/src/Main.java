import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int n;
    static int[] counter = {0,0,0,0,0,0,0,0,0,0};
    static ArrayList<Integer> numList;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        numList = seperate(n);

        for (int i = 0; i < numList.size(); i++){
            count(numList.get(i),i);
        }
        String res = Arrays.toString(counter);
        res = res.replace(",","");
        System.out.println(res.substring(1,res.length()-1));
    }

    static ArrayList<Integer> seperate(int n){
        ArrayList<Integer> lst = new ArrayList<>();
        while (n / 10 != 0){
            lst.add(n % 10);
            n = n / 10;
        }
        lst.add(n);
        ArrayList<Integer> new_lst = new ArrayList<>();
        for (int i = lst.size()-1; i > -1; i--) new_lst.add(lst.get(i));
        return new_lst;
    }

    static void count(int num, int index){
        int preNum = preNum(index);
        for (int i = 1; i < 10; i++) counter[i] += preNum * Math.pow(10,(numList.size() - index - 1));
        counter[0] += (Math.max(0,preNum - 1)) * Math.pow(10,(numList.size() - index - 1));
        counter[num] += subNum(index) + 1;
        for (int i = 1; i < num; i++) counter[i] += Math.pow(10,(numList.size() - index - 1));
        if (preNum != 0 && num != 0) counter[0] += Math.pow(10,(numList.size() - index - 1));
    }

    static int subNum(int index){
        int tmp = 0;
        for (int i = index + 1; i < numList.size(); i++) tmp += numList.get(i)*Math.pow(10,(numList.size() - i - 1));
        return tmp;
    }

    static int preNum(int index){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < index; i++) sb.append(Integer.toString(numList.get(i)));
        if (sb.toString().length() > 0){
            return Integer.parseInt(sb.toString());
        }else{
            return 0;
        }
    }
}
