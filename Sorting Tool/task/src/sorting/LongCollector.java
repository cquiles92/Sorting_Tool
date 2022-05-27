package sorting;


import java.util.*;

public class LongCollector implements DataCollector {
    List<Long> list = new ArrayList<>();

    @Override
    public void processList() {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String input = scanner.next();
            try {
                list.add(Long.parseLong(input));
            } catch (Exception e) {
                System.out.printf("\"%s\" is not a long. It will be skipped.\n", input);
            }
        }
    }

    @Override
    public void sortNatural() {
        list.sort(Long::compareTo);
        System.out.printf("Total numbers: %d.\n", list.size());
        System.out.print("Sorted data: ");
        for (Long number : list) {
            System.out.print(number + " ");
        }
    }

    @Override
    public void sortCount() {
        Map<Long, Integer> naturallySorted = new TreeMap<>();
        for (Long number : list) {
            if (!naturallySorted.containsKey(number)) {
                naturallySorted.put(number, 1);
            } else {
                naturallySorted.put(number, naturallySorted.get(number) + 1);
            }
        }
        LinkedHashMap<Long, Integer> sortedByValue = new LinkedHashMap<>();
        naturallySorted.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .forEachOrdered(o -> sortedByValue.put(o.getKey(), o.getValue()));

        System.out.printf("Total numbers: %d.\n", list.size());
        for (Long number : sortedByValue.keySet()) {
            int numberCounted = naturallySorted.get(number);
            System.out.printf("%d: %d time(s), %d%%\n", number, numberCounted, numberCounted * 100 / list.size());
        }
    }
}
