import java.util.List;

class Utils {

    public static void sortStrings(List<String> strings) {
        // your code here
        strings.sort((o1, o2) -> -o1.compareTo(o2));
    }
}