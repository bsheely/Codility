package com.example.demo;

public class MaxDoubleSliceSum {

    public int solution(int[] A) {
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
}
