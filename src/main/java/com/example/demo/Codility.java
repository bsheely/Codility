package com.example.demo;

import java.util.*;

import static java.util.stream.Collectors.toMap;

public class Codility {

        public int flags(int[] A) {
                List<Integer> peaks = new ArrayList<>();
                List<Integer> distances = new ArrayList<>();
                for (int i = 1; i < A.length - 1; ++i) {
                        if (A[i] > A[i - 1] && A[i] > A[i + 1]) {
                                if (!peaks.isEmpty()) {
                                        int distance = i - peaks.get(peaks.size() - 1);
                                        peaks.add(i);
                                        distances.add(distance);
                                }
                                else {
                                        peaks.add(i);
                                }
                        }
                }
                if (peaks.isEmpty()) {
                        return 0;
                }
                int maxCount = 0;
                for (int i = 1; i <= peaks.size(); ++i) {
                        int planted = 0;
                        int lastFlagDistance = 0;
                        for (int peak = 1; peak <= peaks.size(); ++peak) {
                                if (peak == 1) {
                                        planted += 1;
                                }
                                else {
                                        int distance = distances.get(peak - 2);
                                        lastFlagDistance += distance;
                                        if (lastFlagDistance >= i) {
                                                planted += 1;
                                                lastFlagDistance = 0;
                                        }
                                }
                                if (planted == i) {
                                        break;
                                }
                        }
                        if (planted > maxCount) {
                                maxCount = planted;
                        }
                }
                return maxCount;
        }

        public int minPerimeterRectangle(int N) {
                int min = Integer.MAX_VALUE;
                int x = (int)Math.sqrt(N);
                for (int i = 1; i <= x; ++i) {
                        if (N % i == 0) {
                                int perimeter = 2 * (i + (N/i));
                                if (perimeter < min) {
                                        min = perimeter;
                                }
                        }
                }
                return min;
        }

        public int countFactors(int N) {
                Set<Integer> factors = new HashSet<>();
                int x = (int)Math.sqrt(N);
                for (int i = 1; i <= x; ++i) {
                        if (N % i == 0) {
                                factors.add(i);
                                factors.add(N/i);
                        }
                }
                return factors.size();
        }

        public int maxDoubleSliceSum(int[] A) {
                if (A.length == 3) {
                        return (A[0] < A[1] && A[1] < A[2]) ? A[1] + A[0] + A[2 - 1] : 0;
                }
                int max = 0;
                for (int x = 0; x < A.length - 2; ++x) {
                        for (int y = x + 1; y < A.length - 1; ++y) {
                                for (int z = y + 1; z < A.length; ++z) {
                                        int sum = 0;
                                        for (int i = x + 1; i <= z - 1; ++i) {
                                                if (i == y) {
                                                        continue;
                                                }
                                                sum += A[i];
                                        }
                                        if (sum > max) {
                                                max = sum;
                                        }
                                }
                        }
                }
                return max;
        }

        public int maxSliceSum(int[] A) {
                if (A.length == 1) {
                        return A[0];
                }
                if (A.length == 2) {
                        return A[0] > 0 && A[1] > 0 ? A[0] + A[1] : Math.max(A[0], A[1]);
                }
                int max = A[0];
                for (int i = 0; i < A.length; ++i) {
                        int sum = A[i];
                        if (A[i] > max) {
                                max = A[i];
                        }
                        for (int j = i + 1; j < A.length; ++j) {
                                sum += A[j];
                                if (sum > max) {
                                        max = sum;
                                }
                        }
                }
                return max;
        }

        public int equiLeader(int[] A) {
                if (A.length == 1) {
                        return 0;
                }
                Map<Integer, List<Integer>> map = new HashMap<>();
                for (int i = 0; i < A.length; ++i) {
                        if (map.containsKey(A[i])) {
                                map.get(A[i]).add(i);
                        }
                        else {
                                map.put(A[i], new ArrayList<>(Collections.singletonList(i)));
                        }
                }
                if (map.size() == 1) {
                        return A.length - 1;
                }
                if (map.size() == A.length) {
                        return 0;
                }
                Map<Integer, List<Integer>> sorted = map.entrySet().stream()
                        .sorted(Collections.reverseOrder(Comparator.comparing(o -> o.getValue().size())))
                        .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> { throw new AssertionError(); }, LinkedHashMap::new));
                Iterator<Map.Entry<Integer, List<Integer>>> iterator = sorted.entrySet().iterator();
                List<Integer> indices = iterator.next().getValue();
                int result = 0;
                int leftLeaders = 0;
                int rightLeaders = indices.size();
                for (int i = 0; i < A.length; ++i) {
                        if (indices.contains(i)) {
                                leftLeaders += 1;
                                rightLeaders -= 1;
                        }
                        if (leftLeaders > (i + 1) / 2 && rightLeaders > (A.length - (i + 1)) / 2) {
                                result += 1;
                        }
                }
                return result;
        }

        public int stoneWall(int[] H) {
                int count = 0;
                int stackHeight = 0;
                Stack<Integer> stack = new Stack<>();
                for (int i = 0; i < H.length; ++i) {
                        if (i == 0) {
                                stack.push(H[0]);
                                stackHeight = H[0];
                                count += 1;
                        }
                        else if (H[i] < H[i - 1]) {
                                int previous = stack.peek();
                                stack.pop();
                                stackHeight -= previous;
                                if (stack.isEmpty()) {
                                        stack.push(H[i]);
                                        stackHeight = H[i];
                                        count += 1;
                                }
                                else if (H[i] > stackHeight) {
                                        int step = H[i] - stackHeight;
                                        stack.push(step);
                                        stackHeight += step;
                                        count += 1;
                                }
                                else {
                                        while (!stack.isEmpty() && H[i] < stack.peek()) {
                                                stack.pop();
                                        }
                                        if (stack.isEmpty()) {
                                                stack.push(H[i]);
                                                stackHeight = H[i];
                                                count += 1;
                                        }
                                }
                        }
                        else if (H[i] > H[i - 1]) {
                                int step = H[i] - stackHeight;
                                stack.push(step);
                                stackHeight += step;
                                count += 1;
                        }
                }
                return count;
        }
}
