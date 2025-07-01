# Đếm số chuỗi gốc có thể có (LeetCode 3330)

## Tổng quan
Cho một chuỗi `word` là kết quả Alice gõ trên máy tính. Alice có thể vô tình giữ một phím quá lâu, khiến một ký tự bị lặp lại nhiều lần, và điều này chỉ xảy ra nhiều nhất một lần trong quá trình gõ. Hãy xác định có bao nhiêu chuỗi gốc khác nhau mà Alice có thể đã định gõ ban đầu.

## Ví dụ
**Đầu vào:**
```
word = "aabb"
```
**Đầu ra:**
```
3
```
**Giải thích:**
Các chuỗi gốc có thể là: "aabb" (không có lỗi), "abb" (ký tự 'a' bị lặp), "aab" (ký tự 'b' bị lặp).

## Cách giải
1. Duyệt qua chuỗi, đếm số lần xuất hiện các ký tự giống nhau liên tiếp.
2. Mỗi lần phát hiện một cặp ký tự giống nhau liên tiếp, tăng biến đếm lên 1.
3. Kết quả cuối cùng là số lần phát hiện + 1 (trường hợp không có lỗi).

**Độ phức tạp thời gian:** O(n), với n là độ dài chuỗi.

**Độ phức tạp bộ nhớ:** O(1), chỉ dùng một số biến tạm.

Xem file `SolutionPossibleStringCount.java` để tham khảo cài đặt.
