import java.util.*;

public class SCC {
    static void dfs(int node, List<List<Integer>> adj, boolean[] vis, Stack<Integer> st) {
        vis[node] = true;
        for (int nei : adj.get(node)) {
            if (!vis[nei]) {
                dfs(nei, adj, vis, st);
            }
        }
        st.push(node);
    }

    static void dfs2(int node, List<List<Integer>> adj, boolean[] vis) {
        vis[node] = true;
        System.out.print(node + " ");
        for (int nei : adj.get(node)) {
            if (!vis[nei]) {
                dfs2(nei, adj, vis);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();

        List<List<Integer>> adj = new ArrayList<>();
        List<List<Integer>> adjT = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
            adjT.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj.get(u).add(v);
            adjT.get(v).add(u);
        }

        // Step 1: DFS to get finishing times
        boolean[] vis = new boolean[V];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                dfs(i, adj, vis, st);
            }
        }

        // Step 2: Transpose graph is already adjT

        // Step 3: DFS on transpose graph in order of finishing times
        Arrays.fill(vis, false);
        System.out.println("Strongly Connected Components:");
        while (!st.isEmpty()) {
            int node = st.pop();
            if (!vis[node]) {
                dfs2(node, adjT, vis);
                System.out.println();
            }
        }
    }
}