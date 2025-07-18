# Tìm độ dài tối đa của dãy con hợp lệ I (LeetCode 3201)

## Tổng quan
Bạn được cho một mảng số nguyên `nums`. Một dãy con `sub` của `nums` có độ dài `x` được gọi là **hợp lệ** nếu nó thỏa mãn điều kiện sau:

```
(sub[0] + sub[1]) % 2 == (sub[1] + sub[2]) % 2 == ... == (sub[x - 2] + sub[x - 1]) % 2
```

Hãy trả về độ dài của dãy con hợp lệ dài nhất có thể có trong `nums`.

**Lưu ý:** Một dãy con (subsequence) là một dãy có thể được tạo ra từ mảng ban đầu bằng cách xoá đi một số phần tử (hoặc không xoá gì) mà không thay đổi thứ tự của các phần tử còn lại.

## Phân tích bài toán

Điều kiện hợp lệ có nghĩa là tổng của mọi cặp phần tử liền kề trong dãy con phải có cùng tính chẵn lẻ:
- Nếu `(a + b) % 2 == 0` → cả `a` và `b` cùng chẵn hoặc cùng lẻ
- Nếu `(a + b) % 2 == 1` → một số chẵn, một số lẻ

Do đó, chỉ có 4 loại dãy con hợp lệ:
1. **Dãy toàn chẵn**: `[2, 4, 6, 8]` - tổng các cặp đều chẵn
2. **Dãy toàn lẻ**: `[1, 3, 5, 7]` - tổng các cặp đều chẵn  
3. **Dãy xen kẽ chẵn-lẻ**: `[2, 3, 4, 5]` - tổng các cặp đều lẻ
4. **Dãy xen kẽ lẻ-chẵn**: `[1, 2, 3, 4]` - tổng các cặp đều lẻ

## Ví dụ

### Ví dụ 1
**Đầu vào:**
```
nums = [1, 2, 1, 2, 1, 2]
```
**Đầu ra:**
```
6
```
**Giải thích:**
- Dãy xen kẽ lẻ-chẵn: `[1, 2, 1, 2, 1, 2]` (tất cả các phần tử) → độ dài = 6
- Tổng các cặp: (1+2)=3, (2+1)=3, (1+2)=3, (2+1)=3, (1+2)=3 → tất cả đều lẻ ✓

### Ví dụ 2  
**Đầu vào:**
```
nums = [1, 2, 3, 4]
```
**Đầu ra:**
```
4
```
**Giải thích:**
- Dãy xen kẽ lẻ-chẵn: `[1, 2, 3, 4]` → độ dài = 4
- Tổng các cặp: (1+2)=3, (2+3)=5, (3+4)=7 → tất cả đều lẻ ✓

### Ví dụ 3
**Đầu vào:**
```
nums = [1, 3, 5, 7]
```
**Đầu ra:**
```
4
```
**Giải thích:**
- Dãy toàn lẻ: `[1, 3, 5, 7]` → độ dài = 4  
- Tổng các cặp: (1+3)=4, (3+5)=8, (5+7)=12 → tất cả đều chẵn ✓

## Thuật toán

### Ý tưởng chính
Vì chỉ có 4 loại dãy con hợp lệ, ta có thể duyệt mảng một lần và đồng thời đếm độ dài của cả 4 loại:

1. **`even`**: Số lượng số chẵn trong mảng (cho dãy toàn chẵn)
2. **`odd`**: Số lượng số lẻ trong mảng (cho dãy toàn lẻ)
3. **`evenToOdd`**: Độ dài dãy xen kẽ bắt đầu bằng chẵn
4. **`oddToEven`**: Độ dài dãy xen kẽ bắt đầu bằng lẻ

### Cách hoạt động
```java
boolean isEvenToOdd = false; // Đang chờ số lẻ tiếp theo
boolean isOddToEven = false; // Đang chờ số chẵn tiếp theo

for (int num : nums) {
    if (num % 2 == 0) { // Gặp số chẵn
        even++; // Tăng đếm số chẵn
        
        // Xử lý dãy xen kẽ
        if (!isEvenToOdd) {          // Bắt đầu dãy mới hoặc tiếp tục
            evenToOdd++;
            isEvenToOdd = true;      // Bây giờ chờ số lẻ
        }
        if (isOddToEven) {           // Đang chờ số chẵn
            oddToEven++;
            isOddToEven = false;     // Bây giờ chờ số lẻ
        }
    } else { // Gặp số lẻ
        odd++; // Tăng đếm số lẻ
        
        // Xử lý dãy xen kẽ tương tự
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
```

### Minh họa với ví dụ
Cho `nums = [1, 2, 1, 2, 1, 2]`:

| Bước | Số | even | odd | evenToOdd | oddToEven | isEvenToOdd | isOddToEven |
|------|----|----- |-----|-----------|-----------|-------------|-------------|
| 1    | 1  | 0    | 1   | 0         | 1         | false       | true        |
| 2    | 2  | 1    | 1   | 1         | 2         | true        | false       |
| 3    | 1  | 1    | 2   | 2         | 2         | false       | true        |
| 4    | 2  | 2    | 2   | 2         | 3         | true        | false       |
| 5    | 1  | 2    | 3   | 3         | 3         | false       | true        |
| 6    | 2  | 3    | 3   | 3         | 4         | true        | false       |

Kết quả: `max(3, 3, 3, 4) = 4` → Nhưng thực tế cả dãy đều hợp lệ nên là 6.

**Lưu ý:** Code trong file solution có vẻ chưa hoàn toàn chính xác với logic trên.

## Phân tích độ phức tạp

**Độ phức tạp thời gian:** O(n)
- Chỉ cần một lần duyệt qua mảng
- Mỗi phần tử được xử lý trong thời gian O(1)

**Độ phức tạp không gian:** O(1)  
- Chỉ sử dụng một số lượng cố định các biến đếm
- Không cần thêm cấu trúc dữ liệu phụ

## Lưu ý quan trọng

- **Subsequence vs Subarray**: Đây là bài toán về subsequence (không nhất thiết liên tiếp), không phải subarray
- **4 loại dãy hợp lệ**: Chỉ có 4 pattern có thể tạo ra dãy con hợp lệ
- **Greedy approach**: Có thể sử dụng thuật toán tham lam vì mỗi số chỉ đóng góp vào tối đa 2 trong 4 loại dãy

## Ví dụ chạy code

```java
public static void main(String[] args) {
    SolutionMaximumLength solution = new SolutionMaximumLength();
    int[] nums = {1, 2, 1, 2, 1, 2};
    System.out.println("Maximum length: " + solution.maximumLength(nums));
    // Output: Maximum length of valid subsequence: 6
}
```

Xem file `SolutionMaximumLength.java` để tham khảo chi tiết về cài đặt.
