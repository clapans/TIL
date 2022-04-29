public class Override{
  static int[] arr = {1,2,3,4,5};

  static void errorIsHere(int num){
    try {
      System.out.println(arr[num]);
    }catch(ArrayIndexOutOfBoundsException e){
      System.out.println(e + "가 발생했습니다!");
    }
  }

  static void errorToss(int num) throws ArrayIndexOutOfBoundsException{
    System.out.println(arr[num]);
  }

  public static void main(String[] args) {
    System.out.println("메서드 : ");
    errorIsHere(5);
    errorIsHere(4);
    System.out.println("메인 : ");
    try{
      errorToss(5);
      errorToss(4);
    }catch(ArrayIndexOutOfBoundsException e){
      System.out.println(e + "가 발생했습니다!");
    }
  }
}