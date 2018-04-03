package application.app.launchmodetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by lwz on 2018/4/3.
 */

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //跳转到MainActivity页面
    public void ToMainActivity(View v) {
        startActivity(new Intent(this, StandardActivity.class));
    }

    //跳转到SingleTopActivity页面
    public void ToSingleTopActivity(View v) {
        startActivity(new Intent(this, SingleTopActivity.class));
    }

    //跳转到SingleTaskActivity页面
    public void ToSingleTaskActivity(View v) {
        startActivity(new Intent(this, SingleTaskActivity.class));
    }

    //跳转到SingleInstanceActivity页面
    public void ToSingleInstanceActivity(View v) {
        startActivity(new Intent(this, SingleInstanceActivity.class));
    }

    //关闭页面页面
    public void ToFinish(View v) {
        finish();
    }
}
