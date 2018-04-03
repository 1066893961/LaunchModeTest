package application.app.launchmodetest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * 这个模式是默认的启动模式，即标准模式，在不指定启动模式的前提下，
 * 系统默认使用该模式启动Activity，每次启动一个Activity都会重写创建
 * 一个新的实例，不管这个实例存不存在，这种模式下，谁启动了该模式的Activity，
 * 该Activity就属于启动它的Activity的任务栈中。
 *
 *
 *  standard和singleTop启动模式都是在原任务栈中新建Activity实例，不会启动新的Task，
 *  即使你指定了taskAffinity属性。
 */
public class StandardActivity extends BaseActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard);

        button = (Button) findViewById(R.id.standard_btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StandardActivity.this, StandardActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     进入StandardActivity,点击三次次按钮，启动四次本activity，然后按返回键
     日志信息：
     04-03 16:44:23.556 12938-12938/application.app.launchmodetest I/WooYun: *****onCreate()方法******
     04-03 16:44:23.557 12938-12938/application.app.launchmodetest I/WooYun: onCreate：StandardActivity TaskId: 223 hasCode:140948454
     04-03 16:44:23.557 12938-12938/application.app.launchmodetest I/WooYun: taskAffinity:application.app.launchmodetest
     04-03 16:44:57.308 12938-12938/application.app.launchmodetest I/WooYun: *****onCreate()方法******
     04-03 16:44:57.309 12938-12938/application.app.launchmodetest I/WooYun: onCreate：StandardActivity TaskId: 223 hasCode:60528787
     04-03 16:44:57.310 12938-12938/application.app.launchmodetest I/WooYun: taskAffinity:application.app.launchmodetest
     04-03 16:45:47.551 12938-12938/application.app.launchmodetest I/WooYun: *****onCreate()方法******
     04-03 16:45:47.551 12938-12938/application.app.launchmodetest I/WooYun: onCreate：StandardActivity TaskId: 223 hasCode:113356713
     04-03 16:45:47.552 12938-12938/application.app.launchmodetest I/WooYun: taskAffinity:application.app.launchmodetest
     04-03 16:45:51.249 12938-12938/application.app.launchmodetest I/WooYun: *****onCreate()方法******
     04-03 16:45:51.249 12938-12938/application.app.launchmodetest I/WooYun: onCreate：StandardActivity TaskId: 223 hasCode:105725581
     04-03 16:45:51.250 12938-12938/application.app.launchmodetest I/WooYun: taskAffinity:application.app.launchmodetest

     可以看到日志输出了4次StandardActivity，进入StandardActivity一次，后来我们又按了三次按钮，
     总共四次StandardActivity的日志，并且所属的任务栈的id都是223，这也验证了谁启动了该模式的Activity，该Activity就属于启动它的Activity
     的任务栈中这句话，因为启动StandardActivity的是StandardActivity，而StandardActivity的taskId是223，因此启动的StandardActivity也应该属于
     id为223的这个task，后续的3个StandardActivity是被StandardActivity这个对象启动的，因此也应该还是223，所以taskId都是223。并且
     每一个Activity的hashcode都是不一样的，说明他们是不同的实例，即“每次启动一个Activity都会重写创建一个新的实例”

     */

}
