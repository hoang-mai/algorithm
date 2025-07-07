package solution.max.events;

import java.util.*;

/**
 * LeetCode Problem 1353: Maximum Number of Events That Can Be Attended
 * Given an array of events where events[i] = [startDayi, endDayi]. Every event i starts at startDayi and ends at endDayi.
 * You can attend an event i at any day d where startTime[i] <= d <= endTime[i]. 
 * Notice that you can only attend one event at any time.
 * Return the maximum number of events you can attend.
 * @author Mai Anh Hoàng
 * @since 07/07/2025
 */
public class SolutionMaxEvents {
    /**
     * Tìm số lượng sự kiện tối đa có thể tham dự.
     * Thuật toán sử dụng Greedy approach với PriorityQueue để tối ưu việc chọn sự kiện.
     * Độ phức tạp thời gian: O(n log n), trong đó n là số lượng sự kiện.
     * Độ phức tạp không gian: O(n), để lưu trữ PriorityQueue.
     * 
     * @param events Mảng 2D chứa các sự kiện, mỗi sự kiện có dạng [startDay, endDay].
     * @return Số lượng sự kiện tối đa có thể tham dự.
     */
    public int maxEvents(int[][] events) {
        int result = 0;                // Số lượng sự kiện đã tham dự
        
        // Sắp xếp sự kiện theo ngày bắt đầu, nếu bằng nhau thì theo ngày kết thúc
        Arrays.sort(events, (o1, o2) -> {
            if (o1[0] > o2[0]) return 1;
            else if (o1[0] < o2[0]) return -1;
            else {
                if (o1[1] > o2[1]) return 1;
                else if (o1[1] < o2[1]) return -1;
                return 0;
            }
        });
        
        int startDay = 0;              // Ngày hiện tại
        // PriorityQueue để lưu các sự kiện, ưu tiên sự kiện kết thúc sớm nhất
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        
        // Duyệt qua tất cả các sự kiện
        for (int[] event : events) {
            // Nếu queue rỗng hoặc sự kiện bắt đầu cùng ngày
            if (queue.isEmpty() || event[0] == startDay) {
                startDay = event[0];
                queue.add(event);
                continue;
            }
            
            // Xử lý các sự kiện trong queue cho đến khi đến ngày bắt đầu sự kiện mới
            while (!queue.isEmpty() && event[0] > startDay) {
                int endOfCurDay = queue.peek()[1];
                // Nếu sự kiện chưa kết thúc, tham dự nó
                if (endOfCurDay >= startDay) {
                    result++;
                    startDay++;
                }
                queue.poll();          // Loại bỏ sự kiện khỏi queue
            }
            
            // Nếu queue rỗng, chuyển đến ngày bắt đầu sự kiện mới
            if (queue.isEmpty()) {
                startDay = event[0];
            }
            queue.add(event);
        }
        
        // Xử lý các sự kiện còn lại trong queue
        while (!queue.isEmpty()) {
            if (queue.peek()[1] >= startDay) {
                result++;
                startDay++;
            }
            queue.poll();
        }
        
        return result;
    }
    public static void main(String[] args) {
        SolutionMaxEvents solution = new SolutionMaxEvents();
        
        // Test case 1: Ví dụ phức tạp với nhiều sự kiện
        int[][] events1 = {{25,26},{19,19},{9,13},{16,17},{17,18},{20,21},{22,25},{11,12},{19,23},{7,9},{1,1},{21,23},{14,14},{4,7},{16,16},{24,28},{16,18},{4,5},{18,20},{16,16},{25,26}};
        System.out.println("Test case 1: " + solution.maxEvents(events1));
        
        // Test case 2: Ví dụ đơn giản
        int[][] events2 = {{1,2},{2,3},{3,4}};
        System.out.println("Test case 2: " + solution.maxEvents(events2)); // Expected: 3
        
        // Test case 3: Sự kiện chồng lắp
        int[][] events3 = {{1,2},{1,2},{3,3},{1,5},{1,5}};
        System.out.println("Test case 3: " + solution.maxEvents(events3)); // Expected: 4
    }
}
