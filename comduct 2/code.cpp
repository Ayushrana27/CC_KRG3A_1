#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int f(vector<int>& nums) {
        long long ans = 0;
        int n = nums.size();
        for (int bit = 0; bit < 32; bit++) {
            int ones = 0;
            for (int num : nums) {
                if ((num >> bit) & 1) {
                    ones++;
                }
            }
            ans += 2LL * ones * (n - ones);
        }
        return (int)ans;
    }
};
int main() {
    Solution sol;
    vector<int> nums = {1, 3, 5};

    cout << "Total Hamming Distance: "
         << sol.f(nums);

    return 0;
}
