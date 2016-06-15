package zhangwenhao.kuafu.com.kuafu_retrofit_exercise.rxJava;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Scheduler;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by zhangwenhao on 2016/6/14.
 * call me
 * email:zhangwenhao@iboxpay.com
 * QQ:1160113614
 *
 *
 * 如果你想给Observable操作符链添加多线程功能，你可以指定操作符（或者特定的Observable）在特定的调度器(Scheduler)上执行。
 *
 * 某些ReactiveX的Observable操作符有一些变体，它们可以接受一个Scheduler参数。这个参数指定操作符将它们的部分或全部任务放在一个特定的调度器上执行。
 *
 * 使用ObserveOn和SubscribeOn操作符，你可以让Observable在一个特定的调度器上执行，ObserveOn指示一个Observable在一个特定的调度器上调用观察者的onNext,
 * onError和onCompleted方法，SubscribeOn更进一步，它指示Observable将全部的处理过程（包括发射数据和通知）放在特定的调度器上执行。
 *
 * subscribeOn and ObserveOn 使用scheduler 这里就不在讲述了
 *
 * Schedulers.computation( )	用于计算任务，如事件循环或和回调处理，不要用于IO操作(IO操作请使用Schedulers.io())；默认线程数等于处理器的数量
 * Schedulers.from(executor)	使用指定的Executor作为调度器
 * Schedulers.immediate( )	在当前线程立即开始执行任务
 * Schedulers.io( )	        用于IO密集型任务，如异步阻塞IO操作，这个调度器的线程池会根据需要增长；对于普通的计算任务，
 * 请使用Schedulers.computation()；Schedulers.io( )默认是一个CachedThreadScheduler，很像一个有线程缓存的新线程调度器
 * Schedulers.newThread( )	为每个任务创建一个新线程
 * Schedulers.trampoline( )	当其它排队的任务完成后，在当前线程排队开始执行
 */
public class No5Schedulers {


    /**
     * 使用scheduler
     */
    public static void Schedulers1() {
        Log.d("No5Schedulers", 1 + Thread.currentThread().getName());
        Scheduler.Worker worker = Schedulers.newThread().createWorker();
        worker.schedule(new Action0() {
            @Override
            public void call() {

                Log.d("No5Schedulers", Thread.currentThread().getName());
            }
        });

        // 一段时间后在执行这个
        //worker.unsubscribe();
    }

    /**
     *
     */
    public static void Scheduler2() {
        Schedulers.newThread().createWorker().schedulePeriodically(new Action0() {
            @Override
            public void call() {
                Log.d("No5Schedulers",
                        Thread.currentThread().getName() + System.currentTimeMillis());
            }
        }, 500, 5000, TimeUnit.MILLISECONDS);
    }

}
