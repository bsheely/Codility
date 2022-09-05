package com.example.demo;

public class MinPerimeterRectangle {

    public int solution(int N) {
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
}
