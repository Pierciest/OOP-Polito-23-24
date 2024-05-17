import java.util.TreeMap;
public class TreeMaps {
    public static void main(String[] args) {
        // Create a TreeMap instance with Integer as the key type and String as the value type
        TreeMap<Integer, String> treeMap = new TreeMap<>();

        // Add key-value pairs to the TreeMap
        treeMap.put(3, "Apple");
        treeMap.put(1, "Banana");
        treeMap.put(2, "Orange");

        // Print the original TreeMap
        System.out.println("Original TreeMap: " + treeMap);

        // Sort the TreeMap based on the natural ordering of the keys
        TreeMap<Integer, String> sortedTreeMap = new TreeMap<>(treeMap);

        // Print the sorted TreeMap
        System.out.println("Sorted TreeMap: " + sortedTreeMap);

        // Find the value associated with a specific key
        int keyToFind = 2;
        String value = sortedTreeMap.get(keyToFind);

        // Print the value associated with the key
        System.out.println("Value for key " + keyToFind + ": " + value);
    }
}
