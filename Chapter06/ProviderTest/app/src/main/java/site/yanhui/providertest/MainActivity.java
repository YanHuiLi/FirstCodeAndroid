package site.yanhui.providertest;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String newId;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button AddData= (Button) findViewById(R.id.add_data);
        AddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://site.yanhui.databasetest.provider/book");
                ContentValues values = new ContentValues();
                values.put("name","斜阳");
                values.put("author","太宰治");
                values.put("pages",1040);
                values.put("price",22.85);
                Uri newUri=getContentResolver().insert(uri,values);
                assert newUri != null;
                newId = newUri.getPathSegments().get(1);
            }
        });

        Button queryData= (Button) findViewById(R.id.queryData);

        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://site.yanhui.databasetest.provider/book#");
                Cursor cursor=getContentResolver().query(uri,null,null,null,null);
                if (cursor!=null) {
                    while (cursor.moveToNext()) {
                        Log.d(TAG,"===========================");
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));

                        Log.d(TAG, "book name is "+name);
                        Log.d(TAG,"book author is "+ author);
                        Log.d(TAG,"book pages is "+ pages);
                        Log.d(TAG,"book price is "+ price);
                    }
                    cursor.close();
                }
            }
        });

        Button updateButton = (Button) findViewById(R.id.update_data);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://site.yanhui.databasetest.provider/book/" + newId);
                ContentValues values = new ContentValues();
                values.put("name","人间失格2");
                values.put("pages",1216);
                values.put("price",24.05);
                int update = getContentResolver().update(uri, values, null, null);
                Toast.makeText(MainActivity.this, "受影响的行数有"+update, Toast.LENGTH_SHORT).show();
            }
        });

        Button deleteData= (Button) findViewById(R.id.DeleteData);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //因为是共享的数据，是没有权限对原数据进行删除的，所以只能对自己这个app某次使用提供的数据进行删除。
                //注意，当你添加了数据的时候，相当于往databasetest里面添加，下次打开的时候，同样没有权利修改或者删除
                //只能对本次 新写入的数据进行update和delete。而不能对整个数据进行。
                //因为这个自定义provider是共享的。
                Uri uri = Uri.parse("content://site.yanhui.databasetest.provider/book/" + newId);

                int delete = getContentResolver().delete(uri, null, null);
                Toast.makeText(MainActivity.this, "受到影响的行数有"+delete, Toast.LENGTH_SHORT).show();

            }
        });

    }
}
