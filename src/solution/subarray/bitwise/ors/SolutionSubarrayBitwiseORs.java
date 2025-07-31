package solution.subarray.bitwise.ors;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode Problem 898: Bitwise ORs of Subarrays
 * Given an integer array arr, return the number of distinct bitwise ORs of all the non-empty subarrays of arr.
 * The bitwise OR of a subarray is the bitwise OR of each integer in the subarray. 
 * A subarray is a contiguous non-empty sequence of elements within an array.
 * @author Mai Anh Hoàng
 * @since 31/07/2025
 */
public class SolutionSubarrayBitwiseORs {
    /**
     * Đếm số lượng giá trị OR khác nhau của tất cả các subarray trong mảng.
     * Thuật toán sử dụng dynamic programming với HashSet để lưu trữ các giá trị OR có thể.
     * Độ phức tạp thời gian: O(n * log(max_value)), với n là độ dài mảng arr.
     * Độ phức tạp không gian: O(n * log(max_value)), để lưu trữ các HashSet.
     * 
     * @param arr Mảng số nguyên đầu vào.
     * @return Số lượng giá trị OR khác nhau của tất cả các subarray.
     */
    public int subarrayBitwiseORs(int[] arr) {
        Set<Integer> ans = new HashSet<>();     // Lưu tất cả giá trị OR khác nhau
        Set<Integer> prev = new HashSet<>();    // Lưu các giá trị OR kết thúc tại vị trí trước đó

        for (int num : arr) {
            Set<Integer> cur = new HashSet<>();
            cur.add(num);                       // Mỗi phần tử tự nó là một subarray có OR = chính nó

            // Kết hợp với tất cả giá trị OR từ vị trí trước đó
            for (int x : prev) {
                cur.add(x | num);               // OR của subarray mở rộng thêm num
            }

            ans.addAll(cur);                    // Thêm tất cả giá trị OR mới vào kết quả
            prev = cur;                         // Cập nhật cho vòng lặp tiếp theo
        }

        return ans.size();
    }
    public static void main(String[] args) {
        SolutionSubarrayBitwiseORs solution = new SolutionSubarrayBitwiseORs();

        // Test case 1: Mảng có giá trị OR đa dạng
        int[] arr1 = {1, 1, 2};
        System.out.println("Test case 1: " + solution.subarrayBitwiseORs(arr1)); // Expected: 3

        // Test case 2: Mảng có nhiều phần tử khác nhau
        int[] arr2 = {1, 2, 4};
        System.out.println("Test case 2: " + solution.subarrayBitwiseORs(arr2)); // Expected: 6

        // Test case 3: Mảng có phần tử giống nhau
        int[] arr3 = {0};
        System.out.println("Test case 3: " + solution.subarrayBitwiseORs(arr3)); // Expected: 1

        // Test case 4: Mảng phức tạp
        int[] arr4 = {1, 2, 4, 8, 16};
        System.out.println("Test case 4: " + solution.subarrayBitwiseORs(arr4)); // Expected: 15
    }
}
