import java.util.*;

public class Graph {

    static int n = 7;

    public static class Edge {
        int v = 0;
        int w = 0;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }

        public Edge() {

        }

        @Override
        public String toString() {
            return "{" + this.v + "," + this.w + "}";
        }
    }

    public static void main(String[] args) {
        initGraph();
        addEdge(0, 1, 10);
        addEdge(0, 3, 10);
        addEdge(1, 2, 10);
        addEdge(2, 3, 40);
        addEdge(3, 4, 2);
        addEdge(4, 5, 2);
        addEdge(5, 6, 3);
        addEdge(4, 6, 8);

        // removeEdge(3, 4);
        // removeVertex(3);
        // displayGraph();

        questions();
    }

    // GRAPH CREATION

    static ArrayList<Edge>[] graph;

    public static void initGraph() {
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    public static ArrayList<Edge>[] getGraph() {
        return graph;
    }

    // Add Edge function
    public static void addEdge(int u, int v, int w) {
        graph[u].add(new Edge(v, w));// forward edge
        graph[v].add(new Edge(u, w));// backward edge
    }

    // Find Edge Function
    public static int findEdge(int u, int v) {
        int idx = 0;
        for (Edge e : graph[u]) {
            if (e.v == v) {
                break;
            }
            idx++;
        }
        return idx == graph[u].size() ? n : idx;
    }

    // Remove Edge Function
    public static void removeEdge(int u, int v) {
        int i = findEdge(u, v);
        int j = findEdge(v, u);
        if (i != n) {
            graph[u].remove(i);
        }
        if (j != n) {
            graph[v].remove(j);
        }
    }

    // Remove vertex Function
    public static void removeVertex(int vertex) {
        for (int i = graph[vertex].size() - 1; i >= 0; i--) {
            removeEdge(vertex, graph[vertex].get(i).v);
        }
    }

    // Display Graph
    public static void displayGraph() {
        for (int i = 0; i < n; i++) {
            System.out.print(i + " -> ");
            for (Edge e : graph[i]) {
                System.out.print(e + ",");
            }
            System.out.println();
        }
    }

    ///////////////////////////////// *PROBLEMS*////////////////////////////////////////////

    public static void questions() {
        boolean[] vis = new boolean[n];
        // boolean ans = hasPath(0, 5, vis);
        // int ans = allPath(0, 6, vis, "0");
        // HeavyPair ans = heavyPath(0, 6, vis);
        // boolean ans = hamiltoniianPathAndCycle(0, 0, 0, vis, "0");
        // List<List<Integer>> ans = getConnectedComponents();
        // boolean ans = isGraphCyclic();
        // BFS(0, vis);
        // BFSOptimized(0, vis);
        BFSOptimized_(0);
        // System.out.println(ans);
    }

    // has path function
    public static boolean hasPath(int src, int dest, boolean[] vis) {
        if (src == dest)
            return true;
        boolean res = false;
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.v])
                res = res || hasPath(e.v, dest, vis);
        }
        return res;
    }

    public static int allPath(int src, int dest, boolean[] vis, String path) {
        if (src == dest) {
            System.out.println(path);
            return 1;
        }
        int ans = 0;
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.v])
                ans += allPath(e.v, dest, vis, path + " " + e.v);
        }
        vis[src] = false;
        return ans;
    }

    public static class HeavyPair {
        int w = 0;
        String path = "";

        public HeavyPair(int w, String path) {
            this.w = w;
            this.path = path;
        }

        public HeavyPair() {

        }

        @Override
        public String toString() {
            return this.path + "->" + this.w;
        }
    }

    public static HeavyPair heavyPath(int src, int dest, boolean[] vis) {
        if (src == dest) {
            return new HeavyPair(0, dest + " ");
        }
        vis[src] = true;
        HeavyPair ans = new HeavyPair((Integer.MIN_VALUE), "");
        for (Edge e : graph[src]) {
            if (!vis[e.v]) {
                HeavyPair recAns = heavyPath(e.v, dest, vis);
                int add = e.w;
                if (recAns.w != Integer.MIN_VALUE && recAns.w + add > ans.w) {
                    ans.w = recAns.w + add;
                    ans.path = src + " " + recAns.path;
                }
            }
        }
        vis[src] = false;
        return ans;
    }

    public static boolean hamiltoniianPathAndCycle(int src, int osrc, int edgeVis, boolean[] vis, String path) {
        if (edgeVis == n - 1) {
            int idx = findEdge(src, osrc);
            if (idx != n) {
                System.out.println("Cycle" + " -> " + path + " " + osrc);
            } else {
                System.out.println("Path" + "->" + path);
            }
            return true;
        }
        boolean res = false;
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.v]) {
                res = res || hamiltoniianPathAndCycle(e.v, osrc, edgeVis + 1, vis, path + " " + e.v);
            }
        }
        vis[src] = false;
        return res;
    }

    public static List<List<Integer>> getConnectedComponents() {
        List<List<Integer>> ans = new ArrayList<>();
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                List<Integer> base = new ArrayList<>();
                dfs(i, vis, base);
                ans.add(base);
            }
        }
        return ans;
    }

    public static void dfs(int src, boolean[] vis, List<Integer> base) {
        vis[src] = true;
        base.add(src);
        for (Edge e : graph[src]) {
            if (!vis[e.v]) {
                dfs(e.v, vis, base);
            }
        }
    }

    public static boolean isGraphCyclic() {
        int[] vis = new int[n];
        Arrays.fill(vis, -1);
        boolean res = false;
        for (int i = 0; i < n; i++) {
            if (vis[i] == -1) {
                vis[i] = i;
                res = res || isGraphCyclic_(i, vis);
            }
        }
        return res;
    }

    public static boolean isGraphCyclic_(int src, int[] vis) {
        boolean res = false;
        for (Edge e : graph[src]) {
            if (vis[e.v] == -1) {
                vis[e.v] = src;
                res = res || isGraphCyclic_(e.v, vis);
            } else if (vis[src] != e.v)
                return true;
        }
        return res;
    }

    public static void BFS(int src, boolean[] vis) {
        int dest = 6;
        boolean isCyclic = false;
        int level = 0;
        int fLevel = -1;
        Queue<Integer> que = new LinkedList<>();
        que.add(src);
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int rvtx = que.poll();

                if (vis[rvtx]) {
                    isCyclic = true;
                    continue;
                }
                if (fLevel == -1 && rvtx == dest) {
                    fLevel = level;
                }
                vis[rvtx] = true;
                for (Edge e : graph[rvtx]) {
                    if (!vis[e.v]) {
                        que.add(e.v);
                    }
                }
            }
            level++;
        }
        System.out.println("cyclc:" + isCyclic);
        System.out.println("Dest Level:" + fLevel);
    }

    public static void BFSOptimized(int src, boolean[] vis) {
        int dest = 6;
        int level = 0;
        int fLevel = -1;
        Queue<Integer> que = new LinkedList<>();
        que.add(src);
        vis[src] = true;
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int rvtx = que.poll();

                for (Edge e : graph[rvtx]) {
                    if (!vis[e.v]) {
                        vis[e.v] = true;
                        que.add(e.v);
                        if (e.v == dest)
                            fLevel = level + 1;
                    }
                }

            }
            level++;
        }
        System.out.println("Dest Level:" + fLevel);
    }

    public static void BFSOptimized_(int src) {
        int[] vis = new int[n];
        Arrays.fill(vis, -1);
        int dest = 6;
        boolean isCyclic = false;
        int level = 0;
        int fLevel = -1;
        Queue<Integer> que = new LinkedList<>();
        que.add(src);
        vis[src] = src;
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int rvtx = que.poll();

                for (Edge e : graph[rvtx]) {
                    if (vis[e.v] == -1) {
                        vis[e.v] = rvtx;
                        que.add(e.v);
                        if (e.v == dest)
                            fLevel = level + 1;
                    } else if (vis[rvtx] != e.v) {
                        isCyclic = true;
                    }
                }

            }
            level++;
        }
        System.out.println("cyclc:" + isCyclic);
        System.out.println("Dest Level:" + fLevel);
    }

}