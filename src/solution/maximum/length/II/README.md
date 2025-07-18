# Tìm độ dài tối đa của dãy con hợp lệ II (LeetCode 3202)

## Tổng quan
Bạn được cho một mảng số nguyên `nums` và một số nguyên `k`. Một dãy con `sub` của `nums` có độ dài `x` được gọi là **hợp lệ** nếu nó thỏa mãn điều kiện sau:

```
(sub[0] + sub[1]) % k == (sub[1] + sub[2]) % k == ... == (sub[x - 2] + sub[x - 1]) % k
```

Hãy trả về độ dài của dãy con hợp lệ dài nhất có thể có trong `nums`.

**Lưu ý:** Một dãy con (subsequence) là một dãy có thể được tạo ra từ mảng ban đầu bằng cách xoá đi một số phần tử (hoặc không xoá gì) mà không thay đổi thứ tự của các phần tử còn lại.

## Phân tích bài toán

Điều kiện hợp lệ có nghĩa là tổng của mọi cặp phần tử liền kề trong dãy con phải có cùng số dư khi chia cho `k`. 

Ví dụ với `k = 3`:
- Nếu `(a + b) % 3 == 1`, thì `(b + c) % 3` cũng phải bằng `1`
- Điều này tạo ra một pattern cố định cho các số dư


## Ví dụ

### Ví dụ 1
**Đầu vào:**
```
nums = [1, 2, 3, 4, 5], k = 2
```
**Đầu ra:**
```
5
```
**Giải thích:**
- Mảng mod 2: `[1, 0, 1, 0, 1]`
- Dãy con `[1, 2, 3, 4, 5]`: tổng các cặp `(1+2)%2=1`, `(2+3)%2=1`, `(3+4)%2=1`, `(4+5)%2=1`
- Tất cả đều có cùng số dư 1 → độ dài = 5

### Ví dụ 2
**Đầu vào:**
```
nums = [1, 4, 2, 3, 1, 4], k = 3
```
**Đầu ra:**
```
4
```
**Giải thích:**
- Mảng mod 3: `[1, 1, 2, 0, 1, 1]`
- Một dãy con hợp lệ có thể là `[1, 4, 2, 1]` với mod `[1, 1, 2, 1]`
- Tổng các cặp: `(1+4)%3=2`, `(4+2)%3=0`, `(2+1)%3=0` → không hợp lệ
- Cần tìm dãy con khác...

### Ví dụ 3
**Đầu vào:**
```
nums = [2, 4, 6, 8], k = 2
```
**Đầu ra:**
```
4
```
**Giải thích:**
- Mảng mod 2: `[0, 0, 0, 0]` (toàn số chẵn)
- Dãy con `[2, 4, 6, 8]`: tổng các cặp đều là `0 % 2 = 0`
- Độ dài = 4

## Thuật toán

### Ý tưởng chính
Sử dụng **Dynamic Programming** với ma trận 2D:
- `dp[i][j]` = độ dài tối đa của dãy con kết thúc bằng phần tử có `mod = j` và phần tử trước đó có `mod = i`

### Cách hoạt động
```java
int[][] dp = new int[k][k]; // dp[prev_mod][curr_mod]
int maxLength = 0;

for (int num : nums) {
    int mod = num % k;
    for (int i = 0; i < k; i++) {
        // Nếu phần tử trước có mod = i, phần tử hiện tại có mod = mod
        // thì pattern sum % k = (i + mod) % k
        dp[i][mod] = dp[mod][i] + 1;
        maxLength = Math.max(maxLength, dp[i][mod]);
    }
}
```

### Giải thích logic
1. **Khởi tạo**: Ma trận `dp[k][k]` với tất cả giá trị = 0
2. **Duyệt mảng**: Với mỗi phần tử `num`:
   - Tính `mod = num % k`
   - Với mọi `i` từ 0 đến k-1:
     - Cập nhật `dp[i][mod]` = độ dài dãy con có pattern `(i + mod) % k`
     - Giá trị mới = `dp[mod][i] + 1` (thêm phần tử hiện tại)
3. **Kết quả**: Giá trị lớn nhất trong ma trận `dp`

### Minh họa với ví dụ
Cho `nums = [1, 2, 3, 4, 5], k = 2`:

| Bước | num | mod | dp[0][0] | dp[0][1] | dp[1][0] | dp[1][1] | max |
|------|-----|-----|----------|----------|----------|----------|-----|
| 1    | 1   | 1   | 0        | 1        | 1        | 0        | 1   |
| 2    | 2   | 0   | 2        | 1        | 1        | 2        | 2   |
| 3    | 3   | 1   | 2        | 3        | 3        | 2        | 3   |
| 4    | 4   | 0   | 4        | 3        | 3        | 4        | 4   |
| 5    | 5   | 1   | 4        | 5        | 5        | 4        | 5   |

## Phân tích độ phức tạp

**Độ phức tạp thời gian:** O(n × k)
- Duyệt qua n phần tử
- Với mỗi phần tử, cập nhật k cặp trong ma trận dp

**Độ phức tạp không gian:** O(k²)
- Ma trận dp có kích thước k × k

## Lưu ý quan trọng

- **Tổng quát hóa của bài I**: Bài này mở rộng từ k=2 (chẵn/lẻ) lên k bất kỳ
- **Pattern cố định**: Mỗi dãy con hợp lệ có một pattern `(sum % k)` cố định
- **DP 2D**: Cần theo dõi cả mod hiện tại và mod trước đó

## Ví dụ chạy code

```java
public static void main(String[] args) {
    SolutionMaximumLengthII solution = new SolutionMaximumLengthII();
    int[] nums = {1, 2, 3, 4, 5};
    int k = 2;
    System.out.println("Maximum length: " + solution.maximumLengthII(nums, k));
    // Output: Maximum length of valid subsequence: 5
}
```

Xem file `SolutionMaximumLengthII.java` để tham khảo chi tiết về cài đặt.
