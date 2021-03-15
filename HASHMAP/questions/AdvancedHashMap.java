import java.util.*;

public class AdvancedHashMap {
    public static void main(String[] args) {
        // set1-> pepcoding youtube
        // set1();

        // set2-> pepcoding IP batch
        set2();

        // set3-> Leetcode
        // set3();
    }

    public static void set1() {
        // employee();
        // iternary();
        // divPairsByK();
        // sum0SubArray();
        // countSum0SubArray();
        // minSlidingWindow1();
        // minSubstringWindow2();
        // largestNonRepSubstring();
        // countNonRepCharString();
        // largestNonRepSubstringEqualK();
    }

    public static void set2() {
        // CLASS 1
        // countSubArrayWithGivenSum();
        // ArrayList<pair<Integer, Integer>> ans = indexOfAllSubArrayWithGivenSum();
        // System.out.println(ans);
        // pair ans = longestSubArrayWithGivenSum();
        // System.out.println(ans);

        // int ans = countSubArrayWithSumDivByK();
        // indexOfAllSubArrayWithSumDivByK();
        // longestSubArrayWithSumDivByK();

        // int ans = countSubArrayWithEqual0And1();
        // indexOfSubArrayWithEqual0And1();
        // largestSubArrayWithEqual0And1();

        // int ans = countSubArrayWithEqual0And1And2();
        // indexOfSubArrayWithEqual0And1And2();
        // largestSubArrayWithEqual0And1And2();

        // int ans = rabbitsInForest();

        // class 2

        // bulbSwitch();

        // boolean ans = canAPBeFormed();
        // boolean ans = canAPBeFormed_NoHashMap();

        // boolean ans=isIsomorphic();
        // boolean ans = isIsomorphicOnlyHashMap();

        // int ans = leastBricks();

        // boolean ans = hasGroupsSizeX();

        // int ans = morningAssembly();

        // int ans=longestConsecutiveMultipleTraversals();
        // int ans=longestConsecutiveSingleTraversal();

        // boolean ans = arrayDoubledPairs();

        // CLASS 3
        // int ans = islandPerimeter()

        // int ans = largestContSubarrayNoDuplicates();

        // int ans = largestContSubarrayWithDuplicates();

        // String ans = smallestNumberDigMulToGivenNum();

        // boolean ans = deletionToMakeFreqSame();

        // gridIllumination(N, lamps, queries);

        // CLASS 4
        // int ans = nonCoincidingPairs();

        // int ans = pairsDivByKHashMap();

        // int ans = pairsDivByKArray();

        // String ans = simpleFraction();

        // int ans = modeOfFrequency();

        // findAnagramMapping();

        // boolean ans = lineReflection();

        // boolean ans = lineReflectionModified();

        // RandomizedSet->insertDeleteGetRandomNoDuplicates

        // RandomizedCollection->insertDeleteGetRandomWithDuplicates

        // employeeFreeTime();

        // CLASS 5
        // List<List<String>> ans = groupAnagramUsingSorting();//n*k*logk
        // List<List<String>> ans = groupAnagramUsingHashing();//n*k

        // boolean ans = isAnagramPallindrome();

        // boolean ans = kAnagramArray();
        // boolean ans=kAnagramHashing();

        // List<Integer> ans = allAnagramInString();

        // String ans = minWindowSubstring();
        // String ans = minWindowSubstring2();

        // String ans = longestSubStringWithUniqueChar();

        triple ans = smallestSubArrayWithMFE();

        System.out.println(ans);

    }

    public static void set3() {
        // firstMissingPositive();
        // findKthPositiveMissing();
    }

    public static void employee() {
        HashMap<String, String> question = new HashMap<>();
        question.put("a", "c");
        question.put("b", "c");
        question.put("c", "f");
        question.put("d", "e");
        question.put("e", "f");
        question.put("f", "f");
        String ceo = "";
        System.out.println(question);
        HashMap<String, HashSet<String>> tree = new HashMap<>();
        for (String emp : question.keySet()) {
            String man = question.get(emp);
            if (man.equals(emp)) {
                ceo = man;
                continue;
            } else {
                if (tree.containsKey(man)) {
                    tree.get(man).add(emp);
                } else {
                    HashSet<String> set = new HashSet<>();
                    set.add(emp);
                    tree.put(man, set);
                }
            }
        }
        System.out.println(tree);
        HashMap<String, Integer> ans = new HashMap<>();
        getAnswer(tree, ans, ceo);
        System.out.println(ans);
    }

    public static int getAnswer(HashMap<String, HashSet<String>> tree, HashMap<String, Integer> ans, String ceo) {
        if (!tree.containsKey(ceo)) {
            ans.put(ceo, 0);
            return 1;
        }
        int recAns = 0;
        for (String str : tree.get(ceo)) {
            recAns += getAnswer(tree, ans, str);
        }
        ans.put(ceo, recAns);
        return recAns + 1;
    }

    public static void iternary() {
        HashMap<String, String> question = new HashMap<>();
        question.put("Chennai", "Bangalore");
        question.put("Bombay", "Delhi");
        question.put("Goa", "Chennai");
        question.put("Delhi", "Goa");
        question.put("var", "pun");
        System.out.println(question);
        HashSet<String> start = new HashSet<>();
        HashSet<String> notStart = new HashSet<>();
        for (String dep : question.keySet()) {
            String arr = question.get(dep);
            notStart.add(arr);
            if (!notStart.contains(dep)) {
                start.add(dep);
            }
            if (start.contains(arr)) {
                start.remove(arr);
            }
        }
        // System.out.println(start);
        ArrayList<ArrayList<String>> ans = new ArrayList<>();
        Iterator<String> itr = start.iterator();
        while (itr.hasNext()) {
            String key = itr.next();
            ArrayList<String> list = new ArrayList<>();
            list.add(key);
            String val = question.get(key);
            while (question.containsKey(val)) {
                list.add(val);
                val = question.get(val);
            }
            list.add(val);
            ans.add(list);
        }
        // System.out.println(start);
        System.out.println(ans);
    }

    public static void divPairsByK() {
        int[] arr = { 9, 7, 5, 3 };
        int k = 6;
        int[] map = new int[k];
        for (int ele : arr) {
            map[ele % k]++;
        }
        if (map[0] % 2 != 0) {
            System.out.println("false");
            return;
        }
        int i = 1, j = k - 1;

        while (i <= j) {
            if (map[i] != map[j]) {
                System.out.println("false");
                return;
            }
            i++;
            j--;
        }
        System.out.println("true");
    }

