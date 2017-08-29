package site.yanhui.contactstest;

import android.Manifest;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
     List<String> contactsList =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lv= (ListView) findViewById(R.id.lv);
        adapter= new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, contactsList);
        lv.setAdapter(adapter);
        MainActivityPermissionsDispatcher.readContactsWithCheck(MainActivity.this);


    }

    //读取联系人的方法
    @NeedsPermission(Manifest.permission.READ_CONTACTS)
    void readContacts() {
        Cursor cursor;
        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        if (cursor!=null){
            while(cursor.moveToNext()){
                //获取通讯录的姓名
                String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contactsList.add(displayName+"\n"+number);
            }
            adapter.notifyDataSetChanged();
        }
        if (cursor != null) {
            cursor.close();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnShowRationale(Manifest.permission.READ_CONTACTS)
    void ShowRationale(final PermissionRequest request) {
        AlertDialog.Builder builder=new  AlertDialog.Builder(this);
        builder.setTitle("说明");
        builder.setMessage("需要权限，才能调取通讯录");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                request.proceed();
            }
        });
        builder.setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                request.cancel();
            }
        });
        builder.show();

    }

    @OnPermissionDenied(Manifest.permission.READ_CONTACTS)
    void PermissionDenied() {
    }

    @OnNeverAskAgain(Manifest.permission.READ_CONTACTS)
    void NeverAskAgain() {
    }
}
