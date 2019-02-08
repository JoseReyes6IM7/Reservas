package ipn.cecyt9.edu.josereyes.reservas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    String nombre = "", contra = "", fecha = "", hora = "";
    int personas = 0, noches = 0;
    boolean mar = false;
    String texto = "";
    TextView muestraDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        muestraDatos = findViewById(R.id.muestraDatos);

        Bundle recibe = new Bundle();
        recibe = this.getIntent().getExtras();

        nombre = recibe.getString("nombre");
        contra = recibe.getString("contra");
        personas = recibe.getInt("personas");
        fecha = recibe.getString("fecha");
        hora = recibe.getString("hora");
        noches = recibe.getInt("noches");
        mar = recibe.getBoolean("mar");

        if (mar == false)
            texto = "Sin vista al mar";
         else
            texto = "Con vista al mar";


        muestraDatos.setText("Reservacion a nombre de:\n" + nombre + "\n" + "contraseña: " + contra +"\n" + personas
                + " personas\nFecha: " + fecha + "\nHora: " + hora + "\n" + noches + " noches" + "\n" + texto);

    }

    public void hacerOtraReserva(View v) {
        Intent envia = new Intent(this, Main3Activity.class);
        finish();
        startActivity(envia);
    }
}
