class Solution {
  public:
    int tsp(vector<vector<int>>& cost) {
        int n = cost.size();
        
        
       
        vector<vector<int>> dp(1 << n, vector<int>(n, INT_MAX));
        
        // Base case: from city 0, visit only city 0 itself (mask with just bit 0 set)
        dp[1][0] = 0;  // mask = 1 means only city 0 is visited
        
        // Iterate over all possible masks
        for (int mask = 1; mask < (1 << n); mask++) {
            // If mask does not include city 0, skip (we always start from 0)
            if (!(mask & 1)) continue;
            
            // For each possible current city i in this mask
            for (int i = 0; i < n; i++) {
                if (!(mask & (1 << i))) continue;  // i not in mask
                if (dp[mask][i] == INT_MAX) continue;  // unreachable
                
                // Try to go to each unvisited city j
                for (int j = 0; j < n; j++) {
                    if (mask & (1 << j)) continue;  // j already visited
                    
                    int newMask = mask | (1 << j);
                    dp[newMask][j] = min(dp[newMask][j], 
                                         dp[mask][i] + cost[i][j]);
                }
            }
        }
        
        // Final answer: visit all cities (mask = 2^n - 1), end at some city i,
        // then return to city 0
        int fullMask = (1 << n) - 1;
        int ans = INT_MAX;
        
        for (int i = 0; i < n; i++) {
            if (dp[fullMask][i] == INT_MAX) continue;
            ans = min(ans, dp[fullMask][i] + cost[i][0]);
        }
        
        return ans;
    }
};
