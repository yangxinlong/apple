package jewelry.sellTicket;

import android.os.Handler;
import android.os.HandlerThread;
import android.widget.TextView;


/**
 * Created by yang on 16-1-20.
 */
public class ticket implements Runnable {
    /**
     * Starts executing the active part of the class' code. This method is
     * called when a thread is started that has been created with a class which
     * implements {@code Runnable}.
     */
    private int ticket;
    private TextView textView;
    private Handler handler;
    public ticket(int ticket,TextView textview,Handler handler){
        this.ticket = ticket;
        this.textView =textView;
        this.handler=handler;
    }
    public void run() {
        while (ticket>0){
            textView.append((CharSequence) Thread.currentThread());
            handler.post(this);
        }
    }
}
