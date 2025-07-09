# Thời gian rảnh tối đa I (LeetCode 2409)

## Tổng quan

Bạn được cho một thời gian sự kiện `eventTime` và `k` sự kiện với thời gian bắt đầu và kết thúc của chúng.

Bạn cần tìm thời gian rảnh tối đa có thể đạt được bằng cách chọn `k` sự kiện liên tiếp.

Thời gian rảnh được tính bằng tổng của:
- Thời gian trước sự kiện đầu tiên được chọn
- Khoảng cách giữa các sự kiện liên tiếp được chọn
- Thời gian sau sự kiện cuối cùng được chọn

## Định nghĩa hàm

```java
public int maxFreeTimeI(int eventTime, int k, int[] startTime, int[] endTime);
```

## Phương pháp: Sliding Window

### Trực giác

Để tìm thời gian rảnh tối đa, chúng ta cần:

1. **Tính thời gian rảnh cho mỗi cửa sổ k sự kiện liên tiếp**
2. **Sử dụng sliding window** để tối ưu tính toán
3. **So sánh tất cả các khả năng** và chọn thời gian rảnh tối đa

Công thức tính thời gian rảnh cho k sự kiện liên tiếp từ vị trí i:
```
freeTime = startTime[i] + ∑(startTime[j] - endTime[j-1]) + (eventTime - endTime[i+k-1])
```

### Thuật toán

1. **Tính thời gian rảnh ban đầu**: Cho k sự kiện đầu tiên
2. **Sliding window**: Duyệt qua tất cả các cửa sổ k sự kiện có thể
3. **Cập nhật hiệu quả**: Sử dụng kết quả trước đó để tính toán nhanh
4. **Theo dõi max**: Lưu lại thời gian rảnh tối đa

### Các bước thực hiện:

1. **Khởi tạo**: Tính thời gian rảnh cho k sự kiện đầu tiên
2. **Xử lý trường hợp đặc biệt**: k == n (chọn tất cả sự kiện)
3. **Sliding window**: Duyệt qua các cửa sổ tiếp theo
4. **Cập nhật hiệu quả**: Loại bỏ sự kiện cũ, thêm sự kiện mới
5. **Trả về kết quả**: Thời gian rảnh tối đa

## Ví dụ

### Ví dụ 1:
**Đầu vào:**
```java
int eventTime = 83;
int k = 1;
int[] startTime = {13, 15, 43, 81};
int[] endTime = {15, 22, 78, 83};
```

**Đầu ra:**
```
57
```

**Giải thích:**
- Chọn sự kiện [43, 78]: thời gian rảnh = 43 + (83 - 78) = 48
- Chọn sự kiện [13, 15]: thời gian rảnh = 13 + (15 - 15) = 13
- Chọn sự kiện [15, 22]: thời gian rảnh = (15 - 15) + (43 - 22) = 21
- Chọn sự kiện [81, 83]: thời gian rảnh = (81 - 78) + (83 - 83) = 3
- Tối đa: 48

### Ví dụ 2:
**Đầu vào:**
```java
int eventTime = 100;
int k = 2;
int[] startTime = {10, 20, 30, 40, 50};
int[] endTime = {15, 25, 35, 45, 55};
```

**Đầu ra:**
```
Tính toán các cửa sổ k=2 sự kiện liên tiếp
```

**Giải thích:**
- Cửa sổ [10,15], [20,25]: thời gian rảnh = 10 + (20-15) + (30-25) = 20
- Cửa sổ [20,25], [30,35]: thời gian rảnh = (20-15) + (30-25) + (40-35) = 15
- Cửa sổ [30,35], [40,45]: thời gian rảnh = (30-25) + (40-35) + (50-45) = 15
- Cửa sổ [40,45], [50,55]: thời gian rảnh = (40-35) + (50-45) + (100-55) = 55
- Tối đa: 55

## Độ phức tạp

- **Thời gian:** O(n), trong đó n là số lượng sự kiện
  - Tính toán ban đầu: O(k)
  - Sliding window: O(n-k)
  - Tổng: O(n)

- **Bộ nhớ:** O(1), chỉ sử dụng các biến primitive

## Cách giải chi tiết

### Sliding Window Technique

1. **Tính toán ban đầu**: Tính thời gian rảnh cho cửa sổ đầu tiên
2. **Cập nhật hiệu quả**: Khi chuyển cửa sổ:
   - Loại bỏ: Thời gian rảnh của sự kiện đầu cũ
   - Thêm vào: Thời gian rảnh của sự kiện cuối mới
3. **Công thức cập nhật**:
   ```
   newSum = oldSum - (startTime[i-k-1] - endTime[i-k-2]) + (startTime[i] - endTime[i-1])
   ```

### Xử lý trường hợp đặc biệt

1. **k == n**: Chọn tất cả sự kiện
2. **k + 1 == n**: Chỉ có 2 cửa sổ có thể
3. **Cửa sổ cuối**: Thời gian sau sự kiện cuối = eventTime - endTime[n-1]

### Tối ưu hóa

- Sử dụng biến `sum` để lưu trữ thời gian rảnh hiện tại
- Chỉ cần tính toán sự thay đổi thay vì tính toán lại từ đầu
- Xử lý các trường hợp đặc biệt để tránh lặp không cần thiết

## Ứng dụng

Bài toán này có thể áp dụng cho:
- **Lập lịch sự kiện**: Tối ưu thời gian rảnh giữa các cuộc họp
- **Tối ưu tài nguyên**: Tìm khoảng thời gian rảnh tối đa của máy móc
- **Quản lý thời gian**: Lập kế hoạch nghỉ ngơi hiệu quả

## Biến thể

Bài toán có thể mở rộng thành:
- Chọn k sự kiện không nhất thiết liên tiếp
- Có trọng số cho từng khoảng thời gian rảnh
- Hạn chế về khoảng cách tối đa giữa các sự kiện

Xem file `SolutionMaxFreeTimeI.java` để tham khảo cài đặt chi tiết.
