class Solution {

    static final long MOD = 1000000007;
    static final long BASE = 31;

    public int repeatedStringMatch(String a, String b) {

        int n = a.length();
        int m = b.length();

        int k = (m + n - 1) / n;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k + 2; i++) sb.append(a);

        String big = sb.toString();

        if (rabinKarp(big.substring(0, k * n), b)) return k;
        if (rabinKarp(big.substring(0, (k + 1) * n), b)) return k + 1;
        if (rabinKarp(big.substring(0, (k + 2) * n), b)) return k + 2;

        return -1;
    }

    private boolean rabinKarp(String text, String pat) {
        int n = text.length();
        int m = pat.length();

        if (m > n) return false;

        long patHash = 0;
        long windowHash = 0;
        long power = 1;

        for (int i = 0; i < m; i++) {
            patHash = (patHash * BASE + pat.charAt(i)) % MOD;
            windowHash = (windowHash * BASE + text.charAt(i)) % MOD;
            if (i > 0) power = (power * BASE) % MOD;
        }

        if (patHash == windowHash && text.substring(0, m).equals(pat))
            return true;

        for (int i = m; i < n; i++) {
            windowHash = (windowHash - (text.charAt(i - m) * power) % MOD + MOD) % MOD;
            windowHash = (windowHash * BASE + text.charAt(i)) % MOD;

            if (windowHash == patHash) {
                if (text.substring(i - m + 1, i + 1).equals(pat))
                    return true;
            }
        }
        return false;
    }
}
