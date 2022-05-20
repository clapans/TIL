import java.util.*;

public class sw_1859 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int t = Integer.parseInt(sc.next());
    for (int i=0;i<t;i++){
      int n = Integer.parseInt(sc.next());
      int res = 0;
      ArrayList<Integer> lst = new ArrayList<>();
      for (int j=0;j<n;j++){
        lst.add(Integer.parseInt(sc.next()));
      }
      while (lst.size() > 0){
        int idx = lst.indexOf(Collections.max(lst));
        res += Collections.max(lst) * idx - Arrays.stream(lst).sum();
      }
      
    }
  }
}
