package zhangwenhao.kuafu.com.kuafu_retrofit_exercise.rxJava;

import android.util.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zhangwenhao on 2016/6/16.
 * call me
 * email:zhangwenhao@iboxpay.com
 * QQ:1160113614
 */
public class No6Operator21Buffer {


    /**
     * 6.2 变幻操作 buffer()
     */
    public static void No6Operator21Buffer() {
        Observable.just(1, 2, 3, 4, 5).buffer(2, 2).subscribe(new Action1<List<Integer>>() {
            @Override
            public void call(List<Integer> integers) {
                Log.d("No6Operator21Buffer", integers.toString());
            }
        });
    }


    /**
     * count >skip
     * 每次buffer可以缓存3(count个),但是每次跳转2(skip)个,这里第一次 1,2,3 只skip 2个 也就是下次是从3开始取
     * 第二次就是 3 4 5
     */
    public static void No6Operator21Buffer1() {
        Observable.just(1, 2, 3, 4, 5).buffer(3, 2).subscribe(new Action1<List<Integer>>() {
            @Override
            public void call(List<Integer> integers) {
                Log.d("No6Operator21Buffer", integers.toString());
            }
        });
        //    06-16 11:11:23.355 6410-6410/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator21Buffer: [1, 2, 3]
        //    06-16 11:11:23.355 6410-6410/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator21Buffer: [3, 4, 5]
        //    06-16 11:11:23.355 6410-6410/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator21Buffer: [5]
    }

    /**
     * 每次缓存2个但是只跳转3个
     * 第一是 1 2   第二次就是  4 5
     */
    public static void No6Operator21Buffer2() {
        Observable.just(1, 2, 3, 4, 5).buffer(2, 3).subscribe(new Action1<List<Integer>>() {
            @Override
            public void call(List<Integer> integers) {
                Log.d("No6Operator21Buffer", integers.toString());
            }
        });
        //06-16 11:14:35.324 9938-9938/? D/No6Operator21Buffer: [1, 2]
        //06-16 11:14:35.324 9938-9938/? D/No6Operator21Buffer: [4, 5]
    }

    /**
     * 使用buffer(long timespan, TimeUnit unit, Scheduler scheduler)
     */
    public static void No6Operator21Buffer3() {
        Observable.just(1, 2, 3, 4, 5).buffer(2, TimeUnit.SECONDS, Schedulers.newThread())
                .subscribe(new Action1<List<Integer>>() {
                    @Override
                    public void call(List<Integer> integers) {
                        Log.d("No6Operator21Buffer",
                                Thread.currentThread().getName() + "_______" + integers.toString());
                    }
                });

        //06-16 11:18:58.216 14515-14515/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator21Buffer: main_______[1, 2, 3, 4, 5]
    }

    /**
     * buffer(boundary)
     */
    public static void No6Operator21Buffer4() {
        Observable<Long> observable1 = Observable.interval(1000, TimeUnit.MILLISECONDS);
        Observable<Long> observable2 = Observable.interval(3000, TimeUnit.MILLISECONDS);
        observable1.buffer(observable2).subscribe(new Observer<List<Long>>() {
            @Override
            public void onCompleted() {
                Log.d("No6Operator21Buffer4",
                        Thread.currentThread().getName() + "_______");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("No6Operator21Buffer4",
                        Thread.currentThread().getName() + "_______" + e.toString());
            }

            @Override
            public void onNext(List<Long> longs) {
                Log.d("No6Operator21Buffer4",
                        Thread.currentThread().getName() + "_______" + longs);
            }
        });

    }

    /**
     * buffer(Observable<? extends TOpening> bufferOpenings, Func1<? super TOpening, ? extends
     * Observable<? extends TClosing>> bufferClosingSelector)
     */
    public static void No6Operator21Buffer5() {
        Observable<Long> observable1 = Observable.interval(1000, TimeUnit.MILLISECONDS);
        Observable<Long> observable2 = Observable.interval(3000, TimeUnit.MILLISECONDS);

        observable1.buffer(observable2, new Func1<Long, Observable<?>>() {
            @Override
            public Observable<Long> call(Long aLong) {//100 毫秒刷一次数据
                Log.d("No6Operator21Buffer5",
                        Thread.currentThread().getName() + "_______" + aLong);
                return Observable.just(aLong);
            }
        }).subscribe(new Action1<List<Long>>() {
            @Override
            public void call(List<Long> longs) {
                Log.d("No6Operator21Buffer5",
                        Thread.currentThread().getName() + "_______" + longs);
            }
        });
        //100   Observable<Long> observable2 = Observable.interval(100, TimeUnit.MILLISECONDS);
        //06-16 16:38:36.064 5211-5238/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator21Buffer5: RxComputationThreadPool-1_______249
        //06-16 16:38:36.067 5211-5238/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator21Buffer5: RxComputationThreadPool-1_______[24]
        //.....
        //06-16 16:39:01.064 5211-5238/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator21Buffer5: RxComputationThreadPool-1_______499
        //06-16 16:39:01.064 5211-5238/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator21Buffer5: RxComputationThreadPool-1_______[49]

        //3000  Observable<Long> observable2 = Observable.interval(3000, TimeUnit.MILLISECONDS);
        //06-16 16:42:02.802 8707-8731/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator21Buffer5: RxComputationThreadPool-1_______0
        //06-16 16:42:02.803 8707-8731/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator21Buffer5: RxComputationThreadPool-1_______[2]
        //06-16 16:42:05.801 8707-8731/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator21Buffer5: RxComputationThreadPool-1_______1
        //06-16 16:42:05.801 8707-8731/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator21Buffer5: RxComputationThreadPool-1_______[]
        //06-16 16:42:08.802 8707-8731/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator21Buffer5: RxComputationThreadPool-1_______2
        //06-16 16:42:08.802 8707-8731/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator21Buffer5: RxComputationThreadPool-1_______[]
        //06-16 16:42:11.801 8707-8731/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator21Buffer5: RxComputationThreadPool-1_______3
        //06-16 16:42:11.801 8707-8731/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator21Buffer5: RxComputationThreadPool-1_______[]
        //06-16 16:42:14.802 8707-8731/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator21Buffer5: RxComputationThreadPool-1_______4
        //06-16 16:42:14.802 8707-8731/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator21Buffer5: RxComputationThreadPool-1_______[]
        //06-16 16:42:17.801 8707-8731/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator21Buffer5: RxComputationThreadPool-1_______5
        //06-16 16:42:17.801 8707-8731/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator21Buffer5: RxComputationThreadPool-1_______[]
        //06-16 16:42:20.802 8707-8731/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator21Buffer5: RxComputationThreadPool-1_______6
        //06-16 16:42:20.802 8707-8731/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator21Buffer5: RxComputationThreadPool-1_______[]
        //06-16 16:42:23.801 8707-8731/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator21Buffer5: RxComputationThreadPool-1_______7
        //06-16 16:42:23.808 8707-8731/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator21Buffer5: RxComputationThreadPool-1_______[23]
        //....
        //06-16 16:44:47.802 8707-8731/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator21Buffer5: RxComputationThreadPool-1_______[]
        //06-16 16:44:50.801 8707-8731/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator21Buffer5: RxComputationThreadPool-1_______56
        //06-16 16:44:50.801 8707-8731/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator21Buffer5: RxComputationThreadPool-1_______[]
        //06-16 16:44:53.802 8707-8731/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator21Buffer5: RxComputationThreadPool-1_______57
        //06-16 16:44:53.803 8707-8731/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No6Operator21Buffer5: RxComputationThreadPool-1_______[173]

    }


}