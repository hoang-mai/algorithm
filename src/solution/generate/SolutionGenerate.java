package solution.generate;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode Problem 118: Pascal's Triangle
 * Given an integer numRows, return the first numRows of Pascal's triangle.
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 * @author Mai Anh Hoàng
 * @since 01/08/2025
 */
public class SolutionGenerate {
    /**
     * Tạo tam giác Pascal với numRows hàng.
     * Mỗi số trong tam giác Pascal bằng tổng của hai số trực tiếp phía trên nó.
     * Độ phức tạp thời gian: O(numRows²), vì cần tính toán numRows*(numRows+1)/2 phần tử.
     * Độ phức tạp không gian: O(numRows²), để lưu trữ toàn bộ tam giác Pascal.
     * 
     * @param numRows Số hàng của tam giác Pascal cần tạo.
     * @return Danh sách các hàng của tam giác Pascal.
     */
    public List<List<Integer>> generate(int numRows) {
        // Xử lý trường hợp đặc biệt cho 1 và 2 hàng
        if (numRows == 1) return List.of(List.of(1));
        if (numRows == 2) return List.of(List.of(1), List.of(1, 1));
        
        List<List<Integer>> result = new ArrayList<>(numRows);
        result.add(List.of(1));        // Hàng đầu tiên: [1]
        result.add(List.of(1, 1));     // Hàng thứ hai: [1, 1]
        
        // Tạo các hàng từ thứ 3 trở đi
        for (int i = 2; i < numRows; i++) {
            List<Integer> row = new ArrayList<>(i + 1);
            row.add(1);                // Phần tử đầu tiên luôn là 1
            
            List<Integer> prevRow = result.get(i - 1);
            // Tính các phần tử ở giữa dựa trên hàng trước đó
            for (int j = 1; j < i; j++) {
                // Mỗi phần tử = tổng của hai phần tử trên hàng trước
                row.add(prevRow.get(j - 1) + prevRow.get(j));
            }
            
            row.add(1);                // Phần tử cuối cùng luôn là 1
            result.add(row);
        }
        
        return result;
    }
    public static void main(String[] args) {
        SolutionGenerate solution = new SolutionGenerate();

        // Test case 1: Tam giác Pascal 5 hàng
        int numRows1 = 5;
        System.out.println("Test case 1 (5 hàng): " + solution.generate(numRows1));
        // Expected: [[1], [1, 1], [1, 2, 1], [1, 3, 3, 1], [1, 4, 6, 4, 1]]

        // Test case 2: Tam giác Pascal 1 hàng
        int numRows2 = 1;
        System.out.println("Test case 2 (1 hàng): " + solution.generate(numRows2));
        // Expected: [[1]]

        // Test case 3: Tam giác Pascal 2 hàng
        int numRows3 = 2;
        System.out.println("Test case 3 (2 hàng): " + solution.generate(numRows3));
        // Expected: [[1], [1, 1]]

        // Test case 4: Tam giác Pascal 8 hàng
        int numRows4 = 8;
        System.out.println("Test case 4 (8 hàng): " + solution.generate(numRows4));
        // Expected: [[1], [1,1], [1,2,1], [1,3,3,1], [1,4,6,4,1], [1,5,10,10,5,1], [1,6,15,20,15,6,1], [1,7,21,35,35,21,7,1]]
    }
}
