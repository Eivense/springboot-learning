package eivense.springboot.learning.service;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import eivense.springboot.learning.task.TaskCallback;
import eivense.springboot.learning.task.TestFutureTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 使用Guava实现
 *
 * @author Eivense
 * @date 2021/6/6 3:14 下午
 */
@Slf4j
@Service
public class ThreadPoolServiceGuavaImpl {


    private final ThreadPoolExecutor executor;
    private final ListeningExecutorService listeningExecutorService;

    @Autowired

    public ThreadPoolServiceGuavaImpl(ThreadPoolExecutor executor, ListeningExecutorService listeningExecutorService) {
        this.executor = executor;
        this.listeningExecutorService = listeningExecutorService;
    }


    public void testConcurrent() {

        ConcurrentHashMap<Integer, String> resultMap = new ConcurrentHashMap<>();

        List<Integer> input = new ArrayList<>(10);
        List<ListenableFuture<String>> futureList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            input.add(i);
        }

        for (Integer param : input) {
            ListenableFuture<String> future = listeningExecutorService.submit(new TestFutureTask(param));
            Futures.addCallback(future, new TaskCallback(param, resultMap),
                    executor);
            futureList.add(future);
        }

        try {
            Futures.successfulAsList(futureList).get();
            for (Map.Entry<Integer, String> entry : resultMap.entrySet()) {
                log.info(entry.getValue());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
