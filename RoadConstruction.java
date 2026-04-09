import java.util.*;

class DisjointSet {
    int[] size;
    int[] parent;
    int numComponents;
    int maxComponentSize;

    public DisjointSet(int n) {
        size = new int[n + 1];
        parent = new int[n + 1];
        numComponents = n;
        maxComponentSize = 1;
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int findUPar(int node) {
        if (parent[node] == node) return node;
        return parent[node] = findUPar(parent[node]);
    }

    public void unionBySize(int u, int v) {
        int ulp_u = findUPar(u);
        int ulp_v = findUPar(v);

        if (ulp_u == ulp_v) return;

        if (size[ulp_u] < size[ulp_v]) {
            parent[ulp_u] = ulp_v;
            size[ulp_v] += size[ulp_u];
            maxComponentSize = Math.max(maxComponentSize, size[ulp_v]);
        } else {
            parent[ulp_v] = ulp_u;
            size[ulp_u] += size[ulp_v];
            maxComponentSize = Math.max(maxComponentSize, size[ulp_u]);
        }
        numComponents--;
    }
}

public class RoadConstruction {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;

        int n = sc.nextInt();
        int m = sc.nextInt();

        DisjointSet ds = new DisjointSet(n);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            ds.unionBySize(u, v);
            sb.append(ds.numComponents).append(" ").append(ds.maxComponentSize).append("\n");
        }
        System.out.print(sb);
    }
}
