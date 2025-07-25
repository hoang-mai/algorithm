package solution.max.sum;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode Problem 3487: Maximum Unique Subarray Sum After Deletion
 * Given an array of integers nums, find the maximum sum of distinct elements.
 * If all elements are negative, return the maximum (least negative) element.
 * Otherwise, return the sum of all distinct non-negative elements.
 * @author Mai Anh Hoàng
 * @since 25/07/2025
 */
public class SolutionMaxSum {
    /**
     * Tìm tổng tối đa của các phần tử khác nhau trong mảng.
     * Nếu tất cả phần tử đều âm, trả về phần tử lớn nhất (ít âm nhất).
     * Ngược lại, trả về tổng của tất cả các phần tử không âm khác nhau.
     * Độ phức tạp thời gian: O(n), trong đó n là độ dài của mảng nums.
     * Độ phức tạp không gian: O(n), trong đó n là số phần tử không âm khác nhau.
     *
     * @param nums Mảng các số nguyên đầu vào.
     * @return Tổng tối đa của các phần tử khác nhau.
     */
    public int maxSum(int[] nums) {
        int negativeMax = Integer.MIN_VALUE;        // Phần tử âm lớn nhất (ít âm nhất)
        Set<Integer> set = new HashSet<>();         // Set lưu các phần tử không âm khác nhau
        
        // Phân loại các phần tử: âm và không âm
        for (int num : nums) {
            if (num < 0) {
                negativeMax = Math.max(negativeMax, num);   // Cập nhật phần tử âm lớn nhất
            } else {
                set.add(num);                               // Thêm phần tử không âm vào set (tự động loại trừ trùng lặp)
            }
        }
        
        // Nếu không có phần tử không âm nào, trả về phần tử âm lớn nhất
        if (set.isEmpty()) {
            return negativeMax;
        } else {
            // Tính tổng của tất cả phần tử không âm khác nhau
            int sum = 0;
            for (int num : set) {
                sum += num;
            }
            return sum;
        }
    }
    public static void main(String[] args) {
        SolutionMaxSum solution = new SolutionMaxSum();
        
        // Test case 1: Mảng có cả số dương và số âm
        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Test case 1: " + solution.maxSum(nums1)); // Expected: 7 (1+4+2 = 7)
        
        // Test case 2: Tất cả số âm
        int[] nums2 = {-5, -3, -8, -1, -2};
        System.out.println("Test case 2: " + solution.maxSum(nums2)); // Expected: -1
        
        // Test case 3: Tất cả số không âm
        int[] nums3 = {1, 2, 3, 2, 1, 4};
        System.out.println("Test case 3: " + solution.maxSum(nums3)); // Expected: 10 (1+2+3+4 = 10)
        
        // Test case 4: Có số 0
        int[] nums4 = {0, -1, 2, -3, 2, 0};
        System.out.println("Test case 4: " + solution.maxSum(nums4)); // Expected: 2 (0+2 = 2)
    }
}
