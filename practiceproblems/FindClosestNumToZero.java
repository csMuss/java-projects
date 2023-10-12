class Solution {
    public int findClosestNumber(int[] nums) {
        int length = nums.length;
        int closestNum = nums[0];

        for(int i = 0; i < length; i++){
            if(Math.abs(closestNum) > Math.abs(nums[i])){
                closestNum = Math.abs(nums[i]);
            } 

            if(closestNum == Math.abs(nums[i])){
                closestNum = nums[i];
           }
        }
        return closestNum;
    }
}
