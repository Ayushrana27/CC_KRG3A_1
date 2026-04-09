import java.util.*;

public class C_Dijkstra{

    static class Pair {
        int node;
        long wt;

        public Pair(int node, long wt) {
            this.node = node;
            this.wt = wt;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        List<List<Pair>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();

            adj.get(u).add(new Pair(v, w));
            adj.get(v).add(new Pair(u, w));
        }

        long[] dis = new long[n + 1];
        int[] parent = new int[n + 1];

        Arrays.fill(dis, Long.MAX_VALUE);

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Long.compare(a.wt, b.wt));

        dis[1] = 0;
        pq.add(new Pair(1, 0));
        parent[1] = -1;

        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            int node = curr.node;
            long d = curr.wt;

            if (d > dis[node]) continue;

            for (Pair p : adj.get(node)) {
                int adjN = p.node;
                long wt = p.wt;

                if (dis[node] + wt < dis[adjN]) {
                    dis[adjN] = dis[node] + wt;
                    parent[adjN] = node;
                    pq.add(new Pair(adjN, dis[adjN]));
                }
            }
        }

        if (dis[n] == Long.MAX_VALUE) {
            System.out.println(-1);
            return;
        }

        List<Integer> path = new ArrayList<>();
        int cur = n;

        while (cur != -1) {
            path.add(cur);
            cur = parent[cur];
        }

        Collections.reverse(path);

        for (int v : path) {
            System.out.print(v + " ");
        }
    }
}