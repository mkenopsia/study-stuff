package tasks;

import java.util.Arrays;

interface Foo {
    int x = 10;
}

public class Draft {
    public static void main(String[] args) {
        int[] arr = {2,0,1};
//        new Solution().sortColors(arr);
//        System.out.println(Arrays.toString(arr));
        char c = 65;
        System.out.println("c = " + c);
    }

    static class Solution {
        public void sortColors(int[] nums) {
            int left = 0, mid = 0, right = nums.length-1;
            while(mid <= right) {
                if(nums[mid] == 0) {
                    int temp = nums[left];
                    nums[left++] = 0;
                    nums[mid++] = temp;
                } else if(nums[mid] == 1) {
                    mid++;
                } else if(nums[mid] == 2) {
                    int temp = nums[right];
                    nums[right--] = 2;
                    nums[mid] = temp;
                }
            }
        }
    }
}
