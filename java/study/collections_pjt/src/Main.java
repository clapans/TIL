import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();
        int year = date.getYear();

        int[] dateCase = {0,31,28,31,30,31,30,31,31,30,31,30,31};
        if ((year % 4 == 0) && (year % 100 != 0) || (year % 400) == 0)
            dateCase[2] = 29;

        System.out.println(dateCase[month]);
    }
}
