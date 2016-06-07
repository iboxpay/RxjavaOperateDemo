package zhangwenhao.kuafu.com.kuafu_retrofit_exercise;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

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
        testFuncation(4);//RxJava基础概念的练习
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

        Observable<String> observable = Observable.just("hello", "rxjava", "auto", "create", "observer");

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
}
