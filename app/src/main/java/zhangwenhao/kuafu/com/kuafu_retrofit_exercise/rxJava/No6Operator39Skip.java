package zhangwenhao.kuafu.com.kuafu_retrofit_exercise.rxJava;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by zhangwenhao on 2016/6/27.
 * call me
 * email:zhangwenhao@iboxpay.com
 * QQ:1160113614
 */
public class No6Operator39Skip {

    private static final String TAG = "No6Operator39Skip";

    /**
     * 抑制Observable发射的前N项数据
     */
    public static void skip() {
        Observable.just(0, 1, 2, 3, 4, 5, 6, 7, 8).skip(2).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "call: skip" + integer);
            }
        });
        //提过了 0 , 1
        //06-27 17:11:18.974 3340-3340/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator39Skip: call: skip2
        //06-27 17:11:18.974 3340-3340/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator39Skip: call: skip3
        //06-27 17:11:18.974 3340-3340/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator39Skip: call: skip4
        //06-27 17:11:18.974 3340-3340/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator39Skip: call: skip5
        //06-27 17:11:18.974 3340-3340/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator39Skip: call: skip6
        //06-27 17:11:18.974 3340-3340/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator39Skip: call: skip7
        //06-27 17:11:18.974 3340-3340/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator39Skip: call: skip8
    }

    public static void skip1() {
        Observable.interval(1000, TimeUnit.MILLISECONDS).skip(3000, TimeUnit.MILLISECONDS)
                .subscribe(
                        new Action1<Long>() {
                            @Override
                            public void call(Long aLong) {
                                Log.d(TAG, "call: ship1" + aLong);
                            }
                        });
        //它会丢弃原始Observable开始的那段时间发射的数据，时长和时间单位通过参数指定。
        //06-27 17:14:11.803 9569-9598/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator39Skip: call: ship13
        //06-27 17:14:12.803 9569-9598/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator39Skip: call: ship14
        //06-27 17:14:13.804 9569-9598/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator39Skip: call: ship15
        //06-27 17:14:14.803 9569-9598/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator39Skip: call: ship16
        //06-27 17:14:15.803 9569-9598/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator39Skip: call: ship17
        //06-27 17:14:16.804 9569-9598/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator39Skip: call: ship18

    }

}
