# Tìm cặp số có tổng xác định (LeetCode 1865)

## Tổng quan

Bạn được cho hai mảng số nguyên `nums1` và `nums2`. Bạn cần triển khai một cấu trúc dữ liệu hỗ trợ các truy vấn của hai loại:

1. **Add**: Thêm một số nguyên dương vào một phần tử tại chỉ số `i` trong `nums2`.
2. **Count**: Đếm số lượng cặp `(i, j)` sao cho `nums1[i] + nums2[j]` bằng `tot`.

## Định nghĩa class

```java
class FindSumPairs {
    public FindSumPairs(int[] nums1, int[] nums2);
    public void add(int index, int val);
    public int count(int tot);
}
```

## Phương pháp: Hash Map để tối ưu truy vấn

### Trực giác

Để tối ưu hiệu suất cho các truy vấn count, chúng ta sử dụng HashMap để lưu trữ tần số xuất hiện:

- **map1**: Lưu tần số các phần tử trong `nums1` (không thay đổi)
- **map2**: Lưu tần số các phần tử trong `nums2` (có thể thay đổi)

Khi đếm cặp có tổng bằng `tot`, ta tìm kiếm `complement = tot - nums1[i]` trong `nums2`.

### Thiết kế cấu trúc dữ liệu

```java
private final Map<Integer, Integer> map1 = new HashMap<>();  // Tần số nums1
private final Map<Integer, Integer> map2 = new HashMap<>();  // Tần số nums2
private final int[] nums2;                                   // Mảng nums2 gốc
```

### Thuật toán

#### Constructor: `FindSumPairs(int[] nums1, int[] nums2)`
1. Đếm tần số xuất hiện của các phần tử trong `nums1` và lưu vào `map1`
2. Đếm tần số xuất hiện của các phần tử trong `nums2` và lưu vào `map2`
3. Lưu tham chiếu đến `nums2` để theo dõi thay đổi

#### Method: `add(int index, int val)`
1. Giảm tần số của `nums2[index]` cũ trong `map2`
2. Cập nhật `nums2[index] += val`
3. Tăng tần số của `nums2[index]` mới trong `map2`

#### Method: `count(int tot)`
1. Duyệt qua tất cả các phần tử trong `map1`
2. Với mỗi phần tử `nums1[i]`, tính `complement = tot - nums1[i]`
3. Nếu `complement` tồn tại trong `map2`, cộng `freq1[i] * freq2[complement]` vào kết quả

## Ví dụ

**Khởi tạo:**
```java
int[] nums1 = {1, 1, 2, 2, 2, 3};
int[] nums2 = {1, 4, 5, 2, 5, 4};
FindSumPairs findSumPairs = new FindSumPairs(nums1, nums2);
```

**Trạng thái ban đầu:**
- `map1`: {1: 2, 2: 3, 3: 1}
- `map2`: {1: 1, 4: 2, 5: 2, 2: 1}

**Các phép toán:**

1. `add(3, 2)`: `nums2[3] += 2` → `nums2 = [1,4,5,4,5,4]`
   - `map2`: {1: 1, 4: 3, 5: 2}

2. `add(0, 1)`: `nums2[0] += 1` → `nums2 = [2,4,5,4,5,4]`
   - `map2`: {2: 1, 4: 3, 5: 2}

3. `add(1, 1)`: `nums2[1] += 1` → `nums2 = [2,5,5,4,5,4]`
   - `map2`: {2: 1, 5: 3, 4: 2}

4. `count(7)`: Tìm cặp có tổng bằng 7
   - `nums1[i] = 1`, `complement = 6`: không tồn tại
   - `nums1[i] = 2`, `complement = 5`: tồn tại với tần số 3
   - `nums1[i] = 3`, `complement = 4`: tồn tại với tần số 2
   - Kết quả: `0 + 3*3 + 1*2 = 11`

## Độ phức tạp

### Constructor
- **Thời gian:** O(n + m), với n = len(nums1), m = len(nums2)
- **Bộ nhớ:** O(n + m) để lưu trữ HashMap

### Method add
- **Thời gian:** O(1) - Truy cập HashMap trong thời gian constant
- **Bộ nhớ:** O(1) - Chỉ cập nhật HashMap

### Method count  
- **Thời gian:** O(k), với k là số phần tử khác nhau trong nums1
- **Bộ nhớ:** O(1) - Không sử dụng thêm bộ nhớ

## Ưu điểm của thiết kế

1. **Hiệu quả cho truy vấn count**: O(k) thay vì O(n*m) nếu duyệt brute force
2. **Cập nhật nhanh**: Method add chỉ mất O(1) thời gian
3. **Tối ưu bộ nhớ**: Chỉ lưu tần số thay vì toàn bộ mảng

## Lưu ý implementation

- Sử dụng `getOrDefault(key, 0)` để tránh `NullPointerException`
- Kiểm tra `freq == 1` trước khi remove khỏi HashMap
- Lưu tham chiếu đến `nums2` để theo dõi thay đổi

## Biến thể

Bài toán có thể mở rộng thành:
- Cho phép thay đổi cả `nums1` và `nums2`
- Tìm tất cả các cặp có tổng trong khoảng [min, max]
- Hỗ trợ thêm các phép toán khác như subtract, multiply

Xem file `FindSumPairs.java` để tham khảo cài đặt chi tiết.
