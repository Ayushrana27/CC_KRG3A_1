import java.util.*;
public class Solution {
    public String findOrder(String[] names) {
        int n = names.length;
        Map<Character, List<Character>> graph = new HashMap<>();
        Map<Character, Integer> indeg = new HashMap<>();
        Set<Character> letters = new HashSet<>();
        for (int i = 0; i < n - 1; i++) {
            String s = names[i], t = names[i + 1];
            boolean found = false;
            for (int j = 0; j < Math.min(s.length(), t.length()); j++) {
                char cs = s.charAt(j), ct = t.charAt(j);
                if (cs != ct) {
                    graph.computeIfAbsent(cs, k -> new ArrayList<>()).add(ct);
                    indeg.computeIfAbsent(ct, k -> 0);
                    indeg.put(ct, indeg.get(ct) + 1);
                    letters.add(cs);
                    letters.add(ct);
                    found = true;
                    break;
                }
            }
            if (!found && s.length() > t.length()) return "Impossible";
        }
        for (char c = 'a'; c <= 'z'; c++) {
            indeg.putIfAbsent(c, 0);
            letters.add(c);
        }
        StringBuilder order = new StringBuilder();
        Queue<Character> q = new LinkedList<>();
        for (char c : letters) {
            if (indeg.get(c) == 0) q.offer(c);
        }
        while (!q.isEmpty()) {
            char u = q.poll();
            order.append(u);
            for (char v : graph.getOrDefault(u, new ArrayList<>())) {
                indeg.put(v, indeg.get(v) - 1);
                if (indeg.get(v) == 0) q.offer(v);
            }
        }
        if (order.length() == 26) return order.toString();
        return "Impossible";
    }
}
