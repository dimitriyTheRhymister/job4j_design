package ru.job4j.algo.sliding.window;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MaxOverlapInterval {
    public static int[] findMaxOverlapInterval(List<Interval> intervals) {
        if (intervals.isEmpty()) {
            return new int[]{-1, -1};
        }

        intervals.sort(Comparator.comparingInt(i -> i.start));
        PriorityQueue<Interval> activeIntervals = new PriorityQueue<>(Comparator.comparingInt(i -> i.end));

        int maxOverlap = 0;
        int maxStart = -1;
        int maxEnd = -1;

        for (Interval current : intervals) {
            while (!activeIntervals.isEmpty() && activeIntervals.peek().end < current.start) {
                activeIntervals.poll();
            }

            activeIntervals.offer(current);
            int currentOverlap = activeIntervals.size();

            if (currentOverlap > maxOverlap) {
                maxOverlap = currentOverlap;
                maxStart = current.start;
                maxEnd = activeIntervals.peek().end;
            } else if (currentOverlap == maxOverlap) {
                assert activeIntervals.peek() != null;
                int newEnd = activeIntervals.peek().end;

                if (newEnd - current.start < maxEnd - maxStart) {
                    maxStart = current.start;
                    maxEnd = newEnd;
                }
            }
        }

        return new int[]{maxStart, maxEnd};
    }
}
/*Вычислительная сложность
*   intervals.sort() - O(n*log n), где n — количество интервалов
*   цикл for - O(n)
*   PriorityQueue - вставка и удаление - O(n*log n) + O(n*log n) = 2 O(n*log n) = O(n*log n)
* => O(n*log n) + O(n) + O(n*log n) = O(n*log n)
* ---------------------------------------------------------------
* Объем используемой памяти
*   входные интервалы в списке - O(n)
*   интервалы в очереди - O(n)
* => O(n) + O(n) = 2O(n) = O(n)
* */
