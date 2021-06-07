package eivense.springboot.learning.task;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Eivense
 * @date 2021/6/6 4:39 下午
 */
@Slf4j
public class TestFutureTask implements Callable<String> {

    private int param;

    public TestFutureTask(int param) {
        this.param = param;
    }

    @Override
    public String call() throws Exception {
        String threadName = Thread.currentThread().getName();
        int random = ThreadLocalRandom.current().nextInt(5);
        if (random % 2 == 0) {
            throw new RuntimeException(threadName + "----param:"+param+",random:" + random);
        }
        Thread.sleep(random*1000);
        return threadName +
                "----param:" +
                param +
                ",random:"+
                random;
    }
}
