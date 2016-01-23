package jewelry.apple;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
    Handler mhandler ;
    private TextView textView;
    Button btn1;
    Button btn2;
    childThread childthread1;
    childThread childthread2;
    class childThread extends Thread{
        private Handler childhandler;
        private int nClickTimes=0;

        @Override
        public void run(){
            this.setName("ChildThread");
        Looper.prepare();
            childhandler = new Handler(){
                @Override
                public void handleMessage(Message msg) {

                    try {
                        //子线程对主线程发过来的数据进行加工
                        String sMsg="";
                        sleep(1000);
                        Message tomain =new Message();
                        sMsg= String.valueOf(++nClickTimes);
                        tomain.obj=sMsg+"--childThread--"+msg.obj;
                        //子线程加工好的数据由主线程Handler负责发送
                        mhandler.sendMessage(tomain);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            Looper.loop();
        }
    }

    View.OnClickListener clickListener1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (childthread1.childhandler!=null) {
                Message msg = childthread1.childhandler.obtainMessage();
                msg.obj = "mhandler.getLooper().getThread().getName()--::"
                        +mhandler.getLooper().getThread().getName()
                        +"由btn1点击出发";
                msg.sendToTarget();
            }
        }
    };
    View.OnClickListener clickListener2 =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (childthread2.childhandler!=null){
                Message msg = new Message();
                msg.obj="mhandler.getLooper().getThread().getName()--::"
                        +mhandler.getLooper().getThread().getName()
                        +"由btn2点击触发";
//                如果这个地方开始犯晕了用了mhandler那么最后就直接由mhandler来进行数据处理并显示
                childthread2.childhandler.sendMessage(msg);
/*                Message msg = childthread2.childhandler.obtainMessage();
                msg.obj = "mhandler.getLooper().getThread().getName()--::"
                        +mhandler.getLooper().getThread().getName()
                        +"由btn2点击出发";
                msg.sendToTarget();*/
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        btn1 = (Button) findViewById(R.id.msgBtn);
        btn2 = (Button) findViewById(R.id.msgBtn2);
        textView = (TextView) findViewById(R.id.info);
        mhandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                textView.setText((String) msg.obj);
            }
        };
        childthread1 = new childThread();
        childthread2=new childThread();
        //线程一开启,线程里面设置好的looper就开始不停的旋转,寻找机会找到message队列进行相关的数据处理
        childthread1.start();
        childthread2.start();
//        主线程通过handler向子线程发送数据
        btn1.setOnClickListener(clickListener1);
        btn2.setOnClickListener(clickListener2);
    }
}
