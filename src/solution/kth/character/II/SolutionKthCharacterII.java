package solution.kth.character.II;

/**
 * LeetCode Problem 3307: Find the K-th Character in String Game II
 * Alice and Bob are playing a game. Initially, Alice has a string word = "a".
 * You are given a positive integer k and an integer array operations.
 * Now Bob will ask Alice to perform the following operations forever:
 * - If operations[i] == 0, generate a new string by changing each character in word to its next character in the English alphabet, and append it to the original word.
 * - If operations[i] == 1, generate a new string by changing each character in word to its next character in the English alphabet, and append it to the original word.
 * Return the value of the kth character in word, after enough operations have been done for word to have at least k characters.
 * Note that the character 'z' can be changed to 'a' in the operation.
 * @author Mai Anh Hoàng
 * @since 04/07/2025
 */
public class SolutionKthCharacterII {
    /**
     * Tìm ký tự thứ k trong chuỗi được tạo ra từ việc mở rộng chuỗi ban đầu theo quy tắc với mảng operations.
     * Thuật toán sử dụng tính chất toán học của cấu trúc dữ liệu cây nhị phân để tính toán vị trí.
     * Độ phức tạp thời gian: O(log k), vì thuật toán sử dụng logarit để tính toán.
     * Độ phức tạp không gian: O(1), vì không sử dụng cấu trúc dữ liệu phụ trợ nào có kích thước phụ thuộc vào đầu vào.
     * 
     * @param k Số nguyên dương đại diện cho vị trí ký tự cần tìm trong chuỗi (1-indexed).
     * @param operations Mảng các phép toán để xác định cách dịch chuyển ký tự (0: không dịch chuyển, 1: dịch chuyển).
     * @return Ký tự thứ k trong chuỗi được tạo ra.
     */
    public char kthCharacter(long k, int[] operations) {
        long count = 0;          // Đếm số lần cần dịch chuyển ký tự từ 'a'
        long modForK = k;        // Vị trí hiện tại trong chuỗi

        // Lặp cho đến khi tìm được vị trí cơ sở (vị trí 1)
        while (modForK != 1) {
            long powerOfTwo = floorLogBase2(modForK);
            // Nếu modForK là lũy thừa của 2, ta có thể tính trực tiếp
            if (isLogBase2(modForK)) {
                // Tính tổng các operations từ 0 đến powerOfTwo - 1
                for (int i = 0; i < powerOfTwo; i++) {
                    count += operations[i];
                }
                break;
            }
            // Giảm modForK về phần tử tương ứng trong nửa trái của cây
            modForK = (long) (modForK - Math.pow(2, powerOfTwo));
            // Thêm operations[powerOfTwo] vì ta đã dịch chuyển 1 bước
            count += operations[Math.toIntExact(powerOfTwo)];
        }
        return (char) ('a' + count % 26);  // Trả về ký tự tương ứng với count
    }

    /**
     * Tính logarit cơ số 2 của x và làm tròn xuống.
     *
     * @param x Số nguyên cần tính logarit.
     * @return Giá trị logarit cơ số 2 của x (làm tròn xuống).
     */
    private long floorLogBase2(long x) {
        return (long) Math.floor(Math.log(x) / Math.log(2));
    }

    /**
     * Kiểm tra xem x có phải là lũy thừa của 2 hay không.
     *
     * @param x Số nguyên cần kiểm tra.
     * @return true nếu x là lũy thừa của 2, false trong trường hợp ngược lại.
     */
    private boolean isLogBase2(long x) {
        double v = Math.log(x) / Math.log(2);
        return v == Math.floor(v);
    }

    public static void main(String[] args) {
        SolutionKthCharacterII solution = new SolutionKthCharacterII();
        long k = 3; // Vị trí cần tìm ký tự
        int[] operations = {1, 0}; // Các phép toán dịch chuyển ký tự
        char result = solution.kthCharacter(k, operations);
        System.out.println("Ký tự tại vị trí " + k + " là: " + result); // In ra kết quả
    }
}
