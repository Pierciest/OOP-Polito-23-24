import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class LinkedHashMaps {
    public static void main(String[] args) {
        // Create a LinkedHashMap
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();

        // Add elements to the LinkedHashMap
        linkedHashMap.put("Apple", 10);
        linkedHashMap.put("Banana", 5);
        linkedHashMap.put("Orange", 8);
        linkedHashMap.put("Grapes", 15);

        // Sort the LinkedHashMap by values in ascending order
        LinkedHashMap<String, Integer> sortedMap = linkedHashMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue()) // Sort the entries by value in ascending order
                .collect(Collectors.toMap(
                        Map.Entry::getKey, // Key mapper function
                        Map.Entry::getValue, // Value mapper function
                        (oldValue, newValue) -> oldValue, // Merge function (in case of duplicate keys)
                        LinkedHashMap::new // Supplier for the resulting map
                ));

        // Print the elements of the sorted LinkedHashMap
        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
