package com.novatronic.identidad_digital.views.ui.registerUser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.novatronic.identidad_digital.R;
import com.novatronic.identidad_digital.models.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterUserDataActivity extends AppCompatActivity {
    @BindView(R.id.btnNext)
    Button mEmailSignInButton;

    @BindView(R.id.edtName)
    EditText edtName;
    @BindView(R.id.edtLastNameM) EditText edtLastNameM;
    @BindView(R.id.edtLastNameP) EditText edtLastNameP;
    @BindView(R.id.edtPIN) EditText edtPIN;
    @BindView(R.id.edtPINConfirmation) EditText edtPINConfirmation;
    @BindView(R.id.edtDocument) EditText edtDocument;
    private String kind;
    private User adminuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adminuser= (User)getIntent().getSerializableExtra("adminuser");
        kind=getIntent().getStringExtra("kind");
        setContentView(R.layout.activity_register_user_data);
        ButterKnife.bind(this);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("Registro de "+ kind);
    }
    private void cleanFields(){
        edtName.setError(null);
        edtLastNameM.setError(null);
        edtLastNameP.setError(null);
        edtPIN.setError(null);
        edtPINConfirmation.setError(null);
        edtDocument.setError(null);
    }
    @OnClick(R.id.btnNext)
    public void pinSignIn(View view){

        String name = edtName.getText().toString();
        String lastnameM = edtLastNameM.getText().toString();
        String lastnameP = edtLastNameP.getText().toString();
        String pin = edtPIN.getText().toString();
        String confirmation = edtPINConfirmation.getText().toString();
        String document = edtDocument.getText().toString();

        boolean cancel = false;
        View focusView = null;
        if (!TextUtils.isEmpty(pin) && !isPasswordValid(pin)) {
            edtPIN.setError(getString(R.string.error_invalid_password));
            focusView = edtPIN;
            cancel = true;
        }
        if (TextUtils.isEmpty(pin) || !confirmation.equals(pin)){
            edtPINConfirmation.setError("Confirmacion del PIN invalida");
            focusView = edtPINConfirmation;
            cancel = true;
        }
        if (TextUtils.isEmpty(name) ){
            edtName.setError("El Nombre no puede estar vacio");
            focusView = edtName;
            cancel = true;
        }
        if (TextUtils.isEmpty(lastnameM) ){
            edtLastNameM.setError("El Apellido Materno no puede estar vacio");
            focusView = edtLastNameM;
            cancel = true;
        }
        if (TextUtils.isEmpty(lastnameP) ){
            edtLastNameP.setError("El Apellido Paterno no puede estar vacio");
            focusView = edtLastNameP;
            cancel = true;
        }
        if (TextUtils.isEmpty(document)) {
            edtDocument.setError(getString(R.string.error_field_required));
            focusView = edtDocument;
            cancel = true;
        } else if (!isDNIValid(document)) {
            edtDocument.setError(getString(R.string.error_invalid_dni));
            focusView = edtDocument;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        } else {
           User user= new User();
           user.setLastNameP(lastnameP);
            user.setLastNameM(lastnameM);
           user.setFirstName(name);
            user.setDni(document);
            user.setAmount(0);
            user.setPin(pin);
            user.setKind(kind);
            Intent intent = new Intent(this,RegisterUserFingerprintActivity.class);
            intent.putExtra("user",user);
            intent.putExtra("adminuser",adminuser);
            startActivity(intent);
        }
    }

    private boolean isDNIValid(String dni) {
        return dni.length() >7;
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 3;
    }
}
