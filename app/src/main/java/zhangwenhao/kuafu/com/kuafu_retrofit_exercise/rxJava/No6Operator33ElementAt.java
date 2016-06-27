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
public class No6Operator33ElementAt {

    private static final String TAG = "No6Operator32ElementAt";

    public static void elementAt() {
        Observable<Integer> observable = Observable.just(0, 1, 2, 3, 4, 5, 6, 6, 7, 8);

        /**
         * RxJava将这个操作符实现为elementAt，给它传递一个基于0的索引值，它会发射原始Observable数据序列对应索引位置的值，\
         * 如果你传递给elementAt的值为5，那么它会发射第六项的数据。
         *
         * 如果你传递的是一个负数，或者原始Observable的数据项数小于index+1，将会抛出一个IndexOutOfBoundsException异常。
         */
        /*observable.elementAt(2).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "call: " + integer);
            }
        });
        //06-27 16:07:14.481 25388-25388/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32ElementAt: call: 2
        */
        /**
         * RxJava还实现了elementAtOrDefault操作符。与elementAt的区别是，
         * 如果索引值大于数据项数，它会发射一个默认值（通过额外的参数指定），
         * 而不是抛出异常。但是如果你传递一个负数索引值，它仍然会抛出一个IndexOutOfBoundsException异常。
         *
         * 发射完默认值 就结束了
         *
         *
         */
        observable.elementAtOrDefault(2, 10).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "call: elementAtOrDefault" + integer);
            }
        });
        // TODO: 2016/6/27  为什么发射的不是 10 ???????
        //06-27 16:09:12.655 29694-29694/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32ElementAt: call: elementAtOrDefault2
    }

}
