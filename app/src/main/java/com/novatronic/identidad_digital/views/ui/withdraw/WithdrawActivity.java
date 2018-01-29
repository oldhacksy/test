package com.novatronic.identidad_digital.views.ui.withdraw;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.novatronic.identidad_digital.R;
import com.novatronic.identidad_digital.models.DBHelper;
import com.novatronic.identidad_digital.models.User;

import java.sql.SQLException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WithdrawActivity extends AppCompatActivity {
    private User adminUser;
    @BindView(R.id.editText)
    public EditText edtAccountNumber;
    @BindView(R.id.editText2) public EditText edtAmount;
    @BindView(R.id.btnConfirm) public Button btnConfirm;
    @BindView(R.id.btnCancel) public Button btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);
        ButterKnife.bind(this);
        adminUser = (User)getIntent().getSerializableExtra("adminUser");
    }


    @OnClick(R.id.btnCancel)
    public void cancelar(View view){
        finish();
    }
    @OnClick(R.id.btnConfirm)
    public void depositar(View view){
        edtAccountNumber.setError(null);
        edtAmount.setError(null);
        String accountNumber = edtAccountNumber.getText().toString();
        String monto = edtAmount.getText().toString();
        boolean cancel = false;
        View focusView = null;
        if (TextUtils.isEmpty(monto) || Integer.parseInt(monto) < 1) {
            edtAmount.setError("El monto a retirar debe ser mayor a 0");
        }
        if (TextUtils.isEmpty(accountNumber)) {
            edtAccountNumber.setError(getString(R.string.error_field_required));
            focusView = edtAccountNumber;
            cancel = true;
        } else if (!isDNIValid(accountNumber)) {
            edtAccountNumber.setError(getString(R.string.error_invalid_dni));
            focusView = edtAccountNumber;
            cancel = true;
        }
        //Validar DNI
        User user = getUserByDNI(accountNumber);
        if(user == null){
            edtAccountNumber.setError("No existe un usuario con ese DNI");
            focusView = edtAccountNumber;
            cancel = true;
        }else{
            if(user.getAmount() - Integer.parseInt(monto) < 0 ){
                edtAmount.setError("no tiene suficientes fonds");
                focusView = edtAmount;
                cancel = true;
            }
        }

        //
        if (cancel) {
            focusView.requestFocus();
        } else {
            tryDeposit(user, monto);
        }
    }

    private void tryDeposit(User user, String monto) {
        DBHelper helper = OpenHelperManager.getHelper(this, DBHelper.class);
        try {
            Dao dao = helper.getUserDao();
            user.setAmount( user.getAmount()+ Integer.parseInt(monto));
            dao.update(user);
            Intent intent = new Intent(this, FingerprintWithdrawActivity.class);
            intent.putExtra("user", user);
            intent.putExtra("amount",Integer.parseInt(monto));
            intent.putExtra("adminUser", adminUser);
            startActivity(intent);
        } catch (SQLException e) {
            Snackbar.make(findViewById(android.R.id.content),"Hubo problemas al realizar el retiro", Snackbar.LENGTH_SHORT).show();
        }
    }

    private User getUserByDNI(String accountNumber) {
        DBHelper helper = OpenHelperManager.getHelper(this, DBHelper.class);
        try {
            Dao dao = helper.getUserDao();
            String query = "SELECT * FROM users WHERE dni='accountNumber'  AND kind='usuario' LIMIT 1";

            User user = (User)dao.queryRaw(query,dao.getRawRowMapper()).getFirstResult();
            return user;
        } catch (SQLException e) {
            return null;
        }
    }

    private boolean isDNIValid(String dni) {
        return dni.length() >7;
    }

}
