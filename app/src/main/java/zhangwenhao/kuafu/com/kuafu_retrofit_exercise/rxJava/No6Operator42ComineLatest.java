package zhangwenhao.kuafu.com.kuafu_retrofit_exercise.rxJava;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

/**
 * Created by zhangwenhao on 2016/6/28.
 * call me
 * email:zhangwenhao@iboxpay.com
 * QQ:1160113614
 */
public class No6Operator42ComineLatest {

    private static final String TAG = "No6Operator42ComineLatest";

    /**
     *
     */
    public static void CombineLatest() {

        Observable<Long> observable = Observable.interval(1, TimeUnit.SECONDS);
        Observable<Long> observable1 = Observable.interval(2, TimeUnit.SECONDS);
        Observable<Long> observable2 = Observable
                .combineLatest(observable, observable1, new Func2<Long, Long, Long>() {
                    @Override
                    public Long call(Long aLong, Long aLong2) {
                        Log.d("No6Operator4", "along: " + aLong + "_____" + aLong2);
                        return aLong + aLong2;
                    }
                });
        observable2.subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                Log.d("No6Operator4", "call: " + aLong);
            }
        });
        //06-28 12:38:34.294 4979-5013/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator4: along: 0_____0
        //06-28 12:38:34.294 4979-5013/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator4: call: 0
        //06-28 12:38:34.295 4979-5012/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator4: along: 1_____0
        //06-28 12:38:34.295 4979-5012/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator4: call: 1
        //06-28 12:38:35.293 4979-5012/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator4: along: 2_____0
        //06-28 12:38:35.293 4979-5012/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator4: call: 2
        //06-28 12:38:36.293 4979-5012/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator4: along: 3_____0
        //06-28 12:38:36.293 4979-5012/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator4: call: 3
        //06-28 12:38:36.294 4979-5013/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator4: along: 3_____1
        //06-28 12:38:36.294 4979-5013/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator4: call: 4
        //06-28 12:38:37.294 4979-5012/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator4: along: 4_____1
        //06-28 12:38:37.294 4979-5012/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator4: call: 5
        //06-28 12:38:38.293 4979-5013/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator4: along: 4_____2
        //06-28 12:38:38.293 4979-5013/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator4: call: 6
        //06-28 12:38:38.294 4979-5012/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator4: along: 5_____2
        //06-28 12:38:38.294 4979-5012/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator4: call: 7
        //06-28 12:38:39.293 4979-5012/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator4: along: 6_____2

    }

    /**
     * withLatestFrom 还在开发中
     * 每次发射的数据与前面最后发生的数据进行合并
     */
    public static void withLatestFrom() {

    }


}
