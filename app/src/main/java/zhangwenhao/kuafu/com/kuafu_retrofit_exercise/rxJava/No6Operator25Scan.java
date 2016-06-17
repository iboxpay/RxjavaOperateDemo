package zhangwenhao.kuafu.com.kuafu_retrofit_exercise.rxJava;

import android.util.Log;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

/**
 * Created by zhangwenhao on 2016/6/17.
 * call me
 * email:zhangwenhao@iboxpay.com
 * QQ:1160113614
 */
public class No6Operator25Scan {

    /**
     * Scan操作符对原始Observable发射的第一项数据应用一个函数，然后将那个函数的结果作为自己的第一项数据发射。
     * 它将函数的结果同第二项数据一起填充给这个函数来产生它自己的第二项数据。它持续进行这个过程来产生剩余的数据序列。
     * 这个操作符在某些情况下被叫做accumulator。
     */
    public static void scan() {
        Observable.just(1, 2, 3, 4, 5, 6).scan(new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) {
                Log.d("No6Operator25Scan", "integer1_____" + integer + "");
                Log.d("No6Operator25Scan", "integer2_____" + integer2 + "");
                return integer + integer2;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d("No6Operator25Scan", "call" + integer + "");
            }
        });
        //06-17 18:16:28.501 12597-12597/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator25Scan: call1
        //06-17 18:16:28.501 12597-12597/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator25Scan: integer1_____1
        //06-17 18:16:28.502 12597-12597/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator25Scan: integer2_____2
        //06-17 18:16:28.502 12597-12597/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator25Scan: call3
        //06-17 18:16:28.502 12597-12597/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator25Scan: integer1_____3
        //06-17 18:16:28.502 12597-12597/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator25Scan: integer2_____3
        //06-17 18:16:28.502 12597-12597/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator25Scan: call6
        //06-17 18:16:28.502 12597-12597/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator25Scan: integer1_____6
        //06-17 18:16:28.502 12597-12597/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator25Scan: integer2_____4
        //06-17 18:16:28.502 12597-12597/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator25Scan: call10
        //06-17 18:16:28.502 12597-12597/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator25Scan: integer1_____10
        //06-17 18:16:28.502 12597-12597/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator25Scan: integer2_____5
        //06-17 18:16:28.502 12597-12597/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator25Scan: call15
        //06-17 18:16:28.502 12597-12597/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator25Scan: integer1_____15
        //06-17 18:16:28.502 12597-12597/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator25Scan: integer2_____6
        //06-17 18:16:28.502 12597-12597/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator25Scan: call21
    }

    /**
     * scan(R, new Func2)
     *
     * 你可以传递一个种子值给累加器函数的第一次调用（Observable发射的第一项数据）。
     * 如果你使用这个版本，scan将发射种子值作为自己的第一项数据。
     * TODO 注意：传递null作为种子值与不传递是不同的，null种子值是合法的
     */
    public static void scan1() {
        Observable.just(1, 2, 3, 4, 5, 6).scan(10, new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) {
                Log.d("No6Operator25Scan", "integer1_____" + integer + "");
                Log.d("No6Operator25Scan", "integer2_____" + integer2 + "");
                return integer + integer2;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d("No6Operator25Scan", "call" + integer + "");
            }
        });

        //06-17 18:19:09.885 14767-14767/? D/No6Operator25Scan: call10
        //06-17 18:19:09.885 14767-14767/? D/No6Operator25Scan: integer1_____10
        //06-17 18:19:09.885 14767-14767/? D/No6Operator25Scan: integer2_____1
        //06-17 18:19:09.885 14767-14767/? D/No6Operator25Scan: call11
        //06-17 18:19:09.885 14767-14767/? D/No6Operator25Scan: integer1_____11
        //06-17 18:19:09.885 14767-14767/? D/No6Operator25Scan: integer2_____2
        //06-17 18:19:09.885 14767-14767/? D/No6Operator25Scan: call13
        //06-17 18:19:09.885 14767-14767/? D/No6Operator25Scan: integer1_____13
        //06-17 18:19:09.885 14767-14767/? D/No6Operator25Scan: integer2_____3
        //06-17 18:19:09.885 14767-14767/? D/No6Operator25Scan: call16
        //06-17 18:19:09.885 14767-14767/? D/No6Operator25Scan: integer1_____16
        //06-17 18:19:09.885 14767-14767/? D/No6Operator25Scan: integer2_____4
        //06-17 18:19:09.885 14767-14767/? D/No6Operator25Scan: call20
        //06-17 18:19:09.885 14767-14767/? D/No6Operator25Scan: integer1_____20
        //06-17 18:19:09.885 14767-14767/? D/No6Operator25Scan: integer2_____5
        //06-17 18:19:09.886 14767-14767/? D/No6Operator25Scan: call25
        //06-17 18:19:09.886 14767-14767/? D/No6Operator25Scan: integer1_____25
        //06-17 18:19:09.886 14767-14767/? D/No6Operator25Scan: integer2_____6
        //06-17 18:19:09.886 14767-14767/? D/No6Operator25Scan: call31

    }

}
