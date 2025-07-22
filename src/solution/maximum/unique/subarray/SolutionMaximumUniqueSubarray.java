package solution.maximum.unique.subarray;

import java.util.*;

/**
 * LeetCode Problem 1695: Maximum Erasure Value
 * You are given an array of positive integers nums and want to erase a subarray containing unique elements. 
 * The score you get by erasing the subarray is equal to the sum of its elements.
 * Return the maximum score you can get by erasing exactly one subarray.
 * A subarray b is called to be a subarray of a if it forms a contiguous sequence of elements in a.
 * @author Mai Anh Hoàng
 * @since 22/07/2025
 */
public class SolutionMaximumUniqueSubarray {
    /**
     * Tìm tổng tối đa của mảng con chứa các phần tử duy nhất.
     * Thuật toán sử dụng sliding window với HashMap để theo dõi các phần tử và vị trí của chúng.
     * Độ phức tạp thời gian: O(n), trong đó n là độ dài của mảng nums.
     * Độ phức tạp không gian: O(min(m, n)), trong đó m là số phần tử khác nhau trong mảng.
     * 
     * @param nums Mảng các số nguyên dương.
     * @return Tổng tối đa của mảng con chứa các phần tử duy nhất.
     */
    public int maximumUniqueSubarray(int[] nums) {
        int sum = 0;                                        // Tổng hiện tại của sliding window
        int max = 0;                                        // Tổng tối đa đã tìm được
        int left = 0;                                       // Con trỏ trái của sliding window
        Map<Integer, Integer> map = new HashMap<>(nums.length); // Map lưu trữ phần tử và vị trí cuối cùng
        
        for (int i = 0; i < nums.length; i++) {
            Integer index = map.get(nums[i]);               // Kiểm tra phần tử đã xuất hiện chưa
            
            if (index != null) {
                // Nếu phần tử đã xuất hiện, cập nhật max và thu hẹp window
                max = Math.max(max, sum);
                
                // Loại bỏ các phần tử từ left đến index (bao gồm phần tử trùng lặp)
                for (int j = left; j <= index; j++) {
                    sum -= nums[j];                         // Giảm tổng
                    map.remove(nums[j]);                    // Xóa khỏi map
                }
                left = index + 1;                           // Cập nhật con trỏ trái
            }
            
            map.put(nums[i], i);                            // Thêm phần tử hiện tại vào map
            sum += nums[i];                                 // Cộng vào tổng hiện tại
        }
        
        // Cập nhật max cuối cùng (trường hợp window kết thúc ở cuối mảng)
        max = Math.max(max, sum);
        return max;
    }

    public static void main(String[] args) {
        SolutionMaximumUniqueSubarray solution = new SolutionMaximumUniqueSubarray();
        
        // Test case 1: Ví dụ từ LeetCode
        int[] nums1 = {5, 2, 1, 2, 5, 2, 1, 2, 5};
        System.out.println("Test case 1: " + solution.maximumUniqueSubarray(nums1)); // Expected: 8 ([5,2,1])
        
        // Test case 2: Mảng không có phần tử trùng lặp
        int[] nums2 = {1, 2, 3, 4, 5};
        System.out.println("Test case 2: " + solution.maximumUniqueSubarray(nums2)); // Expected: 15
        
        // Test case 3: Mảng có nhiều phần tử trùng lặp
        int[] nums3 = {4, 2, 4, 5, 6};
        System.out.println("Test case 3: " + solution.maximumUniqueSubarray(nums3)); // Expected: 17 ([2,4,5,6])
        
        // Test case 4: Mảng một phần tử
        int[] nums4 = {10};
        System.out.println("Test case 4: " + solution.maximumUniqueSubarray(nums4)); // Expected: 10
    }
}
