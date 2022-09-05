package com.example.demo.codility;

import java.util.Stack;

public class StoneWall {

    public int solution(int[] H) {
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
