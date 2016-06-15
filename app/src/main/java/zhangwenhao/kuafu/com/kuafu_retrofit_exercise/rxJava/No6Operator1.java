package zhangwenhao.kuafu.com.kuafu_retrofit_exercise.rxJava;

import android.util.Log;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Func0;

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

    public static void No6Operator1From1(){
        //Rxjava
    }


}
