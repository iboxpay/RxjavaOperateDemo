package zhangwenhao.kuafu.com.kuafu_retrofit_exercise.rxJava;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;
import rx.joins.Pattern2;
import rx.joins.Plan0;
import rx.joins.operators.OperatorJoinPatterns;
import rx.observables.JoinObservable;

/**
 * Created by zhangwenhao on 2016/6/27.
 * call me
 * email:zhangwenhao@iboxpay.com
 * QQ:1160113614
 */
public class No6Operator41AndThenWhen {

    private static final String TAG = "No6Operator41AndThenWhen";

    /**
     * 这里主要是JoinObservable 的使用
     *
     * 1:使用JoinObservable类将普通的observable转成JoinObservable对象
     * 2:再对这个JoinObservable对象执行and(普通的observa)操作.返回的是Pattern* 对象
     * 3:对这个pattern对象做then( func )操作,返回的是Plan* 对象,这里的这func是{合并的规则}
     * 4:再使用JoinObservable类使用when( plan )将plan*转成JoinObservable对象
     * 5:最好在从joinObservable的到普通的observable
     */
    public static void and() {
        Observable<Integer> lift = Observable.just(1, 2, 3, 4);
        Observable<Integer> right = Observable.just(9, 8, 7, 6);
      /*    Pattern2<Integer, Integer> pattern2 = OperatorJoinPatterns.and(lift, right);
        Plan0<Integer> plan0 = pattern2.then(new Func2<Integer, Integer, Integer>() {

            @Override
            public Integer call(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        });*/
        Observable<Long> tictoc = Observable.interval(1000, TimeUnit.MILLISECONDS);

        JoinObservable<Integer> from = JoinObservable.from(lift);
        Pattern2<Integer, Integer> and = from.and(right);
        Plan0<Integer> plan0 = and.then(new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        });
        JoinObservable<Integer> when = JoinObservable.when(plan0);
        Observable<Integer> observable = when.toObservable();
        observable.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d("No6Operator", "func" + integer);
            }
        });
        //06-28 09:25:57.582 5566-5566/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator: func10
        //06-28 09:25:57.583 5566-5566/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator: func10
        //06-28 09:25:57.583 5566-5566/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator: func10
        //06-28 09:25:57.583 5566-5566/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator: func10

    }

    /**
     * OperatorJoinPatterns的使用
     * and()  then()  已经了解
     * // TODO: 2016/6/28  OperatorJoinPatterns.when() 用时什么场景还不清楚
     */
    public static void and1() {
        Observable<Integer> lift = Observable.just(1, 2, 3, 4);
        Observable<Integer> right = Observable.just(9, 8, 7, 6);

        Pattern2<Integer, Integer> pattern2 = OperatorJoinPatterns.and(lift, right);
        Plan0<Integer> plan0 = pattern2.then(new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        });
        JoinObservable<Integer> joinObservable = JoinObservable.when(plan0,plan0);
        Observable<Integer> observable = joinObservable.toObservable();
        observable.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d("No6Operator", "call: " + integer);
            }
        });

        //06-28 09:40:43.257 13074-13074/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator: call: 10
        //06-28 09:40:43.257 13074-13074/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator: call: 10
        //06-28 09:40:43.257 13074-13074/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator: call: 10
        //06-28 09:40:43.257 13074-13074/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator: call: 10
    }
}
