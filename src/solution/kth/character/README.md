# Tìm ký tự thứ K trong trò chơi chuỗi I (LeetCode 3304)

## Tổng quan

Alice và Bob đang chơi một trò chơi. Ban đầu, Alice có một chuỗi `word = "a"`.

Bạn được cho một số nguyên dương `k`.

Bây giờ Bob sẽ yêu cầu Alice thực hiện phép toán sau đây vô hạn:
- Tạo một chuỗi mới bằng cách thay đổi mỗi ký tự trong `word` thành ký tự tiếp theo trong bảng chữ cái tiếng Anh, và nối nó vào chuỗi gốc.

Ví dụ: thực hiện phép toán trên "c" tạo ra "cd" và thực hiện phép toán trên "zb" tạo ra "zbac".

Trả về giá trị của ký tự thứ k trong `word`, sau khi đã thực hiện đủ các phép toán để `word` có ít nhất k ký tự.

Lưu ý rằng ký tự 'z' có thể được thay đổi thành 'a' trong phép toán.

## Định nghĩa hàm

```java
public char kthCharacter(int k);
```

## Phương pháp: Thuật toán Logarit + Cây nhị phân

### Trực giác

Chuỗi được tạo ra theo quy luật đệ quy:
- Ban đầu: "a"
- Lần 1: "a" → "ab" (a → b)
- Lần 2: "ab" → "abbc" (a → b, b → c)
- Lần 3: "abbc" → "abbcbccd" (a → b, b → c, b → c, c → d)

Quan sát thấy rằng chuỗi có cấu trúc như một cây nhị phân hoàn chỉnh:
- Nút gốc là ký tự 'a'
- Mỗi nút có 2 con: nút trái giống nút cha, nút phải là ký tự tiếp theo trong bảng chữ cái

### Thuật toán

1. **Phân tích vị trí**: Sử dụng tính chất logarit để xác định vị trí của ký tự thứ k trong cấu trúc cây nhị phân
2. **Tính toán dịch chuyển**: Mỗi lần di chuyển từ nút cha xuống nút con phải, ta tăng giá trị ký tự lên 1
3. **Xử lý lũy thừa của 2**: Nếu k là lũy thừa của 2, ta có thể tính trực tiếp số lần dịch chuyển

### Các bước thực hiện:

1. **Khởi tạo**: `count = 0` (số lần dịch chuyển), `modForK = k` (vị trí hiện tại)
2. **Lặp**: Trong khi `modForK != 1`:
   - Nếu `modForK` là lũy thừa của 2: `count += log₂(modForK)`
   - Ngược lại: giảm `modForK` về vị trí gần nhất ở nửa trái, tăng `count`
3. **Trả về**: Ký tự `'a' + count % 26`

## Ví dụ

**Đầu vào:**
```java
int k = 5;
```

**Đầu ra:**
```
'c'
```

**Giải thích:**
- Chuỗi được tạo ra: "a" → "ab" → "abbc" → "abbcbccd"
- Vị trí 5 trong chuỗi "abbcbccd" là ký tự 'c'

**Quá trình tính toán:**
1. `k = 5`, `count = 0`, `modForK = 5`
2. `5` không phải lũy thừa của 2, `log₂(5) = 2`, `modForK = 5 - 2² = 1`, `count = 1`
3. `modForK = 1`, thoát khỏi vòng lặp
4. Trả về `'a' + 1 = 'b'`

## Độ phức tạp

- **Thời gian:** O(log k), vì thuật toán sử dụng logarit để giảm k về 1
- **Bộ nhớ:** O(1), chỉ sử dụng các biến primitive

## Cách giải

1. Sử dụng tính chất cây nhị phân của chuỗi được tạo ra
2. Áp dụng thuật toán logarit để tính toán vị trí
3. Đếm số lần dịch chuyển ký tự từ 'a'
4. Trả về ký tự tương ứng với số lần dịch chuyển

## Hàm hỗ trợ

- `floorLogBase2(x)`: Tính logarit cơ số 2 của x và làm tròn xuống
- `isLogBase2(x)`: Kiểm tra x có phải là lũy thừa của 2 hay không

Xem file `SolutionKthCharacter.java` để tham khảo cài đặt chi tiết.
