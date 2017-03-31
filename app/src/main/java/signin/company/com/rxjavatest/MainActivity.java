package signin.company.com.rxjavatest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import signin.company.com.rxjavatest.operator.CreateOperator;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    Button bt;
    EditText et;
    CreateOperator createOperator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.id_tv);
        bt = (Button) findViewById(R.id.id_bt);
        et = (EditText) findViewById(R.id.id_et);
//        createOperator = new CreateOperator();
//        createOperator.testJust();
        createOperator.testFrom();

        testNewRxJava2();

    }

    public void testNewRxJava() {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                e.onNext(Integer.valueOf(1));
                e.onNext(Integer.valueOf(2));
                e.onNext(Integer.valueOf(3));
            }
        }, BackpressureStrategy.LATEST)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        Log.v("TAG", "onSubscribe");
                        s.request(3);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.v("TAG", "onNext值："+integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.v("TAG", "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.v("TAG", "onComplete()");
                    }
                });

    }

    public void testNewRxJava2() {

        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                for (int i = 0; ; i++) {
                    e.onNext(i);
                    Log.v("TAG", "send Subscribe"+i);
                }
            }
        }, BackpressureStrategy.LATEST)
                .subscribe(new Subscriber<Integer>() {
                    Subscription s1 = null;
                    @Override
                    public void onSubscribe(Subscription s) {
                        Log.v("TAG", "onSubscribe");
                        s.request(96);
                        s1 = s;
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.v("TAG", "onNext值："+integer);
                        if (integer == 0) {
                            s1.request(96);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.v("TAG", "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.v("TAG", "onComplete()");
                    }
                });

    }
}
