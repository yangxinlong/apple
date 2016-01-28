package jewelry.apple;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import jewelry.intent.Student;

public class Main6Activity extends AppCompatActivity {
    private Button btn;
    private Intent intent;
    private View.OnClickListener onClickListener;
    private Student zhang;
    private Student li;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        btn= (Button) findViewById(R.id.goActive7);
        btn.setOnClickListener(this.getOnClickListener());
    }

    public View.OnClickListener getOnClickListener() {
        onClickListener= new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Main6Activity.this,Main7Activity.class);
//                表单上的内容都要封装到你创建的对象容器里，对象容器负责传递给数据库操作或者想这样的intent转换
                zhang = new Student("张三","男","15531068910");
                li = new Student("李四","女","13811017758");
                intent.putExtra("zhang",zhang);
                intent.putExtra("li",li);
                startActivity(intent);
            }
        };
        return onClickListener;
    }
}
