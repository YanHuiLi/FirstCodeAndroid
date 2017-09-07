package site.yanhui.materialtest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        //滑动菜单的使用抽屉菜单
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBar actionBar = getSupportActionBar();//获得actionbar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu2);//设置指示器 图片不是很适配
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setCheckedItem(R.id.nav_call);//设置成点击的样子
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            /**
             * Called when an item in the navigation menu is selected.
             *
             * @param item The selected item
             * @return true to display the item as the selected item
             */
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //设置点击事件
//                switch (item.getItemId()) {
//                    case R.id.nav_call:
//                        Toast.makeText(MainActivity.this, "you click call", Toast.LENGTH_SHORT).show();
//                        drawerLayout.closeDrawers();
//                        break;
//                }
                drawerLayout.closeDrawers();
                return true;
            }
        });


        FloatingActionButton FAB= (FloatingActionButton) findViewById(R.id.fab);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "you click the fab!", Toast.LENGTH_SHORT).show();
                Snackbar.make(v,"data delete",Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "data restored!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
    }

    /**
     * @param menu 传入的菜单
     * @return 返回true则成功解析。false则不成功
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    /**
     * menu 添加的条目
     *
     * @param item 条目
     * @return 返回true就创建，false就不创建
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);//点击打开抽屉,之前的图片不适配 我取消了使用
                break;
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
