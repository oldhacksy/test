package com.novatronic.identidad_digital.views.ui.registerUser;

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

public class RegisterUserFingerprintActivity extends AppCompatActivity {
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
        adminuser=(User)getIntent().getSerializableExtra("adminuser");
         user = (User)getIntent().getSerializableExtra("user");
    }

    @OnClick(R.id.btnPrevious)
    public void gotoPrevious(Button button) {
        finish();
    }
    @OnClick(R.id.btnValidateFingerprint)
    public void validarAndNext(Button button) {
        if(button.getText().toString().equals("Ingresar Huella")){
            button.setText("Finalizar Registro");
        }else{
            Intent intent = new Intent(this, RegisterUserConfirmationActivity.class);
            intent.putExtra("user", user);
            intent.putExtra("adminuser",adminuser);
            startActivity(intent);
        }
    }

}
