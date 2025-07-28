# Đếm số tập con có giá trị OR lớn nhất (LeetCode 2044)

## Tổng quan
Cho một mảng số nguyên `nums`, hãy đếm số lượng tập con (subset) có giá trị OR lớn nhất.

- Một tập con là bất kỳ dãy con nào (không nhất thiết liên tiếp, có thể rỗng hoặc đầy đủ).
- Giá trị OR của một tập con là kết quả phép toán bitwise OR của tất cả các phần tử trong tập con.
- Trả về số lượng tập con có giá trị OR bằng giá trị OR lớn nhất có thể của toàn bộ mảng.

## Ví dụ

### Ví dụ 1
**Đầu vào:**
```
nums = [3, 1]
```
**Đầu ra:**
```
2
```
**Giải thích:**
- Giá trị OR lớn nhất là 3 (3 | 1 = 3)
- Các tập con có OR = 3: [3], [3, 1]

### Ví dụ 2
**Đầu vào:**
```
nums = [2, 2, 2]
```
**Đầu ra:**
```
7
```
**Giải thích:**
- Giá trị OR lớn nhất là 2
- Có 7 tập con khác rỗng, tất cả đều có OR = 2

### Ví dụ 3
**Đầu vào:**
```
nums = [1, 2, 3]
```
**Đầu ra:**
```
6
```
**Giải thích:**
- Giá trị OR lớn nhất là 3
- Các tập con có OR = 3: [1,2], [1,3], [2,3], [1,2,3], [3], [1,2,3]

## Thuật toán

### Ý tưởng chính
- Tính giá trị OR lớn nhất của toàn bộ mảng (`maxOr`)
- Dùng đệ quy (DFS) để duyệt tất cả các tập con
- Đếm số tập con có giá trị OR đúng bằng `maxOr`

### Cách hoạt động
```java
int maxOr = 0;
for (int num : nums) {
    maxOr |= num;
}
return dfs(nums, 0, 0, maxOr);

// Hàm dfs:
private int dfs(int[] nums, int start, int sumOr, int maxOr) {
    if (start == nums.length) return 0;
    int before = sumOr;
    int count = 0;
    for (int i = 1; i >= 0; i--) {
        if (i == 1) {
            sumOr |= nums[start];
            if (sumOr == maxOr) count++;
        }
        count += dfs(nums, start + 1, sumOr, maxOr);
        if (i == 1) sumOr = before;
    }
    return count;
}
```

### Các bước thực hiện
1. Tính giá trị OR lớn nhất của toàn bộ mảng
2. Đệ quy duyệt tất cả các tập con, với mỗi phần tử:
   - Chọn hoặc không chọn phần tử đó
   - Nếu chọn, cập nhật giá trị OR
   - Nếu OR đạt maxOr, tăng biến đếm
3. Trả về tổng số tập con hợp lệ

## Phân tích độ phức tạp
- **Thời gian:** O(2^n), với n là số phần tử của mảng (duyệt mọi tập con)
- **Không gian:** O(n), cho stack đệ quy

## Lưu ý
- Không tính tập con rỗng
- Đáp án luôn là số nguyên dương

## Ví dụ chạy code
```java
public static void main(String[] args) {
    SolutionCountMaxOrSubsets sol = new SolutionCountMaxOrSubsets();
    int[] nums = {3, 1};
    System.out.println(sol.countMaxOrSubsets(nums)); // Output: 2
}
```

Xem file `SolutionCountMaxOrSubsets.java` để tham khảo chi tiết về cài đặt.
