package zhangwenhao.kuafu.com.kuafu_retrofit_exercise.rxJava;

/**
 * Created by zhangwenhao on 2016/6/27.
 * call me
 * email:zhangwenhao@iboxpay.com
 * QQ:1160113614
 */
public class No6Operator36IgnoreElement {

    /**
     * IgnoreElements操作符抑制原始Observable发射的所有数据，只允许它的终止通知（onError或onCompleted）通过。
     *
     * 如果你不关心一个Observable发射的数据，但是希望在它完成时或遇到错误终止时收到通知，你可以对Observable使用ignoreElements操作符，它会确保永远不会调用观察者的onNext()方法。
     *
     * RxJava将这个操作符实现为ignoreElements。
     *
     * Javadoc: ignoreElements())
     * ignoreElements默认不在任何特定的调度器上执行。
     */
    public static void ignoreElements() {

    }
}
