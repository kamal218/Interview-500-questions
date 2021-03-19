import java.util.*;

public class Graph {
    // static int n = 11;
    static int n = 15;

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
        // addEdge(5, 1, 10);
        // addEdge(5, 0, 10);
        // addEdge(1, 2, 10);
        // addEdge(2, 3, 40);
        // addEdge(0, 6, 2);
        // addEdge(6, 7, 2);
        // addEdge(7, 3, 8);
        // addEdge(4, 0, 3);
        // addEdge(4, 8, 8);
        // addEdge(8, 9, 8);
        // addEdge(9, 10, 8);
        // addEdge(10, 3, 8);

        // removeEdge(3, 4);
        // removeVertex(3);
        // displayGraph();

        // FOR SCC
        addEdge(1, 2, 10);
        addEdge(2, 3, 10);
        addEdge(3, 1, 10);
        addEdge(3, 4, 40);
        addEdge(4, 5, 2);
        addEdge(5, 6, 2);
        addEdge(6, 7, 8);
        addEdge(7, 4, 3);
        addEdge(14, 4, 8);
        addEdge(5, 14, 8);
        addEdge(5, 8, 8);
        addEdge(8, 9, 10);
        addEdge(9, 10, 10);
        addEdge(10, 11, 10);
        addEdge(11, 12, 40);
        addEdge(12, 8, 2);
        addEdge(13, 11, 2);

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
        // graph[v].add(new Edge(u, w));// backward edge
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
        if (i != n) {
            graph[u].remove(i);
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

    public static void questions() {
        // topoSort();
        // kahnsAlgo();
        // cyclicTopoSort();
        kosaRaju();
    }

    public static void topoSort() {
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                topoSort_(i, vis);
            }
        }
    }

    public static void topoSort_(int src, boolean[] vis) {
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.v]) {
                topoSort_(e.v, vis);
            }
        }
        System.out.print(src + " ");
    }

    // CYCLIC DIRECTED GRAPH USING BFS

    public static void kahnsAlgo() {
        ArrayList<Integer> ans = new ArrayList<>();
        Queue<Integer> que = new LinkedList<>();
        int[] inDeg = new int[n];
        for (int i = 0; i < n; i++)
            for (Edge e : graph[i])
                inDeg[e.v]++;

        for (int i = 0; i < n; i++)
            if (inDeg[i] == 0)
                que.add(i);
        int level = 0;
        while (que.size() > 0) {
            int size = que.size();
            while (size-- > 0) {
                int last = que.poll();
                ans.add(last);

                for (Edge e : graph[last])
                    if (--inDeg[e.v] == 0)
                        que.add(e.v);
            }
            level++;
        }

        if (ans.size() != n) {
            System.out.println("Cyclic Graph");
        } else {
            System.out.println(ans);
        }
    }

    // CYCLIC DIRECTED GRAPH USING DFS

    public static void cyclicTopoSort() {
        int[] vis = new int[n];
        Arrays.fill(vis, -1);
        boolean res = false;
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (vis[i] == -1) {
                res = res || cyclicTopoSortDFS(i, vis, ans);
            }
        }
        if (res) {
            System.out.println("Cyclic Graph");
        } else {
            System.out.println(ans);
        }
    }

    public static boolean cyclicTopoSortDFS(int src, int[] vis, ArrayList<Integer> ans) {
        vis[src] = 0;
        boolean res = false;
        for (Edge e : graph[src]) {
            if (vis[e.v] == -1) {
                res = res || cyclicTopoSortDFS(e.v, vis, ans);
            } else if (vis[e.v] == 0)
                return true;
        }
        vis[src] = 1;
        ans.add(src);
        return res;
    }

    // KOSARAJU ALGORITHMS

    public static void kosaRaju() {
        boolean[] vis = new boolean[n];
        // FIND TOPOLOGICAL SORT
        List<Integer> order = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                topoDFS(graph, i, vis, order);
            }
        }
        // CREATE REVERSED GRAPH
        ArrayList<Edge>[] ngraph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            ngraph[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            for (Edge e : graph[i]) {
                ngraph[e.v].add(new Edge(i, e.w));
            }
        }
        // System.out.println(order);
        // CALL DFS ON NGRAPH BASED ON TOPO SORT
        List<List<Integer>> scc = new ArrayList<>();
        vis = new boolean[n];
        for (int i = order.size() - 1; i >= 0; i--) {
            if (!vis[order.get(i)]) {
                List<Integer> list = new ArrayList<>();
                topoDFS(ngraph, order.get(i), vis, list);
                scc.add(list);
            }
        }
        System.out.println(scc);
    }

    public static void topoDFS(ArrayList<Edge>[] graph, int src, boolean[] vis, List<Integer> topo) {
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.v]) {
                topoDFS(graph, e.v, vis, topo);
            }
        }
        topo.add(src);
    }

}