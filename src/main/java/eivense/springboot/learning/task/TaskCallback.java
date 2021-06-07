package eivense.springboot.learning.task;

import com.google.common.util.concurrent.FutureCallback;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Eivense
 * @date 2021/6/6 5:53 下午
 */
public class TaskCallback implements FutureCallback<String> {

    private final int param;

    private final ConcurrentHashMap<Integer,String> resultMap;

    public TaskCallback(int param, ConcurrentHashMap<Integer, String> resultMap) {
        this.resultMap = resultMap;
        this.param = param;
    }

    @Override
    public void onSuccess(@Nullable String result) {
        if (result != null) {
            resultMap.put(param, "success:"+result);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        resultMap.put(param,"fail:   "+t.getMessage());
    }
}
