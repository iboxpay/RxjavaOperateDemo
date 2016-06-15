package zhangwenhao.kuafu.com.kuafu_retrofit_exercise.rxJava;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import rx.Observable;
import rx.Observer;
import rx.Statement;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.schedulers.Schedulers;
import rx.util.async.Async;

/**
 * Created by zhangwenhao on 2016/6/15.
 * call me
 * email:zhangwenhao@iboxpay.com
 * QQ:1160113614
 *
 * No6Operator1 全是创建Observable的各种方法。
 *
 * just( ) — 将一个或多个对象转换成发射这个或这些对象的一个Observable
 * from( ) — 将一个Iterable, 一个Future, 或者一个数组转换成一个Observable
 * repeat( ) — 创建一个重复发射指定数据或数据序列的Observable
 * repeatWhen( ) — 创建一个重复发射指定数据或数据序列的Observable，它依赖于另一个Observable发射的数据
 * create( ) — 使用一个函数从头创建一个Observable
 * defer( ) — 只有当订阅者订阅才创建Observable；为每个订阅创建一个新的Observable
 * range( ) — 创建一个发射指定范围的整数序列的Observable
 * interval( ) — 创建一个按照给定的时间间隔发射整数序列的Observable
 * timer( ) — 创建一个在给定的延时之后发射单个数据的Observable
 * empty( ) — 创建一个什么都不做直接通知完成的Observable
 * error( ) — 创建一个什么都不做直接通知错误的Observable
 * never( ) — 创建一个不发射任何数据的Observable
 */
public class No6Operator1 {

