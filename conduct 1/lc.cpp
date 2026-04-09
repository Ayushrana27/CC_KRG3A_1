class Solution {
public:
    int nthMagicalNumber(int n, int a, int b) {
        int mod = 1e9+7;
        long long l = 1LL *min(a,b);
        long long r = 1LL * n* min(a,b);
        long long lcm = 1LL * a*b/gcd(a,b);
        while(l<r){
            long long mid = l+ (r-l)/2;
            long long cnt = (mid/a)%mod + (mid/b)%mod-(mid/lcm)%mod;
            if(cnt<n){
                l = mid+1;
            }
            else{
                r = mid;
            }
        }
        return l%mod;
    }
};
