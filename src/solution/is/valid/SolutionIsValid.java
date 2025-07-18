package solution.is.valid;

/**
 * LeetCode Problem 3136: Valid Word
 * A valid word is a string that satisfies the following conditions:
 * 1. It contains a minimum of 3 characters.
 * 2. It consists of only digits and letters (both uppercase and lowercase).
 * 3. It includes at least one vowel.
 * 4. It includes at least one consonant.
 * @author Mai Anh Hoàng
 * @since 18/07/2025
 */
public class SolutionIsValid {

    /**
     * Kiểm tra xem một chuỗi có phải là "valid word" hay không.
     * Một chuỗi hợp lệ phải thỏa mãn:
     * 1. Có ít nhất 3 ký tự
     * 2. Chỉ chứa chữ cái và chữ số
     * 3. Có ít nhất một nguyên âm
     * 4. Có ít nhất một phụ âm
     * 
     * Độ phức tạp thời gian: O(n), trong đó n là độ dài của chuỗi.
     * Độ phức tạp không gian: O(1), vì chỉ sử dụng một vài biến boolean.
     * 
     * @param word Chuỗi đầu vào cần kiểm tra
     * @return true nếu chuỗi hợp lệ, false nếu không hợp lệ
     */
    public boolean isValid(String word) {
        if (word.length() < 3) return false;

        boolean isVowel = false;

        boolean isConsonant = false;

        for (char w : word.toCharArray()) {
            if (Character.isDigit(w)) {
            } else if (isVowel(w)) {
                isVowel = true;
            } else if (Character.isLetter(w)) {
                isConsonant = true;
            } else {
                return false;
            }
        }
        return isVowel && isConsonant;
    }

    /**
     * Kiểm tra xem một ký tự có phải là nguyên âm hay không.
     * @param c Ký tự cần kiểm tra
     * @return true nếu là nguyên âm (cả hoa và thường), false nếu không phải
     */
    private boolean isVowel(char c) {
        return switch (c) {
            case 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' -> true;
            default -> false;
        };
    }

    public static void main(String[] args) {
        SolutionIsValid solutionIsValid = new SolutionIsValid();
        System.out.println(solutionIsValid.isValid("aab"));
    }

}