    /**
     * create() 这个例子跳过
     *
     * // TODO: 建议你在传递给create方法的函数中检查观察者的isUnsubscribed状态，
     * // TODO: 以便在没有观察者的时候，让你的Observable停止发射数据或者做昂贵的运算。
     */
    public static void No6Operator1Create() {

        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(1);
                    //......
                }

            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {

            }
        });

    }

    /**
     * defer() 延迟推迟
     *
     * 直到有观察者订阅时才创建Observable，并且为每个观察者创建一个新的Observable
     * todo defer ()  冷
     */
    public static void No6Operator1Defer() {
        Observable<Integer> observable = Observable.defer(new Func0<Observable<Integer>>() {
            @Override
            public Observable<Integer> call() {
                return Observable.just(1, 2, 3, 4, 5, 6);
            }
        });
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d("No6Operator1", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.d("No6Operator1", "onNext_____" + integer);
            }
        };
        observable.subscribe(observer);
    }


    /**
     * 'io.reactivex:rxjava-computation-expressions:0.21.0'
     *
     * Statement.xxxxx
     * switchCase()
     * ifThen()
     * doWhile();第一个参数是要发射的数据源   第二个参数是 dowhile的判断
     * whileDo();
     *
     * todo defer ()  冷
     */
    public static void No6Operator1SwitchCase() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5, 6);
        //dowhile();   说白了就是  dowhile
      /*  Observable<Integer> observable1 = Statement.doWhile(observable, new Func0<Boolean>() {
            @Override
            public Boolean call() {
                return true;//判断的条件
            }
        });*/

        //swihchcase();    说白了就是  switch
        Map<Integer, Observable<Integer>> map = new HashMap();
        map.put(1, Observable.just(1, 2, 3, 4, 5));
        map.put(2, Observable.just(6, 7, 8, 9, 10));

        Observable<Integer> observable1 = Statement.switchCase(new Func0<Integer>() {
            @Override
            public Integer call() {
                return 1;
            }
        }, map);

        //ifThen()    说白了 就是if() else()
       /* Statement.ifThen(new Func0<Boolean>() {
            @Override
            public Boolean call() {
                return null;
            }
        },Observable.just(1,2,3,4,5));*/
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d("No6Operator1", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.d("No6Operator1", "onNext_____" + integer);
            }
        };

        observable1.subscribe(observer);

    }

    /**
     * empty() 创建一个不能发射任何数据但是能正常终止的Observable
     *
     * todo  冷暖判断      冷
     */
    public static void No6Operator1Empty() {
        Observable<Object> empty = Observable.empty();
        Observer<Object> observer = new Observer<Object>() {
            @Override
            public void onCompleted() {
                Log.d("No6Operator1", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {

            }
        };
        empty.subscribe(observer);

    }

    /**
     * never() 创建一个不发射数据也不终止的Observable
     */
    public static void No6Operator1Never() {
        Observable<Object> observable = Observable.never();
        Observer<Object> observer = new Observer<Object>() {
            @Override
            public void onCompleted() {
                Log.d("No6Operator1", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("No6Operator1", "onError");
            }

            @Override
            public void onNext(Object o) {
                Log.d("No6Operator1", "onNext");
            }
        };

        observable.subscribe(observer);
    }

    /**
     * error() 创建一个不发射数据以一个错误终止的Observable
     *
     * todo 冷热判断   冷
     */
    public static void No6Operator1Throw() {
        Observable<Object> observable = Observable.error(new Throwable());
        Observer<Object> observer = new Observer<Object>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.d("No6Operator1", "onError" + e.toString());
            }

            @Override
            public void onNext(Object o) {

            }
        };

        observable.subscribe(observer);
    }

    /**
     * form() 的使用
     *
     * // TODO:  冷暖判断   冷
     */
    public static void No6Operator1From() {
        Integer[] integers = new Integer[]{1, 2, 3, 4, 5, 6};
        Observable<Integer> observable = Observable.from(integers);
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d("No6Operator1", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.d("No6Operator1", "onNext" + integer);
            }
        };

        observable.subscribe(observer);
    }

    /**
     * form(future) 对于Future，它会发射Future.get()方法返回的单个数据
     * 还有一个延时的版本
     * TODO:  冷暖判断   冷
     */
    public static void No6Operator1FromFuture() {
        Observable<Integer> observable = Observable.from(new Future<Integer>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public Integer get() throws InterruptedException, ExecutionException {
                return 1;
            }

            @Override
            public Integer get(long timeout, TimeUnit unit)
                    throws InterruptedException, ExecutionException, TimeoutException {
                return 1;
            }
        });

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d("No6Operator1", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.d("No6Operator1", "onNext" + integer);
            }
        };

        observable.subscribe(observer);
        //06-15 14:27:26.503 13188-13188/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator1: onNext1
        //06-15 14:27:26.503 13188-13188/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator1: onCompleted
    }

    /**
     * RxjavaAsyncUtil 扩展包里面的提供的   后面 start部分还会有
     *
     * Async.xxxxx
     * fromAction
     * fromCallable
     * fromFunc0
     * fromRunnable
     */
    public static void No6Operator1From1() {
        Observable<Integer> observable = Async.fromAction(new Action0() {
            @Override
            public void call() {

            }
        }, 1);

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d("No6Operator1", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.d("No6Operator1", "onNext" + integer);
            }
        };

        observable.subscribe(observer);

    }

    /**
     * 创建一个按固定时间间隔发射整数序列的Observable
     */
    public static void No6Operator1Interval() {
        Observable.interval(1000, TimeUnit.MILLISECONDS, Schedulers.newThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {

                        Log.d("No6Operator1", "onNext" + Thread.currentThread() + "_____" + aLong);
                    }
                });
    }

    /**
     * 6.1.6just  就直接跳过了
     */

    /**
     * 6.1.7Range()
     */
    public static void No6Operator1Range() {
        Observable.range(0, 9, Schedulers.newThread()).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d("No6Operator1",
                        "onNext" + Thread.currentThread().getName() + "_____" + integer);
            }
        });
    }

    /**
     * 6.1.8 repeat()
     *
     * 创建一个发射特定数据重复多次的Observable
     */
    public static void No6Operator1Repeat() {

        Observable.just(1, 2).repeat(3, Schedulers.newThread()).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d("No6Operator1Repeat",
                        "onNext" + Thread.currentThread().getName() + "_____" + integer);
            }
        });
    }


}
