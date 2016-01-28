package jewelry.apple;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btn1;
    private Button btn2;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent();
        intent.setAction("jewelry.apple.intent.custome");
        sendCustomeIntent1();
    }
    public void sendCustomeIntent(View view){
        sendBroadcast(intent);
    };
    public void sendCustomeIntent1(){
     sendBroadcast(intent);
    };

}
