package site.yanhui.broadcastbestpractice.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import site.yanhui.broadcastbestpractice.R;

/**
 * 实现记住登陆密码和账号的功能
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.tv_user)
    TextView tvUser;
    @BindView(R.id.et_User)
    EditText etUser;
    @BindView(R.id.tv_password)
    TextView tvPassword;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.remember_pass)
    CheckBox rememberPass;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        //得到pref对象
        pref = PreferenceManager.getDefaultSharedPreferences(this);

        //被强制下线以后，如果勾选了，就应该从pref读取数据
        boolean isRemember = pref.getBoolean("remember_password", false);
       if (isRemember){
           String userName = pref.getString("UserName", "");
           String password = pref.getString("Password", "");

           etUser.setText(userName);
           etUser.setSelection(userName.length());
           etPassword.setText(password);
           rememberPass.setChecked(true);

       }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UserName = etUser.getText().toString();//得到用户名
                String Password = etPassword.getText().toString();//得到密码
                //如果账号是admin 密码是123 就登陆成功
                if (UserName.equals("admin") && Password.equals("123")) {

                    //首先拿到默认的edit才可以存入数据
                    editor = pref.edit();

                    //判断，如果checkbox是否勾选状态，那么就存入数据
                    if (rememberPass.isChecked()){
                        editor.putString("UserName",UserName);
                        editor.putString("Password",Password);
                        editor.putBoolean("remember_password",true);
                    }else {
                        editor.clear(); //清空pref文件
                    }
                    editor.apply();//提交才会生效

                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();//登陆成功以后就销毁LoginActivity
                } else {
                    Toast.makeText(LoginActivity.this, "用户名是admin，密码是123", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
