package solution.maximum.length.II;

/**
 * LeetCode Problem 3202: Find the Maximum Length of Valid Subsequence II
 * You are given an integer array nums and an integer k.
 * A subsequence sub of nums with length x is called valid if it satisfies:
 * (sub[0] + sub[1]) % k == (sub[1] + sub[2]) % k == ... == (sub[x - 2] + sub[x - 1]) % k.
 * Return the length of the longest valid subsequence of nums.
 * A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
 * @author Mai Anh Hoàng
 * @since 18/07/2025
 */
public class SolutionMaximumLengthII {
    /**
     * Tìm độ dài tối đa của dãy con hợp lệ với modulo k.
     * Một dãy con hợp lệ là dãy mà tổng của mọi cặp phần tử liền kề
     * đều có cùng số dư khi chia cho k.
     * 
     * Sử dụng dynamic programming với ma trận dp[i][j] để lưu trữ
     * độ dài tối đa của dãy con kết thúc bằng phần tử có mod = j
     * và có pattern mod trước đó là i.
     * 
     * Độ phức tạp thời gian: O(n * k), trong đó n là độ dài mảng.
     * Độ phức tạp không gian: O(k²), cho ma trận dp.
     * 
     * @param nums Mảng các số nguyên đầu vào
     * @param k Số nguyên dương để tính modulo
     * @return Độ dài tối đa của dãy con hợp lệ
     */
    public int maximumLengthII(int[] nums, int k) {
        int[][] dp = new int[k][k];
        int maxLength = 0;
        for (int num : nums) {
            int mod = num % k;
            for (int i = 0; i < k; i++) {
                dp[i][mod] = dp[mod][i] + 1;
                maxLength = Math.max(maxLength, dp[i][mod]);
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        SolutionMaximumLengthII solutionMaximumLengthII = new SolutionMaximumLengthII();
        int[] nums = {1, 2, 3, 4, 5};
        int k = 2;
        System.out.println("Maximum length of valid subsequence: " + solutionMaximumLengthII.maximumLengthII(nums, k));
    }
}
