package app.jy.com.login_demo;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


import app.jy.com.login_demo.service.FileService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private EditText et_username;
    private EditText et_password;
    private Button button;
    private CheckBox cb_remember;
    private FileService fileService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        button = (Button) findViewById(R.id.button);
        cb_remember = (CheckBox) findViewById(R.id.cb_remember);

        button.setOnClickListener(this);

        //初始化文件服务
        fileService = new FileService(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                String username = et_username.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                Log.i("PackageResourcePath:",this.getApplicationContext().getPackageResourcePath());
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
                    Toast.makeText(this,R.string.error,Toast.LENGTH_SHORT).show();
                    return;
                }
                if (cb_remember.isChecked()){
                    Log.i(TAG,"保存用户名和密码,"+username+":"+password);
                    boolean result = fileService.saveToRom(username,password,"private.txt");
                    if (result){
                        Toast.makeText(this,R.string.success,Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this,NextActivity.class);
                        intent.putExtra("username",username);
                        intent.putExtra("password",password);
                        startActivity(intent);
                    }else{
                        Toast.makeText(this,R.string.failed,Toast.LENGTH_SHORT).show();
                        Log.i("PackageResourcePath:",this.getApplicationContext().getPackageResourcePath());
                    }
                }else{
                    Log.i(TAG,"不保存用户名和密码,"+username+":"+password);
                }
//                et_username.setText("");
//                et_password.setText("");
                break;
        }

    }
}
