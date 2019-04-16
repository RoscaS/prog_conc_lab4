package com.ch.smartBike;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Helpers {

    public static List<String> alphabet() {
        return Arrays.asList("abcdefghijklmnopqrstuvwxyz".split(""));
    }

    public static List<Integer> range(int start, int end) {
        List<Integer> range = new ArrayList<>();
        IntStream.range(start, end+1).forEach(range::add);
        return range;
    }

    public static int randint(int max) {
        Random r = new Random();
        return r.nextInt(max + 1);
    }
}
