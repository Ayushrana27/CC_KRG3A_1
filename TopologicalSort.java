import java.util.*;

public class TopologicalSort {

    public static List<Integer> kahnsAlgorithm(int V, List<List<Integer>> adj) {
        int[] indegree = new int[V];
        for (int i = 0; i < V; i++) {
            for (int nei : adj.get(i)) {
                indegree[nei]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        List<Integer> topoOrder = new ArrayList<>();
        while (!q.isEmpty()) {
            int node = q.poll();
            topoOrder.add(node);

            for (int nei : adj.get(node)) {
                indegree[nei]--;
                if (indegree[nei] == 0) {
                    q.add(nei);
                }
            }
        }

        if (topoOrder.size() != V) {
            return new ArrayList<>();
        }
        return topoOrder;
    }

    private static void dfs(int node, List<List<Integer>> adj, boolean[] vis, Stack<Integer> st) {
        vis[node] = true;
        for (int nei : adj.get(node)) {
            if (!vis[nei]) {
                dfs(nei, adj, vis, st);
            }
        }
        st.push(node);
    }

    public static List<Integer> dfsTopologicalSort(int V, List<List<Integer>> adj) {
        boolean[] vis = new boolean[V];
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                dfs(i, adj, vis, st);
            }
        }

        List<Integer> topoOrder = new ArrayList<>();
        while (!st.isEmpty()) {
            topoOrder.add(st.pop());
        }
        return topoOrder;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj.get(u).add(v);
        }

        System.out.println("Kahn's Algorithm (BFS Topological Sort):");
        List<Integer> kahns = kahnsAlgorithm(V, adj);
        if (kahns.isEmpty()) {
            System.out.println("Cycle detected, no topological order possible.");
        } else {
            for (int node : kahns) {
                System.out.print(node + " ");
            }
            System.out.println();
        }

        System.out.println("DFS Topological Sort:");
        List<Integer> dfsTopo = dfsTopologicalSort(V, adj);
        for (int node : dfsTopo) {
            System.out.print(node + " ");
        }
        System.out.println();
    }
}