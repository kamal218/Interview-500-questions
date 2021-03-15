import java.util.*;

public class inBuiltHeap {
    public static void main(String[] args) {
        // Method 1 using Collections Class
        // PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        // Method2 using Anonymous class + comparator
        // PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
        // @Override
        // public int compare(Integer t, Integer o) {
        // return o - t;
        // }
        // });

        // Method3 using Lambda function
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return b - a;
        });
        pq.add(34);
        pq.add(34);
        pq.add(2);
        pq.add(24);
        pq.add(94);
        pq.add(76);
        displayHeap(pq);
    }

    public static void displayHeap(PriorityQueue<Integer> pq) {
        while (pq.size() != 0) {
            System.out.print(pq.poll() + " ");
        }
        System.out.println();
    }
}