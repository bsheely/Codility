package com.example.demo;

import java.util.*;

public class Peaks {

    public int solution(int[] A) {
        if (A.length < 3) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        int x = (int)Math.sqrt(A.length);
        for (int i = 1; i <= x; ++i) {
            if (A.length % i == 0) {
                set.add(i);
                set.add(A.length / i);
            }
        }
        List<Integer> factors = new ArrayList<>(set);
        Collections.sort(factors);
        int result = 0;
        for (int factor : factors) {
            int size = A.length / factor;
            int[][] blocks = new int[factor][size];
            int index = 0;
            for (int j = 0; j < factor; ++j) {
                for (int k = 0; k < size; ++k) {
                    blocks[j][k] = A[index];
                    ++index;
                }
            }
            boolean noPeak = false;
            for (int j = 0; j < factor; ++j) {
                if (noPeak) {
                    break;
                }
                boolean hasPeak = false;
                for (int k = 0; k < size; ++k) {
                    if (size >= 3 && k > 0 && k < size - 1 && blocks[j][k - 1] < blocks[j][k] && blocks[j][k] > blocks[j][k + 1]) {
                        hasPeak = true;
                    }
                    else if (k == 0 || k == size - 1) {
                        if (size == 1 && j > 0 && j < factors.size() - 1 && blocks[j - 1][0] < blocks[j][0] && blocks[j][0] > blocks[j + 1][0]) {
                            hasPeak = true;
                        }
                        else if (size > 1 && factor > 1) {
                            if (k == 0 && j > 0 && blocks[j - 1][size - 1] < blocks[j][0] && blocks[j][0] > blocks[j][1]) {
                                hasPeak = true;
                            }
                            else if (k == size - 1 && j < factor - 1 && blocks[j][size - 2] < blocks[j][size - 1] && blocks[j][size - 1] > blocks[j + 1][0]) {
                                hasPeak = true;
                            }
                        }
                    }
                    if (k == size - 1 && !hasPeak) {
                        noPeak = true;
                        break;
                    }
                }
                if (j == factor - 1 && !noPeak) {
                    result = factor;
                }
            }
        }
        return result;
    }
}
