package eivense.springboot.learning.task;

import com.google.common.util.concurrent.FutureCallback;
import lombok.extern.log4j.Log4j2;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.List;
import java.util.Map;

/**
 * @author Eivense
 * @date 2021/7/7 11:10 上午
 */
@Log4j2
public class TestConsumerCallback implements FutureCallback<Map<String, List<Integer>>> {

    private String workerName;

    public TestConsumerCallback(String workerName) {
        this.workerName = workerName;
    }


    @Override
    public void onSuccess(@Nullable Map<String,List<Integer>>  result) {
        List<Integer> succeed = result.get("succeed");
        List<Integer> failed = result.get("failed");
        log.info("Workder:{},succeed:{},failed:{}", workerName,succeed.size(),failed.size());
    }

    @Override
    public void onFailure(Throwable t) {

    }
}
