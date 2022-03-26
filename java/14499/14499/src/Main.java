import java.util.ArrayList;
import java.util.Scanner;

public class Main{
  int[] dx = {1,-1,0,0};
  int[] dy = {0,0,1,-1};

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();
    int x = sc.nextInt();
    int y = sc.nextInt();
    int k = sc.nextInt();
    sc.nextLine();
    ArrayList<Integer[]> map = new ArrayList<>();
    for (int i = 0;i < n; i++){
      ArrayList<Integer> tmp = new ArrayList<>();
      for (int j = 0;j < 2; j++){
        tmp.add(sc.nextInt());
      }
      map.add(tmp);
    }
  }
}
