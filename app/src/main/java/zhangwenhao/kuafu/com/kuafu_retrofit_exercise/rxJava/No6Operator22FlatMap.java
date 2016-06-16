package zhangwenhao.kuafu.com.kuafu_retrofit_exercise.rxJava;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by zhangwenhao on 2016/6/16.
 * call me
 * email:zhangwenhao@iboxpay.com
 * QQ:1160113614
 */
public class No6Operator22FlatMap {

    /**
     * 造数据
     */
    public static List<List<Integer>> getList() {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(11);
        list1.add(12);
        list1.add(13);
        List<Integer> list2 = new ArrayList<>();
        list2.add(21);
        list2.add(22);
        list2.add(23);
        List<Integer> list3 = new ArrayList<>();
        list3.add(31);
        list3.add(32);
        list3.add(33);

        list.add(list1);
        list.add(list2);
        list.add(list3);
        return list;
    }

    public static void flatMap() {
        Observable.from(getList()).flatMap(new Func1<List<Integer>, Observable<Integer>>() {
            @Override
            public Observable<Integer> call(List<Integer> integers) {
                return Observable.from(integers);
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d("No6Operator22FlatMap", integer.toString());
            }
        });
    }

    /**
     * flatmap(func1 ,maxcount)
     *
     * maxcount同时订阅 最大数量
     */
    public static void flatMap2() {
        Observable.from(getList()).flatMap(new Func1<List<Integer>, Observable<Integer>>() {
            @Override
            public Observable<Integer> call(List<Integer> integers) {
                return Observable.from(integers);
            }
        }, 4).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d("No6Operator22FlatMap", integer.toString());
            }
        });
    }

    /**
     * flatmap(func1 ,func2)
     */
    public static void flatMap3() {

        Observable.from(getList()).flatMap(new Func1<List<Integer>, Observable<Integer>>() {
            @Override
            public Observable<Integer> call(List<Integer> integers) {
                Log.d("No_func1__List<Integer>", integers.toString());
                return Observable.from(integers);
            }
        }, new Func2<List<Integer>, Integer, Integer>() {
            @Override
            public Integer call(List<Integer> integers, Integer integer) {
                Log.d("No_func2__List<Integer>", integers.toString());
                Log.d("No_func2__integer", integer + "");
                //return integer;
                return integers.get(0) == integer ? integer : 0;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d("No_func3__integer", integer + "");
            }
        });
    }


    /**
     * 和前面差不多 只是换成了 iterater
     */
    public static void flatMapIterable() {

    }
}
