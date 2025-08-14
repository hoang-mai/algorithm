package solution.largest.good.integer;

/**
 * LeetCode Problem 2264: Largest 3-Same-Digit Number in String
 * Given a string num representing a large integer, return the largest-valued three-digit number 
 * (as a string) that is a substring of num, or an empty string "" if no such substring exists.
 * A substring is a contiguous sequence of characters in a string.
 * @author Mai Anh Hoàng
 * @since 14/08/2025
 */
public class SolutionLargestGoodInteger {
    /**
     * Tìm số có 3 chữ số giống nhau lớn nhất trong chuỗi.
     * Duyệt chuỗi để tìm các nhóm 3 ký tự liên tiếp giống nhau và chọn nhóm có giá trị lớn nhất.
     * Độ phức tạp thời gian: O(n), với n là độ dài của chuỗi num.
     * Độ phức tạp không gian: O(1), chỉ sử dụng các biến primitive.
     * 
     * @param num Chuỗi số đầu vào.
     * @return Chuỗi 3 chữ số giống nhau lớn nhất, hoặc chuỗi rỗng nếu không tồn tại.
     */
    public String largestGoodInteger(String num) {
        int count = 1;                          // Đếm số ký tự liên tiếp giống nhau
        char maxChar = ' ';                     // Ký tự lớn nhất tạo thành "good integer"
        
        for (int i = 1; i < num.length(); i++) {
            if (num.charAt(i) == num.charAt(i - 1)) {
                count++;                        // Tăng count nếu ký tự giống nhau
                if (count > 3) {
                    continue;                   // Bỏ qua nếu đã vượt quá 3 ký tự
                }
            } else {
                count = 1;                      // Reset count nếu ký tự khác nhau
            }
            
            // Khi tìm được nhóm 3 ký tự giống nhau
            if (count == 3) {
                maxChar = (char) Math.max(maxChar, num.charAt(i));
            }
        }
        
        // Trả về kết quả
        if (maxChar != ' ') {
            return String.valueOf(maxChar).repeat(3);
        }
        return "";
    }

    public static void main(String[] args) {
        SolutionLargestGoodInteger solution = new SolutionLargestGoodInteger();
        
        // Test case 1: Có nhiều nhóm 3 chữ số giống nhau
        System.out.println("Test case 1: '" + solution.largestGoodInteger("6777133339") + "'"); // Expected: "777"
        
        // Test case 2: Có nhóm "000"
        System.out.println("Test case 2: '" + solution.largestGoodInteger("2300019") + "'"); // Expected: "000"
        
        // Test case 3: Không có nhóm 3 chữ số giống nhau
        System.out.println("Test case 3: '" + solution.largestGoodInteger("42352338") + "'"); // Expected: ""
        
        // Test case 4: Có nhiều nhóm khác nhau
        System.out.println("Test case 4: '" + solution.largestGoodInteger("222333999") + "'"); // Expected: "999"
        
        // Test case 5: Chỉ có đúng 3 ký tự giống nhau
        System.out.println("Test case 5: '" + solution.largestGoodInteger("444") + "'"); // Expected: "444"
    }
}
