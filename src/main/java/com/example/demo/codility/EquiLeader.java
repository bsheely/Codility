package com.example.demo.codility;

import java.util.*;

import static java.util.stream.Collectors.toMap;

public class EquiLeader {

    public int solution(int[] A) {
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
}
