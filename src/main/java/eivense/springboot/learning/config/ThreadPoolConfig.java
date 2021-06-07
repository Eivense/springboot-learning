package eivense.springboot.learning.config;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Eivense
 * @date 2021/6/6 2:21 下午
 */
@Configuration
@EnableConfigurationProperties(ThreadPoolProperties.class)
public class ThreadPoolConfig {



    @Bean
    public ThreadPoolExecutor threadPoolExecutor(ThreadPoolProperties properties) {
        // 线程名称
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat(properties.getThreadNameFormat()).build();
        return new ThreadPoolExecutor(
                properties.getCoreSize(),
                properties.getMaxSize(),
                properties.getKeepAliveTime(),
                // 超时单位
                TimeUnit.MILLISECONDS,
                // 阻塞队列
                new LinkedBlockingQueue<>(properties.getQueueSize()),
                // 线程工厂
                threadFactory,
                // 拒绝策略
                new ThreadPoolExecutor.AbortPolicy()
                );
    }


    /**
     * Guava 线程池增强
     *
     * @param threadPoolExecutor ThreadPoolExecutor
     * @return ListeningExecutorService
     */
    @Bean
    public ListeningExecutorService listeningExecutorService(ThreadPoolExecutor threadPoolExecutor) {
        return MoreExecutors.listeningDecorator(threadPoolExecutor);
    }
}
