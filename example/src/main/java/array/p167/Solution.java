package array.p167;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    /**
     * 双指针遍历
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        int low = 0;
        int high = numbers.length - 1;

        while (low < high) {
            int sum = numbers[low] + numbers[high];

            if (sum < target) {
                low++;
            } else if (sum > target) {
                high--;
            } else {
                return new int[]{low + 1, high + 1};
            }
        }

        return new int[]{};
    }

    /**
     * 常规解法
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum1(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int i = 0; i < numbers.length; i++) {
            int temp = target - numbers[i];
            if (map.containsKey(temp)) {
                return new int[]{map.get(temp) + 1, i + 1};
            }
            map.put(numbers[i], i);
        }

        return new int[]{0, 0};
    }
}
