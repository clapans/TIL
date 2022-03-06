import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    for (int i=0;i<n;i++){
      int cur_num = sc.nextInt();
      if (i == 0){
        minHeap.add(cur_num);
      }else if (i == 1){
        minHeap.add(cur_num);
        maxHeap.add(minHeap.poll());
      }else{
        if (cur_num <= maxHeap.peek()){
          maxHeap.add(cur_num);
        }else{
          minHeap.add(cur_num);
        }
      }
      if (maxHeap.size() < minHeap.size()){
        while (maxHeap.size() < minHeap.size()){
          maxHeap.add(minHeap.poll());
        }
      }else{
        while (maxHeap.size() > minHeap.size()){
          minHeap.add(maxHeap.poll());
        }
      }
      if (minHeap.size() < maxHeap.size()){
        System.out.printf("%d", maxHeap.peek());
      }else if (minHeap.size() > maxHeap.size()){
        System.out.printf("%d", minHeap.peek());
      }else{
        if (minHeap.peek() < maxHeap.peek()){
          System.out.printf("%d", minHeap.peek());
        }else{
          System.out.printf("%d", maxHeap.peek());
        }
      }
    }
  }
}
