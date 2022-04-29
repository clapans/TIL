class Node{
  int x,y;
  Node(int x, int y){
    this.x = x;
    this.y = y;
  }
}

public class selectMethod{
  public static void main(String[] args) {
    Node node = new Node(1,2);
    System.out.println(node);
  }
}