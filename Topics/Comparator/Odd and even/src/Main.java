
import java.util.List;

class Utils {

    public static List<Integer> sortOddEven(List<Integer> numbers) {
        numbers.sort((a, b) -> a % 2 != 0 ? b % 2 == 0 ? -1 : Integer.compare(a, b)
                : b % 2 != 0 ? 1 : Integer.compare(b, a));

        return numbers;
    }
}