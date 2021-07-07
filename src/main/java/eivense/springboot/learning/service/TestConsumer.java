package eivense.springboot.learning.service;

import lombok.extern.log4j.Log4j2;

import java.security.SecureRandom;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

/**
 * @author Eivense
 * @date 2021/7/5 4:00 下午
 */
@Log4j2
public class TestConsumer implements Callable<Map<String,List<Integer>> > {

    private final BlockingQueue<Integer> queue;
    private final Random random = new SecureRandom();
    private String workerName;

    public TestConsumer(BlockingQueue<Integer> queue, String workerName) {
        this.queue = queue;
        this.workerName = workerName;
    }


    @Override
    public Map<String,List<Integer>> call() throws Exception {
        log.info("worker:{} start",workerName);
        Map<String, List<Integer>> resultMap = new HashMap<>(2);
        List<Integer> succeedStid = new ArrayList<>();
        List<Integer> failedStid = new ArrayList<>();
        // 队列不为空则一直进行
        while(!queue.isEmpty()) {
            try {
                // 从队列中取一个
                Integer stid = queue.take();
                log.info("Worker:{},Stid:{}",workerName, stid);
                // 模拟业务
                int randomInt = random.nextInt(10000);
                Thread.sleep(randomInt);
                // 模拟失败业务
                if (randomInt % 7 == 0) {
                    failedStid.add(stid);
                }else{
                    succeedStid.add(stid);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        resultMap.put("succeed", succeedStid);
        resultMap.put("failed", failedStid);
        return resultMap;
    }

}
