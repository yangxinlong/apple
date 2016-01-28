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
        private Button windowB;
        private Button windowC;
        private Button windowBclose;
        private Button windowCclose;
        private boolean onclick1 = true;
        private boolean onclick2 = true;
        private TextView textView;
        Handler mhandler;
        Runnable rn;
        Thread thread1;
        Thread thread2;
        Thread thread3;
        View.OnClickListener onClickListener1 = new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                if (onclick1) {
                    thread2.start();
                    onclick1=false;
                }
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
                if (onclick2){
                thread3.start();
                onclick2=false;
                }
            }
        };
    View.OnClickListener onClickListener3 =new View.OnClickListener(){

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            thread2.interrupt();
        }
    };
    View.OnClickListener onClickListener4 =new View.OnClickListener(){

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            thread3.interrupt();
        }
    };
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
            textView = (TextView) findViewById(R.id.textTicket);
            windowB = (Button) findViewById(R.id.button3);
            windowC= (Button) findViewById(R.id.button4);
            windowBclose = (Button) findViewById(R.id.button5);
            windowCclose = (Button) findViewById(R.id.button6);
            windowB.setOnClickListener(onClickListener1);
            windowC.setOnClickListener(onClickListener2);
            windowBclose.setOnClickListener(onClickListener3);
            windowCclose.setOnClickListener(onClickListener4);
            mhandler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    textView.setText((String)msg.obj);
                }
            };
            Runnable rn = new AutoSaleTicket(200,mhandler) ;
            thread1 = new Thread(rn,"thread1");
            thread2 = new Thread(rn,"thread2");
            thread3 = new Thread(rn,"thread3");
            thread1.start();


    }
}
