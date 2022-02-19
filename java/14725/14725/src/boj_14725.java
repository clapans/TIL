import java.util.*;

public class boj_14725 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = Integer.parseInt(sc.nextLine());
    String[] arr = new String[n];
    for (int i=0;i<n;i++){
      String s = sc.nextLine();
      arr[i] = s.substring(2);
    }
    Arrays.sort(arr);    
    String[] tmp = arr[0].split(" ");
    for (int j=0;j<tmp.length;j++){
      for (int t=0;t<j;t++){
        System.out.printf("--");
      }
      System.out.printf("%s\n",tmp[j]);
    }
    for (int v=1;v<arr.length;v++){
      String[] nw_tmp = arr[v].split(" ");
      int cnt = tmp.length;
      for (int a=0;a<tmp.length;a++){
        if (!nw_tmp[a].equals(tmp[a])){
          cnt = a;
          break;
        }      
      }
      for (int b=0;b<nw_tmp.length;b++){
        if (b >= cnt){
          for (int t=0;t<b;t++){
            System.out.printf("--");
          }
          System.out.printf("%s\n",nw_tmp[b]);
        }
      }
      tmp = nw_tmp;
    }
    sc.close();
  }
}
