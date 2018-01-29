package com.novatronic.identidad_digital.views.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.novatronic.identidad_digital.R;
import com.novatronic.identidad_digital.models.User;
import com.novatronic.identidad_digital.views.ui.login.LoginActivity;
import com.novatronic.identidad_digital.views.ui.main.MainActivity;
import com.novatronic.identidad_digital.views.ui.withdraw.WithdrawActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SocialHelpActivity extends AppCompatActivity {

    private User adminUser;
    @BindView(R.id.edtLoggedUser)
    EditText edtLoggedUser;
    @BindView(R.id.btnDeposit)
    Button btnDeposit;
    @BindView(R.id.btnWithdraw)
    Button btnWithdraw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_help);
        ButterKnife.bind(this);
        adminUser = (User)getIntent().getSerializableExtra("adminuser");
        edtLoggedUser.setText("Bienvenido Gestor:"+ adminUser.getFirstName()+ " " + adminUser.getLastNameP());
    }
    @OnClick(R.id.btnLogout)
    public void cerrarSession(Button button) {
        Intent intent =new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("adminUser",adminUser);
        startActivity(intent);
        finish();
    }
    @OnClick(R.id.btnWithdraw)
    public void abrirReitro(Button button) {
        Intent intent =new Intent(this, WithdrawActivity.class);
        intent.putExtra("adminUser",adminUser);
        startActivity(intent);
    }
    @OnClick(R.id.btnDeposit)
    public void abrirDeposito(Button button) {
        Intent intent =new Intent(this, DepositActivity.class);
        intent.putExtra("adminUser",adminUser);
        startActivity(intent);
    }
}
