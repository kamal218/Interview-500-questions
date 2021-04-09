import java.util.*;

class Main {
    public static void main(String[] args) {
        int ans = longestConsecutive();
        System.out.println(ans);
    }

    public static int longestConsecutive() {
        int[] nums = { 4, 0, -4, -2, 2, 5, 2, 0, -8, -8, -8, -8, -1, 7, 4, 5, 5, -4, 6, 6, -3 };
        HashMap<Integer, Integer> map = new HashMap<>();

        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]))
                continue;

            int prev = nums[i] - 1;
            int next = nums[i] + 1;

            if (map.containsKey(prev) && map.containsKey(next)) {
                int sp = nums[i] - map.get(prev);
                int ep = nums[i] + map.get(next);
                int tot = map.get(prev) + map.get(next) + 1;
                map.put(sp, tot);
                map.put(ep, tot);

                ans = Math.max(ans, tot);
            } else if (map.containsKey(prev)) {
                int sp = nums[i] - map.get(prev);
                int ep = nums[i];
                int tot = map.get(prev) + 1;
                map.put(sp, tot);
                map.put(ep, tot);

                ans = Math.max(ans, tot);
            } else if (map.containsKey(next)) {
                int sp = nums[i];
                int ep = nums[i] + map.get(next);
                int tot = map.get(next) + 1;
                map.put(sp, tot);
                map.put(ep, tot);

                ans = Math.max(ans, tot);
            } else {
                map.put(nums[i], 1);
                ans = Math.max(ans, 1);
            }

        }
        for (int key : map.keySet()) {
            System.out.println(key + "->" + map.get(key));
        }
        return ans;
    }
}