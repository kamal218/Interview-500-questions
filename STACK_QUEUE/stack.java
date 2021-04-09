import java.util.Stack;
import java.util.HashMap;

public class stack {
    public static void main(String[] args) {
        set1();
    }

    public static void set1() {
        // CLASS 1
        // int[] ans = NGER();
        // int[] ans = NSER();
        // int[] ans = NGEL();
        // int[] ans = NSEL();
        // int[] ans=NGELeetcode();
        // int[] ans = NGELeetcode2();
        // int[] ans = dailyTemperatures();
        // int[] ans = stockSpan();
        // int ans = maxDiffNSRAndNSL();
        // largestHistogramArea()
        // maximalRectangle();
        // asteroidCollision();

        // CLASS 2
        // boolean ans=isbalanced();
        // int ans = longestValidParentheses();
        // boolean ans = checkForDupParentheses();
        // minReversalsToMakebalanced();
        // minAddToMakeValid();
        // removeKdigits();
        // longestUnbalancedSubSequence();
        // firstNegativeInWindowOfKExtraSpace();
        // firstNegativeInWindowOfKNoExtraSpace();

        // System.out.println(ans);
        // display(ans);

    }

    public static int[] NGER() {
        int[] arr = { 6, 10, 7, 12 };
        int[] ans = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i <= arr.length; i++) {
            int val = (i == arr.length ? Integer.MAX_VALUE : arr[i]);
            while (st.size() > 0 && arr[st.peek()] < val) {
                ans[st.pop()] = i;
            }
            st.push(i);
        }
        return ans;
    }

    public static int[] NSER() {
        int[] arr = { 6, 10, 7, 12 };
        int[] ans = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i <= arr.length; i++) {
            int val = (i == arr.length ? Integer.MIN_VALUE : arr[i]);
            while (st.size() > 0 && arr[st.peek()] > val) {
                ans[st.pop()] = i;
            }
            st.push(i);
        }
        return ans;
    }

    public static int[] NGEL() {
        int[] arr = { 6, 10, 7, 12 };
        int[] ans = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        for (int i = arr.length - 1; i >= -1; i--) {
            int val = (i == -1 ? Integer.MAX_VALUE : arr[i]);
            while (st.size() > 0 && arr[st.peek()] < val) {
                ans[st.pop()] = i;
            }
            st.push(i);
        }
        return ans;
    }

    public static int[] NSEL() {
        int[] arr = { 6, 10, 7, 12 };
        int[] ans = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        for (int i = arr.length - 1; i >= -1; i--) {
            int val = (i == -1 ? Integer.MIN_VALUE : arr[i]);
            while (st.size() > 0 && arr[st.peek()] > val) {
                ans[st.pop()] = i;
            }
            st.push(i);
        }
        return ans;
    }

    public static int[] NGELeetcode() {
        int[] arr = { 4, 1, 2 };
        int[] ref = { 1, 3, 4, 2 };
        Stack<Integer> st = new Stack<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i <= ref.length; i++) {
            int val = (i == ref.length ? Integer.MAX_VALUE : ref[i]);
            while (st.size() > 0 && ref[st.peek()] < val) {
                if (i == ref.length)
                    map.put(ref[st.pop()], -1);
                else
                    map.put(ref[st.pop()], ref[i]);
            }
            st.push(i);
        }
        for (int i = 0; i < arr.length; i++) {
            int val = map.get(arr[i]);
            arr[i] = val;
        }
        return arr;
    }

    public static int[] NGELeetcode2() {
        int[] arr = { 1, 2, 3, 4, 3 };
        int len = arr.length;
        int[] ans = new int[len];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i <= 2 * len; i++) {
            int val = (i == 2 * len ? Integer.MAX_VALUE : arr[i % len]);
            while (st.size() > 0 && arr[st.peek()] < val) {
                if (i == 2 * len) {
                    ans[st.pop() % len] = -1;
                } else {
                    ans[st.pop() % len] = arr[i % len];
                }
            }
            if (i < len)
                st.push(i);
        }
        return ans;
    }

    public static int[] dailyTemperatures() {
        int[] arr = { 73, 74, 75, 71, 69, 72, 76, 73 };
        int[] ans = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i <= arr.length; i++) {
            int val = (i == arr.length ? Integer.MAX_VALUE : arr[i]);
            while (st.size() > 0 && arr[st.peek()] < val) {
                ans[st.peek()] = i == arr.length ? 0 : i - st.peek();
                st.pop();
            }
            st.push(i);
        }
        return ans;
    }

    public static int[] stockSpan() {
        int[] arr = { 100, 80, 60, 70, 60, 75, 85 };
        int[] ans = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        for (int i = arr.length - 1; i >= -1; i--) {
            int val = (i == -1 ? Integer.MAX_VALUE : arr[i]);
            while (st.size() > 0 && arr[st.peek()] < val) {
                ans[st.peek()] = st.pop() - i;
            }
            st.push(i);
        }
        return ans;
    }

    public static int maxDiffNSRAndNSL() {
        int[] arr = { 2, 1, 8 };
        Stack<Integer> st = new Stack<>();
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i <= arr.length; i++) {
            int val = (i == arr.length ? Integer.MIN_VALUE : arr[i]);
            while (st.size() > 0 && arr[st.peek()] > val) {
                st.pop();
                int left = st.size() == 0 ? 0 : arr[st.peek()];
                int right = i == arr.length ? 0 : arr[i];
                ans = Math.max(ans, Math.abs(right - left));
            }
            st.push(i);
        }
        return ans;
    }

    public static int largestHistogramArea(int[] arr) {
        Stack<Integer> st = new Stack<>();
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i <= arr.length; i++) {
            int val = (i == arr.length ? Integer.MIN_VALUE : arr[i]);
            while (st.size() > 0 && arr[st.peek()] > val) {
                int top = st.pop();
                int left = st.size() == 0 ? -1 : st.peek();
                int right = i == arr.length ? arr.length : i;
                ans = Math.max(ans, Math.abs(right - left - 1) * arr[top]);
            }
            st.push(i);
        }
        return ans;
    }

    public static int maximalRectangle(char[][] mat) {
        if (mat.length == 0)
            return 0;
        int[] help = new int[mat[0].length];
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                int cv = mat[i][j] - '0';
                int pv = (i == 0 ? 0 : (help[j]));
                help[j] = cv == 0 ? 0 : (cv + pv);
            }
            ans = Math.max(ans, largestHistogramArea(help));
        }
        return ans;
    }

    public static int[] asteroidCollision() {
        int[] arr = { 5, 10, -5 };
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            if (st.size() == 0) {
                st.push(i);
            } else {
                if (arr[i] < 0 && arr[st.peek()] > 0) {
                    if (-arr[i] == arr[st.peek()]) {
                        st.pop();
                    } else if (-arr[i] > arr[st.peek()]) {
                        st.pop();
                        i--;
                    }
                } else {
                    st.push(i);
                }
            }
        }

        int[] ans = new int[st.size()];
        int k = st.size() - 1;
        while (st.size() != 0) {
            ans[k--] = arr[st.pop()];
        }
        return ans;
    }

    public static boolean isbalanced(String str) {
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            if (st.size() == 0 || str.charAt(i) == '(' || str.charAt(i) == '[' || str.charAt(i) == '{') {
                st.push(str.charAt(i));
            } else if (st.size() > 0) {
                if (str.charAt(i) == ')' && st.peek() != '(')
                    return false;
                if (str.charAt(i) == ']' && st.peek() != '[')
                    return false;
                if (str.charAt(i) == '}' && st.peek() != '{')
                    return false;
                st.pop();
            }
        }
        return st.size() == 0;
    }

    public static int longestValidParentheses() {
        String str = ")()())";
        int ans = 0;
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        for (int i = 0; i < str.length(); i++) {
            if (st.size() == 1) {
                st.push(i);
                continue;
            }
            char coming = str.charAt(i);
            char top = str.charAt(st.peek());
            if (coming == ')' && top == '(') {
                st.pop();
            } else if (coming == '[' && top == ']') {
                st.pop();
            } else if (coming == '{' && top == '}') {
                st.pop();
            } else {
                st.push(i);
            }
            ans = Math.max(ans, i - st.peek());
        }
        return ans;
    }

    public static boolean checkForDupParentheses() {
        String str = "{([(a+b)]+((c+d)))}";
        Stack<Character> st = new Stack<>();
        int dup = 0;
        for (int i = 0; i < str.length(); i++) {
            if (st.size() == 0) {
                st.push(str.charAt(i));
                continue;
            }
            if (str.charAt(i) == ')') {
                int pop = 1;
                while (st.pop() != '(') {
                    pop++;
                }
                if (pop == 1)
                    dup++;
            } else if (str.charAt(i) == ']') {
                int pop = 1;
                while (st.pop() != '[') {
                    pop++;
                }
                if (pop == 1)
                    dup++;
            } else if (str.charAt(i) == '}') {
                int pop = 1;
                while (st.pop() != '{') {
                    pop++;
                }
                if (pop == 1)
                    dup++;
            } else {
                st.push(str.charAt(i));
            }
        }
        System.out.println(dup);
        return dup != 0;
    }

    public static int minReversalsToMakebalanced() {
        String str = "}{{}}{{{";
        int open = 0, close = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '{') {
                open++;
            } else {
                if (open > 0)
                    open--;
                else
                    close++;
            }
        }
        if ((open + close) % 2 != 0)
            return -1;
        return (int) (Math.ceil(open * 1.0 / 2) + Math.ceil(close * 1.0 / 2));
    }

    public static int minAddToMakeValid(String str) {
        int open = 0, close = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                open++;
            } else {
                if (open > 0)
                    open--;
                else
                    close++;
            }
        }
        return open + close;
    }

    public static String removeKdigits() {

        String str = "1432219";
        int k = 3;

        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i) - '0';
            if (st.size() == 0) {
                st.push(val);
                continue;
            }
            while (st.size() > 0 && st.peek() > val && k != 0) {
                st.pop();
                k--;
            }
            st.push(val);
        }
        while (k != 0) {
            st.pop();
            k--;
        }

        StringBuilder sb = new StringBuilder(st.size());

        while (st.size() != 0) {
            sb.append((char) (st.pop() + '0'));
        }
        int idx = sb.length() - 1;

        while (idx > 0 && sb.charAt(idx) == '0') {// skip starting zeroes
            sb.deleteCharAt(sb.length() - 1);
            idx--;
        }

        if (sb.length() == 0)// "10" and k=2
            return "0";

        return sb.reverse().toString();
    }

    public static int longestUnbalancedSubSequence() {
        String str = "{{{}]}";
        if (!isbalanced(str))
            return 0;
        return str.length() == 0 ? -1 : 1;
    }

    public static void firstNegativeInWindowOfKExtraSpace() {
        int[] arr = { -8, -5, -2, 1 };
        int k = 2;
        int[] help = new int[k];
        int[] ans = new int[arr.length - k + 1];
        int i = 0, j = 0;
        int cn = 0;
        // preprocessing for k-1 elements
        for (int pt = 0; pt < k - 1; pt++) {
            if (arr[pt] < 0) {
                help[j++] = arr[pt];
                cn++;
            }
        }
        for (int pt = k - 1; pt < arr.length; pt++) {
            // include
            if (arr[pt] < 0) {
                help[j] = arr[pt];
                j = (j + 1) % k;
                cn++;
            }
            // update answer
            if (cn != 0) {
                ans[pt - k + 1] = help[i];
                if (arr[pt - k + 1] < 0) {
                    i = (i + 1) % k;
                    cn--;
                }
            }
        }
        for (int ele : ans) {
            System.out.print(ele + " ");
        }
    }

    public static void firstNegativeInWindowOfKNoExtraSpace() {
        int[] arr = { -8, -5, -2, 1 };
        int k = 2;
        int[] ans = new int[arr.length - k + 1];
        int negidx = arr.length;
        for (int i = arr.length - 1; i > arr.length - k; i--) {
            if (arr[i] < 0)
                negidx = i;
        }
        for (int i = arr.length - k; i >= 0; i--) {
            if (arr[i] < 0)
                negidx = i;
            if (negidx < i + k) {
                ans[i] = arr[negidx];
            }
        }
        for (int ele : ans) {
            System.out.print(ele + " ");
        }
    }

    public static int maxSumOfSmallestAndSecondSmallestOfAllSubarray() {
        int[] arr = { 4, 3, 1, 5, 6 };
        if (arr.length == 0)
            return 0;
        if (arr.length == 1)
            return 2 * arr[0];
        int ans = Integer.MIN_VALUE;
        for (int i = 1; i < arr.length; i++) {
            ans = Math.max(ans, Math.abs(arr[i] + arr[i - 1]));
        }
        return ans;
    }

    public static void display(int[] ans) {
        for (int ele : ans) {
            System.out.println(ele + " ");
        }
    }
}