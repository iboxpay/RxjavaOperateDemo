package zhangwenhao.kuafu.com.kuafu_retrofit_exercise.rxJava;

import android.util.Log;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by zhangwenhao on 2016/6/27.
 * call me
 * email:zhangwenhao@iboxpay.com
 * QQ:1160113614
 */
public class No6Operator39TakeLast {

    private static final String TAG = "No6Operator39TakeLast";

    /**
     * 使用TakeLast操作符修改原始Observable，你可以只发射Observable'发射的后N项数据，忽略前面的数据。
     */
    public static void takeLast() {
        Observable.just(1, 2, 3, 3, 4, 5, 6, 7).takeLast(4).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "call: takelast" + integer);
            }
        });
    }
    //06-27 17:40:34.998 5791-5791/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator39TakeLast: call: takelast4
    //06-27 17:40:34.998 5791-5791/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator39TakeLast: call: takelast5
    //06-27 17:40:34.998 5791-5791/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator39TakeLast: call: takelast6
    //06-27 17:40:34.998 5791-5791/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator39TakeLast: call: takelast7

    /**
     * 这个不会创建observable
     *
     * 有一个takeLast变体接受一个时长而不是数量参数。它会发射在原始Observable的生命周期内最后一段时间内发射的数据。时长和时间单位通过参数指定。
     *
     * 注意：这会延迟原始Observable发射的任何数据项，直到它全部完成。
     */
    public static void takeLastt() {

    }

    /**
     * 操作符takeLastBuffer，它和takeLast类似，，唯一的不同是它把所有的数据项收集到一个List再发射，而不是依次发射一个
     */
    public static void takeLastBuffer() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9).takeLastBuffer(3).subscribe(
                new Action1<List<Integer>>() {
                    @Override
                    public void call(List<Integer> integers) {
                        Log.d(TAG, "call: takelastbuffer" + integers);
                    }
                });
        //06-27 17:44:12.811 13541-13541/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator39TakeLast: call: takelastbuffer[7, 8, 9]
    }

}
