# Độ dài nhỏ nhất của các subarray với OR tối đa (LeetCode 2411)

## Tổng quan

Cho mảng số nguyên `nums`, với mỗi vị trí `i`, hãy tìm độ dài nhỏ nhất của subarray bắt đầu tại `i` có OR bằng OR của toàn bộ subarray từ `i` đến cuối.

## Định nghĩa hàm

```java
public int[] smallestSubarrays(int[] nums);
```

## Phương pháp: Duyệt ngược + Theo dõi vị trí bit

### Trực giác

Để đạt OR tối đa cho subarray bắt đầu tại i, cần bao phủ tất cả các bit xuất hiện từ i về sau.
- Với mỗi bit, lưu vị trí xuất hiện cuối cùng của nó
- Với mỗi i, tìm vị trí xa nhất cần bao phủ để đạt OR tối đa
- Độ dài nhỏ nhất = vị trí xa nhất - i + 1

### Thuật toán

1. Khởi tạo mảng `pos[32]` lưu vị trí xuất hiện cuối cùng của mỗi bit
2. Duyệt ngược từ cuối về đầu
3. Với mỗi vị trí i:
   - Kiểm tra từng bit từ 0 đến 31
   - Nếu bit k chưa xuất hiện ở nums[i], cần bao phủ vị trí cuối cùng của bit đó
   - Cập nhật vị trí xa nhất cần bao phủ
4. Độ dài nhỏ nhất cho vị trí i = vị trí xa nhất - i + 1

## Ví dụ

### Ví dụ 1:
**Đầu vào:**
```java
int[] nums = {1, 0, 2, 1, 3};
```
**Đầu ra:**
```
[3, 3, 2, 2, 1]
```
**Giải thích:**
- i=0: OR từ 0 đến cuối là 3, cần bao phủ đến index 2 (bit 1 và 2), độ dài = 3
- i=1: OR từ 1 đến cuối là 3, cần bao phủ đến index 3, độ dài = 3
- i=2: OR từ 2 đến cuối là 3, cần bao phủ đến index 3, độ dài = 2
- i=3: OR từ 3 đến cuối là 3, chỉ cần index 4, độ dài = 2
- i=4: OR từ 4 đến cuối là 3, chỉ cần index 4, độ dài = 1

### Ví dụ 2:
**Đầu vào:**
```java
int[] nums = {0, 0, 0};
```
**Đầu ra:**
```
[1, 1, 1]
```
**Giải thích:**
- Mỗi vị trí chỉ cần chính nó, vì OR luôn là 0

### Ví dụ 3:
**Đầu vào:**
```java
int[] nums = {1, 2, 4, 8};
```
**Đầu ra:**
```
[4, 3, 2, 1]
```
**Giải thích:**
- i=0: OR từ 0 đến cuối là 15, cần bao phủ đến index 3, độ dài = 4
- i=1: OR từ 1 đến cuối là 14, cần bao phủ đến index 3, độ dài = 3
- i=2: OR từ 2 đến cuối là 12, cần bao phủ đến index 3, độ dài = 2
- i=3: OR từ 3 đến cuối là 8, chỉ cần index 3, độ dài = 1

## Độ phức tạp

- **Thời gian:** O(n * 32), với n là độ dài mảng nums
- **Bộ nhớ:** O(32 + n), mảng vị trí bit và mảng kết quả

## Cách giải chi tiết

1. Duyệt ngược từ cuối về đầu
2. Với mỗi vị trí, kiểm tra từng bit
3. Cập nhật vị trí xa nhất cần bao phủ
4. Tính độ dài nhỏ nhất cho mỗi vị trí

## Lưu ý implementation

- Sử dụng mảng pos[32] để lưu vị trí xuất hiện cuối cùng của mỗi bit
- Duyệt ngược để cập nhật vị trí bit hiệu quả
- Độ dài nhỏ nhất luôn >= 1

## Biến thể

- Tìm subarray có tổng OR lớn nhất
- Tìm subarray có tổng AND lớn nhất
- Tìm subarray có tổng XOR lớn nhất

Xem file `SolutionSmallestSubarrays.java` để tham khảo cài đặt chi tiết.
