package jewelry.apple;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btn1;
    private Button btn2;
    private boolean isoncl;
//    下面的这些部分都相当于变量的设定所以最后的部分都是需要打上双引号的.
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    btn1.setText(R.string.text2);
                    break;
                default:
                    break;
            }
        }
    };
    Thread thread =new Thread(new Runnable() {
        @Override
        public void run() {
            Message message = new Message();
            message.what=1;
            handler.sendMessage(message);
        }
    });
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(isoncl){
                thread.start();
                isoncl=false;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1= (Button) findViewById(R.id.button);
        btn2= (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(clickListener);
    }
}
