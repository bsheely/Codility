package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class Flags {

    public int solution(int[] A) {
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
}
