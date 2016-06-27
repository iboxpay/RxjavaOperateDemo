package zhangwenhao.kuafu.com.kuafu_retrofit_exercise.rxJava;

import android.util.Log;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by zhangwenhao on 2016/6/27.
 * call me
 * email:zhangwenhao@iboxpay.com
 * QQ:1160113614
 */
public class No6Operator39SkipLast {

    private static final String TAG = "No6Operator39SkipLast";

    /**
     * 使用SkipLast操作符修改原始Observable，你可以忽略Observable'发射的后N项数据，只保留前面的数据。
     */
    public static void skipLast() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8).skipLast(4).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "call: skiplast" + integer);
            }
        });
        //06-27 17:18:08.894 18551-18551/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator39SkipLast: call: skiplast1
        //06-27 17:18:08.894 18551-18551/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator39SkipLast: call: skiplast2
        //06-27 17:18:08.894 18551-18551/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator39SkipLast: call: skiplast3
        //06-27 17:18:08.894 18551-18551/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator39SkipLast: call: skiplast4
    }

    /**
     * Javadoc: skipLast(long,TimeUnit,Scheduler))
     * 这种操作不知道该怎么写这个observable
     */
    public static void skipLast1() {

    }
}
