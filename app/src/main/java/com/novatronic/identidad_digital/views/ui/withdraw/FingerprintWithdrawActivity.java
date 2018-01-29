package com.novatronic.identidad_digital.views.ui.withdraw;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.novatronic.identidad_digital.R;
import com.novatronic.identidad_digital.models.User;
import com.novatronic.identidad_digital.views.ui.registerUser.RegisterUserConfirmationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.novatronic.identidad_digital.R;
import com.novatronic.identidad_digital.models.User;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FingerprintWithdrawActivity extends AppCompatActivity {
    @BindView(R.id.btnPrevious)
    Button btnPrevious;
    User user;
    @BindView(R.id.btnValidateFingerprint) Button btnValidateFingerprint;
    private User adminuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user_fingerprint);
        ButterKnife.bind(this);
        adminuser=(User)getIntent().getSerializableExtra("adminUser");
        user = (User)getIntent().getSerializableExtra("user");
    }

    @OnClick(R.id.btnPrevious)
    public void gotoPrevious(Button button) {
        finish();
    }
    @OnClick(R.id.btnValidateFingerprint)
    public void validarAndNext(Button button) {
        if(button.getText().toString().equals("Ingresar Huella")){
            button.setText("Finalizar Retiro");
        }else{
            Intent intent = new Intent(this, ConfirmationWithdrawActivity.class);
            intent.putExtra("user", user);
            intent.putExtra("adminUser",adminuser);
            startActivity(intent);
        }
    }

}
