package application.app.launchmodetest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * 这个模式十分复杂，有各式各样的组合。在这个模式下，如果栈中存在这个Activity的实例就会复用这个Activity，
 * 不管它是否位于栈顶，复用时，会将它上面的Activity全部出栈，并且会回调该实例的onNewIntent方法。其实这个
 * 过程还存在一个任务栈的匹配，因为这个模式启动时，会在自己需要的任务栈中寻找实例，这个任务栈就是通过
 * taskAffinity属性指定。如果这个任务栈不存在，则会创建这个任务栈。
 */
public class SingleTaskActivity extends BaseActivity {
    private Button jump, jump2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        jump = (Button) findViewById(R.id.btn_task);
        jump2 = (Button) findViewById(R.id.btn_other);
        jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleTaskActivity.this, SingleTaskActivity.class);
                startActivity(intent);
            }
        });
        jump2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleTaskActivity.this, OtherTaskActivity.class);
                startActivity(intent);
            }
        });
    }
    /**不指定任何taskAffinity属性
     * Main-->task-->other-->task  返回直接到main
     *
     *
     当我们从MainActiviyty进入到SingleTaskActivity，再进入到OtherActivity后，此时栈中有3个Activity实例，
     并且SingleTaskActivity不在栈顶，而在OtherActivity跳到SingleTaskActivity时，并没有创建一个新的
     SingleTaskActivity，而是复用了该实例，并且回调了onNewIntent方法。并且原来的OtherActivity出栈了，
     *
     * 04-03 17:40:35.509 25392-25392/application.app.launchmodetest I/WooYun: *****onCreate()方法******
     04-03 17:40:35.510 25392-25392/application.app.launchmodetest I/WooYun: onCreate：MainActivity TaskId: 236 hasCode:140948454
     04-03 17:40:35.510 25392-25392/application.app.launchmodetest I/WooYun: taskAffinity:application.app.launchmodetest
     04-03 17:40:42.792 25392-25392/application.app.launchmodetest I/WooYun: *****onCreate()方法******
     04-03 17:40:42.793 25392-25392/application.app.launchmodetest I/WooYun: onCreate：SingleTaskActivity TaskId: 236 hasCode:260176442
     04-03 17:40:42.794 25392-25392/application.app.launchmodetest I/WooYun: taskAffinity:application.app.launchmodetest
     04-03 17:40:52.151 25392-25392/application.app.launchmodetest I/WooYun: *****onNewIntent()方法*****
     04-03 17:40:52.152 25392-25392/application.app.launchmodetest I/WooYun: onNewIntent：SingleTaskActivity TaskId: 236 hasCode:260176442
     04-03 17:40:52.153 25392-25392/application.app.launchmodetest I/WooYun: taskAffinity:application.app.launchmodetest
     04-03 17:41:11.873 25392-25392/application.app.launchmodetest I/WooYun: *****onCreate()方法******
     04-03 17:41:11.873 25392-25392/application.app.launchmodetest I/WooYun: onCreate：OtherTaskActivity TaskId: 236 hasCode:55451978
     04-03 17:41:11.874 25392-25392/application.app.launchmodetest I/WooYun: taskAffinity:application.app.launchmodetest
     04-03 17:41:18.911 25392-25392/application.app.launchmodetest I/WooYun: *****onNewIntent()方法*****
     04-03 17:41:18.912 25392-25392/application.app.launchmodetest I/WooYun: onNewIntent：SingleTaskActivity TaskId: 236 hasCode:260176442
     04-03 17:41:18.913 25392-25392/application.app.launchmodetest I/WooYun: taskAffinity:application.app.launchmodetest
     */


    /**指定 taskActivity  taskAffinity属性 application.app.launchmodetest.lwz
     * Main-->task-->other
     * 返回到main  再返回到taskActivity  返回退出
     *
     * 04-03 17:49:12.955 26716-26716/application.app.launchmodetest I/WooYun: *****onCreate()方法******
     04-03 17:49:12.956 26716-26716/application.app.launchmodetest I/WooYun: onCreate：MainActivity TaskId: 239 hasCode:94749401
     04-03 17:49:12.958 26716-26716/application.app.launchmodetest I/WooYun: taskAffinity:application.app.launchmodetest
     04-03 17:49:15.151 26716-26716/application.app.launchmodetest I/WooYun: *****onCreate()方法******
     04-03 17:49:15.152 26716-26716/application.app.launchmodetest I/WooYun: onCreate：SingleTaskActivity TaskId: 240 hasCode:13520703
     04-03 17:49:15.153 26716-26716/application.app.launchmodetest I/WooYun: taskAffinity:application.app.launchmodetest.lwz
     04-03 17:49:24.444 26716-26716/application.app.launchmodetest I/WooYun: *****onCreate()方法******
     04-03 17:49:24.445 26716-26716/application.app.launchmodetest I/WooYun: onCreate：OtherTaskActivity TaskId: 239 hasCode:255012246
     04-03 17:49:24.446 26716-26716/application.app.launchmodetest I/WooYun: taskAffinity:application.app.launchmodetest
     *
     */
}
