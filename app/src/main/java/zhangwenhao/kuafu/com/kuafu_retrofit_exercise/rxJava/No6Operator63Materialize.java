package zhangwenhao.kuafu.com.kuafu_retrofit_exercise.rxJava;

import android.util.Log;

import rx.Notification;
import rx.Observable;
import rx.Observer;

/**
 * Created by zhangwenhao on 2016/6/28.
 * call me
 * email:zhangwenhao@iboxpay.com
 * QQ:1160113614
 */
public class No6Operator63Materialize {

    private static final String TAG = "No6Operator6";

    public static void materialize() {

        final Observable<Integer> observable = Observable.just(1, 2, 3, 4);

        Observable<Notification<Integer>> materialize = observable.materialize();

        Observable<Integer> dematerialize = materialize.dematerialize();
        dematerialize.subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext: " + integer);
            }
        });
        //06-28 19:54:40.294 3231-3231/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator6: onNext: 1
        //06-28 19:54:40.294 3231-3231/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator6: onNext: 2
        //06-28 19:54:40.295 3231-3231/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator6: onNext: 3
        //06-28 19:54:40.295 3231-3231/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator6: onNext: 4
        //06-28 19:54:40.295 3231-3231/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator6: onCompleted:

    }
}

