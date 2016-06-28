package zhangwenhao.kuafu.com.kuafu_retrofit_exercise.rxJava;

import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by zhangwenhao on 2016/6/28.
 * call me
 * email:zhangwenhao@iboxpay.com
 * QQ:1160113614
 */
public class No6Operator61Do {

    private static final String TAG = "No6Operator61Do";

    /**
     * doOnEach操作符让你可以注册一个回调，它产生的Observable每发射一项数据就会调用它一次。
     * 你可以以Action的形式传递参数给它，
     * 这个Action接受一个onNext的变体Notification作为它的唯一参数，
     * 你也可以传递一个Observable给doOnEach，这个Observable的onNext会被调用，就好像它订阅了原始的Observable一样
     */
    public static void doOnEach() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5);
        Observable<Integer> observable2 = observable.doOnEach(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext: " + integer);
            }
        });
        observable2.subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ____");

            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ____");

            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext: ___" + integer);
            }
        });

        //06-28 17:13:18.692 20953-20953/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Do: onNext: 1
        //06-28 17:13:18.692 20953-20953/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Do: onNext: ___1
        //06-28 17:13:18.692 20953-20953/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Do: onNext: 2
        //06-28 17:13:18.692 20953-20953/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Do: onNext: ___2
        //06-28 17:13:18.692 20953-20953/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Do: onNext: 3
        //06-28 17:13:18.692 20953-20953/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Do: onNext: ___3
        //06-28 17:13:18.692 20953-20953/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Do: onNext: 4
        //06-28 17:13:18.692 20953-20953/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Do: onNext: ___4
        //06-28 17:13:18.692 20953-20953/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Do: onNext: 5
        //06-28 17:13:18.692 20953-20953/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Do: onNext: ___5
        //06-28 17:13:18.692 20953-20953/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Do: onCompleted:
        //06-28 17:13:18.692 20953-20953/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Do: onCompleted: ____
    }

    public static void doOnNext() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4);
        observable.doOnNext(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                if (integer > 1) {
                    throw new RuntimeException("Item exceeds maximum value");
                }
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext: " + integer);
            }
        });
        //06-28 18:58:04.536 13723-13723/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Do: onNext: 1
        //06-28 18:58:04.537 13723-13723/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Do: onError:
    }


    /**
     * doOnSubscribe操作符注册一个动作，当观察者订阅它生成的Observable它就会被调用。
     */
    public static void doOnSubscribe() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        Log.d(TAG, "call: ");
                    }
                });
        Log.d(TAG, "doOnSubscribe: " + "订阅");
        observable.subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext: " + integer);
            }
        });
        //06-28 19:09:12.483 11322-11322/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Do: doOnSubscribe: 订阅
        //06-28 19:09:12.484 11322-11322/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Do: call:
        //06-28 19:09:12.485 11322-11322/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Do: onNext: 1
        //06-28 19:09:12.485 11322-11322/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Do: onNext: 2
        //06-28 19:09:12.485 11322-11322/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Do: onNext: 3
        //06-28 19:09:12.485 11322-11322/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Do: onNext: 4
        //06-28 19:09:12.485 11322-11322/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Do: onCompleted:
    }

    /**
     * doOnUnsubscribe操作符注册一个动作，当观察者取消订阅它生成的Observable它就会被调用。
     */
    public static void doOnUnsubscrobe() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4);

        observable.doOnUnsubscribe(new Action0() {
            @Override
            public void call() {
                Log.d(TAG, "call: doOnUnsubscribe");
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext: " + integer);
                if (integer > 2) {
                    if (!this.isUnsubscribed()) {
                        unsubscribe();
                        Log.d(TAG, "unsubscribe " + isUnsubscribed());
                    }

                }
            }
        });

        //06-28 19:18:54.486 4567-4567/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Do: onNext: 1
        //06-28 19:18:54.486 4567-4567/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Do: onNext: 2
        //06-28 19:18:54.486 4567-4567/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Do: onNext: 3
        //06-28 19:18:54.486 4567-4567/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Do: call: doOnUnsubscribe
        //06-28 19:18:54.486 4567-4567/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Do: unsubscribe true
    }

    /**
     * doOnCompleted 操作符注册一个动作，当它产生的Observable正常终止调用onCompleted时会被调用。
     */
    public static void doOnCompleted() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4);
        observable.doOnCompleted(new Action0() {
            @Override
            public void call() {
                Log.d(TAG, "call: " + "action0_dooncompleted");
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext: " + integer);
            }
        });

        //06-28 19:12:43.713 19659-19659/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Do: onNext: 1
        //06-28 19:12:43.713 19659-19659/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Do: onNext: 2
        //06-28 19:12:43.713 19659-19659/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Do: onNext: 3
        //06-28 19:12:43.713 19659-19659/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Do: onNext: 4
        //06-28 19:12:43.713 19659-19659/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Do: call: action0_dooncompleted
        //06-28 19:12:43.713 19659-19659/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator61Do: onCompleted:
    }

    /**
     * doOnError 操作符注册一个动作，当它产生的Observable异常终止调用onError时会被调用。
     */
    public static void doOnError() {

    }

    /**
     * doOnTerminate 操作符注册一个动作，当它产生的Observable终止之前会被调用，无论是正常还是异常终止。
     */
    public static void doOnTerminate() {

    }

    /**
     * finallyDo 操作符注册一个动作，当它产生的Observable终止之后会被调用，无论是正常还是异常终止。
     */
    public static void finallyDo() {

    }


}
