package zhangwenhao.kuafu.com.kuafu_retrofit_exercise.rxJava;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by zhangwenhao on 2016/6/28.
 * call me
 * email:zhangwenhao@iboxpay.com
 * QQ:1160113614
 */
public class No6Operator44Merge {

    private static final String TAG = "No6Operator44Merge";

    public static void merge() {
        Observable<Long> observable = Observable.interval(5, 1, TimeUnit.SECONDS);
        Observable<Long> observable1 = Observable.interval(1, TimeUnit.SECONDS);
        Observable<Long> merge = Observable.merge(observable, observable1);
        merge.subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                Log.d(TAG, "call: call" + aLong);
            }
        });
        //06-28 11:30:04.827 9436-9458/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator44Merge: call: call0
        //06-28 11:30:05.827 9436-9458/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator44Merge: call: call1
        //06-28 11:30:06.828 9436-9458/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator44Merge: call: call2
        //06-28 11:30:07.828 9436-9458/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator44Merge: call: call3
        //06-28 11:30:08.827 9436-9457/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator44Merge: call: call0
        //06-28 11:30:08.827 9436-9458/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator44Merge: call: call4
        //06-28 11:30:09.826 9436-9457/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator44Merge: call: call1
        //06-28 11:30:09.827 9436-9458/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator44Merge: call: call5
        //06-28 11:30:10.826 9436-9457/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator44Merge: call: call2
        //06-28 11:30:10.828 9436-9458/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator44Merge: call: call6
        //06-28 11:30:11.826 9436-9457/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator44Merge: call: call3
        //06-28 11:30:11.827 9436-9458/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator44Merge: call: call7
        //06-28 11:30:12.827 9436-9457/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator44Merge: call: call4
        //06-28 11:30:12.827 9436-9458/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator44Merge: call: call8
        //.....
    }

    /**
     * meger(observables , int) 的使用
     *
     * 如果你传递一个发射Observables序列的Observable，你可以指定merge应该同时订阅的Observable'的最大数量
     * 。一旦达到订阅数的限制，它将不再订阅原始Observable发射的任何其它Observable，
     * 直到某个已经订阅的Observable发射了onCompleted通知。
     *
     * 同时订阅.估计要开线程去同时订阅
     * 不知道该怎么测试好...
     */
    public static void meger1() {

        Observable<Integer> observable1 = Observable.just(1, 2, 3, 4, 5);
        Observable<Integer> observable2 = Observable.just(6, 7, 8, 9, 10);
        Observable<Integer> observable3 = Observable.just(11, 12, 13, 14, 15);
        Observable<Observable<Integer>> observable = Observable
                .just(observable1, observable2, observable3);

        Observable<Integer> merge = Observable.merge(observable, 2);
        merge.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "call__1: " + integer);
            }
        });
        merge.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "call__2: " + integer);
            }
        });
        merge.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "call__3: " + integer);
            }
        });
    }
}
