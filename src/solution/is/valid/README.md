# Kiểm tra từ hợp lệ (Valid Word)

## Tổng quan
Cho một chuỗi `word`, hãy kiểm tra xem chuỗi đó có phải là một "từ hợp lệ" hay không. Một từ hợp lệ phải thỏa mãn tất cả các điều kiện sau:
1. Có ít nhất 3 ký tự
2. Chỉ chứa các chữ cái (cả hoa và thường) và chữ số
3. Có ít nhất một nguyên âm (a, e, i, o, u)
4. Có ít nhất một phụ âm

## Ví dụ
**Đầu vào:**
```
word = "b3"
```
**Đầu ra:**
```
false
```
**Giải thích:**
Chuỗi chỉ có 2 ký tự, không đủ điều kiện tối thiểu 3 ký tự.

**Đầu vào:**
```
word = "a3$e"
```
**Đầu ra:**
```
false
```
**Giải thích:**
Chuỗi chứa ký tự đặc biệt '$' không được phép.

**Đầu vào:**
```
word = "aab"
```
**Đầu ra:**
```
true
```
**Giải thích:**
- Có 3 ký tự (đủ điều kiện)
- Chỉ chứa chữ cái
- Có nguyên âm 'a'
- Có phụ âm 'b'

## Cách giải
1. Kiểm tra độ dài chuỗi phải ≥ 3
2. Duyệt qua từng ký tự trong chuỗi:
   - Nếu là chữ số: bỏ qua
   - Nếu là nguyên âm: đánh dấu đã có nguyên âm
   - Nếu là chữ cái khác (phụ âm): đánh dấu đã có phụ âm
   - Nếu là ký tự đặc biệt: trả về false
3. Cuối cùng kiểm tra đã có cả nguyên âm và phụ âm

**Độ phức tạp thời gian:** O(n), với n là độ dài chuỗi.

**Độ phức tạp bộ nhớ:** O(1), chỉ dùng một số biến boolean.

Xem file `SolutionIsValid.java` để tham khảo cài đặt.
