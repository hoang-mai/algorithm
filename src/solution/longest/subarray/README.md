# Mảng con dài nhất có giá trị AND lớn nhất (LeetCode 2419)

## Tổng quan
Cho một mảng số nguyên `nums` có kích thước `n`. Hãy tìm độ dài của mảng con liên tiếp dài nhất có giá trị bitwise AND lớn nhất có thể.

Nói cách khác, gọi `k` là giá trị lớn nhất của phép AND bitwise của bất kỳ mảng con nào của `nums`. Chỉ những mảng con có giá trị AND bằng `k` mới được xem xét.

## Quan sát quan trọng
**Giá trị AND lớn nhất có thể của bất kỳ mảng con nào chính là giá trị lớn nhất trong mảng.**

**Lý do:** Phép AND bitwise chỉ có thể làm giảm hoặc giữ nguyên giá trị, không bao giờ làm tăng giá trị:
- `a & b ≤ min(a, b)` với mọi `a, b`
- Do đó, AND của nhiều số chỉ có thể ≤ số nhỏ nhất trong nhóm

**Kết luận:** Ta chỉ cần tìm mảng con liên tiếp dài nhất gồm toàn bộ các phần tử có giá trị bằng giá trị lớn nhất trong mảng.

## Ví dụ

### Ví dụ 1
**Đầu vào:**
```
nums = [1, 2, 3, 3, 2, 2]
```
**Đầu ra:**
```
2
```
**Giải thích:**
- Giá trị lớn nhất trong mảng: `3`
- Mảng con liên tiếp dài nhất chứa toàn số `3`: `[3, 3]` → độ dài = 2

### Ví dụ 2
**Đầu vào:**
```
nums = [1, 2, 3, 4]
```
**Đầu ra:**
```
1
```
**Giải thích:**
- Giá trị lớn nhất trong mảng: `4`
- Chỉ có một phần tử `4` → độ dài = 1

### Ví dụ 3
**Đầu vào:**
```
nums = [5, 5, 5, 5]
```
**Đầu ra:**
```
4
```
**Giải thích:**
- Tất cả phần tử đều bằng `5` (giá trị lớn nhất)
- Toàn bộ mảng là mảng con hợp lệ → độ dài = 4

## Thuật toán

### Ý tưởng chính
1. Tìm giá trị lớn nhất trong mảng
2. Duyệt mảng và tìm dãy con liên tiếp dài nhất chứa toàn giá trị lớn nhất

### Cách hoạt động
```java
int length = 0;     // Độ dài dãy hiện tại
int maxLength = 0;  // Độ dài dãy dài nhất
int max = 0;        // Giá trị lớn nhất

for (int i = 0; i < nums.length; i++) {
    if (nums[i] > max) {
        // Tìm thấy giá trị lớn hơn
        max = nums[i];
        length = 1;
        maxLength = 1;
    } else if (nums[i] == max) {
        // Tiếp tục dãy hiện tại
        length++;
    } else {
        // Kết thúc dãy hiện tại
        length = 0;
    }
    maxLength = Math.max(maxLength, length);
}
```

### Các bước thực hiện
1. **Khởi tạo**: `length = 0`, `maxLength = 0`, `max = 0`
2. **Duyệt mảng**: Với mỗi phần tử `nums[i]`:
   - Nếu `nums[i] > max`: Cập nhật `max`, reset độ dài = 1
   - Nếu `nums[i] == max`: Tăng độ dài dãy hiện tại
   - Nếu `nums[i] < max`: Reset độ dài = 0
   - Cập nhật `maxLength`
3. **Kết quả**: Trả về `maxLength`

### Minh họa với ví dụ
Cho `nums = [1, 2, 2, 3, 3, 3, 2, 1]`:

| Bước | nums[i] | max | length | maxLength | Ghi chú                    |
|------|---------|-----|--------|-----------|----------------------------|
| 0    | 1       | 1   | 1      | 1         | Giá trị lớn nhất mới       |
| 1    | 2       | 2   | 1      | 1         | Giá trị lớn nhất mới       |
| 2    | 2       | 2   | 2      | 2         | Tiếp tục dãy               |
| 3    | 3       | 3   | 1      | 2         | Giá trị lớn nhất mới       |
| 4    | 3       | 3   | 2      | 2         | Tiếp tục dãy               |
| 5    | 3       | 3   | 3      | 3         | Tiếp tục dãy               |
| 6    | 2       | 3   | 0      | 3         | Kết thúc dãy (2 < 3)       |
| 7    | 1       | 3   | 0      | 3         | Kết thúc dãy (1 < 3)       |

Kết quả: `maxLength = 3`

## Phân tích độ phức tạp

**Độ phức tạp thời gian:** O(n)
- Chỉ cần một lần duyệt qua mảng
- Mỗi phần tử được xử lý trong thời gian O(1)

**Độ phức tạp không gian:** O(1)
- Chỉ sử dụng một số lượng cố định các biến
- Không cần thêm cấu trúc dữ liệu phụ

## Tại sao thuật toán này đúng?

1. **Phép AND không tăng giá trị**: `a & b ≤ min(a, b)`
2. **AND của mảng con**: Chỉ có thể ≤ phần tử nhỏ nhất trong mảng con
3. **AND lớn nhất**: Đạt được khi tất cả phần tử trong mảng con đều bằng giá trị lớn nhất
4. **Mảng con liên tiếp**: Phải tìm dãy liên tiếp dài nhất, không phải subsequence

## Ví dụ chạy code

```java
public static void main(String[] args) {
    SolutionLongestSubarray solution = new SolutionLongestSubarray();
    int[] nums = {1, 2, 2, 3, 3, 3, 2, 1};
    System.out.println("Longest subarray length: " + solution.longestSubarray(nums));
    // Output: Longest subarray length: 3
}
```

Xem file `SolutionLongestSubarray.java` để tham khảo chi tiết về cài đặt.
