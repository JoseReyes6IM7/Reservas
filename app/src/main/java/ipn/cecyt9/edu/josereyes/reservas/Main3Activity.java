package ipn.cecyt9.edu.josereyes.reservas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    public void regresarPrincipal(View us) {
        Intent envia = new Intent(this, MainActivity.class);
        finish();
        startActivity(envia);
    }
}
