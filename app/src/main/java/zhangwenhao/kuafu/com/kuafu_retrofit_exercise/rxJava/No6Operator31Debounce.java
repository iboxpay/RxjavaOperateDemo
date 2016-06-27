package zhangwenhao.kuafu.com.kuafu_retrofit_exercise.rxJava;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zhangwenhao on 2016/6/27.
 * call me
 * email:zhangwenhao@iboxpay.com
 * QQ:1160113614
 */
public class No6Operator31Debounce {

    private static final String TAG = "No6Operator31Debounce";

    /**
     * Debounce操作符会过滤掉发射速率过快的数据项
     * 去抖
     * timeout  是过滤掉在多少时间内的重复发射
     */
    public static void debounce() {
        Observable<Long> observable = Observable.interval(1000, TimeUnit.MILLISECONDS);
        Observable<Long> debounce = observable
                .debounce(100, TimeUnit.MILLISECONDS, Schedulers.newThread());
        debounce.subscribe(new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");

            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "onNext: " + aLong);
            }
        });
        //06-27 14:58:24.708 11262-11337/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator31Debounce: onNext: 0
        //06-27 14:58:25.706 11262-11337/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator31Debounce: onNext: 1
        //06-27 14:58:26.706 11262-11337/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator31Debounce: onNext: 2
        //06-27 14:58:27.706 11262-11337/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator31Debounce: onNext: 3
        //06-27 14:58:28.706 11262-11337/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator31Debounce: onNext: 4
        //06-27 14:58:29.706 11262-11337/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator31Debounce: onNext: 5
        //06-27 14:58:30.705 11262-11337/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator31Debounce: onNext: 6
    }

    /**
     * 同 debounce
     */
    public static void throttleWithTimeout() {
        Observable<Long> observable = Observable.interval(1000, TimeUnit.MILLISECONDS);
        Observable<Long> throttleWithTimeout = observable
                .throttleWithTimeout(500, TimeUnit.MILLISECONDS, Schedulers.newThread());
        throttleWithTimeout.subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                Log.d(TAG, "call: throttleWithTimeout" + aLong);
            }
        });
        //06-27 15:04:41.392 24857-24940/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator31Debounce: call: throttleWithTimeout0
        //06-27 15:04:42.391 24857-24940/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator31Debounce: call: throttleWithTimeout1
        //06-27 15:04:43.391 24857-24940/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator31Debounce: call: throttleWithTimeout2
        //06-27 15:04:44.391 24857-24940/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator31Debounce: call: throttleWithTimeout3
        //06-27 15:04:45.391 24857-24940/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator31Debounce: call: throttleWithTimeout4
    }

    /**
     * debounce操作符的一个变体通过对原始Observable的每一项应用一个函数进行限流，这个函数返回一个Observable。
     * 如果原始Observable在这个新生成的Observable终止之前发射了另一个数据，debounce会抑制(suppress)这个数据项
     *
     * 也就是说 原observable=每发射一个数据之前 会新生成一个observable 发射另外一个数据.
     *
     * todo 疑惑: 发射的这个另外的数据  有什么用,哪里可以接收.
     */
    public static void debounce1() {
        final Observable<Long> observable = Observable.interval(1000, TimeUnit.MILLISECONDS);
        Observable<Long> debounce = observable.debounce(new Func1<Long, Observable<String>>() {
            @Override
            public Observable<String> call(Long aLong) {
                Log.d(TAG, "call: Func" + aLong);
                return observable.just(aLong + "string");
            }
        });
        debounce.subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                Log.d(TAG, "call: debounce" + aLong);
            }
        });
    }

    //06-27 15:10:27.322 5625-5653/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator31Debounce: call: debounce0
    //06-27 15:10:28.310 5625-5653/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator31Debounce: call: Func1
    //06-27 15:10:28.310 5625-5653/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator31Debounce: call: debounce1
    //06-27 15:10:29.310 5625-5653/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator31Debounce: call: Func2
    //06-27 15:10:29.310 5625-5653/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator31Debounce: call: debounce2
    //06-27 15:10:30.310 5625-5653/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator31Debounce: call: Func3
    //06-27 15:10:30.310 5625-5653/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator31Debounce: call: debounce3

}
