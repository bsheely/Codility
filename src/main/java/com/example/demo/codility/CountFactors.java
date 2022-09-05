package com.example.demo.codility;

import java.util.HashSet;
import java.util.Set;

public class CountFactors {

    public int solution(int N) {
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
}
