package eivense.springboot.learning.service;

import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import eivense.springboot.learning.task.TestConsumerCallback;
import lombok.extern.log4j.Log4j2;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Eivense
 * @date 2021/7/3 3:37 下午
 */
@Log4j2
@Component
public class TestConcurrentService {

    private final ThreadPoolExecutor executor;
    private final ListeningExecutorService listeningExecutorService;

    @Autowired
    public TestConcurrentService(ThreadPoolExecutor executor, ListeningExecutorService listeningExecutorService) {
        this.executor = executor;
        this.listeningExecutorService = listeningExecutorService;
    }


    public void prcoess() {
        log.info("Max pool size"+executor.getMaximumPoolSize());
        BlockingQueue<Integer> stidQueue = TestSupplier.genStidQueue(500);
        List<ListenableFuture<Map<String,List<Integer>>>> futureList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            String workerName = "worker" + i;
            ListenableFuture<Map<String,List<Integer>>> future = listeningExecutorService.submit(new TestConsumer(stidQueue, "worker"+i));
            Futures.addCallback(future, new TestConsumerCallback(workerName),
                    executor);
            futureList.add(future);
        }

        try {
            Stopwatch stopwatch = Stopwatch.createStarted();
            Futures.successfulAsList(futureList).get();
            Duration duration = stopwatch.elapsed();
            log.info("Duration:"+duration.getSeconds());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }



}
