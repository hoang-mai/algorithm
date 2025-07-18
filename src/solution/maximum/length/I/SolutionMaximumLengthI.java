package solution.maximum.length.I;

/**
 * LeetCode Problem 3201: Find the Maximum Length of Valid Subsequence I
 * You are given an integer array nums.
 * A subsequence sub of nums with length x is called valid if it satisfies:
 * (sub[0] + sub[1]) % 2 == (sub[1] + sub[2]) % 2 == ... == (sub[x - 2] + sub[x
 * - 1]) % 2.
 * Return the length of the longest valid subsequence of nums.
 * A subsequence is an array that can be derived from another array by deleting
 * some or no elements without changing the order of the remaining elements.
 * 
 * @author Mai Anh Hoàng
 * @since 18/07/2025
 */
public class SolutionMaximumLengthI {

    /**
     * Tìm độ dài tối đa của dãy con bán xen kẽ.
     * Dãy con bán xen kẽ có thể là:
     * 1. Dãy toàn số chẵn
     * 2. Dãy toàn số lẻ
     * 3. Dãy bắt đầu bằng số chẵn và xen kẽ chẵn-lẻ
     * 4. Dãy bắt đầu bằng số lẻ và xen kẽ lẻ-chẵn
     * 
     * Độ phức tạp thời gian: O(n), trong đó n là độ dài của mảng.
     * Độ phức tạp không gian: O(1), vì chỉ sử dụng một số biến đếm.
     * 
     * @param nums Mảng các số nguyên đầu vào
     * @return Độ dài tối đa của dãy con bán xen kẽ
     */
    public int maximumLengthI(int[] nums) {
        int even = 0;
        int odd = 0;
        int evenToOdd = 0;
        boolean isEvenToOdd = false;
        int oddToEven = 0;
        boolean isOddToEven = false;

        for (int num : nums) {
            if (num % 2 == 0) {
                even++;
                if (!isEvenToOdd) {
                    evenToOdd++;
                    isEvenToOdd = true;
                }
                if (isOddToEven) {
                    oddToEven++;
                    isOddToEven = false;
                }
            } else {
                odd++;
                if (!isOddToEven) {
                    oddToEven++;
                    isOddToEven = true;
                }
                if (isEvenToOdd) {
                    evenToOdd++;
                    isEvenToOdd = false;
                }
            }
        }
        int max = Math.max(even, odd);
        max = Math.max(max, evenToOdd);
        max = Math.max(max, oddToEven);
        return max;
    }

    public static void main(String[] args) {
        SolutionMaximumLengthI solutionMaximumLength = new SolutionMaximumLengthI();
        int[] nums = { 1, 2, 1, 2, 1, 2 };
        System.out.println("Maximum length of valid subsequence: " + solutionMaximumLength.maximumLengthI(nums));
    }

}
