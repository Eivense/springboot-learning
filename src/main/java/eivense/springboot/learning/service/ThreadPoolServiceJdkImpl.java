package eivense.springboot.learning.service;

import com.google.common.util.concurrent.ListenableFuture;
import com.rabbitmq.tools.json.JSONUtil;
import eivense.springboot.learning.task.TestFutureTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * JDK原生方式
 *
 * @author Eivense
 * @date 2021/6/6 6:35 下午
 */
@Slf4j
@Service
public class ThreadPoolServiceJdkImpl {

    private final ThreadPoolExecutor executor;

    @Autowired
    public ThreadPoolServiceJdkImpl(ThreadPoolExecutor executor) {
        this.executor = executor;
    }

    public void testConcurrent() {
        List<Integer> input = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            input.add(i);
        }
        List<String> succeedList = new CopyOnWriteArrayList<>();
        List<String> failedList = new CopyOnWriteArrayList<>();

        List<CompletableFuture<Void>> completableFutureList = new ArrayList<>(10);
        for (Integer param : input) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                try {
                    TestFutureTask task=new TestFutureTask(param);
                    String result = task.call();
                    succeedList.add(result);
                } catch (Exception e) {
                    failedList.add(e.getMessage());
                }
            }, executor);
            completableFutureList.add(future);
        }

        CompletableFuture<Void> resultFuture = CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[0]));
        resultFuture.thenRun(()->{
            Map<String, List<String>> result = new HashMap<>();
            result.put("success", succeedList);
            result.put("failed", failedList);
            log.info(result.toString());
        });
    }
}
