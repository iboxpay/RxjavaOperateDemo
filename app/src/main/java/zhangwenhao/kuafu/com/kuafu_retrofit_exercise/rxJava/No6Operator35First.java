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
public class No6Operator35First {

    private static final String TAG = "No6Operator35First";

    public static void first() {
        Observable<Integer> observable = Observable.just(0, 1, 2, 3, 4, 5, 6, 7, 8);
 /*       observable.first().subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "call: first" + integer);
            }
        });
        //06-27 16:27:32.818 4848-4848/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator35First: call: first0*/

        observable.first(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return 0 == integer % 2 ? false : true;//第一个奇数
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "call: first" + integer);
            }
        });
        //06-27 16:30:06.727 10737-10737/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator35First: call: first1
    }

    /**
     * firstOrDefault与first类似，但是在Observagle没有发射任何数据时发射一个你在参数中指定的默认值
     *
     * todo 如果没有发射任何数据  才发射默认值
     */
    public static void firstOrDefault() {

        Observable.just(0, 1, 2, 3, 4, 5, 6, 7, 8)
                .firstOrDefault(10, new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return 0 == integer % 2 ? true : false;
                    }
                }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "call: firstorDefault" + integer);
            }
        });

        //06-27 16:33:41.674 18628-18628/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator35First: call: firstorDefault0
    }

    /**
     * takeFirst与first类似，除了这一点：
     * 如果原始Observable没有发射任何满足条件的数据，first会抛出一个NoSuchElementException，
     * takeFist会返回一个空的Observable（不调用onNext()但是会调用onCompleted）。
     */
    public static void takeFirst() {

    }

    /**
     * {@link No3Single}
     */
    public static void single(){

    }

    /**
     * 和firstOrDefault类似，但是如果原始Observable发射超过一个的数据，会以错误通知终止。
     */
    public static void singleorDefault(){

    }
}
