package solution.count.max.or.subsets;

/**
 * LeetCode Problem 2044: Count Number of Maximum Bitwise-OR Subsets
 * Given an integer array nums, find the maximum possible bitwise OR of a subset of nums and return the number of different non-empty subsets with the maximum bitwise OR.
 * An array a is a subset of an array b if a can be obtained from b by deleting some (possibly zero) elements of b. Two subsets are considered different if the indices of the elements chosen are different.
 * The bitwise OR of an array a is equal to a[0] OR a[1] OR ... OR a[a.length - 1] (0-indexed).
 *
 * @author Mai Anh Hoàng
 * @since 28/07/2025
 */
public class SolutionCountMaxOrSubsets {
    /**
     * Đếm số tập con có giá trị OR lớn nhất.
     * Độ phức tạp thời gian: O(2^n), với n là số phần tử của mảng.
     * Độ phức tạp không gian: O(n), cho stack đệ quy.
     *
     * @param nums Mảng số nguyên đầu vào
     * @return Số tập con có giá trị OR lớn nhất
     */
    public int countMaxOrSubsets(int[] nums) {
        int maxOr = 0;
        for (int num : nums) {
            maxOr |= num;
        }
        return dfs(nums, 0, 0, maxOr);
    }

    /**
     * Đệ quy duyệt tất cả các tập con, đếm số tập con có giá trị OR bằng maxOr.
     * @param nums Mảng số nguyên
     * @param start Vị trí bắt đầu xét
     * @param sumOr Giá trị OR hiện tại
     * @param maxOr Giá trị OR lớn nhất toàn mảng
     * @return Số tập con hợp lệ
     */
    private int dfs(int[] nums, int start, int sumOr, int maxOr) {
        if (start == nums.length) {
            return 0;
        }
        int before = sumOr;
        int count = 0;
        for (int i = 1; i >= 0; i--) {
            if (i == 1) {
                sumOr |= nums[start];
                if (sumOr == maxOr) {
                    count++;
                }
            }
            count += dfs(nums, start + 1, sumOr, maxOr);
            if (i == 1) {
                sumOr = before;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        SolutionCountMaxOrSubsets sol = new SolutionCountMaxOrSubsets();
        int[] nums = {3, 1};
        System.out.println(sol.countMaxOrSubsets(nums)); // Output: 2
    }
}
