package solution.kth.character;

/**
 * LeetCode Problem 3304: Find the K-th Character in String Game I
 * Alice and Bob are playing a game. Initially, Alice has a string word = "a".
 * You are given a positive integer k.
 * Now Bob will ask Alice to perform the following operation forever:
 * Generate a new string by changing each character in word to its next character in the English alphabet, and append it to the original word.
 * For example, performing the operation on "c" generates "cd" and performing the operation on "zb" generates "zbac".
 * Return the value of the kth character in word, after enough operations have been done for word to have at least k characters.
 * Note that the character 'z' can be changed to 'a' in the operation.
 * @author Mai Anh Hoàng
 * @since 04/07/2025
 */
public class SolutionKthCharacter {

    /**
     * Tìm ký tự thứ k trong chuỗi được tạo ra từ việc mở rộng chuỗi ban đầu theo quy tắc.
     * Thuật toán sử dụng tính chất toán học của cấu trúc dữ liệu cây nhị phân để tính toán vị trí.
     * Độ phức tạp thời gian: O(log k), vì thuật toán sử dụng logarit để tính toán.
     * Độ phức tạp không gian: O(1), vì không sử dụng cấu trúc dữ liệu phụ trợ nào có kích thước phụ thuộc vào đầu vào.
     * 
     * @param k Số nguyên dương đại diện cho vị trí ký tự cần tìm trong chuỗi (1-indexed).
     * @return Ký tự thứ k trong chuỗi được tạo ra.
     */
    public char kthCharacter(int k) {
        int count = 0;          // Đếm số lần cần dịch chuyển ký tự từ 'a'
        int modForK = k;        // Vị trí hiện tại trong chuỗi

        // Lặp cho đến khi tìm được vị trí cơ sở (vị trí 1)
        while (modForK != 1) {
            // Nếu modForK là lũy thừa của 2, ta có thể tính trực tiếp
            if (isLogBase2(modForK)) {
                count += floorLogBase2(modForK) % 26;
                break;
            }
            // Giảm modForK về phần tử gần nhất trong nửa trái của cây
            modForK = (int) (modForK - Math.pow(2, floorLogBase2(modForK)));
            count++;            // Tăng count vì ta đã dịch chuyển 1 bước
        }
        return (char) ('a' + count % 26);  // Trả về ký tự tương ứng với count
    }

    /**
     * Tính logarit cơ số 2 của x và làm tròn xuống.
     * 
     * @param x Số nguyên cần tính logarit.
     * @return Giá trị logarit cơ số 2 của x (làm tròn xuống).
     */
    private int floorLogBase2(int x) {
        return (int) Math.floor(Math.log(x) / Math.log(2));
    }

    /**
     * Kiểm tra xem x có phải là lũy thừa của 2 hay không.
     * 
     * @param x Số nguyên cần kiểm tra.
     * @return true nếu x là lũy thừa của 2, false trong trường hợp ngược lại.
     */
    private boolean isLogBase2(int x) {
        return Math.log(x) / Math.log(2) == Math.floor(Math.log(x) / Math.log(2));
    }

    public static void main(String[] args) {
        SolutionKthCharacter solution = new SolutionKthCharacter();
        System.out.println(solution.kthCharacter(5));
    }
}
