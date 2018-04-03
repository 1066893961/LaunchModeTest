package application.app.launchmodetest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by lwz on 2018/4/3.
 */

/**
 * 我们先从MainActivity中进入到SingleTopActivity，然后再跳转到OtherActivity中，
 * 再从OtherActivity中跳回SingleTopActivity，再从SingleTopActivity跳到SingleTopActivity中，
 * 看看整个过程的日志。
 */
public class OtherTopActivity extends BaseActivity {
    private Button jump;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_top);

        jump= (Button) findViewById(R.id.btn_other);
        jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OtherTopActivity.this, SingleTopActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * * 我们先从MainActivity中进入到SingleTopActivity，然后再跳转到OtherActivity中，
     * 再从OtherActivity中跳回SingleTopActivity，再从SingleTopActivity跳到SingleTopActivity中，
     * 看看整个过程的日志。
     *
     * 04-03 17:18:40.553 20260-20260/application.app.launchmodetest I/WooYun: *****onCreate()方法******
     04-03 17:18:40.553 20260-20260/application.app.launchmodetest I/WooYun: onCreate：MainActivity TaskId: 229 hasCode:37951280
     04-03 17:18:40.555 20260-20260/application.app.launchmodetest I/WooYun: taskAffinity:application.app.launchmodetest
     04-03 17:18:47.327 20260-20260/application.app.launchmodetest I/WooYun: *****onCreate()方法******
     04-03 17:18:47.328 20260-20260/application.app.launchmodetest I/WooYun: onCreate：SingleTopActivity TaskId: 229 hasCode:153182351
     04-03 17:18:47.328 20260-20260/application.app.launchmodetest I/WooYun: taskAffinity:application.app.launchmodetest
     04-03 17:18:50.672 20260-20260/application.app.launchmodetest I/WooYun: *****onCreate()方法******
     04-03 17:18:50.673 20260-20260/application.app.launchmodetest I/WooYun: onCreate：OtherTopActivity TaskId: 229 hasCode:88350823
     04-03 17:18:50.673 20260-20260/application.app.launchmodetest I/WooYun: taskAffinity:application.app.launchmodetest
     04-03 17:19:36.997 20260-20260/application.app.launchmodetest I/WooYun: *****onCreate()方法******
     04-03 17:19:36.997 20260-20260/application.app.launchmodetest I/WooYun: onCreate：SingleTopActivity TaskId: 229 hasCode:227139143
     04-03 17:19:36.998 20260-20260/application.app.launchmodetest I/WooYun: taskAffinity:application.app.launchmodetest
     04-03 17:19:47.030 20260-20260/application.app.launchmodetest I/WooYun: *****onNewIntent()方法*****
     04-03 17:19:47.031 20260-20260/application.app.launchmodetest I/WooYun: onNewIntent：SingleTopActivity TaskId: 229 hasCode:227139143
     04-03 17:19:47.032 20260-20260/application.app.launchmodetest I/WooYun: taskAffinity:application.app.launchmodetest

     */
}