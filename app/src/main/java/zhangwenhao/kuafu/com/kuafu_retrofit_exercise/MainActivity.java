package zhangwenhao.kuafu.com.kuafu_retrofit_exercise;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import zhangwenhao.kuafu.com.kuafu_retrofit_exercise.factory.DataFactory;
import zhangwenhao.kuafu.com.kuafu_retrofit_exercise.model.Course;
import zhangwenhao.kuafu.com.kuafu_retrofit_exercise.model.Student;
import zhangwenhao.kuafu.com.kuafu_retrofit_exercise.rxJava.No6Operator26Window;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    private ProgressBar mProgressBar;

    private ImageView mImageViewm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        mImageViewm = (ImageView) findViewById(R.id.image);
        //testFuncation(9);//RxJava基础概念的练习

        //No3Single.Single1()};

        //No4Subject.Subject1AsyncSubject();
        //No4Subject.Subject1BehaviorSubject();
        //No4Subject.Subject1PublishSubject();
        //No4Subject.Subject1ReplaySubject();

        //No5Schedulers.Scheduler1();
        //No5Schedulers.Scheduler2();

        //No6Operator1.No6Operator1Defer();
        //No6Operator1.No6Operator1SwitchCase();
        //No6Operator1.No6Operator1Empty();
        //No6Operator1.No6Operator1Never();
        //No6Operator1.No6Operator1Throw();
        //No6Operator1.No6Operator1From();
        //No6Operator1.No6Operator1FromFuture();
        //No6Operator1.No6Operator1From1();
        //No6Operator1.No6Operator1Interval();
        //No6Operator1.No6Operator1Range();
        //No6Operator1.No6Operator1Repeat();
        //No6Operator1.No6Operator1Start();
        //No6Operator1.No6Operator1toAsync();
        //No6Operator1.No6Operator1Timer();
        //No6Operator21.No6Operator21Buffer();
        //No6Operator21.No6Operator21Buffer1();
        //No6Operator21.No6Operator21Buffer2();
        //No6Operator21.No6Operator21Buffer3();
        //No6Operator21.No6Operator21Buffer4();
        //No6Operator21Buffer.No6Operator21Buffer5();
        //No6Operator22FlatMap.flatMap();
        //No6Operator22FlatMap.flatMap2();
        //No6Operator22FlatMap.flatMap3();
        //No6Operator22FlatMap.concatMap();
        //No6Operator23GroupBy.gropBy();
        //No6Operator23GroupBy.gropBy1();
        //No6Operator24Map.map();
        //No6Operator24Map.cast();
        //No6Operator24Map.encode();
        //No6Operator24Map.byLine();
        //No6Operator25Scan.scan();
