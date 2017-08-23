package site.yanhui.broadcastbestpractice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import site.yanhui.broadcastbestpractice.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UserName = etUser.getText().toString();//得到用户名
                String Password = etPassword.getText().toString();//得到弥勒
              //如果账号是admin 密码是123 就登陆成功
                if (UserName.equals("admin")&&Password.equals("123")) {
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                     finish();//登陆成功以后就销毁LoginActivity
                }else {
                    Toast.makeText(LoginActivity.this, "用户名是admin，密码是123", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
