import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Scanner;

class Fish {
	int num,dir;
	String role;
	
	public Fish(int num, int dir, String role){
		this.num = num;
		this.dir = dir;
		this.role = role;
	}

}

class Point {
	int x,y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static Fish[][] fishArr = new Fish[4][4];
	static Map<Integer,Point> fishMap = new HashMap<>();
	static int[] dx = {-1,-1,0,1,1,1,0,-1};
	static int[] dy = {0,-1,-1,-1,0,1,1,1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++) 
				fishArr[i][j] = new Fish(sc.nextInt(),sc.nextInt(),"fish");
		fishArr[0][0].role = "shark";
		
		sc.close();
	}
	
	static void fillMap() {
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				fishMap.put(fishArr[i][j].num,new Point(i,j));
	}
	
	static void moveOrder() {
		for (int i = 1; i < 16; i++) {
			move(i);
		}
	}
	
	static Fish move(int num) {
		Point point = fishMap.get(num);
		Fish fish = fishArr[point.x][point.y];
		int dir = fish.dir;
		int cnt = 0;
		int nx,ny;
		while (true) {
			nx = point.x + dx[dir];
			ny = point.y + dy[dir];
			if (cnt == 8) break;
			if (!(0 <= nx && nx < 4 && 0 <= ny && ny < 4) && fishArr[nx][ny].role == "shark") {
				dir++;
				cnt++;
			}
			else break;
		}
		if (fishArr[nx][ny].num != -1) change(num,fishArr[nx][ny].num);
		return fish;
	}
	
	static void change(int f1, int f2) {
		Point p1 = fishMap.get(f1);
		Point p2 = fishMap.get(f2);
		int tmp = fishArr[p2.x][p2.y].dir;
		fishArr[p1.x][p1.y].num = f2;
		fishArr[p2.x][p2.y].num = f1;
		fishArr[p2.x][p2.y].dir = fishArr[p1.x][p1.y].dir;
		fishArr[p1.x][p1.y].dir = tmp;
	}
}