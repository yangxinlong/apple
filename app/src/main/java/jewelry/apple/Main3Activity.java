package jewelry.apple;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
    private Button btn1;
    private Button btn2;
    private TextView info;
    private Handler mhandler;
    private childThread childThread1;
    private childThread childThread2;

    class childThread extends Thread {
        private int ncNumber = 0;
        private Handler childHandler;

        @Override
        public void run() {
            this.setName("这个是this.setName()");
            Looper.prepare();
            childHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    try {
//                        对得到的数据进行加工封装
                        String sMsg = "";
                        sleep(500);
                        sMsg = String.valueOf(++ncNumber);
                        Message toMain = new Message();
                        toMain.obj = sMsg + this.getLooper().getThread().getName() + msg;
//                        加工好的数据由mhandler发送给主线程mhandler
                        mhandler.sendMessage(toMain);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
             Looper.loop();
        }
    }

    View.OnClickListener onclicklistener1 = new View.OnClickListener() {
        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            Message msg = new Message();
            msg.obj = "bnt1被触发::" + mhandler.getLooper().getThread().getName();
            childThread1.childHandler.sendMessage(msg);
        }
    };
    View.OnClickListener onclicklistener2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Message msg =new Message();
            msg.obj = "btn2被触::"+mhandler.getLooper().getThread().getName();
            childThread2.childHandler.sendMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        btn1 = (Button) findViewById(R.id.msgBtn);
        btn2 = (Button) findViewById(R.id.msgBtn2);
        info = (TextView) findViewById(R.id.info);
        mhandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                info.setText((String) msg.obj);
            }
        };
        childThread1 = new childThread();
        childThread2 = new childThread();
        childThread1.start();
        childThread2.start();
        btn1.setOnClickListener(onclicklistener1);
        btn2.setOnClickListener(onclicklistener2);
    }

    protected void onDestory() {
        super.onDestroy();
        if (childThread1.childHandler != null) {
            childThread1.childHandler.getLooper().quit();
        }
        if (childThread2.childHandler != null) {
            childThread2.childHandler.getLooper().quit();
        }
    }
}
