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
    private  static final String TAG = "Main Thread";
    private Handler mMainHandler =null;
    private TextView info;
    private Button msgBtn;
    private Button btn1;
    private int nclick=0;
    ChildThread child1;
    ChildThread child2;
    class ChildThread extends Thread {
        private Handler childHandler = null;
        private int nClickTimes = 0;
        private static final String CHILD_TAG = "childThread";

        @Override
        public void run() {
            this.setName("ChildThread");
//            初始化信息队列
            Looper.prepare();
            childHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    Log.i(CHILD_TAG, "childThread got msg from mainThread-" + msg.obj);
                    try {
                        // 在子线程中可以做一些耗时的工作
                        String sMsg = "";
                        sleep(1000);
                        Message toMain = new Message();
                        // mMainHandler.obtainMessage();
                        sMsg = String.valueOf(++nClickTimes);
                        toMain.obj = sMsg + "This is "
                                + this.getLooper().getThread().getName()
                                + ".  你发送了消息: \"" + (String) msg.obj + "\"?"
                                + "这是第" + sMsg + "次";
                        mMainHandler.sendMessage(toMain);
                        Message toChild = new Message();
                        toChild.obj = "over";
                        // mChildHandler.sendMessage(toChild);
                        Log.i(CHILD_TAG, "childHandler Send a mesg to the mainThread - "
                                + (String) toMain.obj);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                };
            };
//            启动子线程消息循环队列
            Looper.loop();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        info = (TextView) findViewById(R.id.info);
        msgBtn = (Button) findViewById(R.id.msgBtn);
        btn1 = (Button) findViewById(R.id.button6);
        mMainHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                Log.i(TAG, "mMainHandler got the msg from childThread: - "
                        + (String) msg.obj);

                // 接收子线程的消息
                info.setText((String) msg.obj);//String对msg.obj进行了强制类型转换
            }

        };
        child1 = new ChildThread();
        child1.start();

        child2 = new ChildThread();
        child2.start();

        msgBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (child1.childHandler != null) {

                    // 发送消息给子线程
                    Message childMsg = child1.childHandler.obtainMessage();
                    Message msg = new Message();
                    msg.obj = "mMainHandler.getLooper().getThread().getName():"+mMainHandler.getLooper().getThread().getName()
                            + ";由线程1按钮点击触发";

                    child1.childHandler.sendMessage(msg);
                    Log.i(TAG, "child1.childHandler.sendMessage(msg) "
                            + (String) msg.obj);
                }
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (child2.childHandler != null) {

                    // 发送消息给子线程  方法一
/*                    Message msg = new Message();
                    msg.obj = "mMainHandler.getLooper().getThread().getName():"+mMainHandler.getLooper().getThread().getName()
                            + ";由线程2按钮点击触发";
                    child2.childHandler.sendMessage(msg);*/
                    //发送消息给子线程  方法二
                    Message msg = child2.childHandler.obtainMessage();
                    msg.obj = "mMainHandler.getLooper().getThread().getName():"+mMainHandler.getLooper().getThread().getName()
                            + ";由线程2按钮点击触发";
                    msg.sendToTarget();
                    Log.i(TAG, "child2.childHandler.sendMessage(msg) - "
                            + (String) msg.obj);
                }
            }
        });
    }

    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Stop looping the child thread's message queue");

        if (child1.childHandler != null) {
            child1.childHandler.getLooper().quit();
        }
        if (child2.childHandler != null) {
            child2.childHandler.getLooper().quit();
        }
    }
}
