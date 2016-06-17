package zhangwenhao.kuafu.com.kuafu_retrofit_exercise.rxJava;

import android.util.Log;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.StringObservable;

/**
 * Created by zhangwenhao on 2016/6/17.
 * call me
 * email:zhangwenhao@iboxpay.com
 * QQ:1160113614
 */
public class No6Operator24Map {


    /**
     * Map操作符对原始Observable发射的每一项数据应用一个你选择的函数，然后返回一个发射这些结果的Observable。
     */
    public static void map() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8).map(new Func1<Integer, Integer>() {
            @Override
            public Integer call(Integer integer) {

                return integer + 100;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integr) {
                Log.d("No6Operator24Map", integr + "");
            }
        });
        //06-17 17:49:15.704 22332-22332/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator24Map: 101
        //06-17 17:49:15.704 22332-22332/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator24Map: 102
        //06-17 17:49:15.704 22332-22332/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator24Map: 103
        //06-17 17:49:15.704 22332-22332/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator24Map: 104
        //06-17 17:49:15.704 22332-22332/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator24Map: 105
        //06-17 17:49:15.704 22332-22332/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator24Map: 106
        //06-17 17:49:15.704 22332-22332/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator24Map: 107
        //06-17 17:49:15.704 22332-22332/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator24Map: 108
    }


    /**
     * cast操作符将原始Observable发射的每一项数据都强制转换为一个指定的类型，然后再发射数据，它是map的一个特殊版本。
     */
    public static void cast() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8).cast(String.class).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d("No6Operator24Map", s);
            }
        });
    }


    /**
     * encode在StringObservable类中，不是标准RxJava的一部分，它也是一个特殊的map操作符。
     * encode将一个发射字符串的Observable变换为一个发射字节数组（这个字节数组按照原始字符串中的多字节字符边界划分）的Observable。
     */
    public static void encode() {
        StringObservable.encode(Observable.just("ABCD", "abcd", "1234"), "UTF8").subscribe(
                new Action1<byte[]>() {
                    @Override
                    public void call(byte[] bytes) {
                        Log.d("No6Operator24Map", bytes.toString() + "_____" + new String(bytes));
                    }
                });
        //06-17 17:58:01.844 29856-29856/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator24Map: [B@423daf70_____ABCD
        //06-17 17:58:01.844 29856-29856/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator24Map: [B@423db308_____abcd
        //06-17 17:58:01.844 29856-29856/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator24Map: [B@423db578_____1234
    }

    /**
     * byLine同样在StringObservable类中，也不是标准RxJava的一部分，它也是一个特殊的map操作符。
     * byLine将一个发射字符串的Observable变换为一个按行发射来自原始Observable的字符串的Observable。
     *
     * todo  注意是按照换行符 来判断行的!!!
     */
    public static void byLine() {
        StringObservable.byLine(Observable.just("ABCD\n", "abcd\n", "123\n4")).subscribe(
                new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.d("No6Operator24Map", s);
                    }
                });
        //06-17 18:03:58.287 3289-3289/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator24Map: ABCD
        //06-17 18:03:58.287 3289-3289/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator24Map: abcd
        //06-17 18:03:58.287 3289-3289/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator24Map: 123
        //06-17 18:03:58.288 3289-3289/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator24Map: 4
    }
}
