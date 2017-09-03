package site.yanhui.servicetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    private void initUI() {
        Button StartService = (Button) findViewById(R.id.start_service);
        Button StopService = (Button) findViewById(R.id.stop_service);
        StartService.setOnClickListener(this);
        StopService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_service:

                break;

            case R.id.stop_service:

                break;
        }
    }
}
