package jewelry.apple;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public Button button;
    private Button btn;
//    这个的作用就是用来让鼠标只能实现一次点击功能的
    private boolean isoncl=true;
    private Handler mHandler=new Handler()
    {
        public void handleMessage(Message msg)
        {
            switch(msg.what)
            {
                case 1:
                    button.setText(R.string.text2);
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };
    private Thread thread =new Thread(new Runnable() {
        @Override
        public void run() {
            Log.e("111","111111");
            Message message = new Message();
            message.what=1;
            mHandler.sendMessage(message);
        }
    });
    View.OnClickListener click = new View.OnClickListener(){

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            if (isoncl){
                thread.start();
                isoncl=false;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        btn = (Button) findViewById(R.id.button2);
        btn.setOnClickListener(click);
    }
}
