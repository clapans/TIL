import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;

public class sw_6485 {
  public static void main(String[] args) throws IOException{
    Scanner sc = new Scanner(System.in);
    int t = Integer.parseInt(sc.next());
    for (int i=0;i<t;i++){
      int n = Integer.parseInt(sc.next());
      int[] terminal = new int[5001];
      for (int j=0;j<n;j++){
        int a = Integer.parseInt(sc.next());
        int b = Integer.parseInt(sc.next());
        for (int k=a;k<=b;k++){
          terminal[k] += 1;
        }
      }
      int p = Integer.parseInt(sc.next());
      int[] res = new int[p];
      
      for (int m=0;m<p;m++){
        int tmp = Integer.parseInt(sc.next());
        res[m] = terminal[tmp];
      }
      Arrays.toString(res);
      System.out.printf("%c %d ",'#',i+1);
      for (int a=0;a<res.length;a++){
        System.out.printf("%d ",res[a]);
      }
    }
    sc.close();
  }
}
