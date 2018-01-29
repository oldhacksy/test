package com.novatronic.identidad_digital.views.ui.withdraw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.novatronic.identidad_digital.R;
import com.novatronic.identidad_digital.models.User;
import com.novatronic.identidad_digital.views.ui.SocialHelpActivity;
import com.novatronic.identidad_digital.views.ui.login.LoginActivity;
import com.novatronic.identidad_digital.views.ui.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfirmationWithdrawActivity extends AppCompatActivity {
    private User adminUser;
    private User user;
    @BindView(R.id.btnFinish) public Button btnFinish;
    @BindView(R.id.textView3) public TextView textView3;
    private Integer monto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_withdraw);
        ButterKnife.bind(this);
        adminUser = (User)getIntent().getSerializableExtra("adminUser");
        user = (User)getIntent().getSerializableExtra("user");
        monto = (Integer) getIntent().getIntExtra("amount", 0);
        textView3.setText("Se ha retirado "+ monto+ " al usuario "+ user.getFirstName() + " " +user.getLastNameP() +  " con cuenta "+user.getDni() +" \\n "+
                " La cuenta ahora tiene un total de "+ user.getAmount());
    }

    @OnClick(R.id.btnFinish)
    public void finalizar(Button button) {
        Intent intent =new Intent(this, SocialHelpActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("adminUser",adminUser);
        startActivity(intent);
        finish();
    }
}
