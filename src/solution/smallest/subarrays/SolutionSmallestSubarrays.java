package solution.smallest.subarrays;

import java.util.Arrays;

/**
 * LeetCode Problem 2411: Smallest Subarrays With Maximum Bitwise OR
 * You are given a 0-indexed array nums of length n, consisting of non-negative integers. For each index i from 0 to n - 1, you must determine the size of the minimum sized non-empty subarray of nums starting at i (inclusive) that has the maximum possible bitwise OR.
 * In other words, let Bij be the bitwise OR of the subarray nums[i...j]. You need to find the smallest subarray starting at i, such that bitwise OR of this subarray is equal to max(Bik) where i <= k <= n - 1.
 * The bitwise OR of an array is the bitwise OR of all the numbers in it.
 * Return an integer array answer of size n where answer[i] is the length of the minimum sized subarray starting at i with maximum bitwise OR.
 * A subarray is a contiguous non-empty sequence of elements within an array.
 * @author Mai Anh Hoàng
 * @since 29/07/2025
 */
public class SolutionSmallestSubarrays {
    /**
     * Tìm độ dài nhỏ nhất của các subarray bắt đầu tại mỗi vị trí có OR bằng OR của toàn bộ subarray từ vị trí đó đến cuối.
     * Thuật toán duyệt ngược kết hợp mảng vị trí bit để tối ưu hóa.
     * Độ phức tạp thời gian: O(n * 32), với n là độ dài mảng nums.
     * Độ phức tạp không gian: O(32 + n).
     *
     * @param nums Mảng số nguyên đầu vào.
     * @return Mảng kết quả với độ dài nhỏ nhất cho mỗi vị trí.
     */
    public int[] smallestSubarrays(int[] nums) {
        int[] pos = new int[32]; // Vị trí xuất hiện cuối cùng của mỗi bit
        int[] res = new int[nums.length]; // Kết quả độ dài nhỏ nhất cho mỗi vị trí
        Arrays.fill(pos, -1); // Khởi tạo tất cả vị trí là -1
        // Duyệt ngược từ cuối về đầu
        for (int i = nums.length - 1; i >= 0; i--) {
            int j = i; // Vị trí xa nhất cần bao phủ để đạt OR tối đa
            // Kiểm tra từng bit từ 0 đến 31
            for (int k = 0; k < 32; k++) {
                // Nếu bit k chưa xuất hiện ở nums[i], cần bao phủ vị trí cuối cùng của bit đó
                if ((nums[i] & (1 << k)) == 0) {
                    if (pos[k] != -1) {
                        j = Math.max(pos[k], j); // Cập nhật vị trí xa nhất cần bao phủ
                    }
                } else {
                    pos[k] = i; // Cập nhật vị trí xuất hiện cuối cùng của bit k
                }
            }
            res[i] = j - i + 1; // Độ dài nhỏ nhất cho vị trí i
        }
        return res;
    }

    public static void main(String[] args) {
        SolutionSmallestSubarrays solution = new SolutionSmallestSubarrays();
        // Test case 1: Mảng có nhiều bit khác nhau
        int[] nums1 = {1, 0, 2, 1, 3};
        System.out.println("Test case 1: " + Arrays.toString(solution.smallestSubarrays(nums1))); // Expected: [3,3,2,2,1]
        // Test case 2: Mảng toàn số 0
        int[] nums2 = {0,0,0};
        System.out.println("Test case 2: " + Arrays.toString(solution.smallestSubarrays(nums2))); // Expected: [1,1,1]
        // Test case 3: Mảng tăng dần
        int[] nums3 = {1,2,4,8};
        System.out.println("Test case 3: " + Arrays.toString(solution.smallestSubarrays(nums3))); // Expected: [4,3,2,1]
    }
}
