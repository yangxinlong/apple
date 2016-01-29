package jewelry.apple;



import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import jewelry.a;
import jewelry.intent.Student;

public class MainActivity extends AppCompatActivity {
    private Intent intent;
    private Student li;
    private Student zhang;
    private View.OnClickListener onClickListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        li =  new Student("李四","男","13811017758");
        zhang=new Student("张三","女","13131025171");
        intent =new Intent();
        intent.setAction("jewelry.apple.intent.custome");
        intent.putExtra("li",li);
        intent.putExtra("zhang",zhang);
    }
    public void sendCustomeIntent(View view){
//        Context bctx = getBaseContext();
//        Context actx = getApplicationContext();
        sendBroadcast(intent);
    }


}
