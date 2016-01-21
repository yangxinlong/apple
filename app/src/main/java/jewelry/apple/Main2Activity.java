package jewelry.apple;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import jewelry.thread.AutoSaleTicket;

public class Main2Activity extends AppCompatActivity {
        private Button windowA;
        private Button windowB;
        private TextView textView;
        Handler handler =new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 1:
                    textView.setText("88888");
                        break;
                    default:
                        break;
                }
            }
        };
        Runnable rn = new AutoSaleTicket(200,handler) ;
        Thread thread1 = new Thread(rn,"thread1");
        Thread thread2 = new Thread(rn,"thread2");
        Thread thread3 = new Thread(rn,"thread3");
        View.OnClickListener onClickListener1 = new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                thread2.start();
            }
        };
        View.OnClickListener onClickListener2 = new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                thread3.start();
            }
        };
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
            textView = (TextView) findViewById(R.id.button);
            windowA = (Button) findViewById(R.id.button3);
            windowB= (Button) findViewById(R.id.button4);
            windowA.setOnClickListener(onClickListener1);
            windowB.setOnClickListener(onClickListener2);
            thread1.start();

    }
}
