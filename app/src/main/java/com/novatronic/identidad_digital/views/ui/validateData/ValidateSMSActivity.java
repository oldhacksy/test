package com.novatronic.identidad_digital.views.ui.validateData;


        import android.app.NotificationManager;
        import android.content.Context;
        import android.content.Intent;
        import android.media.RingtoneManager;
        import android.support.design.widget.Snackbar;
        import android.support.v4.app.NotificationCompat;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.widget.Button;
        import android.widget.EditText;

        import com.novatronic.identidad_digital.R;

        import java.util.Random;

        import butterknife.BindView;
        import butterknife.ButterKnife;
        import butterknife.OnClick;

public class ValidateSMSActivity extends AppCompatActivity {
    private int randomValidationPIN = 0000;
    @BindView(R.id.btnValidate)  Button btnValidate;
    @BindView(R.id.edtPIN)
    EditText edtPIN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate_sms);
        ButterKnife.bind(this);
        Random r = new Random();
        randomValidationPIN = r.nextInt(9999);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this, "1234")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("Validar Cuenta")
                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                        .setPriority(NotificationManager.IMPORTANCE_HIGH)
                        .setContentText("Validar Cuenta con codigo :" + randomValidationPIN);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(1234, mBuilder.build());
    }
    @OnClick(R.id.btnValidate)
    public void validate(Button button) {
        if(edtPIN.getText().toString().equals(randomValidationPIN+"")){
            Snackbar.make(findViewById(android.R.id.content), "Cuenta Validada correctamente", Snackbar.LENGTH_LONG).show();
        }else{
            Snackbar.make(findViewById(android.R.id.content), "Codigo de Validacion invalido", Snackbar.LENGTH_LONG).show();
        }
    }
}