    public static void sum0SubArray() {
        int[] arr = { 2, 8, -3, -5, 2, -4, 6, 1, 2, 1, -3, 4 };
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int ans = 0;
        int csum = 0;
        for (int i = 0; i < arr.length; i++) {
            csum += arr[i];
            if (map.containsKey(csum)) {
                ans = Math.max(ans, i - map.get(csum));
            } else {
                map.put(csum, i);
            }
        }
        System.out.println(ans);
    }

    public static void countSum0SubArray() {
        int[] arr = { 2, 8, -3, -5, 2, -4, 6, 1, 2, 1, -3, 4 };
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int ans = 0;
        int csum = 0;
        for (int i = 0; i < arr.length; i++) {
            csum += arr[i];
            if (map.containsKey(csum)) {
                ans += map.get(csum);
                map.put(csum, map.get(csum) + 1);
            } else {
                map.put(csum, 1);
            }
        }
        System.out.println(ans);
    }

    public static void minSlidingWindow1() {
        String pstr = "dbaecbbabdcaafbddcabgba";
        String cstr = "abbcdc";
        String ans = "";
        HashMap<Character, Integer> cMap = new HashMap<>();
        for (int i = 0; i < cstr.length(); i++) {
            char ch = cstr.charAt(i);
            cMap.put(ch, cMap.getOrDefault(ch, 0) + 1);
        }
        HashMap<Character, Integer> pMap = new HashMap<>();
        int i = -1;
        int j = -1;
        int psize = 0;
        int csize = cstr.length();
        while (true) {
            boolean f1 = false;
            boolean f2 = false;
            // Expand
            while (i < pstr.length() - 1 && psize != csize) {
                i++;
                char ch = pstr.charAt(i);
                pMap.put(ch, pMap.getOrDefault(ch, 0) + 1);
                if (pMap.getOrDefault(ch, 0) <= cMap.getOrDefault(ch, 0)) {
                    psize++;
                }
                f1 = true;
            }
            // create answer and ontract
            while (j < i && psize == csize) {
                f2 = true;
                if (i - j < ans.length() || ans.length() == 0)
                    ans = pstr.substring(j + 1, i + 1);
                j++;
                char ch = pstr.charAt(j);
                pMap.put(ch, pMap.getOrDefault(ch, 0) - 1);
                if (pMap.getOrDefault(ch, 0) < cMap.getOrDefault(ch, 0)) {
                    psize--;
                    break;
                }
            }
            if (f1 == false && f2 == false)
                break;
        }
        System.out.println(ans);
    }

