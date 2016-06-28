package zhangwenhao.kuafu.com.kuafu_retrofit_exercise.rxJava;

import android.util.Log;

import rx.Observable;
import rx.Observer;

/**
 * Created by zhangwenhao on 2016/6/28.
 * call me
 * email:zhangwenhao@iboxpay.com
 * QQ:1160113614
 */
public class No6Operator51Retry {

    private static final String TAG = "No6Operator51Retry";

    public static void retry() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4);

        Observable<Integer> error = Observable.error(new Throwable());

        Observable<Integer> observable1 = Observable.just(5, 6, 7, 8);

        final Observable<Integer> merge = Observable.merge(observable, error, observable1);

        Observable<Integer> retry = merge.retry(/*new Func2<Integer, Throwable, Boolean>() {
            @Override
            public Boolean call(Integer integer, Throwable throwable) {
                return integer == 4 ? true : false;
            }
        }*/);

        retry.subscribe(new Observer<Integer>() {
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

        //这里会一直发射 1234  因为那个error 一直存在
        //如果那个error只出现一次  日志就会是1234  error  12345678;
        //06-28 16:00:06.465 25515-25515/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator51Retry: onNext: 1
        //06-28 16:00:06.465 25515-25515/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator51Retry: onNext: 2
        //06-28 16:00:06.465 25515-25515/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator51Retry: onNext: 3
        //06-28 16:00:06.465 25515-25515/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator51Retry: onNext: 4
        //06-28 16:00:06.465 25515-25515/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator51Retry: onNext: 1
        //06-28 16:00:06.465 25515-25515/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator51Retry: onNext: 2
        //06-28 16:00:06.465 25515-25515/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator51Retry: onNext: 3
        //06-28 16:00:06.465 25515-25515/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator51Retry: onNext: 4
        //.....

    }

}