//        No6Operator25Scan.scan1();
//        No6Operator26Window.window();
//        No6Operator26Window.window1();
//        No6Operator26Window.window2();
//        No6Operator26Window.window3();
//        No6Operator26Window.window4();
        No6Operator26Window.window5();
    }


    private void testFuncation(int i) {

        switch (i) {
            case 0:
                method0();
                break;
            case 1:
                method1();
                break;
            case 2:
                method3();
                break;
            case 3:
                method4();
                break;
            case 4:
                method5();
                break;
            case 5:
                method6();
                break;
            case 6:
                method7();
                break;
            case 7:
                method8();
                break;
            case 8:
                method9();
                break;
            case 9:
                method10();
                break;

        }

    }


    /**
     * observable 的 create
     */
    private void method0() {
        //创建observable
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("RxJava");
                subscriber.onNext("!");
                subscriber.onCompleted();
            }
        });

        //创建observer
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onNext(String s) {

                Log.d(TAG, "onNext: " + s);
            }
        };

        //订阅
        observable.subscribe(observer);

    }

    /**
     * just
     */
    private void method1() {
        Observable<String> observable = Observable.just("hello", "RxJAVA", "!");

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: " + s);
            }
        };

        observable.subscribe(observer);
    }

    /**
     * from
     */
    private void method3() {
        String[] array = new String[]{"Hello", "rxJAVA", "!"};
        Observable<String> observable = Observable.from(array);

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: ");
            }
        };

        observable.subscribe(observer);
    }

    /**
     * user Action0 & 1  auto create oberser
     */
    private void method4() {

        Observable<String> observable = Observable
                .just("hello", "rxjava", "auto", "create", "observer");

        //use action0 no parameter
        Action0 onCompletedAction = new Action0() {
            @Override
            public void call() {
                Log.d(TAG, "call: onCompletedAction");
            }
        };

        //user action1 have 1 parameter
        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, "onNextAction:" + s);
            }
        };

        //use action1 have 1 parameter
        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.d(TAG, "call: onErrorAction" + throwable.toString());
            }
        };

        observable.subscribe(onNextAction, onErrorAction, onCompletedAction);


    }

    /**
     * map 变换
     * map change
     * <p/>
     * 需求：加载图片的时候在主线程显示progressbar
     * 最后将图片显示在主线程
     * <p/>
     * 细节：progressbar  和 imageview 在主线程操作
     * <p/>
     * 总结：
     * 1：使用map变换是将Func1（参数类型1，参数类型2）  是将参数类型1 变换为参数类型2
     * 这个参数类型2 会传递给observer
     * <p/>
     * 2：这里涉及了Thread的切换
     * subscribeOn ：指定 subscribe（）发生的线程
     * observeOn: 指定 observer 中方法执行的线程
     * <p/>
     * Schedulers.immediate(): 直接在当前线程运行，相当于不指定线程。这是默认的 Scheduler。
     * Schedulers.newThread(): 总是启用新线程，并在新线程执行操作。
     * Schedulers.io(): I/O 操作（读写文件、读写数据库、网络信息交互等）所使用的 Scheduler。行为模式和 newThread() 差不多，区别在于 io()
     * 的内部实现是是用一个无数量上限的线程池，可以重用空闲的线程，因此多数情况下 io() 比 newThread() 更有效率。不要把计算工作放在 io() 中，可以避免创建不必要的线程。
     * Schedulers.computation(): 计算所使用的 Scheduler。这个计算指的是 CPU 密集型计算，即不会被 I/O 等操作限制性能的操作，例如图形的计算。这个
     * Scheduler 使用的固定的线程池，大小为 CPU 核数。不要把 I/O 操作放在 computation() 中，否则 I/O 操作的等待时间会浪费 CPU。
     * 另外， Android 还有一个专用的 AndroidSchedulers.mainThread()，它指定的操作将在 Android 主线程运行
     * <p/>
     * <p/>
     * todo ：这里有一个细节 doOnSubscribe 的call方法 是执行在Main线程中的
     * todo ：如果在 doOnSubscribe() 之后有 subscribeOn() 的话，它将执行在离它最近的 subscribeOn() 所指定的线程
     * todo ：如果在 doOnSubscribe() 之后没有subscribeOn 的话，他将执行在当前线程中。
     */
    private void method5() {

        final int chinaoldconan = R.drawable.chinaoldconan;

        Observable.just(chinaoldconan).map(new Func1<Integer, Drawable>() {
            @Override
            public Drawable call(Integer integer) {
                return getResources().getDrawable(chinaoldconan);
            }
        }).subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        if (mProgressBar != null) {
                            mProgressBar.setVisibility(View.VISIBLE);
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Drawable>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Drawable o) {
                        if (mProgressBar != null) {
                            mProgressBar.setVisibility(View.GONE);
                        }
                        if (mImageViewm != null) {
                            mImageViewm.setImageDrawable(o);
                        }
                    }
                });

    }


    /**
     * copy 别人代码 造数据
     */
    private void method6() {
        ArrayList<Student> students = DataFactory.getData();
        int size = students.size();
        for (int i = 0; i < size; i++) {
            Log.d(TAG, "姓名:" + students.get(i).name);
            int sizeCourses = students.get(i).courses.size();
            for (int j = 0; j < sizeCourses; j++) {
                Log.d(TAG, "课程:" + students.get(i).courses.get(j).name);
            }
        }
    }

    /**
     * copy 别人代码
     */
    private void method7() {
        //just(T...): 将传入的参数依次发送出来,实现遍历的目的
        Observable.from(DataFactory.getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Student>() {
                    @Override
                    public void call(Student student) {
                        Log.d(TAG, "观察者:" + student.name);
                    }
                });
    }


    /**
     * flatmap
     * <p/>
     * 应用场景 ：一个对象 经过map变换之后 的结果还是一个数组 或者 集合
     * 为了在对这个数组或者集合遍历 使用flatmap
     * <p/>
     * 怎么理解这个玩意呢：
     * 不看源码的理解：还是可以当做for循环来理解 ：如这里的 没传入一个student对象，就变换一次，
     * 将student对象变换新的Observable ，再由这个 新observable 传递数据，直到传递完成，再由 老observable继续传递数据
     * <p/>
     * FlatMap对这些Observables发射的数据做的是合并(merge)操作，因此它们可能是交错的。
     * 注意：如果任何一个通过这个flatMap操作产生的单独的Observable调用onError异常终止了，这个Observable自身会立即调用onError并终止。
     * 暂时先这样 回头研究下源码  lift（） 具体实现
     */
    private void method8() {
        Observable.from(DataFactory.getData())
                .flatMap(new Func1<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Student student) {
                        return Observable.from(student.courses);
                    }
                }).subscribe(new Observer<Course>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onNext(Course course) {
                Log.d(TAG, "onNext: " + course);
            }
        });
    }


    /**
     * 1:尝试取消注册
     * 2:强行在onNext中unsubscribe
     */
    private void method9() {
        Observable.from(DataFactory.getData())
                .flatMap(new Func1<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Student student) {
                        return Observable.from(student.courses);
                    }
                }).subscribe(new Subscriber<Course>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
                Log.d(TAG, "unsubscribe " + isUnsubscribed());
                if (!this.isUnsubscribed()) {
                    unsubscribe();
                    Log.d(TAG, "unsubscribe " + isUnsubscribed());
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onNext(Course course) {
                Log.d(TAG, "onNext: " + course);
                Log.d(TAG, "onNext " + isUnsubscribed());
               /* if (!this.isUnsubscribed()) {
                    unsubscribe();
                    Log.d(TAG, " onNext unsubscribe ");
                }*/
            }
        });
    }

    //====================================变幻===========================================//

    /**
     * buffer
     * 测试 buffer ( count , skip)
     * buffer(count, skip)从原始Observable的第一项数据开始创建新的缓存，此后每当收到skip项数据，
     * 用count项数据填充缓存：开头的一项和后续的count-1项，它以列表(List)的形式发射缓存，
     * 取决于count和skip的值，这些缓存可能会有重叠部分（比如skip
     * < count时），也可能会有间隙（比如skip > count时）
     * 1:count>skip
     * .buffer(5, 3)分别打印onNext: [1, 2, 3, 4, 5][4, 5, 6, 7, 8][7, 8, 9, 10]
     * 2:count<skip
     * buffer(2,3) 分别打印onNext: [1, 2] [4, 5] [7, 8] [10]
     * 3:count=skip
     * buffer(3,3) 分别打印onNext: [1, 2, 3] [4, 5, 6] [7, 8, 9][10]
     */
    private void method10() {

        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Observable<List<Integer>> observable1 = observable.buffer(5, 3);

        observable1.subscribe(new Observer<List<Integer>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onNext(List<Integer> integers) {
                Log.d(TAG, "onNext: " + integers);
            }
        });

   /*     observable1.buffer(2,2).subscribe(new Observer<List<List<Integer>>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onNext(List<List<Integer>> lists) {
                Log.d(TAG, "onNext: " + lists);
            }
        });
*/

    }

    /**
     * 这个暂时跳过
     */
    private void method11() {
        Integer[] arrary = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Observable<Integer> observable = Observable.from(arrary);

        observable.buffer(new Func0<Observable<Integer>>() {
            @Override
            public Observable<Integer> call() {
                Log.d(TAG, "call: ");
                return null;
            }
        });

    }


}
