import java.util.Queue;

public class LUQuestions {
    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        // shortestPathBinaryMatrix();
        // orangesRotting(grid);
        // wallsAndGates(rooms);
    }

    public static int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1)
            return -1;
        Queue<Integer> que = new LinkedList<>();
        int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }, { 1, 1 }, { 1, -1 }, { -1, 1 }, { -1, -1 } };
        int level = 1;
        que.add(0);
        grid[0][0] = 1;
        while (que.size() > 0) {
            int size = que.size();
            while (size-- > 0) {
                // remove
                int last = que.poll();
                if (last == n * n - 1)
                    return level;
                // mark
                // add unvisited nbrs
                for (int d = 0; d < 8; d++) {
                    int r = (last / n) + dir[d][0];
                    int c = (last % n) + dir[d][1];
                    if (r >= 0 && c >= 0 && r < n && c < n && grid[r][c] == 0) {
                        grid[r][c] = 1;
                        que.add(r * n + c);
                    }
                }
            }
            level++;
        }
        return -1;
    }

    public static int orangesRotting(int[][] grid) {
        int fo = 0;
        int m = grid.length;
        int n = grid[0].length;
        Queue<Integer> que = new LinkedList<>();
        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1)
                    fo++;
                if (grid[i][j] == 2)
                    que.add(i * n + j);
            }
        }
        if (fo == 0)
            return 0;
        int level = 1;
        while (que.size() > 0) {
            int size = que.size();
            while (size-- > 0) {
                int last = que.poll();

                for (int d = 0; d < 4; d++) {
                    int r = (last / n) + dir[d][0];
                    int c = (last % n) + dir[d][1];
                    if (r >= 0 && c >= 0 && r < m && c < n && grid[r][c] == 1) {
                        grid[r][c] = 2;
                        que.add(r * n + c);
                        fo--;
                        if (fo == 0)
                            return level;
                    }
                }
            }
            level++;
        }
        return -1;
    }

    public static void wallsAndGates(int[][] rooms) {
        Queue<Integer> que = new LinkedList<>();
        int level = 0;
        int r = rooms.length;
        int c = rooms[0].length;
        int crooms = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (rooms[i][j] == 0)
                    que.add(i * c + j);
                if (rooms[i][j] == Integer.MAX_VALUE) {
                    crooms++;
                }
            }
        }
        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        int dist = 0;
        while (que.size() > 0) {
            int size = que.size();
            while (size-- > 0) {
                int last = que.poll();

                for (int d = 0; d < 4; d++) {
                    int row = (last / c) + dir[d][0];
                    int col = (last % c) + dir[d][1];
                    if (row >= 0 && col >= 0 && row < r && col < c && rooms[row][col] == Integer.MAX_VALUE) {
                        crooms--;
                        rooms[row][col] = dist + 1;
                        que.add(row * c + col);
                        if (crooms == 0)
                            return;
                    }
                }
            }
            dist++;
        }
    }

    public static boolean isBipartite(int[][] graph) {
        int r = graph.length;
        int c = graph[0].length;
        int[] vis = new int[r];
        Arrays.fill(vis, -1);
        boolean res = true;
        for (int i = 0; i < r; i++) {
            if (vis[i] == -1) {
                res = res && isBipartite_(graph, vis, i);
            }
        }
        return res;
    }

    public static boolean isBipartite_(int[][] graph, int[] vis, int src) {
        int r = graph.length;
        int c = graph[0].length;
        Queue<Integer> que = new LinkedList<>();
        que.add(src);
        int color = 0;
        while (que.size() > 0) {
            int size = que.size();
            while (size-- > 0) {
                int last = que.poll();

                for (int ele : graph[last]) {
                    if (vis[ele] == -1) {
                        vis[ele] = color;
                        que.add(ele);
                    } else if (vis[ele] != color)
                        return false;
                }
            }
            color = (color + 1) % 2;
        }
        return true;
    }
}