package application.app.launchmodetest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * 该模式具备singleTask模式的所有特性外，与它的区别就是，这种模式下的Activity会单独占用一个Task栈，
 * 具有全局唯一性，即整个系统中就这么一个实例，由于栈内复用的特性，后续的请求均不会创建新的Activity实例，
 * 除非这个特殊的任务栈被销毁了。以singleInstance模式启动的Activity在整个系统中是单例的，如果在启动这
 * 样的Activiyt时，已经存在了一个实例，那么会把它所在的任务调度到前台，重用这个实例。
 */
public class SingleInstanceActivity extends BaseActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard);


        button = (Button)findViewById(R.id.standard_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("application.app.launchmodetest.lwz");
                startActivity(intent);
            }
        });
    }

    /**
     * 04-03 18:03:25.320 30537-30537/application.app.launchmodetest I/WooYun: *****onCreate()方法******
     04-03 18:03:25.321 30537-30537/application.app.launchmodetest I/WooYun: onCreate：MainActivity TaskId: 248 hasCode:140948454
     04-03 18:03:25.321 30537-30537/application.app.launchmodetest I/WooYun: taskAffinity:application.app.launchmodetest
     04-03 18:03:31.836 30537-30537/application.app.launchmodetest I/WooYun: *****onCreate()方法******
     04-03 18:03:31.837 30537-30537/application.app.launchmodetest I/WooYun: onCreate：SingleInstanceActivity TaskId: 249 hasCode:260176442
     04-03 18:03:31.838 30537-30537/application.app.launchmodetest I/WooYun: taskAffinity:application.app.launchmodetest
     04-03 18:03:45.258 30537-30537/application.app.launchmodetest I/WooYun: *****onNewIntent()方法*****
     04-03 18:03:45.259 30537-30537/application.app.launchmodetest I/WooYun: onNewIntent：SingleInstanceActivity TaskId: 249 hasCode:260176442
     04-03 18:03:45.260 30537-30537/application.app.launchmodetest I/WooYun: taskAffinity:application.app.launchmodetest

     */

}
