import java.util.*;
public class Solution {
    public List<String> processQueries(String[] memory, String[] queries) {
        List<String> res = new ArrayList<>();
        Map<String, Set<String>> diffMap = new HashMap<>();
        int maxLen = 0;
        for (String s : memory) {
            int len = s.length();
            maxLen = Math.max(maxLen, len);
            for (int p = 0; p < len; p++) {
                String mask = s.substring(0, p) + "*" + s.substring(p + 1);
                diffMap.computeIfAbsent(mask, k -> new HashSet<>()).add(s);
            }
        }
        for (String q : queries) {
            int len = q.length();
            boolean found = false;
            if (len > maxLen) {
                res.add("NO");
                continue;
            }
            for (int p = 0; p < len; p++) {
                String mask = q.substring(0, p) + "*" + q.substring(p + 1);
                Set<String> candidates = diffMap.get(mask);
                if (candidates != null) {
                    for (String t : candidates) {
                        if (diffByOne(q, t)) {
                            found = true;
                            break;
                        }
                    }
                }
                if (found) break;
            }
            res.add(found ? "YES" : "NO");
        }
        return res;
    }
    private boolean diffByOne(String s1, String s2) {
        int diff = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (++diff > 1) return false;
            }
        }
        return true;
    }
}
