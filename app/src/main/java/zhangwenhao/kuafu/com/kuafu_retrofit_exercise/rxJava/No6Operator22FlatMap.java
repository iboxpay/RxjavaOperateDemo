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

    /**
     * FlatMap对这些Observables发射的数据做的是合并(merge)操作，因此它们可能是交错的。
     */
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

    /**
     * concatMap
     * 但是它按次序连接而不是合并那些生成的Observables
     * todo 不合并
     */
    public static void concatMap() {
        Observable.from(getList()).concatMap(new Func1<List<Integer>, Observable<Integer>>() {
            @Override
            public Observable<Integer> call(List<Integer> integers) {
                Log.d("No_func1__integer", integers.toString());
                return Observable.from(integers);
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d("No_func2__integer", integer + "");
            }
        });
    }

    /**
     * 它和flatMap很像，除了一点：当原始Observable发射一个新的数据（Observable）时，
     * 它将取消订阅并停止监视产生执之前那个数据的Observable，只监视当前这一个。
     */
    public static void switchMap() {
        Observable.from(getList()).switchMap(new Func1<List<Integer>, Observable<Integer>>() {
            @Override
            public Observable<Integer> call(List<Integer> integers) {
                return null;
            }
        });
    }

    /**
     * StringObservable类 中还有很多的方法  byte <------>string <------>stream 直接转换
     * 在特殊的StringObservable类（默认没有包含在RxJava中）中还有一个split操作符。
     * 它将一个发射字符串的Observable转换为另一个发射字符串的Observable，只不过，
     * 后者将原始的数据序列当做一个数据流，使用一个正则表达式边界分割它们，然后合并发射分割的结果
     */
}
