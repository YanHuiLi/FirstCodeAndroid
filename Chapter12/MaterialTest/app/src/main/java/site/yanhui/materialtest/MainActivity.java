package site.yanhui.materialtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
    }

    /**
     *
     * @param menu 传入的菜单
     * @return 返回true则成功解析。false则不成功
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    /**
     * menu 添加的条目
     *
     * @param item  条目
     * @return  返回true就创建，false就不创建
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.backUp:
                Toast.makeText(this, "you click backup", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "you click  delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting:
                Toast.makeText(this, "you click  setting", Toast.LENGTH_SHORT).show();
                default:
                    break;
        }
        return true;
    }
}
