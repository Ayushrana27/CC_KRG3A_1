class Solution {
    public int triangularSum(int[] nums) {
        for(int size=nums.length-1;size>0;size--){
            for(int i=0;i<size;i++){
                nums[i]=(nums[i]+nums[i+1])%10;
            }
        }
        return nums[0];
    }
}
