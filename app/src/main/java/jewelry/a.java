package jewelry;

import android.content.Context;
import android.content.Intent;

import jewelry.apple.Main7Activity;

/**
 * Created by yang on 16-1-28.
 */
public class a {
    private Context context;

    public a(Context context) {
        this.context = context;
    }

    public void test(){
        context.startActivity(new Intent(context, Main7Activity.class));
    }
}
