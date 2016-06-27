package zhangwenhao.kuafu.com.kuafu_retrofit_exercise.rxJava;

import rx.Observable;
import rx.functions.Func2;
import rx.joins.Pattern2;
import rx.joins.Plan0;
import rx.joins.operators.OperatorJoinPatterns;

/**
 * Created by zhangwenhao on 2016/6/27.
 * call me
 * email:zhangwenhao@iboxpay.com
 * QQ:1160113614
 */
public class No6Operator41AndThenWhen {

    public static void and() {
        Observable<Integer> lift = Observable.just(1, 2, 3, 4);
        Observable<Integer> right = Observable.just(9, 8, 7, 6);
        Pattern2<Integer, Integer> pattern2 = OperatorJoinPatterns.and(lift, right);
        Plan0<Integer> plan0 = pattern2.then(new Func2<Integer, Integer, Integer>() {

            @Override
            public Integer call(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        });


    }
}
