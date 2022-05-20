import java.util.*;

public class sw_5356 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    ArrayList<String> strs = new ArrayList<>();
    int Case = Integer.parseInt(sc.nextLine());
    for (int i=0;i<Case;i++){
      System.out.printf("#%d ",i+1);
      for (int j=0;j<5;j++){
        strs.add(sc.nextLine());
      }
      int cnt = 0;
      while (true){
        String tmp = "";
        for (String s : strs){
          if (cnt < s.length()){
            tmp += s.charAt(cnt);
          }
        }
        if (tmp.length() == 0){
          break;
        }
        System.out.printf("%s",tmp);
        cnt += 1;
      }
      System.out.printf("\n");
    }
  }
}