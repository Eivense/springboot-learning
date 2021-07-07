package eivense.springboot.learning.service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Eivense
 * @date 2021/7/5 3:15 下午
 */
public class TestSupplier {

    private static final Random random = new SecureRandom();

    private static final int total = 2000;


    public static BlockingQueue<Integer> genStidQueue(int total) {
        BlockingQueue<Integer> stidQueue = new ArrayBlockingQueue<>(total);
        for (int i = 0; i < total; i++) {
            stidQueue.add(random.nextInt());
        }
        return stidQueue;
    }
}
