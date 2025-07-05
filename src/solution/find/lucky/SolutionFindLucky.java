package solution.find.lucky;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * LeetCode Problem 1394: Find Lucky Integer in an Array
 * Given an array of integers arr, a lucky integer is an integer that has a frequency in the array equal to its value.
 * Return the largest lucky integer in the array. If there is no lucky integer return -1.
 * @author Mai Anh Hoàng
 * @since 05/07/2025
 */
public class SolutionFindLucky {
    /**
     * Tìm số may mắn lớn nhất trong mảng.
     * Số may mắn là số có tần số xuất hiện trong mảng bằng chính giá trị của nó.
     * Độ phức tạp thời gian: O(n), trong đó n là độ dài của mảng arr.
     * Độ phức tạp không gian: O(n), vì sử dụng HashMap để lưu trữ tần số xuất hiện của các phần tử.
     * 
     * @param arr Mảng các số nguyên đầu vào.
     * @return Số may mắn lớn nhất trong mảng, hoặc -1 nếu không có số may mắn nào.
     */
    public int findLucky(int[] arr) {
        int result = -1;                                    // Kết quả trả về, khởi tạo là -1
        HashMap<Integer, Integer> map = new HashMap<>(500); // HashMap lưu trữ tần số xuất hiện
        
        // Đếm tần số xuất hiện của mỗi phần tử trong mảng
        for (int j : arr) {
            map.put(j, map.getOrDefault(j, 0) + 1);
        }
        
        // Tìm số may mắn lớn nhất (số có giá trị bằng tần số xuất hiện)
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (Objects.equals(entry.getKey(), entry.getValue())) {
                result = Math.max(result, entry.getValue());
            }
        }
        
        return result;
    }

    public static void main(String[] args) {
        SolutionFindLucky solution = new SolutionFindLucky();
        
        // Test case 1: [2,2,3,4]
        int[] arr1 = {2, 2, 3, 4};
        System.out.println("Test case 1: " + solution.findLucky(arr1)); // Expected: 2
        
        // Test case 2: [1,2,2,3,3,3]
        int[] arr2 = {1, 2, 2, 3, 3, 3};
        System.out.println("Test case 2: " + solution.findLucky(arr2)); // Expected: 3
        
        // Test case 3: [2,2,2,3,3]
        int[] arr3 = {2, 2, 2, 3, 3};
        System.out.println("Test case 3: " + solution.findLucky(arr3)); // Expected: -1
    }
}
