package zhangwenhao.kuafu.com.kuafu_retrofit_exercise.rxJava;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

/**
 * Created by zhangwenhao on 2016/6/27.
 * call me
 * email:zhangwenhao@iboxpay.com
 * QQ:1160113614
 */
public class No6Operator39Take {

    private static final String TAG = "No6Operator39Take";

    /**
     * 使用Take操作符让你可以修改Observable的行为，只返回前面的N项数据，然后发射完成通知，忽略剩余的数据
     */
    public static void take() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8).take(2).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "call: take" + integer);
            }
        });
        //06-27 17:29:35.432 13154-13154/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator39Take: call: take1
        //06-27 17:29:35.432 13154-13154/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator39Take: call: take2
    }

    /**
     * take的这个变体接受一个时长而不是数量参数。它会丢发射Observable开始的那段时间发射的数据，时长和时间单位通过参数指定。
     *
     * 只发射 时间范围内的 数据
     */
    public static void take1() {
        Log.d(TAG, "take1: take" + "start");
        Observable.interval(1000, TimeUnit.MILLISECONDS).take(5000, TimeUnit.MILLISECONDS)
                .subscribe(
                        new Observer<Long>() {
                            @Override
                            public void onCompleted() {
                                Log.d(TAG, "onCompleted: ");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d(TAG, "onError: ");
                            }

                            @Override
                            public void onNext(Long integer) {
                                Log.d(TAG, "onNext: observer" + integer);
                            }
                        });
        //06-27 17:36:53.069 29958-29958/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator39Take: take1: takestart
        //06-27 17:36:54.081 29958-29986/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator39Take: onNext: observer0
        //06-27 17:36:55.080 29958-29986/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator39Take: onNext: observer1
        //06-27 17:36:56.081 29958-29986/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator39Take: onNext: observer2
        //06-27 17:36:57.080 29958-29986/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator39Take: onNext: observer3
        //06-27 17:36:58.080 29958-29985/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator39Take: onCompleted:
    }

}
