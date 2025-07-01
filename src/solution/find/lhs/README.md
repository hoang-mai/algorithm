# Dãy con Hòa hợp dài nhất (LeetCode 594)

## Tổng quan
Cho một mảng số nguyên `nums`, nhiệm vụ là tìm độ dài của dãy con hòa hợp dài nhất. Một mảng được gọi là hòa hợp nếu hiệu giữa giá trị lớn nhất và nhỏ nhất của nó đúng bằng 1. Dãy con không nhất thiết phải liên tiếp.

## Ví dụ
**Đầu vào:**
```
nums = [1,3,2,2,5,2,3,7]
```
**Đầu ra:**
```
5
```
**Giải thích:**
Dãy con hòa hợp dài nhất là `[3,2,2,2,3]` hoặc `[2,3,3,2,2]`, đều có độ dài 5.

## Cách giải
1. **Đếm tần suất:** Sử dụng HashMap để đếm số lần xuất hiện của từng số trong mảng.
2. **Sắp xếp khóa:** Sắp xếp các số duy nhất.
3. **Kiểm tra từng cặp liền kề:** Với mỗi cặp số liền kề, nếu hiệu của chúng là 1 thì cộng tổng tần suất của hai số đó. Theo dõi giá trị lớn nhất tìm được.
4. **Trả về kết quả:** Giá trị lớn nhất là độ dài dãy con hòa hợp dài nhất.

**Độ phức tạp thời gian:** O(n log n) do sắp xếp các phần tử duy nhất.

**Độ phức tạp bộ nhớ:** O(n) cho bảng tần suất.

Xem file `SolutionFindLHS.java` để tham khảo cài đặt.
