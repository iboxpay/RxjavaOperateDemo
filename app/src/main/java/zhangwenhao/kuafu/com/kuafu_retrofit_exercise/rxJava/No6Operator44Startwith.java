package zhangwenhao.kuafu.com.kuafu_retrofit_exercise.rxJava;

import android.util.Log;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by zhangwenhao on 2016/6/28.
 * call me
 * email:zhangwenhao@iboxpay.com
 * QQ:1160113614
 */
public class No6Operator44Startwith {

    private static final String TAG = "No6Operator44Startwith";

    /**
     * 如果你想要一个Observable在发射数据之前先发射一个指定的数据序列，可以使用StartWith操作符。
     * （如果你想一个Observable发射的数据末尾追加一个数据序列可以使用Concat操作符。）
     */
    public static void startWith() {
        Observable<Integer> just = Observable.just(1, 2, 3, 4);

        Observable<Integer> observable = Observable.just(5, 6, 7, 8);

        //Observable<Integer> startWith = observable.startWith(1,2,3,4);
        Observable<Integer> startWith = observable.startWith(just);

        startWith.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "call: " + integer);
            }
        });
        //06-28 14:27:58.930 24390-24390/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator44Startwith: call: 1
        //06-28 14:27:58.930 24390-24390/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator44Startwith: call: 2
        //06-28 14:27:58.931 24390-24390/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator44Startwith: call: 3
        //06-28 14:27:58.931 24390-24390/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator44Startwith: call: 4
        //06-28 14:27:58.931 24390-24390/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator44Startwith: call: 5
        //06-28 14:27:58.931 24390-24390/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator44Startwith: call: 6
        //06-28 14:27:58.931 24390-24390/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator44Startwith: call: 7
        //06-28 14:27:58.931 24390-24390/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator44Startwith: call: 8
    }

}
