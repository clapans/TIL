public class App {
    static int[] n;
    public static void main(String[] args) {
        n[0] = 1;
        int sum = 0;
        for (int i = 0; i < n.length; i++) {
            sum += n[i];
        }
        System.out.println("점수 합계 : " + sum);
        System.out.println(sum);    
    }
}
