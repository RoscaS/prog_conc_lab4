package com.ch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Convenience class for basic tasks
 */
public class Helpers {

    /**
     * Python <b>range</b> like helper method.
     * @param start integer <b>min</b> > 0
     * @param end integer <b>max</b> > <b>min</b>
     * @return List of integers
     */
    public static List<Integer> range(int start, int end) {
        List<Integer> range = new ArrayList<>();
        IntStream.range(start, end + 1).forEach(range::add);
        return range;
    }

    /**
     * Return a random value \in [0, <b>max</b>]
     * @param max integer <b>max</b> > 0
     * @return integer
     */
    public static int randint(int max) {
        Random r = new Random();
        return r.nextInt(max + 1);
    }

    /**
     * Return a random value \in [<b>min</b>, <b>max</b>]
     * @param min integer <b>min</b> > 0
     * @param max integer <b>max</b> > <b>min</b>
     * @return integer
     */
    public static int randint(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    /**
     * Return a random value \in [<b>min</b>, <b>max</b>] excluding <b>exclude</b>
     * @param min integer <b>min</b> > 0
     * @param max integer <b>max</b> > <b>min</b>
     * @param exclude integer excluded value
     * @return integer
     */
    public static int randint(int min, int max, int exclude) {
        int value;
        do {
            value = randint(min, max);
        } while (value == exclude);
        return value;
    }
}
