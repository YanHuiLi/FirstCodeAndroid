package site.yanhui.playvideotest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitUI();
        InitPlayVideoPath();
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else {
            InitPlayVideoPath();//初始化playVideo
        }

    }

    private void InitPlayVideoPath() {
        File file= new File(Environment.getExternalStorageDirectory(),"Video.mp4");
        videoView.setVideoPath(file.getPath());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length>0&&grantResults[0]== PackageManager.PERMISSION_GRANTED) {
                    InitPlayVideoPath();
                }else{
                    Toast.makeText(this, "拒绝权限无法使用", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;

            default:
                break;
        }
    }

    private void InitUI() {
        Button Play= (Button) findViewById(R.id.Play);
        Button Pause= (Button) findViewById(R.id.PAUSE);
        Button Replay= (Button) findViewById(R.id.REPLAY);
        videoView = (VideoView) findViewById(R.id.vv);
        Play.setOnClickListener(this);
        Pause.setOnClickListener(this);
        Replay.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Play:
                if (!videoView.isPlaying()) {
                    videoView.start();
                }
                break;

            case R.id.PAUSE:
                if (videoView.isPlaying()) {
                    videoView.pause();
                }
                break;

            case R.id.REPLAY:
                if (videoView.isPlaying()) {
                    videoView.resume();//重新播放 resume 重新开始
                }
                break;

            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoView!=null) {
            videoView.suspend(); //挂起
        }
    }
}
