//package com.demo.proj.service;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//
//import java.util.concurrent.Executor;
//import java.util.concurrent.ThreadPoolExecutor;
//
//
//@Slf4j
//@Configuration
//public class AsyncService {
//    @Value("${async.executor.thread.core_pool_size}")
//    private Integer corePoolSize;
//    @Value("${async.executor.thread.max_pool_size}")
//    private Integer maxPoolSize;
//    @Value("${async.executor.thread.keep_alive_seconds}")
//    private Integer keepAliveTime;
//    @Value("${async.executor.thread.queue_capacity}")
//    private Integer queueCapacity;
//
////    @Value("${async.executor.thread.core_pool_size}")
////    private Integer awaitTerminationSeconds;
//
//    @Value("${async.executor.thread.name_prefix}")
//    private String threadNamePrefix;
//
//
//    /**
//     *  1.当池子大小小于corePoolSize，就新建线程，并处理请求
//     *  2.当池子大小等于corePoolSize，把请求放入workQueue(QueueCapacity)中，
//     *	  池子里的空闲线程就去workQueue中取任务并处理
//     *  3.当workQueue放不下任务时，就新建线程入池，并处理请求，
//     *	  如果池子大小撑到了maximumPoolSize，就用RejectedExecutionHandler来做拒绝处理
//     *  4.当池子的线程数大于corePoolSize时，多余的线程会等待keepAliveTime长时间，
//     *    如果无请求可处理就自行销毁
//     * @return
//     */
//
//    @Bean("AsyncThreadPool") // 自定线程池在容器中的实例名称
//    public Executor AsyncThreadPool() {
//
//        log.info("start asyncServiceExecutor");
//
//        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
//        // 线程池最大线程数
//        threadPoolTaskExecutor.setMaxPoolSize(maxPoolSize);
//        // 线程池核心线程数
//        threadPoolTaskExecutor.setCorePoolSize(corePoolSize);
//        // 任务队列的大小
//        threadPoolTaskExecutor.setQueueCapacity(queueCapacity);
//        // 线程池名的前缀
//        threadPoolTaskExecutor.setThreadNamePrefix(threadNamePrefix);
//        // 允许线程池的空闲时间30秒
//        threadPoolTaskExecutor.setKeepAliveSeconds(keepAliveTime);
//        // 设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean
//        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
//        // 设置线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住
////        threadPoolTaskExecutor.setAwaitTerminationSeconds(awaitTerminationSeconds);
//
//        /**
//         * 拒绝处理策略
//         * CallerRunsPolicy()：交由调用方线程运行，比如 main 线程。
//         * AbortPolicy()：直接抛出异常。
//         * DiscardPolicy()：直接丢弃。
//         * DiscardOldestPolicy()：丢弃队列中最老的任务。
//         */
//        /**
//         * 特殊说明：
//         * 1. 这里演示环境，拒绝策略咱们采用抛出异常
//         * 2.真实业务场景会把缓存队列的大小会设置大一些，
//         *   如果，提交的任务数量超过最大线程数量或将任务环缓存到本地、redis、mysql中,保证消息不丢失
//         * 3.如果项目比较大的话，异步通知种类很多的话，建议采用MQ做异步通知方案
//         */
//        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
//        // 线程初始化
//        threadPoolTaskExecutor.initialize();
//        return threadPoolTaskExecutor;
//    }
//}
