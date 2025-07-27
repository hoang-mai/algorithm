# Đếm số lượng đỉnh và thung lũng trong mảng (LeetCode 2210)

## Tổng quan

Cho một mảng số nguyên `nums`, hãy đếm số lượng đỉnh (hill) và thung lũng (valley) trong mảng.

- **Đỉnh (hill):** nums[i] lớn hơn tất cả các phần tử khác liền kề không bằng nums[i].
- **Thung lũng (valley):** nums[i] nhỏ hơn tất cả các phần tử khác liền kề không bằng nums[i].
- **Lưu ý:** Bỏ qua các phần tử trùng lặp liên tiếp khi xác định đỉnh/thung lũng.

## Định nghĩa hàm

```java
public int countHillValley(int[] nums);
```

## Phương pháp: So sánh hai phía (Brute Force)

### Trực giác

Để xác định một phần tử là đỉnh hay thung lũng:
1. Tìm phần tử khác nums[i] gần nhất bên trái và bên phải
2. So sánh nums[i] với hai phần tử này:
   - Nếu lớn hơn cả hai: là đỉnh
   - Nếu nhỏ hơn cả hai: là thung lũng
3. Bỏ qua các phần tử trùng lặp liên tiếp

### Thuật toán

1. Duyệt từng phần tử từ 1 đến n-2
2. Với mỗi phần tử, tìm phần tử khác nums[i] gần nhất bên trái và bên phải
3. So sánh để xác định đỉnh/thung lũng
4. Đếm số lượng thỏa mãn

## Ví dụ

### Ví dụ 1:
**Đầu vào:**
```java
int[] nums = {2, 4, 1, 1, 6, 5};
```
**Đầu ra:**
```
3
```
**Giải thích:**
- nums[1]=4 là đỉnh (lớn hơn 2 và 1)
- nums[2]=1 là thung lũng (nhỏ hơn 4 và 6)
- nums[4]=6 là đỉnh (lớn hơn 1 và 5)

### Ví dụ 2:
**Đầu vào:**
```java
int[] nums = {1, 1, 1, 1};
```
**Đầu ra:**
```
0
```
**Giải thích:**
- Không có đỉnh/thung lũng vì tất cả phần tử giống nhau

### Ví dụ 3:
**Đầu vào:**
```java
int[] nums = {1, 3, 2, 4, 1};
```
**Đầu ra:**
```
3
```
**Giải thích:**
- nums[1]=3 là đỉnh (lớn hơn 1 và 2)
- nums[2]=2 là thung lũng (nhỏ hơn 3 và 4)
- nums[3]=4 là đỉnh (lớn hơn 2 và 1)

## Độ phức tạp

- **Thời gian:** O(n^2), với n là độ dài mảng nums (vì phải tìm phần tử khác gần nhất hai phía cho mỗi phần tử)
- **Bộ nhớ:** O(1), không sử dụng thêm bộ nhớ ngoài các biến đếm

## Cách giải chi tiết

1. Duyệt từng phần tử (trừ đầu/cuối)
2. Tìm phần tử khác nums[i] gần nhất bên trái và phải
3. So sánh để xác định đỉnh/thung lũng
4. Đếm số lượng thỏa mãn

## Lưu ý implementation

- Cần bỏ qua các phần tử trùng lặp liên tiếp khi xác định đỉnh/thung lũng
- Chỉ đếm khi left == right và khác 0 (không phải phần tử trùng lặp)

## Biến thể

- Đếm riêng số lượng đỉnh và số lượng thung lũng
- Tìm vị trí các đỉnh/thung lũng
- Xác định đỉnh/thung lũng trong mảng 2 chiều

Xem file `SolutionCountHillValley.java` để tham khảo cài đặt chi tiết.
