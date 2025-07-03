# Đếm số chuỗi gốc có thể có II (LeetCode 3333)

## Tổng quan

Alice đang cố gắng gõ một chuỗi ký tự cụ thể trên máy tính. Tuy nhiên, cô ấy có xu hướng vụng về và có thể giữ phím quá lâu, dẫn đến việc một ký tự bị gõ nhiều lần. Mặc dù Alice đã cố gắng tập trung vào việc gõ, cô ấy biết rằng mình có thể vẫn làm điều này nhiều nhất một lần.

Bạn được cung cấp chuỗi `word`, đại diện cho kết quả cuối cùng hiển thị trên màn hình của Alice.

Trả về tổng số chuỗi gốc có thể mà Alice có thể đã định gõ, với điều kiện là có ít nhất `k` phần tử trong kết quả.

## Định nghĩa hàm

```java
public int possibleStringCountII(String word, int k);
```

## Phương pháp: Quy hoạch động + Tối ưu Prefix Sum

### Trực giác

Để có được kết quả với độ dài ít nhất k, trước tiên chúng ta có thể tính tổng số kết quả hợp lệ với bất kỳ độ dài nào, sau đó trừ đi số kết quả có độ dài 1, 2, ..., k-1.

Với bất kỳ độ dài kết quả nào, chúng ta có thể sử dụng nguyên lý nhân: nếu một ký tự xuất hiện liên tiếp p lần trong chuỗi word, thì Alice có thể chọn nhập nó 1, 2, ..., p lần, cho p lựa chọn có thể. Nhân tất cả các giá trị p như vậy sẽ cho tổng số kết quả hợp lệ cho tất cả các độ dài.

Ví dụ, nếu word="abbcccaa", thì độ dài run p là [1,2,3,2], và tổng số kết quả là 1×2×3×2=12.

### Thuật toán

Để đếm số kết quả có độ dài nhỏ hơn k, chúng ta sử dụng quy hoạch động:

1. **Ghi lại tần số**: Ghi lại tất cả các giá trị p trong mảng tần số freq
2. **Định nghĩa trạng thái**: Định nghĩa f(i,j) là số cách để xây dựng một chuỗi sử dụng i+1 phần tử đầu tiên của freq sao cho tổng độ dài được xây dựng là j
3. **Chuyển trạng thái**: Lặp qua số lần ký tự tương ứng với freq[i] được sử dụng, từ 1 đến freq[i]. Nếu nó được sử dụng j' lần, thì chúng ta phải xây dựng một chuỗi có độ dài j-j' sử dụng i phần tử đầu tiên:

```
f(i,j) = ∑(j'=1 to freq[i]) f(i-1, j-j')
```

4. **Trường hợp cơ sở**: f(-1,0) = 1, cho biết có một cách để xây dựng chuỗi rỗng

### Tối ưu hóa

Quan sát thấy rằng phép tính tổng trong công thức truy hồi có các chỉ số liên tiếp, vì vậy chúng ta có thể tính trước prefix sum. Gọi g(i-1,j) là prefix sum:

```
g(i-1,j) = ∑(j'=0 to j) f(i-1,j')
```

Sau đó chúng ta có thể tính f(i,j) trong thời gian O(1):

```
f(i,j) = g(i-1,j-1) - g(i-1,j-freq[i]-1)
```

Điều này giảm độ phức tạp thời gian từ O(k³) xuống O(k²).

Để tối ưu không gian, lưu ý rằng chúng ta chỉ cần hàng trước đó để chuyển trạng thái, vì vậy chúng ta có thể giảm không gian từ O(k²) xuống O(k) bằng cách sử dụng hai mảng 1D.

## Ví dụ

**Đầu vào:**

```java
String word = "ggggggggaaaaallsssssaaaaaaaaaiiqqqqqqqqqqbbbbbbbvvfffffjjjjeeeeeefffmmiiiix";
int k = 34;
```

**Đầu ra:**

```
Số lượng chuỗi gốc có thể
```

**Giải thích:**
Chuỗi được chia thành các nhóm ký tự liên tiếp, mỗi nhóm có thể được rút gọn về 1 ký tự hoặc giữ nguyên một phần, tạo ra nhiều khả năng chuỗi gốc khác nhau.

## Độ phức tạp

- **Thời gian:** O(n * k), với n là độ dài chuỗi word và k là số phần tử tối thiểu
- **Bộ nhớ:** O(n + k), do sử dụng hai mảng 1D để tối ưu không gian

## Cách giải

1. Chia chuỗi thành các nhóm ký tự giống nhau liên tiếp và đếm tần số mỗi nhóm
2. Tính tổng số chuỗi có thể tạo được bằng tích của tất cả tần số
3. Nếu số nhóm ≥ k, trả về tổng số chuỗi
4. Sử dụng quy hoạch động để tính số chuỗi có độ dài < k
5. Trả về hiệu của tổng số chuỗi và số chuỗi có độ dài < k

Xem file `SolutionPossibleStringCountII.java` để tham khảo cài đặt chi tiết.
