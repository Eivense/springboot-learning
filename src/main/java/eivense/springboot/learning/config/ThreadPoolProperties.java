package eivense.springboot.learning.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 线程池配置类
 *
 * @author Eivense
 * @date 2021/6/6 2:41 下午
 */
@Data
@ConfigurationProperties(prefix = ThreadPoolProperties.PREFIX)
public class ThreadPoolProperties {

    public static final String PREFIX="thread-pool";

    /**
     * 线程池核心线程数 没有任务时也会启用这些线程 默认为处理器个数
     *
     * http://ifeve.com/how-to-calculate-threadpool-size/
     *
     *
     */
    private int coreSize=Runtime.getRuntime().availableProcessors();

    /**
     * 最大线程数
     */
    private int maxSize;

    /**
     * 阻塞队列大小
     */
    private int queueSize;

    /**
     * 线程超时时间
     */
    private int keepAliveTime;

    /**
     * 线程名称格式
     */
    private String threadNameFormat = "thread-%d";


}
