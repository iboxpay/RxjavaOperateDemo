package zhangwenhao.kuafu.com.kuafu_retrofit_exercise.rxJava;

import android.util.Log;

import rx.Observer;
import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;

/**
 * Created by zhangwenhao on 2016/6/14.
 * call me
 * email:zhangwenhao@iboxpay.com
 * QQ:1160113614
 */
public class No4Subject {

    /**
     * Subject可以看成是一个桥梁或者代理，在某些ReactiveX实现中（如RxJava），
     * 它同时充当了Observer和Observable的角色。因为它是一个Observer，
     * 它可以订阅一个或多个Observable；又因为它是一个Observable，
     * 它可以转发它收到(Observe)的数据，也可以发射新的数据。
     *
     * todo 由于一个Subject订阅一个Observable，它可以触发这个Observable
     * todo  开始发射数据（如果那个Observable是"冷"的--就是说，它等待有订阅才开始发射数据）。
     * todo  因此有这样的效果，Subject可以把原来那个"冷"的Observable变成"热"的。
     *
     * 针对不同的场景一共有四种类型的Subject
     */


    /**
     * 场景一  AsyncSubject 的使用
     *
     * 一个AsyncSubject只在原始Observable完成后，发射来自原始Observable的最后一个值。
     * （如果原始Observable没有发射任何值，AsyncObject也不发射任何值）它会把这最后一个值发射给任何后续的观察者。
     *
     * todo 最后一个值的标准  是以执行oncompleted() 方法为准的
     */
    public static void Subject1AsyncSubject() {

        //AsyncSubject只有这种创建方式
        AsyncSubject<Integer> subscriber = AsyncSubject.create();//注意这里的泛型
        subscriber.onNext(1);
        subscriber.onNext(2);
        subscriber.onNext(3);
        subscriber.onNext(4);
        subscriber.onNext(5);
        subscriber.onCompleted();
        subscriber.subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d("No4Subject", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("No4Subject", "onError");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d("No4Subject", "onNext" + integer);
            }
        });
        //输出结果
        //06-14 12:30:50.598 29465-29465/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No4Subject: onNext5
        // 06-14 12:30:50.598 29465-29465/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No4Subject: onCompleted
    }

    /**
     * 场景二 todo  BehaviorSubject
     * 当观察者订阅BehaviorSubject时，它开始发射原始Observable最近发射的数据
     * （如果此时还没有收到任何数据，它会发射一个默认值），然后继续发射其它任何来自原始Observable的数据。
     * 如果原始的Observable因为发生了一个错误而终止，BehaviorSubject将不会发射任何数据，
     * 只是简单的向前传递这个错误通知。
     *
     * todo 冷热判断------订阅之后,observable 发射事件 才可能收到      <热></热>
     */
    public static void Subject1BehaviorSubject() {

        //final BehaviorSubject<Integer> subject = BehaviorSubject.create();//没有默认值的情况
        final BehaviorSubject<Integer> subject = BehaviorSubject.create(0);//设置默认值
        final Observer<Integer> observer2 = new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d("No4Subject", "onCompleted__observer2");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("No4Subject", "onError__observer2");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d("No4Subject", "onNext__observer2" + integer);
            }
        };
        final Observer<Integer> observer3 = new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d("No4Subject", "onCompleted__observer3");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("No4Subject", "onError__observer3");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d("No4Subject", "onNext__observer3" + integer);
            }
        };

        Observer<Integer> observer1 = new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d("No4Subject", "onCompleted__observer1");
                subject.subscribe(observer3);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("No4Subject", "onError__observer1");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d("No4Subject", "onNext__observer1" + integer);
                if (integer == 3) {
                    subject.subscribe(observer2);//中途订阅 3事件是 最近发射的事件
                }
            }
        };

        subject.subscribe(observer1);

        subject.onNext(1);
        subject.onNext(2);
        subject.onNext(3);

        subject.onNext(4);
        subject.onNext(5);
        subject.onCompleted();

        //06-14 14:00:21.339 18362-18362/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No4Subject: onNext__observer10//有默认值

        //06-14 14:00:21.339 18362-18362/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No4Subject: onNext__observer11
        //06-14 14:00:21.339 18362-18362/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No4Subject: onNext__observer12
        //06-14 14:00:21.339 18362-18362/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No4Subject: onNext__observer13
        //06-14 14:00:21.339 18362-18362/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No4Subject: onNext__observer23
        //06-14 14:00:21.339 18362-18362/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No4Subject: onNext__observer14
        //06-14 14:00:21.339 18362-18362/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No4Subject: onNext__observer24
        //06-14 14:00:21.339 18362-18362/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No4Subject: onNext__observer15
        //06-14 14:00:21.339 18362-18362/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No4Subject: onNext__observer25
        //06-14 14:00:21.340 18362-18362/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No4Subject: onCompleted__observer1
        //06-14 14:00:21.340 18362-18362/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No4Subject: onCompleted__observer3
        //06-14 14:00:21.340 18362-18362/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No4Subject: onCompleted__observer2

    }

    /**
     * 场景三 todo PublishSubject
     *
     * PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者。
     * 需要注意的是，PublishSubject可能会一创建完成就立刻开始发射数据（除非你可以阻止它发生）
     * ，因此这里有一个风险：在Subject被创建后到有观察者订阅它之前这个时间段内，
     * 一个或多个数据可能会丢失。如果要确保来自原始Observable的所有数据都被分发，
     * 你需要这样做：或者使用Create创建那个Observable以便手动给它引入"冷"Observable的行为
     * （当所有观察者都已经订阅时才开始发射数据），或者改用ReplaySubject。
     *
     * 如果原始的Observable因为发生了一个错误而终止，PublishSubject将不会发射任何数据，只是简单的向前传递这个错误通知。
     */
    public static void Subject1PublishSubject() {
        PublishSubject<Integer> subject = PublishSubject.create();
        Observer<Integer> observer1 = new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d("No4Subject", "onCompleted__observer1");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("No4Subject", "onError__observer1");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d("No4Subject", "onNext__observer1" + integer);
            }
        };
        Observer<Integer> observer2 = new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d("No4Subject", "onCompleted__observer2");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("No4Subject", "onError__observer2");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d("No4Subject", "onNext__observer2" + integer);
            }
        };
        subject.subscribe(observer1);
        subject.onNext(1);
        subject.onNext(2);
        subject.onNext(3);
        subject.subscribe(observer2); //observer2只能收到3之后的 事件
        subject.onNext(4);
        subject.onNext(5);
        subject.onCompleted();

        //06-14 14:20:40.238 7773-7773/? D/No4Subject: onNext__observer11
        //06-14 14:20:40.238 7773-7773/? D/No4Subject: onNext__observer12
        //06-14 14:20:40.238 7773-7773/? D/No4Subject: onNext__observer13
        //06-14 14:20:40.238 7773-7773/? D/No4Subject: onNext__observer14
        //06-14 14:20:40.238 7773-7773/? D/No4Subject: onNext__observer24
        //06-14 14:20:40.238 7773-7773/? D/No4Subject: onNext__observer15
        //06-14 14:20:40.238 7773-7773/? D/No4Subject: onNext__observer25
        //06-14 14:20:40.238 7773-7773/? D/No4Subject: onCompleted__observer1
        //06-14 14:20:40.238 7773-7773/? D/No4Subject: onCompleted__observer2
    }


    /**
     * 场景四 todo ReplaySubject
     * ReplaySubject会发射所有来自原始Observable的数据给观察者，无论它们是何时订阅的。
     * 也有其它版本的ReplaySubject，在重放缓存增长到一定大小的时候或过了一段时间后会丢弃旧的数据（原始Observable发射的）。
     *
     * // TODO: 冷热判断    <>冷</>
     */
    public static void Subject1ReplaySubject() {

        //ReplaySubject<Integer> subject = ReplaySubject.create();
        //ReplaySubject<Integer> subject = ReplaySubject.create(2);//设置buffer 为2

        ReplaySubject<Integer> subject = ReplaySubject.createWithSize(2);//设置重放缓存为2 超过的部分会被丢弃旧
        //06-14 14:51:03.381 7683-7683/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No4Subject: onNext__observer14
        //06-14 14:51:03.381 7683-7683/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No4Subject: onNext__observer15
        //06-14 14:51:03.381 7683-7683/zhangwenhao.kuafu.com.kuafu_retrofit_exercise D/No4Subject: onCompleted__observer1

        //ReplaySubject<Integer> subject = ReplaySubject.createUnbounded();//这个方法还有带研究 这里非public的
        subject.onNext(1);
        subject.onNext(2);
        subject.onNext(3);
        subject.onNext(4);
        subject.onNext(5);
        subject.onCompleted();
        Observer<Integer> observer1 = new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d("No4Subject", "onCompleted__observer1");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("No4Subject", "onError__observer1");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d("No4Subject", "onNext__observer1" + integer);
            }
        };

        subject.subscribe(observer1);
    }

}
