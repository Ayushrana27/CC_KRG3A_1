
import java.util.*;

class DisjointSet{

    ArrayList<Integer> size= new ArrayList<>();
    ArrayList<Integer> parent= new ArrayList<>();

    public DisjointSet(int n){
        for(int i=0;i<=n;i++){
            parent.add(i);
            size.add(1);
        }
    }

    public int findUPar(int node){
        if(parent.get(node)==node) return node;
        int ulp= findUPar(parent.get(node));
        parent.set(node,ulp);
        return ulp;
    }

    public void unionBySize(int u,int v){
        int ulp_u=findUPar(u);
        int ulp_v=findUPar(v);

        if(ulp_u == ulp_v) return;
        int size_u= size.get(ulp_u);
        int size_v=size.get(ulp_v);
        if(size_u < size_v){
            parent.set(ulp_u,ulp_v);
            size.set(ulp_v,size_u+size_v);
        }else{
            parent.set(ulp_v,ulp_u);
            size.set(ulp_u,size_u+size_v);
        }
    }
}

public class DSU {
    public static void main(String[] args) {
        DisjointSet ds = new DisjointSet(7);
        ds.unionBySize(1, 2);
        ds.unionBySize(2, 3);
        ds.unionBySize(4, 5);
        if (ds.findUPar(1) == ds.findUPar(7))
            System.out.println("Same component");
        else
            System.out.println("Different components");
        ds.unionBySize(6, 7);
        ds.unionBySize(5, 6);

        ds.unionBySize(3, 7);

        if (ds.findUPar(1) == ds.findUPar(7))
            System.out.println("Same component");
        else
            System.out.println("Different components");
    }
}
