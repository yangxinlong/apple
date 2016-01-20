package jewelry.apple;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;
import jewelry.sellTicket.ticket;


public class MainActivity extends AppCompatActivity {
    private TextView text_view = null;
    private int ticket;
/*
    private Button start = null;
    private Button end = null;
*/


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text_view = (TextView)findViewById(R.id.text_view);
        ticket = 30;
        ticket sellTicket = new ticket(ticket,text_view);
        Thread thd1 = new Thread(sellTicket);
        Thread thd2 = new Thread(sellTicket);
        Thread thd3 = new Thread(sellTicket);
        thd1.start();
        thd2.start();
        thd3.start();
       /* start = (Button)findViewById(R.id.start);
        start.setOnClickListener(new StartClickListener());
        end = (Button)findViewById(R.id.end);
        end.setOnClickListener(new EndClickListener());
*/
    }

    //使用handler时首先要创建一个handler
/*    Handler handler = new Handler();
    //要用handler来处理多线程可以使用runnable接口，这里先定义该接口
    //线程中运行该接口的run函数
    Runnable update_Thread = new Runnable()
    {
        public void run()
        {
            //线程每次执行时输出"UpdateThread..."文字,且自动换行
            //textview的append功能和Qt中的append类似，不会覆盖前面
            //的内容，只是Qt中的append默认是自动换行模式
            text_view.append("\nUpdateThread...");
            //延时1s后又将线程加入到线程队列中
            handler.postDelayed(update_Thread, 2000);
        }
    };

    private class StartClickListener implements View.OnClickListener
    {
        public void onClick(View v) {
                handler.post(update_Thread);
                }

        }

    private class EndClickListener implements View.OnClickListener
    {
        public void onClick(View v) {
            handler.removeCallbacks(update_Thread);
        }

    }*/

}
