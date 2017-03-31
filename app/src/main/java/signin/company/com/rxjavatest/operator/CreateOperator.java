package signin.company.com.rxjavatest.operator;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liuqun on 2017/3/14.
 */

public class CreateOperator {
    /*
    * just
    * from
    *
    *
    * */


    //接受一个到九个参数，原样发射
    public void testJust() {
        Observable.just(1, 2, 3).subscribeOn(Schedulers.computation()).observeOn(Schedulers.computation()).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.v("TAG", String.valueOf(integer));
            }
        });
    }

    //发射一个数组，Futrue,iterable
    public void testFrom() {
        String[] arrayList = new String[4];
        arrayList[0] = "one";
        arrayList[1]= "two";
        arrayList[2]= "three";
        arrayList[3]= "four";
        Observable.fromArray(arrayList).
                subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String value) {
                        Log.v("TAG", value.toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
