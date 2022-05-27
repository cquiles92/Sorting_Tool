package sorting;

import java.util.*;

public class WordCollector implements DataCollector {
    List<String> list = new ArrayList<>();

    @Override
    public void processList() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            list.add(scanner.next());
        }
    }

    @Override
    public void sortNatural() {
        list.sort(String::compareTo);
        System.out.printf("Total words: %d.\n", list.size());
        System.out.print("Sorted data: ");
        for (String word : list) {
            System.out.print(word + " ");
        }
    }

    @Override
    public void sortCount() {
        Map<String, Integer> naturallySorted = new TreeMap<>();
        for (String word : list) {
            if (!naturallySorted.containsKey(word)) {
                naturallySorted.put(word, 1);
            } else {
                naturallySorted.put(word, naturallySorted.get(word) + 1);
            }
        }

        LinkedHashMap<String, Integer> sortedByValue = new LinkedHashMap<>();
        naturallySorted.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .forEachOrdered(o -> sortedByValue.put(o.getKey(), o.getValue()));


        System.out.printf("Total words: %d.\n", list.size());
        for (String word : sortedByValue.keySet()) {
            int wordsCounted = naturallySorted.get(word);
            System.out.printf("%s: %d time(s), %d%%\n", word, wordsCounted, wordsCounted * 100 / list.size());
        }
    }
}
