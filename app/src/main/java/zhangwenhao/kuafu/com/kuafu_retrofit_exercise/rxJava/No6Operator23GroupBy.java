package zhangwenhao.kuafu.com.kuafu_retrofit_exercise.rxJava;

import android.util.Log;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.GroupedObservable;

/**
 * Created by zhangwenhao on 2016/6/17.
 * call me
 * email:zhangwenhao@iboxpay.com
 * QQ:1160113614
 */
public class No6Operator23GroupBy {

    /**
     * groupBy(final Func1<? super T, ? extends K> keySelector)
     * func1 是为了筛选 key的
     * 哪个数据项由哪一个Observable发射是由一个函数判定的，这个函数给每一项指定一个Key，Key相同的数据会被同一个Observable发射。
     */
    public static void gropBy() {

        Observable.just(1, 2, 3, 4, 5, 6, 7, 8).groupBy(new Func1<Integer, Integer>() {
            @Override
            public Integer call(Integer integer) {
                Log.d("No6Operator23GroupBy", "_________________" + integer + "");
                return integer % 2;
            }
        }).subscribe(new Action1<GroupedObservable<Integer, Integer>>() {
            @Override
            public void call(GroupedObservable<Integer, Integer> integerIntegerGroupedObservable) {
                Log.d("No6Operator23GroupBy", integerIntegerGroupedObservable.toString() + "______"
                        + integerIntegerGroupedObservable.getKey());
            }

            //06-17 17:09:19.443 25854-25854/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator23GroupBy: rx.internal.operators.OperatorGroupBy$GroupedUnicast@423f6950______1
            //06-17 17:09:19.443 25854-25854/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator23GroupBy: rx.internal.operators.OperatorGroupBy$GroupedUnicast@423f6fe8______0
        });

    }


    /**
     * groupBy(final Func1<? super T, ? extends K> keySelector, final Func1<? super T, ? extends R>
     * elementSelector)
     */
    public static void gropBy1() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8).groupBy(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                int s = integer % 2;
                Log.d("No6Operator23GroupBy", "___________________" + integer + "");
                return s + "";
            }
        }, new Func1<Integer, Integer>() {
            @Override
            public Integer call(Integer integer) {
                Log.d("No6Operator23GroupBy", "__________element_________" + integer + 10);
                return integer + 10;
            }
        }).subscribe(new Action1<GroupedObservable<String, Integer>>() {
            @Override
            public void call(GroupedObservable<String, Integer> stringIntegerGroupedObservable) {
                Log.d("No6Operator23GroupBy", stringIntegerGroupedObservable.toString() + "_______"
                        + stringIntegerGroupedObservable.getKey());
                stringIntegerGroupedObservable.subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.d("No6Operator23GroupBy", integer + "");
                    }
                });
            }
        });

        //06-17 17:29:38.243 10389-10389/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator23GroupBy: ___________________1
        //06-17 17:29:38.244 10389-10389/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator23GroupBy: rx.internal.operators.OperatorGroupBy$GroupedUnicast@423f70e0_______1
        //06-17 17:29:38.244 10389-10389/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator23GroupBy: __________element_________110
        //06-17 17:29:38.244 10389-10389/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator23GroupBy: 11
        //06-17 17:29:38.244 10389-10389/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator23GroupBy: ___________________2
        //06-17 17:29:38.244 10389-10389/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator23GroupBy: rx.internal.operators.OperatorGroupBy$GroupedUnicast@423f7e88_______0
        //06-17 17:29:38.244 10389-10389/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator23GroupBy: __________element_________210
        //06-17 17:29:38.244 10389-10389/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator23GroupBy: 12
        //06-17 17:29:38.244 10389-10389/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator23GroupBy: ___________________3
        //06-17 17:29:38.244 10389-10389/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator23GroupBy: __________element_________310
        //06-17 17:29:38.244 10389-10389/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator23GroupBy: 13
        //06-17 17:29:38.244 10389-10389/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator23GroupBy: ___________________4
        //06-17 17:29:38.245 10389-10389/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator23GroupBy: __________element_________410
        //06-17 17:29:38.245 10389-10389/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator23GroupBy: 14
        //06-17 17:29:38.245 10389-10389/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator23GroupBy: ___________________5
        //06-17 17:29:38.245 10389-10389/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator23GroupBy: __________element_________510
        //06-17 17:29:38.245 10389-10389/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator23GroupBy: 15
        //06-17 17:29:38.245 10389-10389/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator23GroupBy: ___________________6
        //06-17 17:29:38.245 10389-10389/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator23GroupBy: __________element_________610
        //06-17 17:29:38.245 10389-10389/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator23GroupBy: 16
        //06-17 17:29:38.245 10389-10389/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator23GroupBy: ___________________7
        //06-17 17:29:38.245 10389-10389/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator23GroupBy: __________element_________710
        //06-17 17:29:38.245 10389-10389/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator23GroupBy: 17
        //06-17 17:29:38.245 10389-10389/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator23GroupBy: ___________________8
        //06-17 17:29:38.245 10389-10389/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator23GroupBy: __________element_________810
        //06-17 17:29:38.245 10389-10389/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator23GroupBy: 18

    }

}
