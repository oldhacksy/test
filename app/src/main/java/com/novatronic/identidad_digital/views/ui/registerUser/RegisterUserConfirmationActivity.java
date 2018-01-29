package com.novatronic.identidad_digital.views.ui.registerUser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.novatronic.identidad_digital.R;
import com.novatronic.identidad_digital.models.DBHelper;
import com.novatronic.identidad_digital.models.User;
import com.novatronic.identidad_digital.views.ui.main.MainActivity;

import java.sql.SQLException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterUserConfirmationActivity extends AppCompatActivity {
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.textView2)
    TextView textView2;
    User user;
    private User adminuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user_confirmation);
        ButterKnife.bind(this);
        adminuser= (User)getIntent().getSerializableExtra("user");
        user = (User)getIntent().getSerializableExtra("user");
        textView.setText("Registro de "+user.getKind()+"");
        textView2.setText(user.getFirstName()+ " "+user.getLastNameP()+ " con DNI:"+user.getDni());
        DBHelper helper = OpenHelperManager.getHelper(this, DBHelper.class);

        try {
            Dao dao = helper.getUserDao();dao.createOrUpdate(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.btnFinish)
    public void gotoMainMenu(Button button) {
        Intent intent =new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("adminuser",adminuser);
        intent.putExtra("user",user);
        startActivity(intent);
        finish(); // call this to finish the current activity
    }
}
