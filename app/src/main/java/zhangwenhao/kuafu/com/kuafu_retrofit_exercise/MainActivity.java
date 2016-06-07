package zhangwenhao.kuafu.com.kuafu_retrofit_exercise;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testFuncation(2);//RxJava基础概念的练习
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
}
