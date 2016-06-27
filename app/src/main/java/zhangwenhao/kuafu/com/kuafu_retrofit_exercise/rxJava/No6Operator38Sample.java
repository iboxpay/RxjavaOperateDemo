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
public class No6Operator38Sample {

    private static final String TAG = "No6Operator37Sample";

    /**
     * 定期发射Observable最近发射的数据项
     * Sample操作符定时查看一个Observable，然后发射自上次采样以来它最近发射的数据。
     */
    public static void sample() {
        Observable<Long> observable = Observable.interval(1000, TimeUnit.MILLISECONDS);
   /*     observable.sample(3000, TimeUnit.MILLISECONDS).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                Log.d(TAG, "call: sample" + aLong);
            }
        });*/

        //06-27 16:53:15.975 27999-28024/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator37Sample: call: sample2
        //06-27 16:53:18.972 27999-28024/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator37Sample: call: sample4
        //06-27 16:53:21.973 27999-28024/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator37Sample: call: sample7
        //06-27 16:53:24.972 27999-28024/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator37Sample: call: sample10
        //.....

        observable.sample(Observable.interval(3000, TimeUnit.MILLISECONDS)).subscribe(
                new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Log.d(TAG, "call: observable" + aLong);
                    }
                });
        //06-27 16:57:53.390 6250-6277/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator37Sample: call: observable1
        //06-27 16:57:56.389 6250-6277/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator37Sample: call: observable4
        //06-27 16:57:59.389 6250-6277/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator37Sample: call: observable7
        //06-27 16:58:02.388 6250-6277/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator37Sample: call: observable10
        //....
    }

    /**
     * throttleFirst与throttleLast/sample不同，在每个采样周期内，它总是发射原始Observable的第一项数据，而不是最近的一项。
     */
    public static void throtteFirst() {
        Observable<Long> observable = Observable.interval(1000, TimeUnit.MILLISECONDS);
        observable.throttleFirst(3000, TimeUnit.MILLISECONDS).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                Log.d(TAG, "call: throttefirst" + aLong);
            }
        });
        /**
         * 在每个采样周期内，它总是发射原始Observable的最后一项数据
         */
        observable.throttleLast(3000, TimeUnit.MILLISECONDS).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                Log.d(TAG, "call: last_____" + aLong);
            }
        });

        //06-27 17:01:36.581 14385-14412/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator37Sample: call: last_____1
        //06-27 17:01:38.579 14385-14411/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator37Sample: call: throttefirst4
        //06-27 17:01:39.581 14385-14412/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator37Sample: call: last_____4
        //06-27 17:01:41.578 14385-14411/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator37Sample: call: throttefirst7
        //06-27 17:01:42.581 14385-14412/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator37Sample: call: last_____8
        //06-27 17:01:44.579 14385-14411/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator37Sample: call: throttefirst10
        //06-27 17:01:45.581 14385-14412/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator37Sample: call: last_____10
    }
}
