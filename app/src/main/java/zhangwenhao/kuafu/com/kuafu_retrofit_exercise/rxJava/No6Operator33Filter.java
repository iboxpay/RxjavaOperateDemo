package zhangwenhao.kuafu.com.kuafu_retrofit_exercise.rxJava;

import android.util.Log;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by zhangwenhao on 2016/6/27.
 * call me
 * email:zhangwenhao@iboxpay.com
 * QQ:1160113614
 */
public class No6Operator33Filter {

    private static final String TAG = "No6Operator33Filter";

    public static void filter() {
        Observable<Integer> observable = Observable.just(0, 1, 2, 3, 4, 5, 6, 7, 8);
        observable.filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return 0 == integer % 2 ? true : false;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "call: func" + integer);
            }
        });

        //Filter操作符使用你指定的一个谓词函数测试数据项，只有通过测试的数据才会被发射
        //06-27 16:19:47.959 20493-20493/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator33Filter: call: func0
        //06-27 16:19:47.959 20493-20493/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator33Filter: call: func2
        //06-27 16:19:47.959 20493-20493/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator33Filter: call: func4
        //06-27 16:19:47.959 20493-20493/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator33Filter: call: func6
        //06-27 16:19:47.959 20493-20493/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator33Filter: call: func8
    }

    public static void ofType() {
        Observable<Integer> observable = Observable.just(0, 1, 2, 3, 4, 5, 6);
        observable.ofType(Object.class).subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                Log.d(TAG, "call: oftype" + o.toString());
            }
        });
        //06-27 16:23:22.136 28040-28040/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator33Filter: call: oftype0
        //06-27 16:23:22.136 28040-28040/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator33Filter: call: oftype1
        //06-27 16:23:22.136 28040-28040/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator33Filter: call: oftype2
        //06-27 16:23:22.136 28040-28040/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator33Filter: call: oftype3
        //06-27 16:23:22.136 28040-28040/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator33Filter: call: oftype4
        //06-27 16:23:22.136 28040-28040/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator33Filter: call: oftype5
        //06-27 16:23:22.136 28040-28040/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator33Filter: call: oftype6
    }
}
