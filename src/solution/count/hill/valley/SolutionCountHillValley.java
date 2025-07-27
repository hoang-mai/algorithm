package solution.count.hill.valley;

/**
 * LeetCode Problem 2210: Count Hills and Valleys in an Array
 * You are given a 0-indexed integer array nums. An index i is part of a hill in nums if the closest non-equal neighbors of i are smaller than nums[i]. Similarly, an index i is part of a valley in nums if the closest non-equal neighbors of i are larger than nums[i]. Adjacent indices i and j are part of the same hill or valley if nums[i] == nums[j].
 * Note that for an index to be part of a hill or valley, it must have a non-equal neighbor on both the left and right of the index.
 * Return the number of hills and valleys in nums.
 * @author Mai Anh Hoàng
 * @since 27/07/2025
 */
public class SolutionCountHillValley {
    /**
     * Đếm số lượng đỉnh (hill) và thung lũng (valley) trong mảng.
     * Đỉnh: nums[i] lớn hơn các phần tử khác liền kề không bằng nums[i].
     * Thung lũng: nums[i] nhỏ hơn các phần tử khác liền kề không bằng nums[i].
     * Độ phức tạp thời gian: O(n^2), với n là độ dài mảng nums.
     * Độ phức tạp không gian: O(1).
     *
     * @param nums Mảng số nguyên đầu vào.
     * @return Số lượng đỉnh và thung lũng trong mảng.
     */
    public int countHillValley(int[] nums) {
        int count = 0; // Số lượng đỉnh và thung lũng
        int left = 0;  // Trạng thái so sánh bên trái
        int right = 0; // Trạng thái so sánh bên phải
        for (int i = 1; i < nums.length - 1; i++) {
            // Tìm phần tử khác nums[i] gần nhất bên trái
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    left = 1; // nums[i] lớn hơn bên trái
                    break;
                } else if (nums[i] < nums[j]) {
                    left = -1; // nums[i] nhỏ hơn bên trái
                    break;
                }
            }
            // Tìm phần tử khác nums[i] gần nhất bên phải
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    right = 1; // nums[i] lớn hơn bên phải
                    break;
                } else if (nums[i] < nums[j]) {
                    right = -1; // nums[i] nhỏ hơn bên phải
                    break;
                }
            }
            // Nếu cùng dấu và khác 0 (không phải phần tử trùng lặp), thì là đỉnh hoặc thung lũng
            if (left == right && left != 0 && right != 0 && nums[i] != nums[i-1]) {
                count++;
            }
            left = 0;
            right = 0;
        }
        return count;
    }

    public static void main(String[] args) {
        SolutionCountHillValley solution = new SolutionCountHillValley();
        // Test case 1: Có cả đỉnh và thung lũng
        int[] nums1 = {2, 4, 1, 1, 6, 5};
        System.out.println("Test case 1: " + solution.countHillValley(nums1)); // Expected: 3
        // Test case 2: Không có đỉnh/thung lũng
        int[] nums2 = {1, 1, 1, 1};
        System.out.println("Test case 2: " + solution.countHillValley(nums2)); // Expected: 0
        // Test case 3: Đỉnh ở giữa, thung lũng ở cuối
        int[] nums3 = {1, 3, 2, 4, 1};
        System.out.println("Test case 3: " + solution.countHillValley(nums3)); // Expected: 3
    }
}
