import java.util.*;

public class BuildingRoads {

    static int n, m;
    static List<List<Integer>> adj = new ArrayList<>();
    static boolean[] visited;
    static List<Integer> bridges = new ArrayList<>();

    static void dfs(int node) {
        visited[node] = true;

        for (int child : adj.get(node)) {
            if (!visited[child]) {
                dfs(child);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        visited = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            adj.get(a).add(b);
            adj.get(b).add(a);
        }

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                bridges.add(i);
                dfs(i);
            }
        }

        System.out.println(bridges.size() - 1);

        for (int i = 0; i < bridges.size() - 1; i++) {
            System.out.println(bridges.get(i) + " " + bridges.get(i + 1));
        }
    }
}