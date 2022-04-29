class Node{
  int x,y;
  Node(int x, int y){
    this.x = x;
    this.y = y;
  }
}

class NodeChild extends Node{
  int z;
  NodeChild(int x, int y, int z){
    super(x,y);
    this.z = z;
  }
  public String toString(){
    return Integer.toString(x) + Integer.toString(y) + Integer.toString(z);
  }
}

public class constructor {
  public static void main(String[] args) {
    NodeChild node = new NodeChild(2, 3, 4);
    System.out.println(node);
  }
}
