package solution.possible.string.count.II;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode Problem 3333: Find the Original Typed String II
 * Alice is attempting to type a specific string on her computer. However, she tends to be clumsy and may press a key for too long, resulting in a character being typed multiple times.
 * Although Alice tried to focus on her typing, she is aware that she may still have done this at most once.
 * You are given a string word, which represents the final output displayed on Alice's screen.
 * Return the total number of possible original strings that Alice might have intended to type, with the condition that there are at least k elements in the result.
 * @author Mai Anh Hoàng
 * @since 01/07/2025
 */
class SolutionPossibleStringCountII {
    private static final int MOD = 1000000007;

    /**
     * Tính số chuỗi có thể tạo được từ chuỗi word với điều kiện là có ít nhất k phần tử
     * Độ phức tạp thời gian: O(n * k), trong đó n là độ dài của chuỗi word và k là số lượng phần tử cần có.
     * Độ phức tạp không gian: O(n + k), vì sử dụng list frequently để lưu tần số xuất hiện của các nhóm và sử dụng mảng f và g để lưu trữ số lượng chuỗi có thể tạo được.
     *
     * @param word Chuỗi đầu vào, có thể chứa các ký tự giống nhau liên tiếp.
     * @param k Số lượng phần tử cần có trong chuỗi kết quả.
     * @return Số lượng chuỗi có thể tạo được từ chuỗi đầu vào với điều kiện có ít nhất k phần tử.
     */
    public int possibleStringCountII(String word, int k) {

        long sum = 1;
        List<Integer> frequently = new ArrayList<>();
        int count = 1;
        for (int i = 0; i < word.length() - 1; i++) {
            if (word.charAt(i) == word.charAt(i + 1)) {
                count++;
            } else {
                sum = (sum * count) % MOD;
                frequently.add(count);
                count = 1;
            }
        }
        frequently.add(count);
        sum = (sum * count) % MOD;
        if (frequently.size() >= k) return (int) sum;
        int[] f = new int[k]; // f là mảng chọn i nhóm và có j phần tử, nhóm thứ i có j' phần tử được chọn
        int[] g = new int[k]; // g là mảng để tính i-1 nhóm, có j-j' phần tử
        f[0] = 1; // f[0] là chọn 0 nhóm thì chỉ có mảng rỗng
        Arrays.fill(g, 1);
        for (int i = 0; i < frequently.size(); i++) {
            int[] f_new = new int[k];
            for (int j = 1; j < k; j++) {
                f_new[j] = g[j - 1];
                if (j - frequently.get(i) - 1 >= 0) {
                    f_new[j] = (f_new[j] - g[j - frequently.get(i) - 1] + MOD) % MOD;
                }
            }
            int[] g_new = new int[k];
            g_new[0] = f_new[0];
            for (int j = 1; j < k; j++) {
                g_new[j] = (g_new[j - 1] + f_new[j]) % MOD;
            }
            f = f_new;
            g = g_new;
        }
        return (int) (sum - g[k - 1] + MOD) % MOD;
    }


    public static void main(String[] args) {
        SolutionPossibleStringCountII solution = new SolutionPossibleStringCountII();
        String word = "ggggggggaaaaallsssssaaaaaaaaaiiqqqqqqqqqqbbbbbbbvvfffffjjjjeeeeeefffmmiiiix";
        int k = 34;
        System.out.println(solution.possibleStringCountII(word, k));
    }
}