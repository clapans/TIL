import java.util.*;

public class sw_13809 {
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int n = Integer.parseInt(sc.nextLine());
    for (int i=0;i<n;i++){
      String s = sc.nextLine();
      ArrayList<String> lst = new ArrayList<>(Arrays.asList(s.split("")));
      ArrayList<String> stack = new ArrayList<>();
      for (String t:lst){
        if (stack.size() > 0 && stack.get(stack.size()-1).equals(t)){
          stack.remove(stack.size()-1);
        }else{
          stack.add(t);
        }
      }
      System.out.printf("#%d %d\n",i+1,stack.size());
    }
  }
}
