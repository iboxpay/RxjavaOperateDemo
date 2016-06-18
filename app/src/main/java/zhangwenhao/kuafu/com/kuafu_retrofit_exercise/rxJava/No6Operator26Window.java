package zhangwenhao.kuafu.com.kuafu_retrofit_exercise.rxJava;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by 63 on 2016/6/18.
 */
public class No6Operator26Window {

    private static final String TAG = "No6Operator26Window";

    /**
     * window(int count) 每发射3个 生成一个新的observable
     */
    public static void window() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).window(3).subscribe(new Action1<Observable<Integer>>() {
            @Override
            public void call(Observable<Integer> integerObservable) {
                Log.d(TAG, "call: integerObservable" + integerObservable.toString());
                integerObservable.subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.d(TAG, "call: integer" + integer);
                    }
                });
            }
        });

//        06-18 17:01:23.331 27012-27012/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integerObservablerx.internal.operators.UnicastSubject@1c602632
//        06-18 17:01:23.331 27012-27012/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer1
//        06-18 17:01:23.331 27012-27012/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer2
//        06-18 17:01:23.331 27012-27012/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer3
//        06-18 17:01:23.341 27012-27012/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integerObservablerx.internal.operators.UnicastSubject@1d3faf83
//        06-18 17:01:23.341 27012-27012/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer4
//        06-18 17:01:23.341 27012-27012/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer5
//        06-18 17:01:23.341 27012-27012/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer6
//        06-18 17:01:23.341 27012-27012/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integerObservablerx.internal.operators.UnicastSubject@1de9d500
//        06-18 17:01:23.341 27012-27012/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer7
//        06-18 17:01:23.341 27012-27012/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer8
//        06-18 17:01:23.341 27012-27012/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer9
//        06-18 17:01:23.341 27012-27012/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integerObservablerx.internal.operators.UnicastSubject@3975e139
//        06-18 17:01:23.341 27012-27012/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer10
    }


    public static void window1() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).window(2, 4).subscribe(new Action1<Observable<Integer>>() {
            @Override
            public void call(Observable<Integer> integerObservable) {
                Log.d(TAG, "call: " + integerObservable.toString());
                integerObservable.subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.d(TAG, "call: integer" + integer);
                    }
                });
            }
        });
//        06-18 17:10:34.441 3138-3138/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: rx.internal.operators.UnicastSubject@1c602632
//        06-18 17:10:34.441 3138-3138/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer1
//        06-18 17:10:34.441 3138-3138/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer2
//        06-18 17:10:34.441 3138-3138/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: rx.internal.operators.UnicastSubject@1d3faf83
//        06-18 17:10:34.441 3138-3138/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer5
//        06-18 17:10:34.441 3138-3138/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer6
//        06-18 17:10:34.441 3138-3138/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: rx.internal.operators.UnicastSubject@1de9d500
//        06-18 17:10:34.441 3138-3138/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer9
//        06-18 17:10:34.441 3138-3138/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer10
    }


    /**
     * window(Func0<? extends Observable<? extends TClosing>> closingSelector)
     * <p/>
     * 例子是每3秒发射一次
     * selector 每10s捕获完成 发射一次
     */
    public static void window2() {
        Observable<Long> observable = Observable.interval(3000, TimeUnit.MILLISECONDS);
        final Observable<Long> observable1 = Observable.interval(10000, TimeUnit.MILLISECONDS);

        observable.window(new Func0<Observable<Long>>() {
            @Override
            public Observable<Long> call() {
                return observable1;
            }
        }).subscribe(new Action1<Observable<Long>>() {
            @Override
            public void call(Observable<Long> longObservable) {
                Log.d(TAG, "call: longObservable" + longObservable.toString());
                longObservable.subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Log.d(TAG, "call: 最终" + aLong);
                    }
                });
            }
        });

