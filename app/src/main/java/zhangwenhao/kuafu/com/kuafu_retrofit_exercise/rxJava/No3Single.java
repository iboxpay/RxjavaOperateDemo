package zhangwenhao.kuafu.com.kuafu_retrofit_exercise.rxJava;

import android.util.Log;

import rx.Single;
import rx.SingleSubscriber;

/**
 * Created by zhangwenhao on 2016/6/14.
 * call me
 * email:zhangwenhao@iboxpay.com
 * QQ:1160113614
 */
public class No3Single {


    /**
     * RxJava（以及它派生出来的RxGroovy和RxScala）中有一个名为Single的Observable变种。
     * Single类似于Observable，不同的是，它总是只发射一个值，或者一个错误通知，而不是发射一系列的值。
     * 因此，不同于Observable需要三个方法onNext, onError, onCompleted，订阅Single只需要两个方法：
     * onSuccess - Single发射单个的值到这个方法
     * onError - 如果无法发射需要的值，Single发射一个Throwable对象到这个方法
     * todo  Single只会调用这两个方法中的一个，而且只会调用一次，调用了任何一个方法之后，{订阅关系终止}。
     *
     * 基本用法
     */
    public static void Single1() {
        Single.create(new Single.OnSubscribe<Integer>() {
            @Override
            public void call(SingleSubscriber<? super Integer> singleSubscriber) {
                //A:成功
                //singleSubscriber.onSuccess(1);
                //B:出错
                singleSubscriber.onError(new Throwable());
            }
        }).subscribe(new SingleSubscriber<Integer>() {
            @Override
            public void onSuccess(Integer value) {
                Log.d("No3Single", "onSuccess");
            }

            @Override
            public void onError(Throwable error) {
                Log.d("No3Single", "onError");
            }
        });
    }

    /**
     *
     * single 操作符说明
     *
     * 操作符	                返回值                   	说明
     compose	                Single	                        创建一个自定义的操作符
     concat and concatWith	Observable	连接多个Single和Observable发射的数据
     create		        Single                调用观察者的create方法创建一个Single
     error		        Single          返回一个立即给订阅者发射错误通知的Single
     flatMap		        Single                返回一个Single，它发射对原Single的数据执行flatMap操作后的结果
     flatMapObservable	        Observable	返回一个Observable，它发射对原Single的数据执行flatMap操作后的结果
     from                       Single          将Future转换成Single
     just                       Single          返回一个发射一个指定值的Single
     map                        Single          返回一个Single，它发射对原Single的数据执行map操作后的结果
     merge                      Single          将一个Single(它发射的数据是另一个Single，假设为B)转换成另一个Single(它发射来自另一个Single(B)的数据)
     merge and mergeWith        Observable	合并发射来自多个Single的数据
     observeOn	                Single	         指示Single在指定的调度程序上调用订阅者的方法
     onErrorReturn	        Single   	将一个发射错误通知的Single转换成一个发射指定数据项的Single
     subscribeOn	        Single   	指示Single在指定的调度程序上执行操作
     timeout             	Single	        它给原有的Single添加超时控制，如果超时了就发射一个错误通知
     toSingle             	Single	        将一个发射单个值的Observable转换为一个Single
     zip and zipWith	        Single    	将多个Single转换为一个，后者发射的数据是对前者应用一个函数后的结果
     */
}

