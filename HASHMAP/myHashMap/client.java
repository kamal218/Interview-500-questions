public class client {
    public static void main(String[] args) {
        hashmap<Integer, Integer> map = new hashmap<>();
        map.put(1, 2);
        map.put(2, 3);
        map.put(3, 4);
        map.put(4, 5);
        map.put(5, 6);
        map.put(2, 9);
        map.remove(2);
        System.out.print(map.keySet());
        for (Integer key : map) {
            System.out.print(key);
        }
    }
}