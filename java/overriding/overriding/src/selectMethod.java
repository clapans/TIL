class Node{
  int x,y;
  Node(int x, int y){
    this.x = x;
    this.y = y;
  }

  public String toString(){
    if (x % 2 == 0){
      return super.toString();
    }
    return Integer.toString(x) + Integer.toString(y);
  }
}

public class selectMethod{
  public static void main(String[] args) {
    Node node1 = new Node(1,2);
    Node node2 = new Node(2,1);
    System.out.println(node1);
    System.out.println(node2);
  }
}