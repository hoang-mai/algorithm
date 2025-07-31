# OR bitwise của các subarray (LeetCode 898)

## Tổng quan

Cho một mảng số nguyên `arr`, trả về số lượng các giá trị OR bitwise khác nhau của tất cả các subarray không rỗng của `arr`.

OR bitwise của một subarray là OR bitwise của từng số nguyên trong subarray đó.

Một subarray là một chuỗi các phần tử liên tiếp và không rỗng trong một mảng.

## Định nghĩa hàm

```java
public int subarrayBitwiseORs(int[] arr);
```

## Phương pháp: Dynamic Programming + HashSet

### Trực giác

Để tính tất cả giá trị OR khác nhau của các subarray:

1. **Tính OR tăng dần**: Với mỗi vị trí, tính OR của các subarray kết thúc tại vị trí đó
2. **Tận dụng kết quả trước**: OR của subarray mới = OR của subarray cũ | phần tử mới
3. **Loại bỏ trùng lặp**: Sử dụng HashSet để lưu các giá trị OR khác nhau

### Thuật toán

1. **Khởi tạo**: `ans` (tất cả OR khác nhau), `prev` (OR kết thúc tại vị trí trước)
2. **Duyệt từng phần tử**: Với mỗi `num`:
   - Tạo `cur` để lưu OR kết thúc tại vị trí hiện tại
   - Thêm `num` (subarray chỉ có `num`)
   - Kết hợp `num` với tất cả OR từ `prev`
   - Cập nhật `ans` và `prev`
3. **Trả về**: Số lượng giá trị OR khác nhau

### Các bước thực hiện:

1. **Khởi tạo**: `HashSet<Integer> ans`, `HashSet<Integer> prev`
2. **Duyệt mảng**: Với mỗi `num`:
   - `cur.add(num)`: Subarray chỉ có `num`
   - Với mỗi `x` trong `prev`: `cur.add(x | num)`
   - `ans.addAll(cur)`: Thêm tất cả OR mới
   - `prev = cur`: Chuẩn bị cho vòng tiếp theo
3. **Trả về**: `ans.size()`

## Ví dụ

### Ví dụ 1:
**Đầu vào:**
```java
int[] arr = {1, 1, 2};
```

**Đầu ra:**
```
3
```

**Giải thích:**
- Các subarray: [1], [1], [2], [1,1], [1,2], [1,1,2]
- OR tương ứng: 1, 1, 2, 1, 3, 3
- Giá trị OR khác nhau: {1, 2, 3} → 3 giá trị

**Quá trình thực hiện:**
1. i=0, num=1: cur={1}, ans={1}, prev={1}
2. i=1, num=1: cur={1}, ans={1}, prev={1}
3. i=2, num=2: cur={2, 3}, ans={1,2,3}, prev={2,3}

### Ví dụ 2:
**Đầu vào:**
```java
int[] arr = {1, 2, 4};
```

**Đầu ra:**
```
6
```

**Giải thích:**
- Các subarray: [1], [2], [4], [1,2], [2,4], [1,2,4]
- OR tương ứng: 1, 2, 4, 3, 6, 7
- Giá trị OR khác nhau: {1, 2, 3, 4, 6, 7} → 6 giá trị

### Ví dụ 3:
**Đầu vào:**
```java
int[] arr = {0};
```

**Đầu ra:**
```
1
```

**Giải thích:**
- Chỉ có subarray [0] với OR = 0
- Giá trị OR khác nhau: {0} → 1 giá trị

## Độ phức tạp

- **Thời gian:** O(n * log(max_value))
  - n là độ dài mảng
  - log(max_value) là số bit tối đa (≤ 32 cho int)
  - Mỗi vị trí có tối đa 32 giá trị OR khác nhau

- **Bộ nhớ:** O(n * log(max_value))
  - Trong trường hợp xấu nhất, ans và prev có thể chứa O(n * log(max_value)) phần tử

## Cách giải chi tiết

### Tại sao độ phức tạp là O(n * log(max_value))?

Quan sát quan trọng: Với mỗi vị trí i, số lượng giá trị OR khác nhau kết thúc tại i là **tối đa log(max_value)**.

**Lý do:**
- OR chỉ có thể làm tăng số bit 1, không bao giờ giảm
- Mỗi lần OR với phần tử mới, có thể thêm tối đa 1 bit mới
- Với 32 bit, có tối đa 32 giá trị OR khác nhau tại mỗi vị trí

### Tại sao sử dụng HashSet?

1. **Loại bỏ trùng lặp tự động**: Không cần kiểm tra phần tử đã tồn tại
2. **Tra cứu nhanh**: O(1) để thêm và kiểm tra
3. **Đơn giản**: Code ngắn gọn và dễ hiểu

### Tối ưu hóa

- Sử dụng `prev` thay vì tính lại từ đầu cho mỗi vị trí
- HashSet tự động loại bỏ trùng lặp
- Chỉ lưu OR kết thúc tại vị trí hiện tại, không cần lưu tất cả subarray

## So sánh với phương pháp khác

### Brute Force: O(n³)
```java
// Duyệt tất cả subarray và tính OR cho từng subarray
for (int i = 0; i < n; i++) {
    for (int j = i; j < n; j++) {
        int or = 0;
        for (int k = i; k <= j; k++) {
            or |= arr[k];
        }
        set.add(or);
    }
}
```

### Optimization 1: O(n²)
```java
// Tính OR tăng dần cho mỗi subarray bắt đầu tại i
for (int i = 0; i < n; i++) {
    int or = 0;
    for (int j = i; j < n; j++) {
        or |= arr[j];
        set.add(or);
    }
}
```

### Current Approach: O(n * log(max_value))
- Tận dụng tính chất OR để giảm độ phức tạp
- Hiệu quả nhất cho bài toán này

## Ứng dụng

1. **Phân tích bit pattern**: Tìm các pattern bit khác nhau trong dữ liệu
2. **Tối ưu query**: Precompute các giá trị OR cho range query
3. **Compression**: Phân tích entropy của dữ liệu bit

## Biến thể

- Đếm giá trị AND khác nhau của các subarray
- Đếm giá trị XOR khác nhau của các subarray
- Tìm subarray có OR bằng target
- Tìm subarray có OR lớn nhất/nhỏ nhất

Xem file `SolutionSubarrayBitwiseORs.java` để tham khảo cài đặt chi tiết.
