import java.util.Scanner;

public class Main{
  static int n;
  static int[] operand;
  static int[] operator = new int[4];
  static int min_ = (int)1e9;
  static int max_ = -(int)1e9;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    operand = new int[n];
    sc.nextLine();
    String[] tmp = sc.nextLine().split(" ");
    for (int i = 0; i < tmp.length; i++){
      operand[i] = Integer.parseInt(tmp[i]);
    }
    for (int i = 0; i < 4; i++){
      operator[i] = sc.nextInt();
    }
    total_cal(0, operator, operand[0]);
    System.out.println(max_);
    System.out.println(min_);
    sc.close();
  }

  static int cal(int x, int y, int oper){
    if (oper == 0){
      return x + y;
    }
    else if (oper == 1){
      return x - y;
    }
    else if (oper == 2){
      return x * y;
    }
    else{
      if (x > 0 && y > 0){
        return x / y;
      }
      x = -x;
      return -(int) x / y;
    }
  }

  static void total_cal(int x,int[] operator,int part_sum){
    if (x == n-1){
      min_ = Math.min(min_,part_sum);
      max_ = Math.max(max_,part_sum);
    }
    else{
      for (int i = 0; i < 4; i++){
        if (operator[i] > 0){
          operator[i] -= 1;
          int tmp = cal(part_sum,operand[x+1],i); 
          total_cal(x+1,operator,tmp);
          operator[i] += 1;
        }
      }
    }
  } 
}