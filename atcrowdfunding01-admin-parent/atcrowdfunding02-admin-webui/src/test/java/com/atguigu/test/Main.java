package com.atguigu.test;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Main {
    public static Stack<Integer> stack = new Stack<>();
    public static void main(String[] args){
        int[] shu = {1,2,2,4};
        f(shu,4,0);



    }

    private static void f(int[] shu, int targ, int cur) {
        if (cur == targ) {
            Integer[] a = new Integer[4];
            Integer[] integers = stack.toArray(a);
            System.out.println(Arrays.toString(integers));
            return;
        }
        for (int i = 0; i < shu.length; i++) {

            if(!stack.contains(shu[i])){
            stack.add(shu[i]);
            f(shu, targ, cur + 1);
            stack.pop();
        }

        }

    }
}
