# Tổng tối đa của các phần tử khác nhau

## Tổng quan

Cho một mảng các số nguyên `nums`, tìm tổng tối đa của các phần tử khác nhau.

**Quy tắc:**
- Nếu tất cả phần tử đều âm, trả về phần tử lớn nhất (ít âm nhất)
- Ngược lại, trả về tổng của tất cả các phần tử không âm khác nhau

## Định nghĩa hàm

```java
public int maxSum(int[] nums);
```

## Phương pháp: HashSet để loại bỏ trùng lặp

### Trực giác

Để tối đa hóa tổng các phần tử khác nhau:

1. **Phân loại phần tử**: Chia thành hai nhóm - âm và không âm
2. **Loại bỏ trùng lặp**: Sử dụng HashSet cho phần tử không âm
3. **Xử lý trường hợp đặc biệt**: Khi tất cả phần tử đều âm

### Thuật toán

1. **Khởi tạo**: `negativeMax` và `HashSet` cho phần tử không âm
2. **Phân loại**: Duyệt mảng và phân chia phần tử theo dấu
3. **Tính kết quả**: 
   - Nếu không có phần tử không âm: trả về `negativeMax`
   - Ngược lại: trả về tổng các phần tử không âm khác nhau

### Các bước thực hiện:

1. **Khởi tạo**: `negativeMax = Integer.MIN_VALUE`, `HashSet<Integer> set`
2. **Duyệt mảng**: Với mỗi phần tử `num`:
   - Nếu `num < 0`: cập nhật `negativeMax = max(negativeMax, num)`
   - Ngược lại: `set.add(num)` (tự động loại bỏ trùng lặp)
3. **Tính kết quả**:
   - Nếu `set.isEmpty()`: trả về `negativeMax`
   - Ngược lại: tính tổng tất cả phần tử trong `set`

## Ví dụ

### Ví dụ 1:
**Đầu vào:**
```java
int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
```

**Đầu ra:**
```
7
```

**Giải thích:**
- Phần tử âm: {-2, -3, -1, -5}, max = -1
- Phần tử không âm khác nhau: {1, 4, 2}
- Tổng: 1 + 4 + 2 = 7

### Ví dụ 2:
**Đầu vào:**
```java
int[] nums = {-5, -3, -8, -1, -2};
```

**Đầu ra:**
```
-1
```

**Giải thích:**
- Tất cả phần tử đều âm
- Phần tử lớn nhất (ít âm nhất): -1

### Ví dụ 3:
**Đầu vào:**
```java
int[] nums = {1, 2, 3, 2, 1, 4};
```

**Đầu ra:**
```
10
```

**Giải thích:**
- Không có phần tử âm
- Phần tử không âm khác nhau: {1, 2, 3, 4}
- Tổng: 1 + 2 + 3 + 4 = 10

### Ví dụ 4:
**Đầu vào:**
```java
int[] nums = {0, -1, 2, -3, 2, 0};
```

**Đầu ra:**
```
2
```

**Giải thích:**
- Phần tử âm: {-1, -3}, max = -1
- Phần tử không âm khác nhau: {0, 2}
- Tổng: 0 + 2 = 2

## Độ phức tạp

- **Thời gian:** O(n), trong đó n là độ dài của mảng nums
  - Duyệt mảng một lần: O(n)
  - Tính tổng HashSet: O(k), với k ≤ n
  - Tổng: O(n)

- **Bộ nhớ:** O(k), trong đó k là số phần tử không âm khác nhau
  - HashSet lưu trữ tối đa k phần tử không âm khác nhau
  - Các biến primitive: O(1)

## Cách giải chi tiết

### Tại sao sử dụng HashSet?

1. **Loại bỏ trùng lặp tự động**: HashSet không cho phép phần tử trùng lặp
2. **Hiệu quả**: Thêm phần tử và kiểm tra tồn tại trong O(1) trung bình
3. **Đơn giản**: Không cần logic phức tạp để xử lý trùng lặp

### Tại sao chỉ xét phần tử không âm?

- **Tối đa hóa tổng**: Phần tử âm sẽ làm giảm tổng
- **Trường hợp đặc biệt**: Khi tất cả đều âm, chọn ít âm nhất

### Xử lý số 0

- Số 0 được coi là không âm
- Đóng góp 0 vào tổng nhưng vẫn là phần tử hợp lệ
- Quan trọng khi tất cả số khác đều âm

## So sánh với các phương pháp khác

### Sắp xếp + Loại bỏ trùng lặp: O(n log n)
```java
// Sắp xếp mảng, loại bỏ trùng lặp, tính tổng phần tử dương
Arrays.sort(nums);
// Logic phức tạp hơn
```

### Sử dụng Stream API: O(n)
```java
// Có thể sử dụng Stream nhưng phức tạp hơn với logic âm/dương
return Arrays.stream(nums)
    .filter(x -> x >= 0)
    .distinct()
    .sum();
// Cần xử lý riêng trường hợp tất cả âm
```

### HashSet (Current): O(n)
- Đơn giản, hiệu quả
- Logic rõ ràng
- Xử lý tốt tất cả trường hợp

## Biến thể bài toán

### Biến thể 1: Cho phép k phần tử âm
```java
// Chọn k phần tử âm ít âm nhất cùng với tất cả phần tử dương
```

### Biến thể 2: Tối đa hóa tích thay vì tổng
```java
// Cần xử lý dấu âm và số 0 khác nhau
```

### Biến thể 3: Có trọng số cho từng phần tử
```java
// Mỗi phần tử có trọng số riêng
```

## Ứng dụng thực tế

1. **Tối ưu danh mục đầu tư**: Chọn tài sản có lợi nhuận dương
2. **Game scoring**: Tính điểm từ các hành động khác nhau
3. **Resource allocation**: Phân bổ tài nguyên để tối đa hóa lợi ích

## Lưu ý implementation

- Khởi tạo `negativeMax = Integer.MIN_VALUE` để xử lý trường hợp không có số âm
- Sử dụng `Math.max()` để tìm số âm lớn nhất
- HashSet tự động xử lý trùng lặp mà không cần logic bổ sung

Xem file `SolutionMaxSum.java` để tham khảo cài đặt chi tiết.
