package com.novatronic.identidad_digital.views.ui.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.novatronic.identidad_digital.R;
import com.novatronic.identidad_digital.models.DBHelper;
import com.novatronic.identidad_digital.models.User;
import com.novatronic.identidad_digital.views.ui.fingerprint.FingerprintActivity;
import com.novatronic.identidad_digital.views.ui.main.MainActivity;


import java.sql.SQLException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.btn_pin_sign_in) Button mEmailSignInButton;
    @BindView(R.id.btn_fingerprint_sign_in) Button btn_fingerprint_sign_in;
    @BindView(R.id.edtDNI) EditText mDniView;
    @BindView(R.id.edtPassword) EditText mPasswordView;
    @BindView(R.id.login_form) View mProgressView;
    @BindView(R.id.login_progress) View mLoginFormView;
    private User loggedUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }


   @OnClick(R.id.btn_pin_sign_in)
    public void pinSignIn(View view){
       mDniView.setError(null);
       mPasswordView.setError(null);
       String dni = mDniView.getText().toString();
       String password = mPasswordView.getText().toString();
       boolean cancel = false;
       View focusView = null;
       if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
           mPasswordView.setError(getString(R.string.error_invalid_password));
           focusView = mPasswordView;
           cancel = true;
       }
       if (TextUtils.isEmpty(dni)) {
           mDniView.setError(getString(R.string.error_field_required));
           focusView = mDniView;
           cancel = true;
       } else if (!isDNIValid(dni)) {
           mDniView.setError(getString(R.string.error_invalid_dni));
           focusView = mDniView;
           cancel = true;
       }
       if (cancel) {
           focusView.requestFocus();
       } else {
         tryLogin();
       }

   }
    private boolean isDNIValid(String dni) {
        return dni.length() >7;
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 3;
    }

private void tryLogin(){
    if(tryUserLogin()){
        Snackbar.make(findViewById(android.R.id.content),"Usuario o contraseña inválida", Snackbar.LENGTH_SHORT).show();
    }else{
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("adminuser", loggedUser);
        startActivity(intent);
    }
}
private boolean tryUserLogin(){
    DBHelper helper = OpenHelperManager.getHelper(this, DBHelper.class);
    try {
        Dao dao = helper.getUserDao();
        String query = "SELECT * FROM users WHERE dni='"+mDniView.getText().toString()+"' AND pin='"+mPasswordView.getText().toString()+"' AND kind='gestor' LIMIT 1";


        loggedUser= (User)dao.queryRaw(query,dao.getRawRowMapper()).getFirstResult();
        if(loggedUser == null){
            return true;
        }
        return false;
    } catch (SQLException e) {
        return false;
    }
}

}

