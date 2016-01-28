package jewelry.apple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import jewelry.intent.Student;

public class Main7Activity extends AppCompatActivity {
    private TextView liShow;
    private TextView zhangShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        liShow= (TextView) findViewById(R.id.li);
        zhangShow= (TextView) findViewById(R.id.zhang);
        Student zhang = getIntent().getParcelableExtra("zhang");
        Student li =getIntent().getParcelableExtra("li");
        Log.i("李四","姓名："+li.getName()+"--性别："+li.getSex()+"--电话："+li.getPhone());
        Log.i("张三", "姓名：" + zhang.getName() + "--性别：" + zhang.getSex() + "--电话：" + zhang.getPhone());
        liShow.setText(li.getName() + "--性别：" + li.getSex() + "--电话：" + li.getPhone());
        zhangShow.setText(zhang.getName() + "--性别：" + zhang.getSex() + "--电话：" + zhang.getPhone());
    }
}
