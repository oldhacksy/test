package com.novatronic.identidad_digital.views.ui.fingerprint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.novatronic.identidad_digital.R;

public class FingerprintActivity extends AppCompatActivity {

    public static final int FINGERPRINT_ID = 9543;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerprint);
    }
}
