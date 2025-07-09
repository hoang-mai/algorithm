package solution.max.free.time.I;

/**
 * LeetCode Problem 2409: Count Days Spent Together (Maximum Free Time I)
 * You are given an event time and k events with their start and end times.
 * You need to find the maximum free time you can get by selecting k consecutive events.
 * Free time is calculated as the sum of gaps between consecutive events plus the time before the first event and after the last event.
 * @author Mai Anh Hoàng
 * @since 09/07/2025
 */
public class SolutionMaxFreeTimeI {
    /**
     * Tìm thời gian rảnh tối đa khi chọn k sự kiện liên tiếp.
     * Thuật toán sử dụng sliding window để tính toán thời gian rảnh cho mỗi cửa sổ k sự kiện.
     * Độ phức tạp thời gian: O(n), trong đó n là số lượng sự kiện.
     * Độ phức tạp không gian: O(1), chỉ sử dụng các biến primitive.
     * 
     * @param eventTime Tổng thời gian của toàn bộ sự kiện.
     * @param k Số lượng sự kiện liên tiếp cần chọn.
     * @param startTime Mảng thời gian bắt đầu của các sự kiện.
     * @param endTime Mảng thời gian kết thúc của các sự kiện.
     * @return Thời gian rảnh tối đa có thể đạt được.
     */
    public int maxFreeTimeI(int eventTime, int k, int[] startTime, int[] endTime) {
        int max = startTime[0];                    // Thời gian trước sự kiện đầu tiên
        int n = endTime.length;                    // Tổng số sự kiện
        
        // Tính thời gian rảnh trong k sự kiện đầu tiên
        for (int i = 1; i < k; i++) {
            max += startTime[i] - endTime[i - 1];  // Khoảng cách giữa các sự kiện liên tiếp
        }
        
        // Nếu chọn tất cả sự kiện
        if (k == n) {
            max += eventTime - endTime[k - 1];     // Thêm thời gian sau sự kiện cuối cùng
            return max;
        } else {
            max += startTime[k] - endTime[k - 1];  // Thêm khoảng cách đến sự kiện tiếp theo
        }
        
        int sum = max;                             // Thời gian rảnh hiện tại
        
        // Trường hợp đặc biệt: k + 1 == n
        if (k + 1 == n) {
            sum = sum - startTime[0] + eventTime - endTime[k];
            max = Math.max(max, sum);
            return max;
        } else {
            sum = sum - startTime[0] + startTime[k + 1] - endTime[k];
            max = Math.max(max, sum);
        }
        
        // Sliding window: duyệt qua các cửa sổ k sự kiện tiếp theo
        for (int i = k + 2; i < n; i++) {
            // Loại bỏ sự kiện đầu cũ và thêm sự kiện cuối mới
            sum = sum - (startTime[i - k - 1] - endTime[i - k - 2]) + startTime[i] - endTime[i - 1];
            max = Math.max(max, sum);
        }
        
        // Xử lý cửa sổ cuối cùng (đến hết eventTime)
        sum = sum - (startTime[n - k - 1] - endTime[n - k - 2]) + eventTime - endTime[n - 1];
        max = Math.max(max, sum);
        
        return max;
    }
    public static void main(String[] args) {
        SolutionMaxFreeTimeI solution = new SolutionMaxFreeTimeI();
        
        // Test case 1: Ví dụ từ LeetCode
        int eventTime1 = 83;
        int k1 = 1;
        int[] startTime1 = {13, 15, 43, 81};
        int[] endTime1 = {15, 22, 78, 83};
        System.out.println("Test case 1: " + solution.maxFreeTimeI(eventTime1, k1, startTime1, endTime1));
        
        // Test case 2: Chọn nhiều sự kiện liên tiếp
        int eventTime2 = 100;
        int k2 = 2;
        int[] startTime2 = {10, 20, 30, 40, 50};
        int[] endTime2 = {15, 25, 35, 45, 55};
        System.out.println("Test case 2: " + solution.maxFreeTimeI(eventTime2, k2, startTime2, endTime2));
        
        // Test case 3: Chọn tất cả sự kiện
        int eventTime3 = 50;
        int k3 = 3;
        int[] startTime3 = {5, 15, 25};
        int[] endTime3 = {10, 20, 30};
        System.out.println("Test case 3: " + solution.maxFreeTimeI(eventTime3, k3, startTime3, endTime3));
    }
}
