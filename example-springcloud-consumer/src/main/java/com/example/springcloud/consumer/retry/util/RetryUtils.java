package com.example.springcloud.consumer.retry.util;

import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.google.common.base.Predicates;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * Created by shiwen on 2017/9/15.
 */
public class RetryUtils {

    private RetryUtils() {

    }

    private static Retryer<Boolean> retryer;

    //设置重试策略：
    // 1.如果是false则重试
    // 2.重试次数为2
    static {
        retryer = RetryerBuilder.<Boolean>newBuilder()
                .retryIfResult(Predicates.alwaysTrue())
                .withStopStrategy(StopStrategies.stopAfterAttempt(2))
                .build();
    }


    /**
     * 将一个方法作为参数传给另外方法
     *
     * @param call
     * @return
     */
    public static boolean retryMethod(Callable<Boolean> call) throws ExecutionException, RetryException {
        return retryer.call(call);
    }
}
