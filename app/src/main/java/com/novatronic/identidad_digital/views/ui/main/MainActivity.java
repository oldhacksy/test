package com.novatronic.identidad_digital.views.ui.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.novatronic.identidad_digital.R;
import com.novatronic.identidad_digital.models.User;
import com.novatronic.identidad_digital.views.ui.SocialHelpActivity;
import com.novatronic.identidad_digital.views.ui.login.LoginActivity;
import com.novatronic.identidad_digital.views.ui.registerUser.RegisterUserDataActivity;
import com.novatronic.identidad_digital.views.ui.validateData.ValidateSMSActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btnValidate) Button btnValidate;
    @BindView(R.id.btNavigate) Button btNavigate;
    @BindView(R.id.btnUserRegister) Button btnUserRegister;
    @BindView(R.id.btnGestorRegister) Button btnGestorRegister;
    @BindView(R.id.btnLogout) Button btnLogout;
    private User adminuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        adminuser = (User)getIntent().getSerializableExtra("adminuser");
    }

    @OnClick(R.id.btnValidate)
    public void gotoRegisterActivity(Button button) {
        Intent intent =new Intent(this, ValidateSMSActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btnUserRegister)
    public void gotoUserRegisterActivity(Button button) {
        Intent intent =new Intent(MainActivity.this, RegisterUserDataActivity.class);
        intent.putExtra("kind", "usuario");
        startActivity(intent);
    }
    @OnClick(R.id.btnGestorRegister)
    public void gotoUserRegisteGestorrActivity(Button button) {
        Intent intent =new Intent(MainActivity.this, RegisterUserDataActivity.class);
        intent.putExtra("kind", "gestor");

        startActivity(intent);
    }
    @OnClick(R.id.btNavigate)
    public void gotoSocialHelp(Button button) {
        Intent intent =new Intent(MainActivity.this, SocialHelpActivity.class);
        intent.putExtra("adminuser",adminuser);
        startActivity(intent);
    }
    @OnClick(R.id.btnLogout)
    public void cerrarSession(Button button) {
        Intent intent =new Intent(MainActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
