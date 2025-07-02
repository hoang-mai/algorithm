package solution.possible.string.count.II;

import java.util.ArrayList;
import java.util.List;

class Solution {
    private static final int MOD= 1000000007;
    public int possibleStringCount(String word, int k) {

        int sum = 1;
        List<Integer> frequently = new ArrayList<>();
        int count = 1;
        for (int i = 0; i < word.length() - 1; i++) {
            if (word.charAt(i) == word.charAt(i + 1)) {
                count++;
            } else {
                sum= (sum * count) % MOD;
                frequently.add(count);
                count = 1;
            }
        }
        sum= (sum * count) % MOD;
        int[][] result = new int[frequently.size()][k];
        for (int i = 0; i < frequently.size(); i++) {

        }
        return sum ;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String word = "aaabbb";
        int k = 3;
        System.out.println(solution.possibleStringCount(word, k));
    }
}