    public static void minSubstringWindow2() {
        String str = "dbaecbbabdcaafbddcabgba";
        HashMap<Character, Integer> map = new HashMap<>();
        HashSet<Character> set = new HashSet<>();
        String ans = "";
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            set.add(ch);
        }
        int csize = 0;
        int nsize = set.size();
        int i = -1;
        int j = -1;
        while (true) {
            boolean f1 = false;
            boolean f2 = false;
            // expand
            while (i < str.length() - 1 && csize != nsize) {
                f1 = true;
                i++;
                char ch = str.charAt(i);
                map.put(ch, map.getOrDefault(ch, 0) + 1);
                if (map.get(ch) == 1)
                    csize++;
            }
            // answer and contract
            while (j < i && nsize == csize) {
                if (i - j < ans.length() || ans.length() == 0) {
                    ans = str.substring(j + 1, i + 1);
                }
                f2 = true;
                j++;
                char ch = str.charAt(j);
                map.put(ch, map.getOrDefault(ch, 0) - 1);
                if (map.get(ch) == 0)
                    csize--;
            }
            if (!f1 && !f2)
                break;
        }
        System.out.println(ans);
    }

    public static void largestNonRepSubstring() {
        String str = "abcabcbb";
        String ans = "";
        int i = -1, j = -1;
        HashMap<Character, Integer> map = new HashMap<>();
        while (true) {
            boolean f1 = false;
            boolean f2 = false;
            // expand
            while (i < str.length() - 1) {
                f1 = true;
                i++;
                char ch = str.charAt(i);
                map.put(ch, map.getOrDefault(ch, 0) + 1);
                if (map.get(ch) == 2) {
                    break;
                } else {
                    if (ans.length() == 0 || ans.length() < i - j)
                        ans = str.substring(j + 1, i + 1);
                }
            }
            // contract
            while (j < i) {
                f2 = true;
                j++;
                char ch = str.charAt(j);
                map.put(ch, map.getOrDefault(ch, 0) - 1);
                if (map.get(ch) == 1) {
                    break;
                }
            }
            if (!f1 && !f2)
                break;
        }
        System.out.println(ans);
    }

    public static void countNonRepCharString() {
        String str = "abbacbcdbadbdbbdcd";
        HashMap<Character, Integer> map = new HashMap<>();
        int ans = 0;
        int i = -1, j = -1;
        while (true) {
            boolean f1 = false;
            boolean f2 = false;
            // expand
            while (i < str.length()) {
                i++;
                char ch = str.charAt(i);
                map.put(ch, map.getOrDefault(ch, 0) + 1);
                if (map.get(ch) == 2) {
                    break;
                } else {
                    ans += i - j;
                }
            }
            // contract
            while (j < i) {
                j++;
                char ch = str.charAt(j);
                map.put(ch, map.get(ch) - 1);
                if (map.get(ch) == 1) {
                    ans += i - j;
                    break;
                }
            }
            if (!f1 && !f2)
                break;
        }
        System.out.println(ans);
    }

    public static void largestNonRepSubstringEqualK() {
        String str = "aabcbbbccdbca";
        String ans = "";
        int k = 2;
        int i = -1, j = -1;
        HashMap<Character, Integer> map = new HashMap<>();
        while (true) {
            boolean f1 = false;
            boolean f2 = false;
            // expand
            while (i < str.length() - 1) {
                f1 = true;
                i++;
                char ch = str.charAt(i);
                map.put(ch, map.getOrDefault(ch, 0) + 1);
                if (map.size() < k) {
                    continue;
                } else if (map.size() == k) {
                    if (ans.length() == 0 || ans.length() < i - j) {
                        ans = str.substring(j + 1, i + 1);
                    }
                } else {
                    break;
                }
            }
            // contract
            while (j < i) {
                f2 = true;
                j++;
                char ch = str.charAt(j);
                map.put(ch, map.getOrDefault(ch, 0) - 1);
                if (map.get(ch) == 0)
                    map.remove(ch);
                if (map.size() < k) {
                    break;
                }
            }
            if (!f1 && !f2)
                break;
        }
        System.out.println(ans);
    }

    /*************************************
     * --------SET2-------
     *************************************************/
    public static int countSubArrayWithGivenSum() {
        int[] nums = { 10, 2, -2, -20, 10 };
        int k = -10;
        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        int csum = 0;
        map.put(0, 1);
        for (int ele : nums) {
            csum += ele;
            ans += map.getOrDefault(csum - k, 0);
            map.put(csum, map.getOrDefault(csum, 0) + 1);
        }
        return ans;
    }

    public static class pair<a, b> {
        a first;
        b second;

        public pair(a f, b s) {
            this.first = f;
            this.second = s;
        }

        @Override
        public String toString() {
            return first + "->" + second + "\n";
        }
    }

    public static ArrayList<pair<Integer, Integer>> indexOfAllSubArrayWithGivenSum() {
        int[] nums = { 10, 2, -2, -20, 10 };
        int k = -10;
        int ct = 0;
        ArrayList<pair<Integer, Integer>> ans = new ArrayList<>();
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        int csum = 0;
        map.put(0, new ArrayList<>());
        map.get(0).add(-1);
        for (int i = 0; i < nums.length; i++) {
            csum += nums[i];
            if (map.containsKey(csum - k)) {
                for (int idx : map.get(csum - k)) {
                    ct++;
                    ans.add(new pair(idx + 1, i));
                }
            }
            if (!map.containsKey(csum))
                map.put(csum, new ArrayList<>());
            map.get(csum).add(i);
        }
        System.out.println(ct);
        return ans;
    }

    public static pair<Integer, Integer> longestSubArrayWithGivenSum() {
        int[] nums = { 10, 2, -2, -20, 10 };
        int k = -10;
        pair ans = new pair(-1, -1);
        int len = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        int csum = 0;
        map.put(0, -1);

        for (int i = 0; i < nums.length; i++) {
            csum += nums[i];
            if (map.containsKey(csum - k) && i - map.get(csum - k) > len) {
                ans.first = map.get(csum - k) + 1;
                ans.second = i;
                len = i - map.get(csum - k);
            }
            if (!map.containsKey(csum)) {
                map.put(csum, i);
            }
        }
        return ans;
    }

    public static int countSubArrayWithSumDivByK() {
        int[] nums = { 4, 5, 0, -2, -3, 1 };
        int k = 5;
        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        int crem = 0;
        map.put(0, 1);
        for (int ele : nums) {
            crem = (crem + ele) % k;
            if (crem < 0)
                crem += k;
            ans += map.getOrDefault(crem, 0);
            map.put(crem, map.getOrDefault(crem, 0) + 1);
        }
        return ans;
    }

    public static int countSubArrayWithEqual0And1() {
        int[] nums = { 1, 0, 0, 1, 0, 1, 1 };
        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int ct0 = 0, ct1 = 0;
        for (int ele : nums) {
            if (ele == 0)
                ct0++;
            else
                ct1++;
            ans += map.getOrDefault(ct0 - ct1, 0);
            map.put(ct0 - ct1, map.getOrDefault(ct0 - ct1, 0) + 1);
        }
        return ans;
    }

    public static int largestSubArrayWithEqual0And1() {
        int[] nums = { 1, 0, 0, 1, 1, 1, 0 };
        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int ct0 = 0, ct1 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0)
                ct0++;
            else
                ct1++;
            if (map.containsKey(ct0 - ct1) && (i - map.get(ct0 - ct1)) > ans) {
                ans = i - map.get(ct0 - ct1);
            }
            if (!map.containsKey(ct0 - ct1))
                map.put(ct0 - ct1, i);
        }
        return ans;
    }

    public static int countSubArrayWithEqual0And1And2() {
        int[] nums = { 0, 1, 0, 2, 0, 1, 0 };
        int ans = 0;
        HashMap<String, Integer> map = new HashMap<>();
        map.put(0 + "#" + 0, 1);
        int ct0 = 0, ct1 = 0, ct2 = 0;
        for (int ele : nums) {
            if (ele == 0)
                ct0++;
            else if (ele == 1)
                ct1++;
            else
                ct2++;
            String str = (ct0 - ct1) + "#" + (ct0 - ct2);
            ans += map.getOrDefault(str, 0);
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        return ans;
    }

    public static int rabbitsInForest() {
        int[] nums = { 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 7 };
        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int ele : nums)
            map.put(ele, map.getOrDefault(ele, 0) + 1);
        for (Integer key : map.keySet()) {
            if (key == 0) {
                ans += map.get(0);
            } else {
                ans = ans + ((key + 1) * ((map.get(key)) / (key + 1)));
                ans = (map.get(key) % (key + 1)) != 0 ? ans + key + 1 : ans;
            }
        }
        return ans;
    }

    public static void bulbSwitch() {
        int n = 18;
        System.out.println((int) Math.sqrt(n));
    }

    public static boolean canAPBeFormed() {
        int[] nums = { 0, 4, 8, 12, 12, 16 };
        int a = Integer.MAX_VALUE;
        int an = Integer.MIN_VALUE;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int ele : nums) {
            if (ele < a)
                a = ele;
            if (ele > an)
                an = ele;
            map.put(ele, map.getOrDefault(ele, 0) + 1);
        }
        int ai = a;
        if (a == an)
            return true; // diff==0
        int n = nums.length;
        if ((an - a) % (n - 1) != 0) // min!=max but diff==0 ex{2,2,3}
            return false;
        int diff = (an - a) / (n - 1);
        while (n-- > 0) {
            if (!map.containsKey(ai))
                return false;
            else
                map.put(ai, map.get(ai) - 1);
            if (map.get(ai) == 0)
                map.remove(ai);
            ai = ai + diff;
        }
        return map.size() == 0;
    }

    public static boolean canAPBeFormed_NoHashMap() {
        int[] nums = { 0, 4, 8, 12, 12, 16, 20 };
        int a = Integer.MAX_VALUE;
        int an = Integer.MIN_VALUE;
        for (int ele : nums) {
            if (ele < a)
                a = ele;
            if (ele > an)
                an = ele;
        }
        if (a == an)
            return true; // diff==0
        int n = nums.length;
        if ((an - a) % (n - 1) != 0) // min!=max but diff==0 ex{2,2,3}
            return false;
        int diff = (an - a) / (n - 1);

        for (int i = 0; i < n; i++) {
            int idx = (nums[i] - a) / diff;
            if ((nums[i] - a) % diff != 0)
                return false; // like {2,5}
            if (idx == i)
                continue;
            if (nums[idx] == (nums[i])) // duplicacy
                return false;
            swap(nums, i, idx);
            i--;
        }
        return true;
    }

    public static boolean isIsomorphic() {
        String s = "paper";
        String t = "title";
        HashMap<Character, Character> map = new HashMap<>();
        HashSet<Character> set = new HashSet<>();
        int i = 0, j = 0;
        while (i < s.length()) {
            char a = s.charAt(i);
            char b = t.charAt(j);
            if (map.containsKey(a)) {
                if (map.get(a) != b)
                    return false;
            } else {
                if (set.contains(b))
                    return false;
                map.put(a, b);
                set.add(b);
            }
            i++;
            j++;
        }
        return true;
    }

    public static boolean isIsomorphicOnlyHashMap() {
        String s = "paper";
        String t = "title";
        HashMap<Character, Character> map = new HashMap<>();
        int i = 0, j = 0;
        while (i < s.length()) {
            char a = s.charAt(i);
            char b = t.charAt(j);
            if (map.containsKey(a)) {
                if (map.get(a) != b)
                    return false;
            } else {
                if (!map.containsValue(b))
                    map.put(a, b);
                else
                    return false;
            }
            i++;
            j++;
        }
        return true;
    }

    public static int leastBricks() {
        List<List<Integer>> wall = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        int cbreak = 0;
        for (int i = 0; i < wall.size(); i++) {
            cbreak = 0;
            for (int j = 0; j < wall.get(i).size() - 1; j++) {
                cbreak += wall.get(i).get(j);
                map.put(cbreak, map.getOrDefault(cbreak, 0) + 1);
            }
        }
        for (Integer key : map.keySet()) {
            ans = Math.max(ans, map.get(key));
        }
        return wall.size() - ans;
    }

    public static boolean hasGroupsSizeX() {
        int[] deck = { 2, 2, 2, 2, 4, 4, 4, 4, 4, 4 };
        int[] count = new int[10000];
        for (int c : deck)
            count[c]++;

        int g = -1;
        for (int i = 0; i < 10000; ++i)
            if (count[i] > 0) {
                if (g == -1)
                    g = count[i];
                else
                    g = gcd(g, count[i]);
            }

        return g >= 2;
    }

    public static int morningAssembly() {
        int[] nums = { 4, 3, 1, 2 };
        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int ele : nums) {
            if (map.containsKey(ele - 1)) {
                map.put(ele, map.get(ele - 1) + 1);
            } else
                map.put(ele, 1);
            if (ans < map.get(ele))
                ans = map.get(ele);
        }
        return nums.length - ans;
    }

    public static int longestConsecutiveMultipleTraversals() {
        int[] nums = { 5, 8, 7, 4, 3, 1, 4, 2, 9, 6, 4, 7 };
        HashMap<Integer, Boolean> map = new HashMap<>();
        int ans = 0;
        for (int ele : nums) {
            map.put(ele, false);
        }
        for (int key : map.keySet()) {
            if (!map.containsKey(key - 1))
                map.put(key, true);
        }
        int cans = 0;
        for (int key : map.keySet()) {
            cans = 1;
            if (map.get(key)) {
                while (map.containsKey(key + cans))
                    cans++;
            }
            if (cans > ans)
                ans = cans;
        }
        return ans;
    }

    public static int longestConsecutiveSingleTraversal() {
        int[] nums = { 5, 8, 7, 4, 3, 1, 4, 2, 9, 6, 4, 7 };
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        int cans = 0;
        for (int ele : nums) {
            if (map.containsKey(ele))
                continue;
            cans = 1;
            Integer left = map.get(ele - 1);
            Integer right = map.get(ele + 1);
            if (left != null && right != null) {
                cans = left + right + 1;
                map.put(ele, cans);
                map.put(ele + right, cans);
                map.put(ele - left, cans);
            } else if (left == null && right != null) {
                cans = right + 1;
                map.put(ele, cans);
                map.put(ele + right, cans);
            } else if (left != null && right == null) {
                cans = left + 1;
                map.put(ele, cans);
                map.put(ele - left, cans);
            } else {
                map.put(ele, 1);
            }
            if (cans > ans)
                ans = cans;
        }
        return ans;
    }

    public static boolean arrayDoubledPairs() {
        int[] A = { -2, 2, 4, -4 };
        Arrays.sort(A);
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int ele : A)
            map.put(ele, map.getOrDefault(ele, 0) + 1);

        for (int ele : A) {

            if (!map.containsKey(ele))
                continue; // already paired

            int db = 0;
            if (ele < 0 && ele % 2 != 0)
                return false; // no pair for -ve number

            db = (ele < 0) ? (ele / 2) : (ele * 2);

            if (map.containsKey(db)) {
                map.put(ele, map.get(ele) - 1);
                map.put(db, map.get(db) - 1);
                if (map.get(ele) == 0)
                    map.remove(ele);
                if (map.containsKey(db) && map.get(db) == 0) // EX {0,0}
                    map.remove(db);
            } else {
                return false;
            }
        }
        return true;
    }

    public static int islandPerimeter() {
        int[][] grid = { { 0, 1 }, { 1, 0 } };
        int ans = 0;
        int row = grid.length;
        int col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    ans += 4;
                    if (j - 1 >= 0 && grid[i][j - 1] == 1) {
                        ans--;
                    }
                    if (i - 1 >= 0 && grid[i - 1][j] == 1) {
                        ans--;
                    }
                    if (i + 1 < row && grid[i + 1][j] == 1) {
                        ans--;
                    }
                    if (j + 1 < col && grid[i][j + 1] == 1) {
                        ans--;
                    }
                }
            }
        }
        return ans;
    }

    public static int largestContSubarrayNoDuplicates() {
        int[] nums = { 1, 56, 58, 57, 90, 92, 94, 93, 91, 45 };
        int ans = 1, min_ = 0, max_ = 0;
        for (int i = 0; i < nums.length; i++) {
            min_ = nums[i];
            max_ = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                min_ = Math.min(min_, nums[j]);
                max_ = Math.max(max_, nums[j]);
                if ((max_ - min_ + 1) == (j - i + 1) && ans < (max_ - min_ + 1)) {
                    ans = max_ - min_ + 1;
                }
            }
        }
        return ans;
    }

    public static int largestContSubarrayWithDuplicates() {
        int[] nums = { 1, 56, 58, 57, 90, 92, 92, 94, 93, 91, 45 };
        int ans = 1, min_ = 0, max_ = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            min_ = nums[i];
            max_ = nums[i];
            set = new HashSet<>();
            set.add(nums[i]);
            for (int j = i + 1; j < nums.length; j++) {
                if (set.contains(nums[j]))
                    break;
                min_ = Math.min(min_, nums[j]);
                max_ = Math.max(max_, nums[j]);
                if ((max_ - min_ + 1) == (j - i + 1) && ans < (max_ - min_ + 1)) {
                    ans = max_ - min_ + 1;
                }
                set.add(nums[j]);
            }
        }
        return ans;
    }

    public static String smallestNumberDigMulToGivenNum() {
        int n = 100;
        StringBuilder sb = new StringBuilder();
        for (int i = 9; i >= 2;) {
            if (n % i == 0) {
                sb.append(i + "");
                n /= i;
            } else {
                i--;
            }
        }
        if (n != 1)
            return ""; // ex: 13 cannot be converted
        return sb.reverse().toString();
    }

    public static boolean deletionToMakeFreqSame() {
        String str = "xxyyz";
        int ct = 0, min = Integer.MAX_VALUE;
        int[] map = new int[26];
        for (char ch : str.toCharArray()) {
            map[ch - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (map[i] != 0 && map[i] < min)
                min = map[i];
        }
        for (int i = 0; i < 26; i++) {
            if (map[i] != 0)
                ct += map[i] - min;
        }
        // System.out.println(ct);
        return ct == 1;

    }

    public static int[] gridIllumination(int N, int[][] lamps, int[][] queries) {
        HashMap<Integer, Integer> bulb = new HashMap<>();
        HashMap<Integer, Integer> row = new HashMap<>();
        HashMap<Integer, Integer> col = new HashMap<>();
        HashMap<Integer, Integer> diag1 = new HashMap<>();
        HashMap<Integer, Integer> diag2 = new HashMap<>();
        for (int i = 0; i < lamps.length; i++) {
            int r = lamps[i][0];
            int c = lamps[i][1];
            int bidx = (r * N) + c;
            bulb.put(bidx, bulb.getOrDefault(bidx, 0) + 1);
            row.put(r, row.getOrDefault(r, 0) + 1);
            col.put(c, col.getOrDefault(c, 0) + 1);
            diag1.put(r + c, diag1.getOrDefault(r + c, 0) + 1);
            diag2.put(r - c, diag2.getOrDefault(r - c, 0) + 1);
        }
        int[] ans = new int[queries.length];
        int[][] dir = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };
        for (int i = 0; i < queries.length; i++) {
            int r = queries[i][0];
            int c = queries[i][1];
            int idx = (r * N) + c;
            if (row.getOrDefault(r, 0) != 0 || col.getOrDefault(c, 0) != 0 || diag1.getOrDefault(r + c, 0) != 0
                    || diag2.getOrDefault(r - c, 0) != 0) {
                ans[i] = 1; // Update answer
                // update grid
                if (bulb.getOrDefault(idx, 0) != 0) { // own check
                    int nr = r;
                    int nc = c;
                    int nidx = idx;
                    int count = bulb.get(nidx);
                    bulb.put(nidx, bulb.get(nidx) - count);
                    row.put(nr, row.get(nr) - count);
                    col.put(nc, col.get(nc) - count);
                    diag1.put(nr + nc, diag1.get(nr + nc) - count);
                    diag2.put(nr - nc, diag2.get(nr - nc) - count);
                }
                for (int k = 0; k < dir.length; k++) { // adjacent check
                    int nr = r + dir[k][0];
                    int nc = c + dir[k][1];
                    int nidx = (nr * N) + nc;
                    if (nr >= 0 && nc >= 0 && nr < N && nc < N && bulb.getOrDefault(nidx, 0) != 0) {
                        int count = bulb.get(nidx);
                        bulb.put(nidx, bulb.get(nidx) - count);
                        row.put(nr, row.get(nr) - count);
                        col.put(nc, col.get(nc) - count);
                        diag1.put(nr + nc, diag1.get(nr + nc) - count);
                        diag2.put(nr - nc, diag2.get(nr - nc) - count);
                    }
                }
            }
        }
        return ans;
    }

    public static int nonCoincidingPairs() {
        int[][] points = { { 1, 2 }, { 2, 3 }, { 1, 3 } };
        HashMap<Integer, Integer> xmap = new HashMap<>();
        HashMap<Integer, Integer> ymap = new HashMap<>();
        HashMap<String, Integer> xymap = new HashMap<>();
        int ans = 0;
        for (int i = 0; i < points.length; i++) {
            int x = points[i][0];
            int y = points[i][1];
            String xy = x + "#" + y;
            ans += xmap.getOrDefault(x, 0);
            ans += ymap.getOrDefault(y, 0);
            ans -= xymap.getOrDefault(xy, 0);
            xmap.put(x, xmap.getOrDefault(x, 0) + 1);
            ymap.put(y, ymap.getOrDefault(y, 0) + 1);
            xymap.put(xy, xymap.getOrDefault(xy, 0) + 1);
        }
        return ans;
    }

    public static int pairsDivByKHashMap() {
        int[] nums = { 5, 9, 36, 74, 52, 31, 42 };
        int k = 3;
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int ele : nums) {
            int rem = (ele % k);
            if (rem == 0) {
                ans += map.getOrDefault(rem, 0);
            } else if (k % 2 == 0 && rem == (k / 2)) {
                ans += map.getOrDefault(rem, 0);
            } else {
                ans += map.getOrDefault(k - rem, 0);
            }
            map.put(rem, map.getOrDefault(rem, 0) + 1);
        }
        return ans;
    }

    public static int pairsDivByKArray() {
        int[] nums = { 5, 9, 36, 74, 52, 31, 42 };
        int k = 3;
        int[] map = new int[k];
        int ans = 0;
        for (int ele : nums) {
            int rem = (ele % k);
            map[rem]++;
        }
        ans += (map[0] * (map[0] - 1)) / 2;
        int si = 1, ei = k - 1;
        while (si < ei) {
            ans += map[si] * map[ei];
            si++;
            ei--;
        }
        if (si == ei)
            ans += (map[si] * (map[si] - 1)) / 2;
        return ans;
    }

    public static String simpleFraction() {
        int num = 8;
        int den = 3;
        int rem = (num % den);
        int quot = (num / den);
        StringBuilder sb = new StringBuilder();
        sb.append(quot + "");
        if (rem == 0) {
            return sb.toString();
        } else {
            sb.append(".");
            HashMap<Integer, Integer> map = new HashMap<>();
            while (rem != 0) {
                if (map.containsKey(rem)) {
                    sb.insert(map.get(rem), "(");
                    sb.append(")");
                    return sb.toString();
                } else {
                    map.put(rem, sb.length());
                    num = rem * 10;
                    quot = (num / den);
                    rem = (num % den);
                    sb.append(quot);
                }
            }
        }
        return sb.toString();
    }

    public static int modeOfFrequency() {
        int[] nums = { 5, 9, 2, 9, 7, 2, 5, 3 };
        HashMap<Integer, Integer> map1 = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();
        // layer1
        for (int ele : nums)
            map1.put(ele, map1.getOrDefault(ele, 0) + 1);
        // layer2
        for (int key : map1.keySet())
            map2.put(map1.get(key), map2.getOrDefault(map1.get(key), 0) + 1);
        // creating answer
        int ans = 0;
        int mode = 0;
        for (int key : map2.keySet()) {
            if (mode < map2.get(key)) {
                mode = map2.get(key);
                ans = key;
            } else if (mode == map2.get(key) && ans > map2.get(key))
                ans = map2.get(key);
        }
        return ans;
    }

    public static int[] findAnagramMapping() {
        int[] arr1 = { 12, 28, 46, 32, 50 };
        int[] arr2 = { 50, 12, 32, 46, 28 };
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr2.length; i++) {
            if (!map.containsKey(arr2[i]))
                map.put(arr2[i], new ArrayList<>());
            map.get(arr2[i]).add(i);
        }
        int[] ans = new int[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            ArrayList<Integer> list = map.get(arr1[i]);
            ans[i] = list.remove(list.size() - 1);
        }
        for (int ele : ans)
            System.out.print(ele + " ");
        return ans;
    }

    public static boolean lineReflection() {
        int[][] points = { { 1, 1 }, { -1, -1 } };
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int[] point : points) {
            min = Math.min(min, point[0]);
            max = Math.max(max, point[0]);
        }
        int m = (max + min) / 2;
        HashMap<String, Integer> map = new HashMap<>();
        for (int[] point : points) {
            int x = point[0];
            int y = point[1];
            String hashString = x + "#" + y;
            int cx = (2 * m - x);
            int cy = y;
            String cHashString = cx + "#" + cy;
            if (map.containsKey(cHashString)) {
                map.put(cHashString, map.get(cHashString) - 1);
                if (map.get(cHashString) == 0)
                    map.remove(cHashString);
            } else {
                map.put(hashString, map.getOrDefault(hashString, 0) + 1);
            }
        }
        return map.size() == 0;
    }

    public static boolean lineReflectionModified() {
        int[][] points = { { 1, 1 }, { -1, 1 } };
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        for (int[] point : points) {
            min = Math.min(min, point[0]);
            max = Math.max(max, point[0]);
            if (!map.containsKey(point[0])) {
                map.put(point[0], new HashSet<>());
            }
            map.get(point[0]).add(point[1]);
        }
        int m = (max + min);
        for (int[] point : points) {
            int x = m - point[0];
            if (map.get(x) == null || !map.get(x).contains(point[1]))
                return false;
        }
        return true;
    }

    class RandomizedSet {
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();

        /** Initialize your data structure here. */
        public RandomizedSet() {

        }

        /**
         * Inserts a value to the set. Returns true if the set did not already contain
         * the specified element.
         */
        public boolean insert(int val) {
            if (map.containsKey(val))
                return false;
            map.put(val, list.size());
            list.add(val);
            return true;
        }

        /**
         * Removes a value from the set. Returns true if the set contained the specified
         * element.
         */
        public boolean remove(int val) {
            if (!map.containsKey(val))
                return false;
            int idx = map.get(val);
            list.set(idx, list.get(list.size() - 1));
            map.put(list.get(idx), idx);
            map.remove(val);
            list.remove(list.size() - 1);
            return true;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            return list.get((int) (Math.floor(Math.random() * list.size())));
        }
    }

    class RandomizedCollection {
        HashMap<Integer, HashSet<Integer>> map;
        ArrayList<Integer> list;

        /** Initialize your data structure here. */
        public RandomizedCollection() {
            this.map = new HashMap<>();
            this.list = new ArrayList<>();
        }

        /**
         * Inserts a value to the collection. Returns true if the collection did not
         * already contain the specified element.
         */
        public boolean insert(int val) {
            boolean ans = false;
            HashSet<Integer> set = map.get(val);
            if (set == null) {
                ans = true;
                set = new HashSet<>();
                map.put(val, set);
            }
            set.add(list.size());
            list.add(val);
            return ans;
        }

        /**
         * Removes a value from the collection. Returns true if the collection contained
         * the specified element.
         */
        public boolean remove(int val) {
            HashSet<Integer> set = map.get(val);
            if (set == null || set.size() == 0)
                return false;
            int idx = set.iterator().next();
            set.remove(idx);
            list.set(idx, list.get(list.size() - 1));
            map.get(list.get(idx)).add(idx);
            map.get(list.get(idx)).remove(list.size() - 1);
            list.remove(list.size() - 1);
            return true;
        }

        /** Get a random element from the collection. */
        public int getRandom() {
            return list.get((int) (Math.floor(Math.random() * list.size())));
        }
    }

    public static class Interval {
        int st;
        int et;

        public Interval(int st, int et) {
            this.st = st;
            this.et = et;
        }
    }

    public static void employeeFreeTime() {
        List<List<Interval>> list = new ArrayList<>();

        list.add(new ArrayList<Interval>());
        list.add(new ArrayList<Interval>());
        list.add(new ArrayList<Interval>());

        list.get(0).add(new Interval(1, 3));
        list.get(0).add(new Interval(6, 7));
        list.get(1).add(new Interval(2, 4));
        list.get(2).add(new Interval(2, 5));
        list.get(2).add(new Interval(9, 12));

        PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> (a.st - b.st));
        for (List<Interval> empList : list) {
            for (Interval i : empList) {
                pq.add(i);
            }
        }

        List<Interval> ans = new ArrayList<>();
        int lastEt = -1;
        while (pq.size() != 0) {
            Interval top = pq.poll();
            if (lastEt != -1 && lastEt < top.st) {
                ans.add(new Interval(lastEt, top.st));
            }
            lastEt = Math.max(lastEt, top.et);
        }
    }

    public static List<List<String>> groupAnagramUsingSorting() {
        String[] str = { "eat", "tea", "ate", "bat", "tab", "hat" };
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (String s : str) {
            char[] ch = s.toCharArray();
            Arrays.sort(ch);
            String sorted = String.valueOf(ch);
            if (!map.containsKey(sorted))
                map.put(sorted, new ArrayList<>());
            map.get(sorted).add(s);
        }
        return new ArrayList<>(map.values());
    }

    public static List<List<String>> groupAnagramUsingHashing() {
        String[] str = { "eat", "tea", "ate", "bat", "tab", "hat" };
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (String s : str) {
            int[] freq = new int[26];
            for (int i = 0; i < s.length(); i++)
                freq[s.charAt(i) - 'a']++;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                sb.append("#");
                sb.append(freq[i] + "");
            }
            String hashed = sb.toString();
            if (!map.containsKey(hashed))
                map.put(hashed, new ArrayList<>());
            map.get(hashed).add(s);
        }
        return new ArrayList<>(map.values());
    }

    public static boolean isAnagramPallindrome() {
        String anagram = "geeksogeeks";
        int[] map = new int[256];
        for (int i = 0; i < anagram.length(); i++) {
            int ch = anagram.charAt(i); // ascii of character
            map[ch]++;
        }
        boolean odd = false;
        for (int i = 0; i < 256; i++) {
            int freq = map[i];
            if (freq % 2 != 0) {
                if (odd)
                    return false;
                odd = true;
            }
        }
        return true;
    }

    public static boolean kAnagramArray() {
        String s1 = "geeks";
        String s2 = "eggkf";
        int k = 1;
        int abs = 0;
        int[] map = new int[256];
        for (int i = 0; i < s1.length(); i++) {
            map[s1.charAt(i)]++;
        }
        for (int i = 0; i < s2.length(); i++) {
            map[s2.charAt(i)]--;
        }

        for (int i = 0; i < 256; i++) {
            abs += Math.abs(map[i]);
        }
        return (abs / 2) <= k;
    }

    public static boolean kAnagramHashing() {
        String s1 = "geeks";
        String s2 = "eggkf";
        int k = 1;
        int diff = 0;
        HashMap<Character, Integer> map1 = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            char ch = s1.charAt(i);
            map1.put(ch, map1.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < s2.length(); i++) {
            char ch = s2.charAt(i);
            map2.put(ch, map2.getOrDefault(ch, 0) + 1);
        }
        for (char key : map1.keySet()) {
            int d = map1.get(key) - map2.getOrDefault(key, 0);
            if (d > 0)
                diff += d;
        }
        return diff <= k;
    }

    public static List<Integer> allAnagramInString() {
        String s = "cbaebabacd";
        String p = "abc";
        List<Integer> ans = new ArrayList<>();
        if (s.length() < p.length())
            return ans;
        int si = 0, ei = 0;
        int count = p.length();
        int ccount = 0;
        HashMap<Character, Integer> smap = new HashMap<>();
        HashMap<Character, Integer> pmap = new HashMap<>();
        for (int i = 0; i < p.length(); i++)
            pmap.put(p.charAt(i), pmap.getOrDefault(p.charAt(i), 0) + 1);
        while (ei < p.length()) {
            char ch = s.charAt(ei);
            smap.put(ch, smap.getOrDefault(ch, 0) + 1);
            if (smap.get(ch) <= pmap.getOrDefault(ch, 0))
                ccount++;
            ei++;
        }
        if (ccount == count)
            ans.add(0);
        while (ei < s.length()) {
            // expand
            char exp = s.charAt(ei);
            smap.put(exp, smap.getOrDefault(exp, 0) + 1);
            if (smap.get(exp) <= pmap.getOrDefault(exp, 0))
                ccount++;
            // release
            char rel = s.charAt(si);
            smap.put(rel, smap.get(rel) - 1);
            if (smap.get(rel) < pmap.getOrDefault(rel, 0))
                ccount--;
            // compare
            if (count == ccount)
                ans.add(si + 1);
            si++;
            ei++;
        }
        return ans;
    }

    public static String minWindowSubstring() {
        String s = "adobecodebanc";
        String p = "abc";
        int l1 = s.length();
        int l2 = p.length();
        if (l1 == 0 || l2 == 0)
            return "";
        boolean f1 = false;
        boolean f2 = false;
        int i = 0, j = 0;
        int count = l2;
        int ccount = 0;
        int ansLen = Integer.MAX_VALUE;
        int st = 0;
        HashMap<Character, Integer> smap = new HashMap<>();
        HashMap<Character, Integer> pmap = new HashMap<>();
        for (int pt = 0; pt < l2; pt++)
            pmap.put(p.charAt(pt), pmap.getOrDefault(p.charAt(pt), 0) + 1);
        while (true) {
            f1 = false;
            f2 = false;
            // expand
            while (i < l1 && ccount != count) {
                char add = s.charAt(i);
                smap.put(add, smap.getOrDefault(add, 0) + 1);
                if (smap.get(add) <= pmap.getOrDefault(add, 0))
                    ccount++;
                i++;
                f1 = true;
            }
            // contract
            while (j < i && ccount == count) {
                if (ansLen > i - j) {
                    ansLen = i - j;
                    st = j;
                }
                char rem = s.charAt(j);
                smap.put(rem, smap.get(rem) - 1);
                if (smap.get(rem) < pmap.getOrDefault(rem, 0))
                    ccount--;
                j++;
                f2 = true;
            }
            if (!f1 && !f2)
                break;
        }
        System.out.println(ansLen);
        System.out.println(st);
        return ansLen == Integer.MAX_VALUE ? "" : s.substring(st, st + ansLen);
    }

    public static String minWindowSubstring2() {
        String s = "bbacacdcbbcaadcdca";
        int l1 = s.length();
        if (l1 == 0)
            return "";
        boolean f1 = false;
        boolean f2 = false;
        int i = 0, j = 0;
        HashMap<Character, Integer> smap = new HashMap<>();
        HashSet<Character> pmap = new HashSet<>();
        for (int pt = 0; pt < l1; pt++)
            pmap.add(s.charAt(pt));
        int count = pmap.size();
        int ccount = 0;
        int ansLen = Integer.MAX_VALUE;
        int st = 0;
        while (true) {
            f1 = false;
            f2 = false;
            // expand
            while (i < l1 && ccount != count) {
                char add = s.charAt(i);
                smap.put(add, smap.getOrDefault(add, 0) + 1);
                if (smap.get(add) == 1 && pmap.contains(add))
                    ccount++;
                i++;
                f1 = true;
            }
            // contract
            while (j < i && ccount == count) {
                if (ansLen > i - j) {
                    ansLen = i - j;
                    st = j;
                }
                char rem = s.charAt(j);
                smap.put(rem, smap.get(rem) - 1);
                if (smap.get(rem) == 0 && pmap.contains(rem))
                    ccount--;
                j++;
                f2 = true;
            }
            if (!f1 && !f2)
                break;
        }
        System.out.println(ansLen);
        System.out.println(st);
        return ansLen == Integer.MAX_VALUE ? "" : s.substring(st, st + ansLen);
    }

    public static String longestSubStringWithUniqueChar() {
        String s = "geeksforgeeks";
        HashMap<Character, Integer> smap = new HashMap<>();
        int ansLen = 0;
        int st = 0;
        int i = 0, j = 0;
        boolean f1 = false, f2 = false;
        while (true) {
            f1 = false;
            f2 = false;
            // expand
            while (i < s.length()) {
                f1 = true;
                char add = s.charAt(i);
                smap.put(add, smap.getOrDefault(add, 0) + 1);
                if (ansLen <= i - j) {
                    st = j;
                    ansLen = i - j;
                }
                i++;
                if (smap.get(add) == 2)
                    break;
            }

            // contract
            while (j < i) {
                f2 = true;
                char rem = s.charAt(j);
                smap.put(rem, smap.get(rem) - 1);
                j++;
                if (smap.get(rem) == 1)
                    break;
            }
            if (!f1 && !f2)
                break;
        }
        System.out.println(ansLen);
        return s.substring(st, st + ansLen);
    }

    public static class triple {
        int st = -1;
        int end = -1;
        int ct = 0;

        public triple(int st, int end, int ct) {
            this.st = st;
            this.end = end;
            this.ct = ct;
        }

        public triple() {

        }

        @Override
        public String toString() {
            return st + " " + end + " ";
        }
    }

    public static triple smallestSubArrayWithMFE() {
        int[] arr = { 4, 1, 3, 2, 2, 1, 3, 3 };
        triple ans = new triple();
        HashMap<Integer, triple> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            triple pr = map.get(arr[i]);
            if (pr == null) {
                map.put(arr[i], new triple(i, i, 1));
            } else {
                map.put(arr[i], new triple(pr.st, i, pr.ct + 1));
            }
            pr = map.get(arr[i]); // compulsary
            if (ans.ct < pr.ct) {
                ans = pr;
            }
        }
        return ans;
    }

    //////////////////////////// ##########SET///////////////////////////////
    //////////////////////////// 3
    //////////////////////////// LEETCODE#############/////////////////////////////////////
    public static int firstMissingPositive() {
        int[] nums = { 1, -11018, -2, 3, -4, 5, 8 };
        int i = 0, idx = 0;
        while (i < nums.length) {
            if (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                idx = nums[i] - 1;
                int temp = nums[idx];
                nums[idx] = nums[i];
                nums[i] = temp;
            } else {
                i++;
            }
        }
        i = 0;
        while (i < nums.length) {
            if (nums[i] != i + 1)
                return i + 1;
            i++;
        }
        return nums.length + 1;
    }

    public static int findKthPositiveMissing() {
        int[] nums = { 2, 3, 5, 4, 7 };
        int k = 5;
        int val = 1;
        int i = 0;
        while (i < nums.length && k != 0) {
            if (nums[i] == val) {
                val++;
                i++;
            } else {
                k--;
                val++;
            }
        }
        if (k == 0)
            return val - 1;
        return val + k - 1;
    }

    // CONTAMINATED TREE LEETCODE
    public class FindElements {
        HashSet<Integer> set = new HashSet<>();

        // public FindElements(TreeNode root) {
        // if (root != null)
        // root.val = 0;
        // create(root);
        // }

        // public void create(TreeNode root) {
        // if (root == null)
        // return;
        // set.add(root.val);
        // if (root.left != null) {
        // root.left.val = root.val * 2 + 1;
        // create(root.left);
        // }
        // if (root.right != null) {
        // root.right.val = root.val * 2 + 2;
        // create(root.right);
        // }
        // }

        public boolean find(int target) {
            if (set.contains(target))
                return true;
            return false;
        }
    }

    //////////////////////// SWAP FUNCTIONS/////////////////
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void swap01(int[] arr, int i, int j) {
        arr[i] = arr[i] - arr[j];
        arr[j] = arr[i] + arr[j];
        arr[i] = arr[j] - arr[i];
    }

    public static void swap02(int[] arr, int i, int j) {
        arr[i] = ((arr[i] + arr[j]) - (arr[i] = arr[j]));
    }

    public static void swap03(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    ////////////////////// GCD /////////////////////////////
    public static int gcd(int x, int y) {
        return x == 0 ? y : gcd(y % x, x);
    }

    public static int gcd_01(int a, int b) {
        if (a == 0)
            return b;
        if (b == 0)
            return a;
        if (a == b)
            return a;
        if (a > b)
            return gcd_01(a - b, b);
        return gcd_01(a, b - a);
    }

}