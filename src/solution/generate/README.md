# Tạo tam giác Pascal (LeetCode 118)

## Tổng quan

Cho một số nguyên `numRows`, trả về `numRows` hàng đầu tiên của tam giác Pascal.

Trong tam giác Pascal, mỗi số bằng tổng của hai số trực tiếp phía trên nó như hình dưới:

```
      1
     1 1
    1 2 1
   1 3 3 1
  1 4 6 4 1
```

## Định nghĩa hàm

```java
public List<List<Integer>> generate(int numRows);
```

## Phương pháp: Dynamic Programming (Bottom-up)

### Trực giác

Tam giác Pascal có các tính chất:

1. **Hàng đầu tiên**: Chỉ có số 1
2. **Các hàng tiếp theo**: 
   - Phần tử đầu và cuối luôn là 1
   - Các phần tử ở giữa = tổng của hai phần tử trên hàng trước
3. **Công thức**: `triangle[i][j] = triangle[i-1][j-1] + triangle[i-1][j]`

### Thuật toán

1. **Xử lý trường hợp đặc biệt**: numRows = 1 hoặc 2
2. **Khởi tạo**: Tạo 2 hàng đầu tiên [1] và [1,1]
3. **Xây dựng từng hàng**: Từ hàng thứ 3:
   - Bắt đầu với 1
   - Tính các phần tử giữa từ hàng trước
   - Kết thúc với 1
4. **Trả về**: Toàn bộ tam giác

### Các bước thực hiện:

1. **Kiểm tra điều kiện**: Xử lý numRows = 1, 2
2. **Khởi tạo result**: ArrayList với capacity = numRows
3. **Thêm 2 hàng đầu**: [1] và [1,1]
4. **Vòng lặp chính**: Với i từ 2 đến numRows-1:
   - Tạo hàng mới với size = i+1
   - Thêm 1 vào đầu
   - Tính các phần tử giữa từ hàng trước
   - Thêm 1 vào cuối
   - Thêm hàng vào result
5. **Trả về result**

## Ví dụ

### Ví dụ 1:
**Đầu vào:**
```java
int numRows = 5;
```

**Đầu ra:**
```
[[1], [1,1], [1,2,1], [1,3,3,1], [1,4,6,4,1]]
```

**Giải thích:**
```
Hàng 0: [1]
Hàng 1: [1, 1]
Hàng 2: [1, 2, 1]        (2 = 1+1)
Hàng 3: [1, 3, 3, 1]     (3 = 1+2, 3 = 2+1)
Hàng 4: [1, 4, 6, 4, 1]  (4 = 1+3, 6 = 3+3, 4 = 3+1)
```

### Ví dụ 2:
**Đầu vào:**
```java
int numRows = 1;
```

**Đầu ra:**
```
[[1]]
```

### Ví dụ 3:
**Đầu vào:**
```java
int numRows = 3;
```

**Đầu ra:**
```
[[1], [1,1], [1,2,1]]
```

## Độ phức tạp

- **Thời gian:** O(numRows²)
  - Tổng số phần tử cần tính: 1 + 2 + 3 + ... + numRows = numRows*(numRows+1)/2
  - Mỗi phần tử tính trong O(1)

- **Bộ nhớ:** O(numRows²)
  - Lưu trữ toàn bộ tam giác Pascal
  - Không tính output, space complexity là O(1)

## Cách giải chi tiết

### Tại sao xử lý riêng numRows = 1, 2?

- **Tối ưu hiệu suất**: Tránh vòng lặp không cần thiết
- **Code đơn giản**: Vòng lặp chính chỉ xử lý trường hợp >= 3 hàng
- **Dễ đọc**: Logic rõ ràng cho từng trường hợp

### Tại sao sử dụng ArrayList với capacity?

- **Tối ưu memory**: Tránh reallocate khi add phần tử
- **Hiệu suất tốt**: Biết trước số lượng phần tử

### Công thức toán học

Tam giác Pascal tương ứng với hệ số nhị thức:
```
C(n,k) = n! / (k! * (n-k)!)
```

Ví dụ hàng 4: [1, 4, 6, 4, 1]
- C(4,0) = 1
- C(4,1) = 4  
- C(4,2) = 6
- C(4,3) = 4
- C(4,4) = 1

## So sánh với các phương pháp khác

### Công thức toán học: O(numRows² * log(numRows))
```java
// Tính trực tiếp C(n,k) cho mỗi phần tử
// Phức tạp hơn và chậm hơn do tính giai thừa
```

### Recursion + Memoization: O(numRows²)
```java
// Sử dụng đệ quy với cache
// Space complexity cao hơn do call stack
```

### Current DP Approach: O(numRows²)
- **Đơn giản nhất**: Code ngắn gọn, dễ hiểu
- **Hiệu quả nhất**: Không có overhead
- **Memory friendly**: Không dùng thêm cache

## Ứng dụng thực tế

1. **Tổ hợp toán học**: Tính hệ số nhị thức
2. **Xác suất**: Phân phối nhị thức
3. **Computer Graphics**: Bézier curves
4. **Algorithm**: Dynamic programming patterns
5. **Number Theory**: Các tính chất số học

## Biến thể bài toán

### Biến thể 1: Chỉ trả về hàng thứ k
```java
// Pascal's Triangle II (LeetCode 119)
// Space complexity: O(k)
```

### Biến thể 2: Tam giác Pascal modulo m
```java
// Tránh overflow với số lớn
// result[i][j] = (prev[j-1] + prev[j]) % m
```

### Biến thể 3: Tính tổng của hàng thứ k
```java
// Kết quả: 2^k (trừ hàng 0)
```

## Tối ưu hóa

### Space Optimization (cho biến thể):
```java
// Chỉ giữ hàng trước đó thay vì toàn bộ tam giác
// Space: O(numRows) thay vì O(numRows²)
```

### Early Termination:
```java
// Nếu chỉ cần một số hàng nhất định
// Có thể dừng sớm
```

Xem file `SolutionGenerate.java` để tham khảo cài đặt chi tiết.
