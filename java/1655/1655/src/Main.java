import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

    for (int i=1;i<n+1;i++){
      int cur_num = sc.nextInt();
      if (i % 2 == 1){
        maxHeap.add(cur_num);
      }else{
        minHeap.add(cur_num);
      }
      if (i > 1){
        int max_tmp = maxHeap.poll();
        int min_tmp = minHeap.poll();
        maxHeap.add(Math.min(max_tmp,min_tmp));
        minHeap.add(Math.max(max_tmp,min_tmp));
      }
      System.out.printf("%d", maxHeap.peek());
    }
  }
}
