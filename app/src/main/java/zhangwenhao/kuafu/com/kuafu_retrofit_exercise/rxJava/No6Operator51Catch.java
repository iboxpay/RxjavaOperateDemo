package zhangwenhao.kuafu.com.kuafu_retrofit_exercise.rxJava;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.functions.Func1;

/**
 * Created by zhangwenhao on 2016/6/28.
 * call me
 * email:zhangwenhao@iboxpay.com
 * QQ:1160113614
 */
public class No6Operator51Catch {

    private static final String TAG = "No6Operator51Catch";

    /**
     * RxJava将Catch实现为三个不同的操作符：
     *
     * onErrorReturn
     *
     * 让Observable遇到错误时发射一个特殊的项并且正常终止。
     *
     * onErrorResumeNext
     *
     * 让Observable在遇到错误时开始发射第二个Observable的数据序列。
     *
     * onExceptionResumeNext
     *
     * 让Observable在遇到错误时继续发射后面的数据项。
     */
    public static void rxCatch() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4);

        Observable<Integer> error = Observable.error(new Throwable());
        Observable<Integer> delay = error.delay(1000, TimeUnit.MILLISECONDS);

        Observable<Integer> merge = Observable.merge(observable, delay);

        merge.onErrorResumeNext(new Func1<Throwable, Observable<? extends Integer>>() {
            @Override
            public Observable<? extends Integer> call(Throwable throwable) {
                return Observable.just(4);
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

        //06-28 15:50:41.639 6135-6135/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator51Catch: onNext: 1
        //06-28 15:50:41.639 6135-6135/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator51Catch: onNext: 2
        //06-28 15:50:41.639 6135-6135/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator51Catch: onNext: 3
        //06-28 15:50:41.639 6135-6135/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator51Catch: onNext: 4
        //06-28 15:50:41.643 6135-6165/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator51Catch: onNext: 4
        //06-28 15:50:41.643 6135-6165/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator51Catch: onCompleted:
    }

}
