package solution.find.lhs;

import java.util.*;

/**
 * LeetCode Problem 594: Longest Harmonious Subsequence.
 * We define a harmonious array as an array where the difference between its maximum value and its minimum value is exactly 1.
 * Given an integer array nums, return the length of its longest harmonious subsequence among all its possible subsequences.
 * @author Mai Anh Hoàng
 * @since 30/06/2025
 */
public class SolutionFindLHS {

    /**
     * Tìm kiếm mảng con có độ dài lớn nhất trong mảng đầu vào sao cho phần tử lớn nhất và nhỏ nhất của nó chỉ chênh lệch nhau 1.
     * Độ phức tạp thời gian là O(n log n) do sắp xếp danh sách các phần tử duy nhất.
     * Độ phức tạp không gian là O(n) do sử dụng một bản đồ để lưu trữ tần suất của các phần tử.
     * @param nums mảng đầu vào chứa các số nguyên
     * @return độ dài của mảng con lớn nhất thỏa mãn điều kiện
     */
    public int findLHS(int[] nums) {
        int result = 0;
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int num : nums) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(frequency.entrySet());
        list.sort(Map.Entry.comparingByKey());
        for (int i = 0; i < list.size() - 1; i++) {
            Map.Entry<Integer, Integer> currentEntry = list.get(i);
            Map.Entry<Integer, Integer> nextEntry = list.get(i + 1);
            if (nextEntry.getKey() - currentEntry.getKey() == 1) {
                result = Math.max(result, currentEntry.getValue() + nextEntry.getValue());
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SolutionFindLHS solution = new SolutionFindLHS();
        int[] nums = {0, 3, 1, 3, 3, 3, 0, 1, 0, 2, 0, 3, 1, 3, -3, 2, 0, 3, 1, 2, 2, -3, 2, 2, 3, 3};
        System.out.println(solution.findLHS(nums));
    }
}