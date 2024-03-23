package Hash;

public class Main {
    public static void main(String[] args) {
        MapMethods<Integer, Integer> map = new MapMethods<>();
        map.put(1, 2020);
        map.put(2, 2021);
        map.put(3, 2022);
        map.put(4, 2023);
        map.put(5, 2024);
        System.out.println(map.get(3));
        System.out.println(map.containsKey(4));
        System.out.println(map.containsValue(2024));
        System.out.println(map.containsValue(2025));
        System.out.println(map.containsKey(2024));
        System.out.println(map.size());
        map.remove(2);
        System.out.println(map.size());
    }
}
