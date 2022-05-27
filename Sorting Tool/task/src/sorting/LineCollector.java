package sorting;

import java.util.*;

public class LineCollector implements DataCollector {
    List<String> list = new ArrayList<>();

    @Override
    public void processList() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            list.add(scanner.nextLine());
        }
    }

    @Override
    public void sortNatural() {
        list.sort(String::compareTo);
        System.out.printf("Total lines: %d.\n", list.size());
        System.out.print("Sorted data: ");
        for (String line : list) {
            System.out.println(line);
        }
    }

    @Override
    public void sortCount() {
        Map<String, Integer> naturallySorted = new TreeMap<>();
        for (String line : list) {
            if (!naturallySorted.containsKey(line)) {
                naturallySorted.put(line, 1);
            } else {
                naturallySorted.put(line, naturallySorted.get(line) + 1);
            }
        }

        LinkedHashMap<String, Integer> sortedByValue = new LinkedHashMap<>();
        naturallySorted.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .forEachOrdered(o -> sortedByValue.put(o.getKey(), o.getValue()));


        System.out.printf("Total lines: %d.\n", list.size());
        for (String line : sortedByValue.keySet()) {
            int linesCounted = naturallySorted.get(line);
            System.out.printf("%s: %d time(s), %d%%\n", line, linesCounted, linesCounted * 100 / list.size());
        }
    }
}
