package application.app.launchmodetest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * 这个模式下，如果新的activity已经位于栈顶，那么这个Activity不会被重写创建，
 * 同时它的onNewIntent方法会被调用，通过此方法的参数我们可以去除当前请求的信息。
 * 如果栈顶不存在该Activity的实例，则情况与standard模式相同。需要注意的是这个
 * Activity它的onCreate()，onStart()方法不会被调用，因为它并没有发生改变。
 *
 *
 * *  standard和singleTop启动模式都是在原任务栈中新建Activity实例，不会启动新的Task，
 *  即使你指定了taskAffinity属性。
 */
public class SingleTopActivity extends BaseActivity {

    private Button jump, jump2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singletop);

        jump = (Button) findViewById(R.id.singletop_btn);
        jump2 = (Button) findViewById(R.id.singletop_btn2);
        jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleTopActivity.this, SingleTopActivity.class);
                startActivity(intent);
            }
        });
        jump2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleTopActivity.this, OtherTopActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * MainActivity -->SingleTopActivity-->SingleTopActivity-->SingleTopActivity
     * 04-03 17:16:25.720 19769-19769/application.app.launchmodetest I/WooYun: *****onCreate()方法******
     04-03 17:16:25.720 19769-19769/application.app.launchmodetest I/WooYun: onCreate：MainActivity TaskId: 227 hasCode:140948454
     04-03 17:16:25.721 19769-19769/application.app.launchmodetest I/WooYun: taskAffinity:application.app.launchmodetest
     04-03 17:16:38.099 19769-19769/application.app.launchmodetest I/WooYun: *****onCreate()方法******
     04-03 17:16:38.099 19769-19769/application.app.launchmodetest I/WooYun: onCreate：SingleTopActivity TaskId: 227 hasCode:260176442
     04-03 17:16:38.100 19769-19769/application.app.launchmodetest I/WooYun: taskAffinity:application.app.launchmodetest
     04-03 17:16:44.450 19769-19769/application.app.launchmodetest I/WooYun: *****onNewIntent()方法*****
     04-03 17:16:44.450 19769-19769/application.app.launchmodetest I/WooYun: onNewIntent：SingleTopActivity TaskId: 227 hasCode:260176442
     04-03 17:16:44.451 19769-19769/application.app.launchmodetest I/WooYun: taskAffinity:application.app.launchmodetest
     04-03 17:16:46.087 19769-19769/application.app.launchmodetest I/WooYun: *****onNewIntent()方法*****
     04-03 17:16:46.088 19769-19769/application.app.launchmodetest I/WooYun: onNewIntent：SingleTopActivity TaskId: 227 hasCode:260176442
     04-03 17:16:46.089 19769-19769/application.app.launchmodetest I/WooYun: taskAffinity:application.app.launchmodetest


     我们看到从MainActivity进入到SingleTopActivity时，新建了一个SingleTopActivity对象，
     并且task id与MainActivity是一样的，然后从SingleTopActivity跳到OtherActivity时，
     新建了一个OtherActivity，此时task中存在三个Activity，从栈底到栈顶依次是MainActivity，
     SingleTopActivity，OtherActivity，此时如果再跳到SingleTopActivity，即使栈中已经有
     SingleTopActivity实例了，但是依然会创建一个新的SingleTopActivity实例，这一点从上面的日志
     的hashCode可以看出，此时栈顶是SingleTopActivity，如果再跳到SingleTopActivity，就会复用栈顶的
     SingleTopActivity，即会调用SingleTopActivity的onNewIntent方法。这就是上述日志的全过程。
     对以上内容进行总结
     standard启动模式是默认的启动模式，每次启动一个Activity都会新建一个实例不管栈中是否已有该Activity的实例。
     singleTop模式分3种情况

     当前栈中已有该Activity的实例并且该实例位于栈顶时，不会新建实例，而是复用栈顶的实例，并且会将Intent对象传入，回调onNewIntent方法
     当前栈中已有该Activity的实例但是该实例不在栈顶时，其行为和standard启动模式一样，依然会创建一个新的实例
     当前栈中不存在该Activity的实例时，其行为同standard启动模式
     */

}
