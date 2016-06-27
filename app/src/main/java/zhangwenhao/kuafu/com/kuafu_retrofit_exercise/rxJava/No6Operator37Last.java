package zhangwenhao.kuafu.com.kuafu_retrofit_exercise.rxJava;

/**
 * Created by zhangwenhao on 2016/6/27.
 * call me
 * email:zhangwenhao@iboxpay.com
 * QQ:1160113614
 */
public class No6Operator37Last {
    /**
     * 如果你只对Observable发射的最后一项数据，或者满足某个条件的最后一项数据感兴趣，你可以使用Last操作符。

     在某些实现中，Last没有实现为一个返回Observable的过滤操作符，而是实现为一个在当时就发射原始Observable指定数据项的阻塞函数。
     在这些实现中，如果你想要的是一个过滤操作符，最好使用TakeLast(1)。

     在RxJava中的实现是last和lastOrDefault。

     可能容易混淆，BlockingObservable也有名叫last和lastOrDefault的操作符，它们会阻塞并返回值，不是立即返回一个Observable。
     *
     * 具体看 first 类比下
     */
}