//        06-18 16:42:40.111 23623-23623/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: longObservablerx.internal.operators.UnicastSubject@6c3c0df
//        06-18 16:42:43.121 23623-23652/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: 最终0
//        06-18 16:42:46.121 23623-23652/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: 最终1
//        06-18 16:42:49.121 23623-23652/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: 最终2
//        06-18 16:42:50.121 23623-23651/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: longObservablerx.internal.operators.UnicastSubject@1a3d360
//        06-18 16:42:52.121 23623-23652/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: 最终3
//        06-18 16:42:55.121 23623-23652/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: 最终4
//        06-18 16:42:58.121 23623-23652/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: 最终5
//        06-18 16:43:00.111 23623-23740/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: longObservablerx.internal.operators.UnicastSubject@dcd54ea
//        06-18 16:43:01.111 23623-23652/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: 最终6
//        06-18 16:43:04.111 23623-23652/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: 最终7
//        06-18 16:43:07.121 23623-23652/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: 最终8
//        06-18 16:43:10.111 23623-23652/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: 最终9
    }

    /**
     * 这个window的变体立即打开它的第一个窗口。
     * 这个变体是window(count)和window(timespan, unit[, scheduler])的结合，
     * 每当过了timespan的时长或者当前窗口收到了count项数据，它就关闭当前窗口并打开另一个。
     * 如果从原始Observable收到了onError或onCompleted通知它也会关闭当前窗口。
     * 这种window变体发射一系列不重叠的窗口，这些窗口的数据集合与原始Observable发射的数据也是一一对应的。
     */
    public static void window3() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9).window(1000, TimeUnit.MILLISECONDS, 4, Schedulers.newThread()).subscribe(new Action1<Observable<Integer>>() {
            @Override
            public void call(Observable<Integer> integerObservable) {
                Log.d(TAG, "call: integerObservable" + integerObservable + "_______" + Thread.currentThread());
                integerObservable.subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.d(TAG, "call: integer" + integer + "_______" + Thread.currentThread());
                    }
                });
            }
        });
//        06-18 17:17:26.491 9112-9112/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integerObservablerx.internal.operators.UnicastSubject@358f9742_______Thread[main,5,main]
//        06-18 17:17:26.501 9112-9112/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer1_______Thread[main,5,main]
//        06-18 17:17:26.501 9112-9112/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integerObservablerx.internal.operators.UnicastSubject@d45b353_______Thread[main,5,main]
//        06-18 17:17:26.501 9112-9112/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer2_______Thread[main,5,main]
//        06-18 17:17:26.501 9112-9112/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer3_______Thread[main,5,main]
//        06-18 17:17:26.501 9112-9112/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer4_______Thread[main,5,main]
//        06-18 17:17:26.501 9112-9112/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer5_______Thread[main,5,main]
//        06-18 17:17:26.501 9112-9112/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integerObservablerx.internal.operators.UnicastSubject@2882d490_______Thread[main,5,main]
//        06-18 17:17:26.501 9112-9112/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer6_______Thread[main,5,main]
//        06-18 17:17:26.501 9112-9112/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer7_______Thread[main,5,main]
//        06-18 17:17:26.501 9112-9112/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer8_______Thread[main,5,main]
//        06-18 17:17:26.501 9112-9112/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer9_______Thread[main,5,main]
    }

    /**
     * 这个和selector func那个有点类似
     */
    public static void window4() {
        Observable.interval(500, TimeUnit.MILLISECONDS).window(Observable.interval(2000, TimeUnit.MILLISECONDS)).subscribe(new Action1<Observable<Long>>() {
            @Override
            public void call(Observable<Long> integerObservable) {
                Log.d(TAG, "call: integerObservable" + integerObservable);
                integerObservable.subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long integer) {
                        Log.d(TAG, "call: integer" + integer);
                    }
                });
            }
        });


