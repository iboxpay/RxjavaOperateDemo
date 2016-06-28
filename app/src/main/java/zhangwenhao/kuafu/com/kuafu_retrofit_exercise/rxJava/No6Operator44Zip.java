package zhangwenhao.kuafu.com.kuafu_retrofit_exercise.rxJava;

import android.util.Log;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

/**
 * Created by zhangwenhao on 2016/6/28.
 * call me
 * email:zhangwenhao@iboxpay.com
 * QQ:1160113614
 */
public class No6Operator44Zip {

    private static final String TAG = "No6Operator44Zip";

    /**
     * Zip操作符返回一个Obversable，它使用这个函数按顺序结合两个或多个Observables发射的数据项，然后它发射这个函数返回的结果。
     * 它按照严格的顺序应用这个函数。它只发射与发射数据项最少的那个Observable一样多的数据。
     */
    public static void zip() {
        Observable<Integer> just = Observable.just(1, 2, 3, 4);
        Observable<Integer> just1 = Observable.just(9, 8, 7, 6, 5, 4);

        Observable.zip(just1, just, new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "call: " + integer);
            }
        });
        //06-28 14:37:18.511 18484-18484/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator44Zip: call: 10
        //06-28 14:37:18.511 18484-18484/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator44Zip: call: 10
        //06-28 14:37:18.511 18484-18484/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator44Zip: call: 10
        //06-28 14:37:18.511 18484-18484/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator44Zip: call: 10
    }

    public static void zipwith() {
        Observable<Integer> just = Observable.just(1, 2, 3, 4);
        Observable<Integer> just1 = Observable.just(5, 5, 5);
        just.zipWith(just1, new Func2<Integer, Integer, String>() {
            @Override
            public String call(Integer integer, Integer integer2) {
                String temp = integer + "____" + integer2;
                return temp;
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, "call: ____" + s);
            }
        });
        //06-28 14:51:18.534 25836-25836/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator44Zip: call: ____1____5
        //06-28 14:51:18.534 25836-25836/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator44Zip: call: ____2____5
        //06-28 14:51:18.534 25836-25836/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator44Zip: call: ____3____5

    }

}
