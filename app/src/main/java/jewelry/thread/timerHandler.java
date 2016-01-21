package jewelry.thread;

import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;

/**
 * Created by yang on 16-1-21.
 */
public class timerHandler implements Runnable {
    private Handler handler;
    public timerHandler(Handler handler){
            this.handler=handler;
    }
    /**
     * Starts executing the active part of the class' code. This method is
     * called when a thread is started that has been created with a class which
     * implements {@code Runnable}.
     */
    @Override
    public void run() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0x1234);
            }
        },0,1200);
    }
}
