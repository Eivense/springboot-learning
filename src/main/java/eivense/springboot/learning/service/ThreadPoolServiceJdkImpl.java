package eivense.springboot.learning.service;

import com.google.common.util.concurrent.ListenableFuture;
import eivense.springboot.learning.task.TestFutureTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
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

        List<CompletableFuture<String>> completableFutureList = new ArrayList<>(10);
        for (Integer param : input) {
            CompletableFuture<String> future = CompletableFuture.supplyAsync(()->{
                try {
                    return new TestFutureTask(param).call();
                } catch (Exception e) {
                    throw new RuntimeException();
                }
            },executor);
            completableFutureList.add(future);
        }
        CompletableFuture<Void> resultFuture = CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[0]));

        resultFuture.thenApply(e -> completableFutureList.stream().map(CompletableFuture::join).collect(Collectors.toList())).join();

    }
}
