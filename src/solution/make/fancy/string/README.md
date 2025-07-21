# Tạo chuỗi "Fancy" (LeetCode 1957)

## Tổng quan
Một chuỗi "fancy" là chuỗi mà không có ba ký tự liên tiếp nào giống nhau. Cho một chuỗi `s`, hãy xóa số ký tự ít nhất có thể từ `s` để tạo thành chuỗi "fancy".

Trả về chuỗi cuối cùng sau khi xóa (đảm bảo rằng câu trả lời là duy nhất).

## Ví dụ

### Ví dụ 1
**Đầu vào:**
```
s = "leeetcode"
```
**Đầu ra:**
```
"leetcode"
```
**Giải thích:**
- Chuỗi ban đầu: `"leeetcode"`
- Có 3 ký tự 'e' liên tiếp: `"leeetcode"`
- Xóa 1 ký tự 'e': `"leetcode"` → chuỗi fancy

### Ví dụ 2
**Đầu vào:**
```
s = "aaabaaaa"
```
**Đầu ra:**
```
"aabaa"
```
**Giải thích:**
- Chuỗi ban đầu: `"aaabaaaa"`
- Nhóm đầu `"aaa"` → giữ `"aa"`
- Ký tự `"b"` → giữ `"b"`
- Nhóm cuối `"aaaa"` → giữ `"aa"`
- Kết quả: `"aabaa"`

### Ví dụ 3
**Đầu vào:**
```
s = "aab"
```
**Đầu ra:**
```
"aab"
```
**Giải thích:**
- Không có 3 ký tự liên tiếp nào giống nhau
- Giữ nguyên chuỗi

## Thuật toán

### Ý tưởng chính
Duyệt qua chuỗi và đếm số ký tự liên tiếp giống nhau. Chỉ giữ lại tối đa 2 ký tự liên tiếp giống nhau trong mỗi nhóm.

### Cách hoạt động
```java
StringBuilder result = new StringBuilder();
result.append(s.charAt(0)); // Luôn thêm ký tự đầu tiên

int count = 1; // Đếm ký tự liên tiếp giống nhau

for (int i = 1; i < s.length(); i++) {
    if (s.charAt(i) == s.charAt(i - 1)) {
        count++; // Tăng đếm nếu giống ký tự trước
    } else {
        count = 1; // Reset đếm cho ký tự mới
    }
    
    // Chỉ thêm nếu chưa có 2 ký tự liên tiếp giống nhau
    if (count <= 2) {
        result.append(s.charAt(i));
    }
}
```

### Các bước thực hiện
1. **Khởi tạo**: Tạo StringBuilder và thêm ký tự đầu tiên
2. **Duyệt chuỗi**: Từ ký tự thứ 2:
   - So sánh với ký tự trước đó
   - Nếu giống: tăng biến đếm
   - Nếu khác: reset biến đếm = 1
   - Chỉ thêm ký tự nếu `count <= 2`
3. **Kết quả**: Trả về chuỗi từ StringBuilder

### Minh họa với ví dụ
Cho `s = "aaabaaaa"`:

| Bước | i | char | prev | count | Thêm? | result     |
|------|---|------|------|-------|-------|------------|
| 0    | - | a    | -    | 1     | ✓     | "a"        |
| 1    | 1 | a    | a    | 2     | ✓     | "aa"       |
| 2    | 2 | a    | a    | 3     | ✗     | "aa"       |
| 3    | 3 | b    | a    | 1     | ✓     | "aab"      |
| 4    | 4 | a    | b    | 1     | ✓     | "aaba"     |
| 5    | 5 | a    | a    | 2     | ✓     | "aabaa"    |
| 6    | 6 | a    | a    | 3     | ✗     | "aabaa"    |
| 7    | 7 | a    | a    | 4     | ✗     | "aabaa"    |

Kết quả cuối cùng: `"aabaa"`

## Phân tích độ phức tạp

**Độ phức tạp thời gian:** O(n)
- Duyệt qua chuỗi một lần
- Mỗi ký tự được xử lý trong thời gian O(1)

**Độ phức tạp không gian:** O(n)
- StringBuilder để lưu kết quả
- Trong trường hợp xấu nhất, không xóa ký tự nào

## Trường hợp đặc biệt

- **Chuỗi rỗng hoặc có 1 ký tự**: Trả về nguyên chuỗi
- **Chuỗi có 2 ký tự**: Luôn là chuỗi fancy
- **Chuỗi đã là fancy**: Không thay đổi gì

## Tối ưu hóa

Algorithm này đã tối ưu với:
- Chỉ một lần duyệt
- Xử lý tại chỗ (streaming)
- Không cần lưu trữ thêm thông tin

## Ví dụ chạy code

```java
public static void main(String[] args) {
    SolutionMakeFancyString sol = new SolutionMakeFancyString();
    
    System.out.println(sol.makeFancyString("leeetcode"));  // "leetcode"
    System.out.println(sol.makeFancyString("aaabaaaa"));   // "aabaa"
    System.out.println(sol.makeFancyString("aab"));        // "aab"
}
```

Xem file `SolutionMakeFancyString.java` để tham khảo chi tiết về cài đặt.
