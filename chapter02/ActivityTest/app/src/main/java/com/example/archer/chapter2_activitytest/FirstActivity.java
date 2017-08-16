package com.example.archer.chapter2_activitytest;

import android.content.Intent;
import android.graphics.PaintFlagsDrawFilter;
import android.icu.text.LocaleDisplayNames;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static android.widget.Toast.makeText;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);

        Button button = (Button) findViewById(R.id.button_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(FirstActivity.this, "这是第一个button1111", Toast.LENGTH_SHORT).show();
//                Toast.makeText(FirstActivity.this, "关闭活动", Toast.LENGTH_SHORT).show();
//                finish();


//               Toast.makeText(FirstActivity.this, "跳转到第二个activity", Toast.LENGTH_SHORT).show();

                //显示intent
//             Intent intent  = new Intent(FirstActivity.this,SecondActivity.class);
//                startActivity(intent);

//               //隐示Intent的使用
//               Intent intent = new Intent("com.example.archer.chapter2_activitytest.ACTION_START");
//                intent.addCategory("com.example.archer.chapter2_activitytest.My_CATEGORY");
//                startActivity(intent);


                //隐示intent跳转到网站
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("http://yanhuili.github.io"));
//                startActivity(intent);

                //拨打电话
//                Intent intent=new Intent(Intent.ACTION_DIAL);
//                intent.setData(Uri.parse("tel18886"));
//                startActivity(intent);


                //向下一个活动传递数据
//                String data= "hello Second Activity";
//
//                Intent intent =new Intent(FirstActivity.this,SecondActivity.class);
//                intent.putExtra("extra_data",data);
//                startActivity(intent);

                //返回数据给上一个活动

                Intent intent =new Intent(FirstActivity.this,SecondActivity.class);

                startActivityForResult(intent,10);//一要求传递过去的secondActivity返回数据

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case 10:
               if (resultCode== RESULT_OK){
                   String mString= data.getStringExtra("data_return");
                   Log.d("FirstActivity",mString);
               }
               break;
            default:
                break;
        }


    }

    /**
     * Initialize the contents of the Activity's standard options menu.  You
     * should place your menu items in to <var>menu</var>.
     * <p>
     * <p>This is only called once, the first time the options menu is
     * displayed.  To update the menu every time it is displayed, see
     * {@link #onPrepareOptionsMenu}.
     * <p>
     * <p>The default implementation populates the menu with standard system
     * menu items.  These are placed in the {@link Menu#CATEGORY_SYSTEM} group so that
     * they will be correctly ordered with application-defined menu items.
     * Deriving classes should always call through to the base implementation.
     * <p>
     * <p>You can safely hold on to <var>menu</var> (and any items created
     * from it), making modifications to it as desired, until the next
     * time onCreateOptionsMenu() is called.
     * <p>
     * <p>When you add items to the menu, you can implement the Activity's
     * {@link #onOptionsItemSelected} method to handle them there.
     *
     * @param menu The options menu in which you place your items.
     * @return You must return true for the menu to be displayed;
     * if you return false it will not be shown.
     * @see #onPrepareOptionsMenu
     * @see #onOptionsItemSelected
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:

                makeText(this, "you click Add ", Toast.LENGTH_SHORT).show();
                break;

            case R.id.remove_item:

                makeText(this, "you click Remove ", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }
}
