class Node_parent{
  void draw(){
      System.out.println("parent");
  } 
}

class Node extends Node_parent{
  int x,y;
  Node(int x,int y){
    this.x = x;
    this.y = y;
  }

  void draw(){
    System.out.println("child");
  }
   
  boolean equals(Node other){
    return this.x == other.x;
  }

  public String toString(){
    return Integer.toString(this.x) + Integer.toString(this.y);
  }
}

public class Main{
  public static void main(String[] args) {
    Node node = new Node(1,2);
    Node other = node;
    System.out.println(node.equals(other));
    /*node.draw();
    Node_parent node_parent = new Node_parent();
    node_parent.draw();*/
  }
}