import java.util.*;
public class Solution {
    public int cntBits(int[] A) {
        int MOD = 1000000007;
        int n = A.length;
        long ans = 0;
        for (int i = 0; i < 31; i++) {
            long cnt1 = 0;
            for (int x : A) {
                if ((x & (1 << i)) != 0) cnt1++;
            }
            long contrib = cnt1 * (n - cnt1) * 2;
            ans = (ans + contrib) % MOD;
        }
        return (int) ans;
    }
}
