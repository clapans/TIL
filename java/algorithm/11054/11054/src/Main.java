import java.util.Arrays;
import java.util.Scanner;

public class Main{
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int[] nums = new int[n];
    int[][] dp = new int[2][n];
    int res = 0;
    for (int i = 0; i < n; i++){
      nums[i] = sc.nextInt();
    }
    for (int i = 0; i < 2; i++){
      Arrays.fill(dp[i],1);
    }
    for (int i = 0; i < n; i++){
      for (int j = 0; j < i; j++){
        if (nums[j] > nums[i]){
          dp[1][i] = Math.max(dp[1][i],dp[1][j]+1);
          dp[1][i] = Math.max(dp[1][i],dp[0][j]+1);
        }
        else if (nums[j] < nums[i]){
          dp[0][i] = Math.max(dp[0][i],dp[0][j]+1);
        }
      }
    }
    for (int[] t : dp){
      for (int i : t){
        if (res < i){
          res = i;
        }
      }
    }
    System.out.println(res);
    sc.close();
  }
}