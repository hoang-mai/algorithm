# Số lượng sự kiện tối đa có thể tham dự (LeetCode 1353)

## Tổng quan

Cho một mảng các sự kiện `events` trong đó `events[i] = [startDayi, endDayi]`. Mỗi sự kiện i bắt đầu tại `startDayi` và kết thúc tại `endDayi`.

Bạn có thể tham dự sự kiện i tại bất kỳ ngày d nào trong khoảng `startTime[i] <= d <= endTime[i]`.

Lưu ý rằng bạn chỉ có thể tham dự một sự kiện tại một thời điểm.

Trả về số lượng sự kiện tối đa bạn có thể tham dự.

## Định nghĩa hàm

```java
public int maxEvents(int[][] events);
```

## Phương pháp: Greedy Algorithm + Priority Queue

### Trực giác

Để tối đa hóa số lượng sự kiện có thể tham dự, chúng ta sử dụng chiến lược tham lam:

1. **Ưu tiên sự kiện kết thúc sớm**: Tham dự sự kiện kết thúc sớm nhất để tối đa hóa cơ hội tham dự các sự kiện sau
2. **Xử lý theo thứ tự thời gian**: Sắp xếp và xử lý các sự kiện theo ngày bắt đầu
3. **Sử dụng PriorityQueue**: Để luôn chọn được sự kiện kết thúc sớm nhất

### Thuật toán

1. **Sắp xếp sự kiện**: Theo ngày bắt đầu, nếu bằng nhau thì theo ngày kết thúc
2. **Sử dụng PriorityQueue**: Ưu tiên sự kiện có ngày kết thúc sớm nhất
3. **Duyệt từng ngày**: Xử lý các sự kiện có thể tham dự trong ngày hiện tại
4. **Chọn tham dự**: Luôn chọn sự kiện kết thúc sớm nhất

### Các bước thực hiện:

1. **Sắp xếp**: Sắp xếp mảng events theo ngày bắt đầu
2. **Khởi tạo**: PriorityQueue để lưu các sự kiện đang có thể tham dự
3. **Duyệt sự kiện**: Với mỗi sự kiện mới:
   - Xử lý các sự kiện cũ trong queue
   - Thêm sự kiện mới vào queue
4. **Xử lý queue cuối**: Xử lý các sự kiện còn lại trong queue
5. **Trả về kết quả**: Số lượng sự kiện đã tham dự

## Ví dụ

### Ví dụ 1:
**Đầu vào:**
```java
int[][] events = {{1,2},{2,3},{3,4}};
```

**Đầu ra:**
```
3
```

**Giải thích:**
- Ngày 1: Tham dự sự kiện [1,2]
- Ngày 2: Tham dự sự kiện [2,3]
- Ngày 3: Tham dự sự kiện [3,4]
- Tổng: 3 sự kiện

### Ví dụ 2:
**Đầu vào:**
```java
int[][] events = {{1,2},{1,2},{3,3},{1,5},{1,5}};
```

**Đầu ra:**
```
4
```

**Giải thích:**
- Ngày 1: Tham dự một sự kiện [1,2] (chọn kết thúc sớm)
- Ngày 2: Tham dự một sự kiện [1,2] khác
- Ngày 3: Tham dự sự kiện [3,3]
- Ngày 4: Tham dự một sự kiện [1,5]
- Tổng: 4 sự kiện

### Ví dụ 3:
**Đầu vào:**
```java
int[][] events = {{1,4},{4,4},{2,2},{3,4},{1,1}};
```

**Đầu ra:**
```
4
```

**Giải thích:**
- Ngày 1: Tham dự sự kiện [1,1]
- Ngày 2: Tham dự sự kiện [2,2]
- Ngày 3: Tham dự sự kiện [3,4]
- Ngày 4: Tham dự sự kiện [4,4]
- Tổng: 4 sự kiện

## Độ phức tạp

- **Thời gian:** O(n log n)
  - Sắp xếp: O(n log n)
  - Xử lý với PriorityQueue: O(n log n)
  - Tổng: O(n log n)

- **Bộ nhớ:** O(n), để lưu trữ PriorityQueue trong trường hợp xấu nhất

## Cách giải chi tiết

### Tại sao sắp xếp theo ngày bắt đầu?
- Đảm bảo xử lý các sự kiện theo thứ tự thời gian
- Tránh bỏ lỡ các sự kiện có thể tham dự

### Tại sao ưu tiên sự kiện kết thúc sớm?
- Tối đa hóa cơ hội tham dự các sự kiện sau
- Tránh "block" quá nhiều ngày cho một sự kiện

### Tại sao sử dụng PriorityQueue?
- Luôn có thể chọn sự kiện kết thúc sớm nhất trong O(log n)
- Tự động duy trì thứ tự ưu tiên

## Thuật toán Greedy

Chiến lược tham lam ở đây là:
1. **Luôn chọn sự kiện kết thúc sớm nhất** trong số các sự kiện có thể tham dự
2. **Không bao giờ để trống một ngày** nếu có sự kiện có thể tham dự
3. **Loại bỏ sự kiện đã hết hạn** để tối ưu bộ nhớ

## Lưu ý implementation

- Kiểm tra `endDay >= currentDay` trước khi tham dự
- Sử dụng `Comparator.comparingInt()` để tạo PriorityQueue
- Xử lý trường hợp queue rỗng khi chuyển sang sự kiện mới

## Biến thể

Bài toán có thể mở rộng thành:
- Tối đa hóa tổng giá trị của các sự kiện (có trọng số)
- Hạn chế số lượng sự kiện có thể tham dự mỗi ngày
- Tìm lịch trình tối ưu với chi phí di chuyển

Xem file `SolutionMaxEvents.java` để tham khảo cài đặt chi tiết.
