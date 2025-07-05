# Tìm số may mắn trong mảng (LeetCode 1394)

## Tổng quan

Cho một mảng các số nguyên `arr`, một số may mắn là một số nguyên có tần số xuất hiện trong mảng bằng chính giá trị của nó.

Trả về số may mắn lớn nhất trong mảng. Nếu không có số may mắn nào thì trả về -1.

## Định nghĩa hàm

```java
public int findLucky(int[] arr);
```

## Phương pháp: Hash Map để đếm tần số

### Trực giác

Để tìm số may mắn, chúng ta cần:
1. Đếm tần số xuất hiện của mỗi phần tử trong mảng
2. Kiểm tra xem có phần tử nào có giá trị bằng tần số xuất hiện của nó không
3. Trả về số may mắn lớn nhất

Ví dụ:
- Mảng [2, 2, 3, 4]: Số 2 xuất hiện 2 lần → 2 là số may mắn
- Mảng [1, 2, 2, 3, 3, 3]: Số 1 xuất hiện 1 lần, số 3 xuất hiện 3 lần → 3 là số may mắn lớn nhất

### Thuật toán

1. **Đếm tần số**: Sử dụng HashMap để đếm tần số xuất hiện của mỗi phần tử
2. **Tìm số may mắn**: Duyệt qua HashMap, kiểm tra phần tử nào có giá trị bằng tần số
3. **Trả về kết quả**: Trả về số may mắn lớn nhất, hoặc -1 nếu không có

### Các bước thực hiện:

1. **Khởi tạo**: `result = -1`, `HashMap<Integer, Integer> map`
2. **Đếm tần số**: Duyệt mảng và đếm tần số xuất hiện của mỗi phần tử
3. **Tìm số may mắn**: Duyệt HashMap, kiểm tra `key == value`
4. **Cập nhật kết quả**: Nếu tìm thấy số may mắn, cập nhật `result = max(result, value)`
5. **Trả về**: Trả về `result`

## Ví dụ

### Ví dụ 1:
**Đầu vào:**
```java
int[] arr = {2, 2, 3, 4};
```

**Đầu ra:**
```
2
```

**Giải thích:**
- Tần số: {2: 2, 3: 1, 4: 1}
- Số may mắn: 2 (có giá trị bằng tần số xuất hiện)
- Kết quả: 2

### Ví dụ 2:
**Đầu vào:**
```java
int[] arr = {1, 2, 2, 3, 3, 3};
```

**Đầu ra:**
```
3
```

**Giải thích:**
- Tần số: {1: 1, 2: 2, 3: 3}
- Số may mắn: 1, 2, 3 (tất cả đều có giá trị bằng tần số)
- Kết quả: 3 (số may mắn lớn nhất)

### Ví dụ 3:
**Đầu vào:**
```java
int[] arr = {2, 2, 2, 3, 3};
```

**Đầu ra:**
```
-1
```

**Giải thích:**
- Tần số: {2: 3, 3: 2}
- Không có số nào có giá trị bằng tần số xuất hiện
- Kết quả: -1

## Độ phức tạp

- **Thời gian:** O(n), trong đó n là độ dài của mảng arr
  - Duyệt mảng để đếm tần số: O(n)
  - Duyệt HashMap để tìm số may mắn: O(k), với k là số phần tử khác nhau (k ≤ n)
  - Tổng: O(n + k) = O(n)

- **Bộ nhớ:** O(n), trong trường hợp xấu nhất khi tất cả phần tử đều khác nhau

## Cách giải

1. **Sử dụng HashMap**: Đếm tần số xuất hiện của mỗi phần tử
2. **Kiểm tra điều kiện**: Tìm phần tử có giá trị bằng tần số xuất hiện
3. **Tìm max**: Trả về số may mắn lớn nhất

## Lưu ý implementation

- Sử dụng `Objects.equals()` để so sánh an toàn giữa `Integer` objects
- Khởi tạo HashMap với capacity 500 để tối ưu hiệu suất
- Sử dụng `Math.max()` để tìm số may mắn lớn nhất

## Biến thể

Bài toán này có thể mở rộng thành:
- Tìm tất cả các số may mắn trong mảng
- Tìm số may mắn nhỏ nhất
- Đếm tổng số lượng số may mắn

Xem file `SolutionFindLucky.java` để tham khảo cài đặt chi tiết.
