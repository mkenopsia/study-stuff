package tasks;

import java.util.Arrays;

public class Draft {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().sortArray(new int[]{5, 1, 1, 2, 0, 0, 13})));
    }

    static class Solution {

        private void merge(int[] nums, int l, int mid, int r) {
            final int[] left = new int[mid - l + 1];
            final int[] right = new int[r - mid];
            System.arraycopy(nums, l, left, 0, left.length);
            System.arraycopy(nums, mid + 1, right, 0, right.length);

            int i = l, j = 0, k = 0;
            final int leftLen = left.length;
            final int rightLen = right.length;
            while (j < leftLen || k < rightLen) {
                if (j == leftLen) {
                    nums[i++] = right[k++];
                } else if (k == rightLen) {
                    nums[i++] = left[j++];
                } else if (left[j] < right[k]) {
                    nums[i++] = left[j++];
                } else {
                    nums[i++] = right[k++];
                }
            }
        }

        public void mergeSort(int[] nums, int left, int right) {
            if (left < right) {
                final int mid = (left + right) / 2;
                mergeSort(nums, left, mid);
                mergeSort(nums, mid + 1, right);
                merge(nums, left, mid, right);
            }
        }

        public int[] sortArray(int[] nums) {
            mergeSort(nums, 0, nums.length - 1);
            return nums;
        }
    }
}
