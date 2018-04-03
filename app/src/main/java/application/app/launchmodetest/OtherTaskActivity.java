package application.app.launchmodetest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by lwz on 2018/4/3.
 */

public class OtherTaskActivity extends BaseActivity {
    private Button jump;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_task);

        jump = (Button) findViewById(R.id.btn_other);
        jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OtherTaskActivity.this, SingleTaskActivity.class);
                startActivity(intent);
            }
        });
    }
}