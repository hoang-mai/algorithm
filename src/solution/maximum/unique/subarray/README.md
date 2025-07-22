# Tổng tối đa của mảng con chứa phần tử duy nhất (LeetCode 1695)

## Tổng quan

Bạn được cho một mảng các số nguyên dương `nums` và muốn xóa một mảng con chứa các phần tử duy nhất.

Điểm số bạn nhận được khi xóa mảng con bằng tổng các phần tử của nó.

Trả về điểm số tối đa bạn có thể nhận được bằng cách xóa chính xác một mảng con.

Một mảng con được gọi là mảng con của mảng khác nếu nó tạo thành một chuỗi các phần tử liên tiếp.

## Định nghĩa hàm

```java
public int maximumUniqueSubarray(int[] nums);
```

## Phương pháp: Sliding Window + HashMap

### Trực giác

Để tìm mảng con có tổng tối đa với các phần tử duy nhất, chúng ta sử dụng:

1. **Sliding Window**: Mở rộng window về bên phải, thu hẹp khi gặp phần tử trùng lặp
2. **HashMap**: Theo dõi vị trí cuối cùng của mỗi phần tử
3. **Greedy approach**: Luôn cố gắng mở rộng window để tối đa hóa tổng

### Thuật toán

1. **Khởi tạo**: Các biến `left`, `sum`, `max` và HashMap
2. **Duyệt mảng**: Với mỗi phần tử `nums[i]`:
   - Kiểm tra phần tử đã xuất hiện trong window hiện tại chưa
   - Nếu có: thu hẹp window từ trái đến vị trí xuất hiện cuối
   - Thêm phần tử hiện tại vào window và cập nhật tổng
3. **Cập nhật kết quả**: Theo dõi tổng tối đa trong quá trình

### Các bước thực hiện:

1. **Khởi tạo**: `left = 0`, `sum = 0`, `max = 0`, `HashMap`
2. **Duyệt từng phần tử**: Với `i` từ 0 đến n-1:
   - Kiểm tra `nums[i]` có trong HashMap không
   - Nếu có và trong window hiện tại: thu hẹp window
   - Thêm `nums[i]` vào HashMap và cộng vào `sum`
   - Cập nhật `max`
3. **Trả về**: `max`

## Ví dụ

### Ví dụ 1:
**Đầu vào:**
```java
int[] nums = {5, 2, 1, 2, 5, 2, 1, 2, 5};
```

**Đầu ra:**
```
8
```

**Giải thích:**
- Mảng con tối ưu: [5, 2, 1] (các phần tử từ index 0 đến 2)
- Tổng: 5 + 2 + 1 = 8
- Các mảng con khác có tổng nhỏ hơn

**Quá trình thực hiện:**
1. i=0: nums[0]=5, window=[5], sum=5, max=5
2. i=1: nums[1]=2, window=[5,2], sum=7, max=7  
3. i=2: nums[2]=1, window=[5,2,1], sum=8, max=8
4. i=3: nums[3]=2 (trùng), thu hẹp window, window=[1,2], sum=3, max=8
5. Tiếp tục... max vẫn là 8

### Ví dụ 2:
**Đầu vào:**
```java
int[] nums = {4, 2, 4, 5, 6};
```

**Đầu ra:**
```
17
```

**Giải thích:**
- Mảng con tối ưu: [2, 4, 5, 6] (các phần tử từ index 1 đến 4)
- Tổng: 2 + 4 + 5 + 6 = 17

### Ví dụ 3:
**Đầu vào:**
```java
int[] nums = {1, 2, 3, 4, 5};
```

**Đầu ra:**
```
15
```

**Giải thích:**
- Không có phần tử trùng lặp
- Mảng con tối ưu: toàn bộ mảng [1, 2, 3, 4, 5]
- Tổng: 1 + 2 + 3 + 4 + 5 = 15

## Độ phức tạp

- **Thời gian:** O(n), trong đó n là độ dài của mảng nums
  - Mỗi phần tử được thêm và xóa khỏi HashMap tối đa một lần
  - Vòng lặp for bên trong chỉ chạy khi có phần tử trùng lặp

- **Bộ nhớ:** O(min(m, n))
  - m là số phần tử khác nhau trong mảng
  - n là độ dài mảng
  - HashMap chỉ lưu trữ các phần tử duy nhất trong window hiện tại

## Cách giải chi tiết

### Sliding Window Technique

1. **Mở rộng window**: Luôn thêm phần tử mới vào bên phải
2. **Thu hẹp window**: Khi gặp phần tử trùng lặp, xóa từ trái đến vị trí trùng lặp
3. **Duy trì tính duy nhất**: HashMap đảm bảo không có phần tử trùng lặp trong window

### Tại sao sử dụng HashMap?

- **Tra cứu nhanh**: O(1) để kiểm tra phần tử đã tồn tại
- **Lưu vị trí**: Biết chính xác vị trí cần thu hẹp window
- **Cập nhật hiệu quả**: Dễ dàng thêm/xóa phần tử

### Tối ưu hóa

1. **Thu hẹp thông minh**: Chỉ thu hẹp đến vị trí cần thiết
2. **Cập nhật liên tục**: Theo dõi max trong suốt quá trình
3. **Tránh tính toán lại**: Sử dụng biến `sum` để cập nhật tổng

## So sánh với các phương pháp khác

### Brute Force: O(n³)
- Kiểm tra tất cả mảng con có thể
- Kiểm tra tính duy nhất cho mỗi mảng con
- Không hiệu quả cho mảng lớn

### Two Pointers: O(n²)  
- Sử dụng hai con trỏ nhưng không có HashMap
- Phải kiểm tra trùng lặp mỗi lần mở rộng
- Chậm hơn sliding window + HashMap

### Sliding Window + HashMap: O(n)
- Tối ưu nhất cho bài toán này
- Kết hợp ưu điểm của cả hai kỹ thuật

## Ứng dụng

Bài toán này có thể áp dụng cho:
- **Phân tích dữ liệu**: Tìm chuỗi dữ liệu duy nhất có giá trị cao nhất
- **Tối ưu tài nguyên**: Chọn tập hợp tài nguyên không trùng lặp có giá trị cao
- **Game development**: Tìm combo skill có điểm cao nhất không trùng lặp

## Biến thể

Bài toán có thể mở rộng thành:
- Tìm k mảng con không chồng lắp có tổng tối đa
- Cho phép tối đa m phần tử trùng lặp
- Tìm mảng con ngắn nhất có tổng ≥ target với phần tử duy nhất

Xem file `SolutionMaximumUniqueSubarray.java` để tham khảo cài đặt chi tiết.
