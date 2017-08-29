package site.yanhui.providertest;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
    }
}
