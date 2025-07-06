package find.sum.pairs;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode Problem 1865: Finding Pairs With a Certain Sum
 * You are given two integer arrays nums1 and nums2. You are tasked to implement a data structure that supports queries of two types:
 * 1. Add a positive integer to an element at index i in nums2.
 * 2. Count the number of pairs (i, j) such that nums1[i] + nums2[j] equals tot.
 * 
 * Implement the FindSumPairs class:
 * - FindSumPairs(int[] nums1, int[] nums2) Initializes the FindSumPairs object with two integer arrays nums1 and nums2.
 * - void add(int index, int val) Adds val to nums2[index], i.e., apply nums2[index] += val.
 * - int count(int tot) Returns the number of pairs (i, j) such that nums1[i] + nums2[j] == tot.
 * 
 * @author Mai Anh Hoàng
 * @since 06/07/2025
 */
class FindSumPairs {

    // HashMap lưu trữ tần số xuất hiện của các phần tử trong nums1 (không thay đổi)
    private final Map<Integer, Integer> map1 = new HashMap<>();
    // HashMap lưu trữ tần số xuất hiện của các phần tử trong nums2 (có thể thay đổi)
    private final Map<Integer, Integer> map2 = new HashMap<>();
    // Mảng nums2 gốc để theo dõi các thay đổi
    private final int[] nums2;

    /**
     * Khởi tạo đối tượng FindSumPairs với hai mảng số nguyên nums1 và nums2.
     * Độ phức tạp thời gian: O(n + m), trong đó n là độ dài nums1 và m là độ dài nums2.
     * Độ phức tạp không gian: O(n + m), để lưu trữ tần số trong hai HashMap.
     * 
     * @param nums1 Mảng số nguyên thứ nhất (không thay đổi).
     * @param nums2 Mảng số nguyên thứ hai (có thể thay đổi).
     */
    public FindSumPairs(int[] nums1, int[] nums2) {
        // Đếm tần số xuất hiện của các phần tử trong nums1
        for (int num : nums1) {
            this.map1.put(num, this.map1.getOrDefault(num, 0) + 1);
        }
        // Đếm tần số xuất hiện của các phần tử trong nums2
        for (int num : nums2) {
            this.map2.put(num, this.map2.getOrDefault(num, 0) + 1);
        }
        this.nums2 = nums2;
    }

    /**
     * Thêm val vào nums2[index], tức là thực hiện nums2[index] += val.
     * Cập nhật tần số trong map2 để đảm bảo tính nhất quán.
     * Độ phức tạp thời gian: O(1).
     * Độ phức tạp không gian: O(1).
     * 
     * @param index Chỉ số của phần tử trong nums2 cần thêm giá trị.
     * @param val Giá trị cần thêm vào nums2[index].
     */
    public void add(int index, int val) {
        // Giảm tần số của giá trị cũ trong map2
        if (this.map2.get(this.nums2[index]) == 1) {
            this.map2.remove(this.nums2[index]);
        } else {
            this.map2.put(this.nums2[index], this.map2.get(this.nums2[index]) - 1);
        }
        // Cập nhật giá trị mới trong nums2
        this.nums2[index] += val;
        // Tăng tần số của giá trị mới trong map2
        this.map2.put(this.nums2[index], this.map2.getOrDefault(this.nums2[index], 0) + 1);
    }

    /**
     * Đếm số lượng cặp (i, j) sao cho nums1[i] + nums2[j] == tot.
     * Độ phức tạp thời gian: O(n), trong đó n là số phần tử khác nhau trong nums1.
     * Độ phức tạp không gian: O(1), không sử dụng thêm bộ nhớ.
     * 
     * @param tot Tổng mục tiêu cần tìm.
     * @return Số lượng cặp (i, j) thỏa mãn điều kiện.
     */
    public int count(int tot) {
        int count = 0;
        // Duyệt qua tất cả các phần tử trong nums1
        for (Map.Entry<Integer, Integer> entry : this.map1.entrySet()) {
            int complement = tot - entry.getKey(); // Tính phần bù cần tìm trong nums2
            if (map2.containsKey(complement)) {
                // Số cặp = tần số trong nums1 * tần số trong nums2
                count += entry.getValue() * map2.get(complement);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        // Test case từ LeetCode
        int[] nums1 = {1, 1, 2, 2, 2, 3};
        int[] nums2 = {1, 4, 5, 2, 5, 4};
        FindSumPairs findSumPairs = new FindSumPairs(nums1, nums2);
        
        // Thực hiện các phép add
        findSumPairs.add(3, 2);    // nums2[3] += 2, nums2 = [1,4,5,4,5,4]
        findSumPairs.add(0, 1);    // nums2[0] += 1, nums2 = [2,4,5,4,5,4]
        findSumPairs.add(1, 1);    // nums2[1] += 1, nums2 = [2,5,5,4,5,4]
        
        // Đếm số cặp có tổng bằng 7
        System.out.println("Số cặp có tổng bằng 7: " + findSumPairs.count(7));
    }
}

/**
 * Your FindSumPairs object will be instantiated and called as such:
 * FindSumPairs obj = new FindSumPairs(nums1, nums2);
 * obj.add(index,val);
 * int param_2 = obj.count(tot);
 */