package zhangwenhao.kuafu.com.kuafu_retrofit_exercise.rxJava;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by zhangwenhao on 2016/6/28.
 * call me
 * email:zhangwenhao@iboxpay.com
 * QQ:1160113614
 */
public class No6Operator43Join {

    private static final String TAG = "No6Operator43Join";

    /**
     * join  和  groupJoin()  暂时还不知道使用场景
     */
    public static void join() {
        Observable<Long> observable = Observable.interval(100, TimeUnit.MILLISECONDS);
        Observable<Long> right = Observable.interval(200, TimeUnit.MILLISECONDS);

        observable.join(right, new Func1<Long, Observable<String>>() {
            @Override
            public Observable<String> call(Long aLong) {
                Log.d("No6Operator4leftfunc", "call: left__" + aLong);
                return Observable.just(aLong + "left_string");
            }
        }, new Func1<Long, Observable<String>>() {
            @Override
            public Observable<String> call(Long aLong) {
                Log.d("No6Operator4rightfunc", "call: right__" + aLong);
                return Observable.just(aLong + "right_string");
            }
        }, new Func2<Long, Long, String>() {
            @Override
            public String call(Long aLong, Long aLong2) {
                Log.d("No6Operator4func2", "call: func2__" + aLong + "___" + aLong2);
                String temp = aLong + "" + aLong2;
                return temp;
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, "call: _____" + s);
            }
        });

        //observable.groupJoin()
    }
}
