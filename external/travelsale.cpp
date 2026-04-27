class Solution {
  public:
    int tsp(vector<vector<int>>& cost) {
        int n = cost.size();
        
        vector<vector<int>> dp(1 << n, vector<int>(n, INT_MAX));
        
        dp[1][0] = 0;  
        
        for (int mask = 1; mask < (1 << n); mask++) {
            if (!(mask & 1)) continue;
            
            for (int i = 0; i < n; i++) {
                if (!(mask & (1 << i))) continue;  
                if (dp[mask][i] == INT_MAX) continue;  
                
                for (int j = 0; j < n; j++) {
                    if (mask & (1 << j)) continue;  
                    
                    int newMask = mask | (1 << j);
                    dp[newMask][j] = min(dp[newMask][j], dp[mask][i] + cost[i][j]);
                }
            }
        }
        
        int fullMask = (1 << n) - 1;
        int ans = INT_MAX;
        
        for (int i = 0; i < n; i++) {
            if (dp[fullMask][i] == INT_MAX) continue;
            ans = min(ans, dp[fullMask][i] + cost[i][0]);
        }
        
        return ans;
    }
};
