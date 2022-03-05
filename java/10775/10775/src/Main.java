import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int[] gate = new int[n];
    for (int i=0;i<n;i++){
      gate[i] = 0;
    }
    int plane = sc.nextInt();
    int[] dock = new int[plane];
    for (int i=0;i<plane;i++){
      dock[i] = sc.nextInt();
    }
    int cnt = 0;
    for (int t : dock){
      int ch = 0;
      for (int j=t-1;j>=0;j--){
        if (gate[j]==0){
          ch = 1;
          gate[j] = 1;
          break;
        }
      }
      if (ch==0){
        break;
      }else{
        cnt += 1;
      }
    }
    System.out.printf("%d",cnt);
  }
}

