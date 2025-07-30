package solution.longest.subarray;

/**
 * LeetCode Problem 2419: Longest Subarray With Maximum Bitwise AND
 * You are given an integer array nums of size n.
 * Consider a non-empty subarray from nums that has the maximum possible bitwise AND.
 * In other words, let k be the maximum value of the bitwise AND of any subarray of nums. Then, only subarrays with a bitwise AND equal to k should be considered.
 * Return the length of the longest such subarray.
 *  The bitwise AND of an array is the bitwise AND of all the numbers in it.
 * A subarray is a contiguous sequence of elements within an array.
 * @author Mai Anh Hoàng
 * @since 30/07/2025
 */
public class SolutionLongestSubarray {
    /**
     * Tìm độ dài của mảng con liên tiếp dài nhất có giá trị AND lớn nhất.
     * 
     * Quan sát quan trọng: Giá trị AND lớn nhất có thể của bất kỳ mảng con nào
     * chính là giá trị lớn nhất trong mảng. Điều này là do phép AND chỉ có thể
     * làm giảm hoặc giữ nguyên giá trị, không thể tăng lên.
     * 
     * Do đó, ta chỉ cần tìm mảng con liên tiếp dài nhất gồm toàn bộ các phần tử
     * có giá trị bằng giá trị lớn nhất trong mảng.
     * 
     * Độ phức tạp thời gian: O(n), trong đó n là độ dài của mảng.
     * Độ phức tạp không gian: O(1), chỉ sử dụng các biến cục bộ.
     * 
     * @param nums Mảng số nguyên đầu vào
     * @return Độ dài mảng con liên tiếp dài nhất có giá trị AND lớn nhất
     */
    public int longestSubarray(int[] nums) {
        int length =0;
        int maxLength = 0;
        int max=0;
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
                length = 1;
                maxLength = 1;
            } else if (nums[i] == max) {
                length++;
            } else {
                length = 0;
            }
            maxLength = Math.max(maxLength, length);
        }
        return maxLength;
    }
    public static void main(String[] args) {
        SolutionLongestSubarray solution = new SolutionLongestSubarray();
        int[] nums = {1, 2, 2, 3, 3, 3, 2, 1};
        System.out.println("Longest subarray length: " + solution.longestSubarray(nums)); // Output: 3
    }
}
