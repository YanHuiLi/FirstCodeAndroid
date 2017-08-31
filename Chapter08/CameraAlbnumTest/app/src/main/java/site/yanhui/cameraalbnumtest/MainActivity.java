package site.yanhui.cameraalbnumtest;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * 1. 拍照，展示图片
 * 2.调用相册的功能
 */

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {

    private static final int TAKE_PHOTO = 1;
    private static final int CHOOSE_PHOTO = 2;
    private static final int WRITE_EXTERNAL_STORAGE_PERMISSION_REQUESTCODE = 0;
    private Uri imageUri;
    private ImageView picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        picture = (ImageView) findViewById(R.id.iv);

        //拍照
        Button TakePhoto = (Button) findViewById(R.id.take_Photo);
        TakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建一个file对象用于存储拍照以后的照片
                File outputImage = new File(getExternalCacheDir(), "outputImage.jpg");
                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (Build.VERSION.SDK_INT > 24) {
                    imageUri = FileProvider.getUriForFile(MainActivity.this, "site.yanhui.cameraalbnumtest.fileprovider", outputImage);
                } else {
                    imageUri= Uri.fromFile(outputImage);
                }
                //启动相机程序
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//把拍照以后的图片地址传入保存。
                startActivityForResult(intent, TAKE_PHOTO);//
            }
        });


        Button ChoosePhoto= (Button) findViewById(R.id.Choose_Photo);
        ChoosePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},WRITE_EXTERNAL_STORAGE_PERMISSION_REQUESTCODE);
                }
                else {
//                    Toast.makeText(MainActivity.this, "给了也要给你执行不了，因为我在研究代码顺序", Toast.LENGTH_SHORT).show();
                    openAlbum();
                }
            }
        });

    }

    //打开相册
    private void openAlbum() {
        Intent intent= new Intent("android.intent.action.GET_CONTENT");//启动相册的action
        intent.setType("image/*");//设置成图片
        startActivityForResult(intent,CHOOSE_PHOTO);
    }


    //允许权限以后的回调函数


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case WRITE_EXTERNAL_STORAGE_PERMISSION_REQUESTCODE:
                if (grantResults.length>0&&grantResults[0]== PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                }else {
                    Toast.makeText(this, "你拒绝了权限", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                Toast.makeText(this, "无法匹配", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //回调函数
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode==RESULT_OK) {
                    try {
                        //通过传入一个uri地址就能解析得到一个bitmap，相当于传入了java流里面的地址
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        picture.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            case  CHOOSE_PHOTO:
                //resultCode其实就是我们拍完照片或者录完视频以后的保存的那个行为
                //点击ok就是保存，和后续的逻辑
                //或者是取消以后要执行的逻辑
                if (resultCode==RESULT_OK) {
                    //判断版本号，因为19以后，出于安全性的考虑，不可以直接拿到照片的uri，而是进一步封装起来
                    //所以分开完成两个方法
                    if (Build.VERSION.SDK_INT>=19){
                        //4统使用这个方法
                        handleImageOnKitkat(data);
                    }else {
                        //4.4以下的系统使用此方法
                        handleImageBeforeKitkat(data);
                    }
                }
                break;
            default:
                break;



        }
    }

    //Api 19以下的
    private void handleImageBeforeKitkat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);

    }

    //api19 以上的，用这个方法
    @TargetApi(19)
    private void handleImageOnKitkat(Intent data) {
        String imagePath =null;
        Uri uri=data.getData();
        if (DocumentsContract.isDocumentUri(this,uri)) {
            //如果是Documents类型的uri，则通过document id 处理
            String documentId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id = documentId.split(":")[1];//解析得到数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                 imagePath=getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId));
                imagePath=getImagePath(contentUri,null);
            }
        }else if ("content".equalsIgnoreCase(uri.getScheme())){
            //如果是content类型的uri，则使用普通方式直接处理
            imagePath=getImagePath(uri,null);
        }else if ("file".equalsIgnoreCase(uri.getScheme())){
            //如果是file类型的uri 直接传入获取图片的路径就可以了
            imagePath=uri.getPath();
        }
        
        displayImage(imagePath);
    }




    private void displayImage(String ImagePath) {
        if (ImagePath!=null){
            Bitmap bitmap = BitmapFactory.decodeFile(ImagePath);
            picture.setImageBitmap(bitmap);
        }else {
            Toast.makeText(this, "faild to get image", Toast.LENGTH_SHORT).show();
        }
    }

    private String getImagePath(Uri uri, String selection){
        String path= null;
        //通过uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor!=null) {
            if (cursor.moveToFirst()) {
            path= cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }
}
