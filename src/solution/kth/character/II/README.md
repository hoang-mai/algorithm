# Tìm ký tự thứ K trong trò chơi chuỗi II (LeetCode 3305)

## Tổng quan

Alice và Bob đang chơi một trò chơi. Ban đầu, Alice có một chuỗi `word = "a"`.

Bạn được cho một số nguyên dương `k` và một mảng số nguyên `operations`.

Bây giờ Bob sẽ yêu cầu Alice thực hiện các phép toán sau đây vô hạn:
- Nếu `operations[i] == 0`: Tạo một chuỗi mới bằng cách sao chép từng ký tự trong `word` và nối nó vào chuỗi gốc.
- Nếu `operations[i] == 1`: Tạo một chuỗi mới bằng cách thay đổi mỗi ký tự trong `word` thành ký tự tiếp theo trong bảng chữ cái tiếng Anh, và nối nó vào chuỗi gốc.

Trả về giá trị của ký tự thứ k trong `word`, sau khi đã thực hiện đủ các phép toán để `word` có ít nhất k ký tự.

Lưu ý rằng ký tự 'z' có thể được thay đổi thành 'a' trong phép toán.

## Định nghĩa hàm

```java
public char kthCharacter(long k, int[] operations);
```

## Phương pháp: Thuật toán Logarit + Cây nhị phân với Operations

### Trực giác

Chuỗi được tạo ra theo quy luật đệ quy với mảng operations:
- Ban đầu: "a"
- Nếu operations[0] == 0: "a" → "aa" (sao chép)
- Nếu operations[0] == 1: "a" → "ab" (a → b)
- Tiếp tục với operations[1], operations[2]...

Quan sát thấy rằng chuỗi có cấu trúc như một cây nhị phân hoàn chỉnh:
- Nút gốc là ký tự 'a'
- Mỗi nút có 2 con: nút trái là bản sao hoặc dịch chuyển của nút cha tùy theo operations

### Thuật toán

1. **Phân tích vị trí**: Sử dụng tính chất logarit để xác định vị trí của ký tự thứ k trong cấu trúc cây nhị phân
2. **Tính toán dịch chuyển**: Sử dụng mảng operations để xác định số lần dịch chuyển ký tự
3. **Xử lý lũy thừa của 2**: Nếu k là lũy thừa của 2, ta có thể tính trực tiếp bằng cách cộng tất cả operations từ 0 đến log₂(k)-1

### Các bước thực hiện:

1. **Khởi tạo**: `count = 0` (số lần dịch chuyển), `modForK = k` (vị trí hiện tại)
2. **Lặp**: Trong khi `modForK != 1`:
   - Tính `powerOfTwo = log₂(modForK)`
   - Nếu `modForK` là lũy thừa của 2: `count += ∑operations[i]` với i từ 0 đến powerOfTwo-1
   - Ngược lại: giảm `modForK` về vị trí tương ứng phía bên trái, thêm `operations[powerOfTwo]` vào `count`
3. **Trả về**: Ký tự `'a' + count % 26`

## Ví dụ

**Đầu vào:**
```java
long k = 5;
int[] operations = {1, 0, 1};
```

**Đầu ra:**
```
'c'
```

**Giải thích:**
- Ban đầu: "a"
- operations[0] = 1: "a" → "ab" (a → b)
- operations[1] = 0: "ab" → "abab" (sao chép)
- operations[2] = 1: "abab" → "ababbcbc" (a → b, b → c)
- Vị trí 5 trong chuỗi "ababbcbc" là ký tự 'b'

**Quá trình tính toán:**
1. `k = 5`, `count = 0`, `modForK = 5`
2. `powerOfTwo = log₂(5) = 2`, `5` không phải lũy thừa của 2
3. `modForK = 5 - 2² = 1`, `count += operations[2] = 1`
4. `modForK = 1`, thoát khỏi vòng lặp
5. Trả về `'a' + 1 = 'b'`

## Điểm khác biệt so với phiên bản I

1. **Tham số bổ sung**: Thêm mảng `operations` để điều khiển cách dịch chuyển ký tự
2. **Kiểu dữ liệu**: Sử dụng `long` thay vì `int` cho k để xử lý giá trị lớn hơn
3. **Logic dịch chuyển**: Không phải lúc nào cũng dịch chuyển ký tự, phụ thuộc vào giá trị trong mảng operations

## Độ phức tạp

- **Thời gian:** O(log k), vì thuật toán sử dụng logarit để giảm k về 1
- **Bộ nhớ:** O(1), chỉ sử dụng các biến primitive (không tính mảng operations đầu vào)

## Cách giải

1. Sử dụng tính chất cây nhị phân của chuỗi được tạo ra
2. Áp dụng thuật toán logarit để tính toán vị trí
3. Sử dụng mảng operations để xác định số lần dịch chuyển ký tự
4. Trả về ký tự tương ứng với tổng số lần dịch chuyển

## Hàm hỗ trợ

- `floorLogBase2(x)`: Tính logarit cơ số 2 của x và làm tròn xuống
- `isLogBase2(x)`: Kiểm tra x có phải là lũy thừa của 2 hay không

## Lưu ý quan trọng

- Mảng operations xác định cách thức dịch chuyển ký tự tại mỗi bước
- operations[i] = 0: Sao chép ký tự (không dịch chuyển)
- operations[i] = 1: Dịch chuyển ký tự lên 1 bậc trong bảng chữ cái

Xem file `SolutionKthCharacterII.java` để tham khảo cài đặt chi tiết.
