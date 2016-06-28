package zhangwenhao.kuafu.com.kuafu_retrofit_exercise.rxJava;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;

/**
 * Created by zhangwenhao on 2016/6/28.
 * call me
 * email:zhangwenhao@iboxpay.com
 * QQ:1160113614
 */
public class No6Operator61Delay {

    private static final String TAG = "No6Operator61Delay";

    public static void delay() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4);
        Log.d(TAG, "delay: ");
        observable.delay(3, TimeUnit.SECONDS).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "call: " + integer);
            }
        });
        //主要是看时间
        //06-28 16:45:55.719 12597-12597/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Delay: delay:
        //06-28 16:45:58.727 12597-12625/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Delay: call: 1
        //06-28 16:45:58.727 12597-12625/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Delay: call: 2
        //06-28 16:45:58.727 12597-12625/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Delay: call: 3
        //06-28 16:45:58.727 12597-12625/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Delay: call: 4
    }

    /**
     * delay(
     * Func0<? extends Observable<U>> subscriptionDelay,Func1<? super T, ? extends Observable<V>> itemDelay)
     */
    public static void delay1() {

        Observable<Integer> just = Observable.just(1, 2, 3, 4);
        Log.d(TAG, "call: _____");
        just.delay(new Func0<Observable<Integer>>() {
            @Override
            public Observable<Integer> call() {
                Log.d(TAG, "call: funco");
                return Observable.just(0).delay(3, TimeUnit.SECONDS);
            }
        }, new Func1<Integer, Observable<Integer>>() {
            @Override
            public Observable<Integer> call(Integer integer) {
                Log.d(TAG, "call: func1" + integer);
                Observable<Integer> temp;
                switch (integer) {
                    case 1:
                        temp = Observable.just(integer).delay(4, TimeUnit.SECONDS);
                        break;
                    case 2:
                        temp = Observable.just(integer).delay(2, TimeUnit.SECONDS);
                        break;
                    default:
                        temp = Observable.just(integer).delay(1, TimeUnit.SECONDS);
                }
                return temp;
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext: " + integer);
            }
        });

        //主要是看时间
        // func0 订阅延时  3秒  1--->2的时间
        // func1 每个元素延时控制  具体看 6789
        //06-28 17:02:12.594 25187-25187/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Delay: call: _____      |
        //06-28 17:02:12.600 25187-25187/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Delay: call: funco      |    1
        //06-28 17:02:15.609 25187-25218/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Delay: call: func11     |    2
        //06-28 17:02:15.618 25187-25218/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Delay: call: func12     |    3
        //06-28 17:02:15.618 25187-25218/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Delay: call: func13     |    4
        //06-28 17:02:15.618 25187-25218/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Delay: call: func14     |    5
        //06-28 17:02:16.618 25187-25326/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Delay: onNext: 3        |    6
        //06-28 17:02:16.618 25187-25327/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Delay: onNext: 4        |    7
        //06-28 17:02:17.618 25187-25218/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Delay: onNext: 2        |    8
        //06-28 17:02:19.620 25187-25328/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Delay: onNext: 1        |    9
        //06-28 17:02:19.621 25187-25328/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Delay: onCompleted      |
    }


}
