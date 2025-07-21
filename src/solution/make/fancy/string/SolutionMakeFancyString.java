package solution.make.fancy.string;

/**
 * LeetCode Problem 1957: Delete Characters to Make Fancy String
 * A fancy string is a string where no three consecutive characters are equal.
 * Given a string s, delete the minimum possible number of characters from s to make it fancy.
 * Return the final string after the deletion (it is guaranteed that the answer is unique).
 * @author Mai Anh Hoàng
 * @since 21/07/2025
 */
public class SolutionMakeFancyString {
    /**
     * Tạo chuỗi "fancy" bằng cách xóa ký tự để không có 3 ký tự liên tiếp giống nhau.
     * Một chuỗi "fancy" là chuỗi mà không có ba ký tự liên tiếp nào giống nhau.
     * 
     * Thuật toán:
     * - Duyệt qua chuỗi và đếm số ký tự liên tiếp giống nhau
     * - Chỉ giữ lại tối đa 2 ký tự liên tiếp giống nhau
     * - Bỏ qua các ký tự thứ 3 trở đi trong cùng một nhóm
     * 
     * Độ phức tạp thời gian: O(n), trong đó n là độ dài chuỗi.
     * Độ phức tạp không gian: O(n), cho StringBuilder kết quả.
     * 
     * @param s Chuỗi đầu vào
     * @return Chuỗi "fancy" sau khi xóa ký tự
     */
    public String makeFancyString(String s) {
        if (s.length() <= 2) return s;
        
        StringBuilder result = new StringBuilder();
        result.append(s.charAt(0)); // Luôn thêm ký tự đầu tiên
        
        int count = 1; // Đếm ký tự liên tiếp giống nhau
        
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                count = 1; // Reset đếm cho ký tự mới
            }
            
            // Chỉ thêm nếu chưa có 2 ký tự liên tiếp giống nhau
            if (count <= 2) {
                result.append(s.charAt(i));
            }
        }
        
        return result.toString();
    }

    public static void main(String[] args) {
        SolutionMakeFancyString sol = new SolutionMakeFancyString();
        System.out.println(sol.makeFancyString("leeetcode"));
    }
}
