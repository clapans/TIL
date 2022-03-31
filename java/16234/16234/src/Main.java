import java.util.Scanner;

public class Main{
  static int n,l,r;
  static int[][] arr;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    n = sc.nextInt();
    //l = sc.nextInt();
    //r = sc.nextInt();
    for(int i = 0;i < n;i++){
      for(int j = 0;j < n;j++){
        arr[i][j] = sc.nextInt();
      }
    }
    for(int i = 0;i < n;i++){
      for(int j = 0;j < n;j++){
        System.out.println(arr[i][j]);    
      }
    }
  }

  
}