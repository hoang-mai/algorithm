package solution.possible.string.count;

/**
 * LeetCode Problem 3330: Find the Original Typed String I
 * Alice is attempting to type a specific string on her computer. However, she tends to be clumsy and may press a key for too long, resulting in a character being typed multiple times.
 * Although Alice tried to focus on her typing, she is aware that she may still have done this at most once.
 * You are given a string word, which represents the final output displayed on Alice's screen.
 * Return the total number of possible original strings that Alice might have intended to type.
 * @author Mai Anh Hoàng
 * @since 01/07/2025
 */
public class SolutionPossibleStringCount {

    /**
     * Tính số lượng chuỗi có thể tạo được từ một chuỗi đã cho.
     * Độ phức tạp thời gian: O(n), trong đó n là độ dài của chuỗi.
     * Độ phức tạp không gian: O(1), vì chỉ sử dụng một vài biến tạm thời.
     * @param word Chuỗi đầu vào, có thể chứa các ký tự giống nhau liên tiếp.
     * @return Số lượng chuỗi có thể tạo được từ chuỗi đầu vào.
     */
    public int possibleStringCount(String word) {
        int result = 0;
        char preCharacter = word.charAt(0);
        for (int i = 1; i < word.length(); i++) {
            char currentCharacter = word.charAt(i);
            if (preCharacter == currentCharacter) {
                result++;
            } else {
                preCharacter = currentCharacter;
            }
        }
        return result + 1;
    }

    public static void main(String[] args) {
        SolutionPossibleStringCount solution = new SolutionPossibleStringCount();
        System.out.println(solution.possibleStringCount("aaaa"));
    }
}
