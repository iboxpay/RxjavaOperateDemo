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
public class No6Operator32Distinct {

    private static final String TAG = "No6Operator32Distinct";

    public static void distinct() {
     /*   Observable<Integer> observable = Observable.just(1, 2, 3, 3, 1, 4, 5, 6);
        observable.distinct().subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "call: distinct" + integer);
            }
        });*/
        //去掉重复的
        //06-27 15:20:43.356 17152-17152/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: distinct1
        //06-27 15:20:43.356 17152-17152/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: distinct2
        //06-27 15:20:43.356 17152-17152/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: distinct3
        //06-27 15:20:43.356 17152-17152/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: distinct4
        //06-27 15:20:43.356 17152-17152/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: distinct5
        //06-27 15:20:43.356 17152-17152/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: distinct6

        Observable<Integer> observable = Observable.just(1, 1, 1, 3, 3, 3, 4, 4, 4, 5);
        observable.distinct(new Func1<Integer, Integer>() {
            @Override
            public Integer call(Integer integer) {
                Log.d(TAG, "call: func" + integer);
                return integer / 2;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "call: " + integer);
            }
        });
        //按照func 分组过滤
        //06-27 15:27:32.268 24933-24933/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: 1
        //06-27 15:27:32.268 24933-24933/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: func1
        //06-27 15:27:32.268 24933-24933/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: func1
        //06-27 15:27:32.268 24933-24933/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: func3
        //06-27 15:27:32.268 24933-24933/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: 3
        //06-27 15:27:32.268 24933-24933/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: func3
        //06-27 15:27:32.268 24933-24933/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: func3
        //06-27 15:27:32.268 24933-24933/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: func4
        //06-27 15:27:32.268 24933-24933/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: 4
        //06-27 15:27:32.268 24933-24933/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: func4
        //06-27 15:27:32.268 24933-24933/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: func4
        //06-27 15:27:32.268 24933-24933/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: func5

    }

    public static void distinctUntilChanged() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 4, 5, 3, 6, 6, 7);

        observable.distinctUntilChanged(new Func1<Integer, Integer>() {
            @Override
            public Integer call(Integer integer) {
                Log.d(TAG, "call: func" + integer);
                int i = integer % 2;
                Log.d(TAG, "call: func___" + i);
                return i;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "call: " + integer);
            }
        });
        //很鸡肋的东东  感觉  不就是个去除重复吗  分组又没效果
        //06-27 15:41:07.746 7368-7368/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: func1
        //06-27 15:41:07.746 7368-7368/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: func___1
        //06-27 15:41:07.746 7368-7368/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: 1
        //06-27 15:41:07.746 7368-7368/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: func2
        //06-27 15:41:07.746 7368-7368/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: func___0
        //06-27 15:41:07.746 7368-7368/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: 2
        //06-27 15:41:07.746 7368-7368/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: func3
        //06-27 15:41:07.746 7368-7368/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: func___1
        //06-27 15:41:07.746 7368-7368/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: 3
        //06-27 15:41:07.746 7368-7368/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: func4
        //06-27 15:41:07.747 7368-7368/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: func___0
        //06-27 15:41:07.747 7368-7368/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: 4
        //06-27 15:41:07.747 7368-7368/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: func4
        //06-27 15:41:07.747 7368-7368/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: func___0
        //06-27 15:41:07.747 7368-7368/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: func5
        //06-27 15:41:07.747 7368-7368/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: func___1
        //06-27 15:41:07.747 7368-7368/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: 5
        //06-27 15:41:07.747 7368-7368/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: func3
        //06-27 15:41:07.747 7368-7368/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: func___1
        //06-27 15:41:07.747 7368-7368/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: func6
        //06-27 15:41:07.747 7368-7368/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: func___0
        //06-27 15:41:07.747 7368-7368/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: 6
        //06-27 15:41:07.747 7368-7368/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: func6
        //06-27 15:41:07.747 7368-7368/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: func___0
        //06-27 15:41:07.747 7368-7368/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: func7
        //06-27 15:41:07.747 7368-7368/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: func___1
        //06-27 15:41:07.747 7368-7368/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator32Distinct: call: 7
    }


}
