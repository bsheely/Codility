package com.example.demo;

public class MaxSliceSum {

    public int solution(int[] A) {
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
}
