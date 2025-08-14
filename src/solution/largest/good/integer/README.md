# Số có 3 chữ số giống nhau lớn nhất (LeetCode 2264)

## Tổng quan

Cho một chuỗi `num` biểu diễn một số nguyên lớn, trả về số có ba chữ số giống nhau lớn nhất (dưới dạng chuỗi) là substring của `num`, hoặc chuỗi rỗng `""` nếu không tồn tại substring như vậy.

Một substring là một chuỗi các ký tự liên tiếp trong một chuỗi.

## Định nghĩa hàm

```java
public String largestGoodInteger(String num);
```

## Phương pháp: Single Pass với Counter

### Trực giác

Để tìm số có 3 chữ số giống nhau lớn nhất:

1. **Duyệt một lần**: Scan chuỗi từ trái qua phải
2. **Đếm ký tự liên tiếp**: Theo dõi số lượng ký tự giống nhau liên tiếp
3. **Cập nhật maximum**: Khi tìm được nhóm 3 ký tự, so sánh với ký tự lớn nhất hiện tại
4. **Trả về kết quả**: Tạo chuỗi 3 ký tự từ ký tự lớn nhất

### Thuật toán

1. **Khởi tạo**: `count = 1`, `maxChar = ' '`
2. **Duyệt chuỗi**: Từ vị trí thứ 2:
   - Nếu ký tự giống ký tự trước: tăng `count`
   - Nếu khác: reset `count = 1`
   - Nếu `count == 3`: cập nhật `maxChar`
3. **Trả về**: Chuỗi 3 ký tự từ `maxChar` hoặc chuỗi rỗng

### Các bước thực hiện:

1. **Khởi tạo biến**: `count = 1`, `maxChar = ' '` (space character)
2. **Vòng lặp chính**: i từ 1 đến n-1:
   - So sánh `num[i]` với `num[i-1]`
   - Cập nhật `count` tương ứng
   - Kiểm tra `count == 3` để cập nhật `maxChar`
3. **Xử lý kết quả**: Tạo chuỗi từ `maxChar` hoặc trả về rỗng

## Ví dụ

### Ví dụ 1:
**Đầu vào:**
```java
String num = "6777133339";
```

**Đầu ra:**
```
"777"
```

**Giải thích:**
- Các nhóm 3 ký tự giống nhau: "777", "333"
- Ký tự lớn nhất: '7'
- Kết quả: "777"

### Ví dụ 2:
**Đầu vào:**
```java
String num = "2300019";
```

**Đầu ra:**
```
"000"
```

**Giải thích:**
- Chỉ có một nhóm 3 ký tự giống nhau: "000"
- Kết quả: "000"

### Ví dụ 3:
**Đầu vào:**
```java
String num = "42352338";
```

**Đầu ra:**
```
""
```

**Giải thích:**
- Không có nhóm nào có 3 ký tự giống nhau liên tiếp
- Kết quả: chuỗi rỗng

### Ví dụ 4:
**Đầu vào:**
```java
String num = "222333999";
```

**Đầu ra:**
```
"999"
```

**Giải thích:**
- Các nhóm: "222", "333", "999"
- Ký tự lớn nhất: '9'
- Kết quả: "999"

## Độ phức tạp

- **Thời gian:** O(n), với n là độ dài chuỗi num
  - Duyệt chuỗi một lần: O(n)
  - Mỗi ký tự xử lý trong O(1)

- **Bộ nhớ:** O(1)
  - Chỉ sử dụng các biến primitive: count, maxChar
  - Không phụ thuộc vào kích thước input

## Cách giải chi tiết

### Tại sao sử dụng space character ' ' làm giá trị khởi tạo?

- **ASCII value**: ' ' (32) < '0' (48), đảm bảo bất kỳ chữ số nào cũng lớn hơn
- **Kiểm tra dễ dàng**: `maxChar != ' '` để biết đã tìm thấy good integer
- **An toàn**: Không xung đột với bất kỳ chữ số nào

### Logic xử lý count

```java
if (num.charAt(i) == num.charAt(i - 1)) {
    count++;                    // Ký tự giống nhau → tăng count
    if (count > 3) continue;    // Tối ưu: bỏ qua nếu > 3
} else {
    count = 1;                  // Ký tự khác nhau → reset count
}
```

### Tại sao kiểm tra `count > 3`?

- **Tối ưu**: Không cần xử lý thêm nếu đã vượt quá 3 ký tự
- **Đúng đắn**: Vẫn cập nhật `maxChar` chính xác khi `count == 3`

## So sánh với các phương pháp khác

### Substring enumeration: O(n²)
```java
// Duyệt tất cả substring độ dài 3
for (int i = 0; i <= n-3; i++) {
    String sub = num.substring(i, i+3);
    if (sub.charAt(0) == sub.charAt(1) && 
        sub.charAt(1) == sub.charAt(2)) {
        // So sánh với max
    }
}
```

### Regular Expression: O(n)
```java
// Sử dụng regex để tìm pattern
Pattern pattern = Pattern.compile("(\\d)\\1\\1");
Matcher matcher = pattern.matcher(num);
// Tìm tất cả matches và chọn lớn nhất
```

### Current Single Pass: O(n)
- **Hiệu quả nhất**: Duyệt một lần, O(1) space
- **Đơn giản**: Logic rõ ràng, dễ hiểu
- **Optimal**: Không thể tối ưu hơn về time complexity

## Edge Cases

### Chuỗi ngắn (< 3 ký tự)
```java
String num = "12"; // Return ""
```

### Tất cả ký tự giống nhau
```java
String num = "1111"; // Return "111" 
```

### Chỉ có đúng 3 ký tự giống nhau
```java
String num = "444"; // Return "444"
```

### Có nhóm "000"
```java
String num = "1000234"; // Return "000" (không phải rỗng)
```

## Ứng dụng thực tế

1. **String pattern matching**: Tìm pattern trong text processing
2. **Data validation**: Kiểm tra format số điện thoại, mã số
3. **Game development**: Tìm combo trong puzzle games
4. **Log analysis**: Phát hiện pattern lặp lại trong logs

## Biến thể bài toán

### Biến thể 1: Tìm tất cả good integers
```java
// Trả về List<String> chứa tất cả good integers
```

### Biến thể 2: Good integer với k ký tự
```java
// Tìm nhóm k ký tự giống nhau lớn nhất
public String largestGoodInteger(String num, int k)
```

### Biến thể 3: Tìm good integer nhỏ nhất
```java
// Trả về good integer có giá trị nhỏ nhất
```

## Tối ưu hóa

### Early termination
```java
if (maxChar == '9') {
    return "999"; // Không thể tìm được lớn hơn
}
```

### StringBuilder cho output lớn
```java
// Nếu cần tạo nhiều chuỗi kết quả
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 3; i++) {
    sb.append(maxChar);
}
return sb.toString();
```

Xem file `SolutionLargestGoodInteger.java` để tham khảo cài đặt chi tiết.