//        06-18 17:30:45.911 22625-22625/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integerObservablerx.internal.operators.UnicastSubject@3975e139
//        06-18 17:30:46.421 22625-22684/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer0
//        06-18 17:30:46.921 22625-22684/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer1
//        06-18 17:30:47.411 22625-22684/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer2
//        06-18 17:30:47.911 22625-22683/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integerObservablerx.internal.operators.UnicastSubject@3696a592
//        06-18 17:30:47.921 22625-22683/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer3
//        06-18 17:30:48.411 22625-22684/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer4
//        06-18 17:30:48.911 22625-22684/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer5
//        06-18 17:30:49.411 22625-22684/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer6
//        06-18 17:30:49.911 22625-22683/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integerObservablerx.internal.operators.UnicastSubject@2c62c58c
//        06-18 17:30:49.911 22625-22684/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer7
//        06-18 17:30:50.411 22625-22684/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer8
//        06-18 17:30:50.911 22625-22684/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer9
//        06-18 17:30:51.421 22625-22684/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer10
//        06-18 17:30:51.911 22625-22683/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integerObservablerx.internal.operators.UnicastSubject@27a3d5b6
//        06-18 17:30:51.911 22625-22684/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer11
//        06-18 17:30:52.421 22625-22684/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer12
//        06-18 17:30:52.911 22625-22684/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer13
//        06-18 17:30:53.421 22625-22684/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer14
//        06-18 17:30:53.921 22625-22683/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integerObservablerx.internal.operators.UnicastSubject@2882d490
//        06-18 17:30:53.921 22625-22684/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer15
//        06-18 17:30:54.421 22625-22684/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer16
//        06-18 17:30:54.911 22625-22684/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer17
//        06-18 17:30:55.411 22625-22684/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer18
//        06-18 17:30:55.921 22625-22683/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integerObservablerx.internal.operators.UnicastSubject@3b5ccc9a
//        06-18 17:30:55.921 22625-22684/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer19
    }

    /**
     * 不是很理解   不知道应用场景是什么
     * <p/>
     * 无论何时，只要window观察到windowOpenings这个Observable发射了一个Opening对象，
     * 它就打开一个窗口，并且同时调用closingSelector生成一个与那个窗口关联的关闭(closing)Observable。
     * 当这个关闭(closing)Observable发射了一个对象时，window操作符就会关闭那个窗口。
     * 对这个变体来说，由于当前窗口的关闭和新窗口的打开是由单独的Observable管理的，
     * 它创建的窗口可能会存在重叠（重复某些来自原始Observable的数据）或间隙（丢弃某些来自原始Observable的数据
     */
    public static void window5() {
        Observable.interval(100, TimeUnit.MILLISECONDS).window(Observable.interval(1000, TimeUnit.MILLISECONDS), new Func1<Long, Observable<?>>() {
            @Override
            public Observable<?> call(Long aLong) {
                return Observable.just(aLong);
            }
        }).subscribe(new Action1<Observable<Long>>() {
            @Override
            public void call(Observable<Long> integerObservable) {
                Log.d(TAG, "call: integerObservable" + integerObservable);
                integerObservable.subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long integer) {
                        Log.d(TAG, "call: integer" + integer);
                    }
                });
            }
        });
//        06-18 17:38:11.001 27336-28709/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integerObservablerx.internal.operators.UnicastSubject@e2d64bc
//        06-18 17:38:11.111 27336-29503/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integerObservablerx.internal.operators.UnicastSubject@2c254ea7
//        06-18 17:38:11.111 27336-29503/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer9
//        06-18 17:38:12.001 27336-28709/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integerObservablerx.internal.operators.UnicastSubject@397b0308
//        06-18 17:38:12.111 27336-29503/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integerObservablerx.internal.operators.UnicastSubject@3d974cd9
//        06-18 17:38:13.001 27336-28709/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integerObservablerx.internal.operators.UnicastSubject@3f4340bd
//        06-18 17:38:13.111 27336-29503/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integerObservablerx.internal.operators.UnicastSubject@1f1bedac
//        06-18 17:38:13.111 27336-29503/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer29
//        06-18 17:38:14.001 27336-28709/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integerObservablerx.internal.operators.UnicastSubject@2b51fa99
//        06-18 17:38:14.101 27336-29503/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integerObservablerx.internal.operators.UnicastSubject@3033e2f8
//        06-18 17:38:14.111 27336-29503/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator26Window: call: integer39

    }
}
