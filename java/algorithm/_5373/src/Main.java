import java.util.*;

public class Main {
    static String[][] top;
    static String[][] down;
    static String[][] left;
    static String[][] right;
    static String[][] front;
    static String[][] back;
    static Map<Character,String[][]> cubeMap = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        cubeMap.put('U',new String[][] {{"top","front","left","back","right"},{"u","u","u","u"}});
        cubeMap.put('D',new String[][] {{"down","front","right","back","left"},{"d","d","d","d"}});
        cubeMap.put('F',new String[][] {{"front","top","right","down","left"},{"d","l","u","r"}});
        cubeMap.put('B',new String[][] {{"back","top","left","down","right"},{"u","l","d","r"}});
        cubeMap.put('L',new String[][] {{"left","top","front","down","back"},{"l","l","l","r"}});
        cubeMap.put('R',new String[][] {{"right","top","back","down","front"},{"r","l","r","r"}});
        int tc = sc.nextInt();
        int rotateCnt;
        for (int i = 0; i < tc; i++){
            reset();
            rotateCnt = sc.nextInt();
            for (int v = 0; v < rotateCnt; v++){
                rotate(sc.next());
            }
            for (int j = 0; j < 3; j++){
                for (int k = 0; k <3; k++)
                    System.out.print(top[j][k]);
                System.out.println("");}
        }
    }
    static void reset(){
        top = new String[][] {{"w","w","w"},{"w","w","w"},{"w","w","w"}};
        down = new String[][] {{"y","y","y"},{"y","y","y"},{"y","y","y"}};
        left = new String[][] {{"g","g","g"},{"g","g","g"},{"g","g","g"}};
        right = new String[][] {{"b","b","b"},{"b","b","b"},{"b","b","b"}};
        front = new String[][] {{"r","r","r"},{"r","r","r"},{"r","r","r"}};
        back = new String[][] {{"o","o","o"},{"o","o","o"},{"o","o","o"}};
    }
    static void rotate(String command){
        String[][] allSide = cubeMap.get(command.charAt(0));
        rotateSide(allSide,command.charAt(1));
        rotateMine(allSide[0][0],command.charAt(1));
    }

    static void rotateSide(String[][] rotateSet, char command){
        List<String[]> sideSet = makeSideSet(rotateSet);
        if (command == '-') sideSet.add(sideSet.remove(0));
        else {
            sideSet.add(0,sideSet.remove(3));
        }
        applyRotate(sideSet, rotateSet);
    }

    static void rotateMine(String rotateOne, char command){
        String[][] all = match(rotateOne);
        if (command == '-')
            change(new String[][] {{all[0][2],all[1][2],all[2][2]},
                                    {all[0][1],all[1][1],all[2][1]},
                                    {all[0][0],all[1][0],all[2][0]}},rotateOne);
        else {
            change(new String[][] {{all[2][0],all[1][0],all[0][0]},
                                    {all[2][1],all[1][1],all[0][1]},
                                    {all[2][2],all[1][2],all[0][2]}},rotateOne);
        }
    }

    static List<String[]> makeSideSet(String[][] rotateSet){
        List<String[]> sideSet = new ArrayList<>();
        for (int i = 1; i < 5; i++){
            String side = rotateSet[1][i-1];
            String[][] all = match(rotateSet[0][i]);
            String[] tmp;
            String pre;
            switch (side) {
                case "u":
                    if (rotateSet[0][i].equals("down")) {
                        pre = all[0][2];
                        all[0][2] = all[0][0];
                        all[0][0] = pre;
                    }
                    sideSet.add(all[0]);
                    break;
                case "d":
                    if (rotateSet[0][i].equals("down")) {
                        pre = all[2][2];
                        all[2][2] = all[2][0];
                        all[2][0] = pre;
                    }
                    sideSet.add(all[2]);
                    break;
                case "l":
                    tmp = new String[3];
                    if (rotateSet[0][i].equals("left") || rotateSet[0][i].equals("back")){
                        for (int j = 0; j < 3; j++)
                            tmp[j] = all[2-j][0];
                    }else{
                        for (int j = 0; j < 3; j++)
                            tmp[j] = all[j][0];
                    }
                    sideSet.add(tmp);
                    break;
                case "r":
                    tmp = new String[3];
                    if (rotateSet[0][i].equals("left") || rotateSet[0][i].equals("back")){
                        for (int j = 0; j < 3; j++)
                            tmp[j] = all[2-j][2];
                    }else{
                        for (int j = 0; j < 3; j++)
                            tmp[j] = all[j][2];
                    }
                    sideSet.add(tmp);
                    break;
            }
        }
        return sideSet;
    }

    static String[][] match(String str){
        String[][] tmp = new String[3][3];
        switch (str) {
            case "top":
                for (int i = 0; i < 3; i++)
                    for (int j = 0; j < 3; j++)
                        tmp[i][j] = top[i][j];
                break;
            case "down":
                for (int i = 0; i < 3; i++)
                    for (int j = 0; j < 3; j++)
                        tmp[i][j] = down[i][j];
                break;
            case "left":
                for (int i = 0; i < 3; i++)
                    for (int j = 0; j < 3; j++)
                        tmp[i][j] = left[i][j];
                break;
            case "right":
                for (int i = 0; i < 3; i++)
                    for (int j = 0; j < 3; j++)
                        tmp[i][j] = right[i][j];
                break;
            case "front":
                for (int i = 0; i < 3; i++)
                    for (int j = 0; j < 3; j++)
                        tmp[i][j] = front[i][j];
                break;
            default:
                for (int i = 0; i < 3; i++)
                    for (int j = 0; j < 3; j++)
                        tmp[i][j] = back[i][j];
                break;
        }
        return tmp;
    }

    static void applyRotate(List<String[]> sideSet, String[][] rotateSet){
        for (int i = 1; i < 5; i++){
            String side = rotateSet[1][i-1];
            String[][] all = match(rotateSet[0][i]);
            String pre;
            switch (side) {
                case "u":
                    all[0] = sideSet.get(i-1);
                    if (rotateSet[0][i].equals("down")) {
                        pre = all[0][2];
                        all[0][2] = all[0][0];
                        all[0][0] = pre;
                    }
                    break;
                case "d":
                    all[2] = sideSet.get(i - 1);
                    if (rotateSet[0][i].equals("down")) {
                        pre = all[2][2];
                        all[2][2] = all[2][0];
                        all[2][0] = pre;
                    }
                    break;
                case "l":
                    if (rotateSet[0][i].equals("left") || rotateSet[0][i].equals("back")) {
                        for (int j = 0; j < 3; j++)
                            all[j][0] = sideSet.get(i - 1)[2 - j];
                    }else {
                        for (int j = 0; j < 3; j++)
                            all[j][0] = sideSet.get(i - 1)[j];
                    }
                    break;
                case "r":
                    if (rotateSet[0][i].equals("left") || rotateSet[0][i].equals("back")){
                        for (int j = 0; j < 3; j++)
                            all[j][2] = sideSet.get(i - 1)[2-j];
                    }else {
                        for (int j = 0; j < 3; j++)
                            all[j][2] = sideSet.get(i - 1)[j];
                    }
                    break;
            }
            change(all,rotateSet[0][i]);
        }
    }

    static void change(String[][] all, String str){
        switch (str) {
            case "top":
                for (int i = 0; i < 3; i++)
                    for (int j = 0; j < 3; j++)
                        top[i][j] = all[i][j];
                break;
            case "down":
                for (int i = 0; i < 3; i++)
                    for (int j = 0; j < 3; j++)
                        down[i][j] = all[i][j];
                break;
            case "left":
                for (int i = 0; i < 3; i++)
                    for (int j = 0; j < 3; j++)
                        left[i][j] = all[i][j];
                break;
            case "right":
                for (int i = 0; i < 3; i++)
                    for (int j = 0; j < 3; j++)
                        right[i][j] = all[i][j];
                break;
            case "front":
                for (int i = 0; i < 3; i++)
                    for (int j = 0; j < 3; j++)
                        front[i][j] = all[i][j];
                break;
            default:
                for (int i = 0; i < 3; i++)
                    for (int j = 0; j < 3; j++)
                        back[i][j] = all[i][j];
                break;
        }
    }
}
