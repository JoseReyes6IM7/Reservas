package ipn.cecyt9.edu.josereyes.reservas;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener,
        View.OnClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    EditText nombre, contra;
    TextView cuantasPersonas, numNoches;
    Button fecha, hora;
    SeekBar barraPersonas, barraNoches;
    Switch mar;

    SimpleDateFormat horaFormato, fechaFormato;

    String nombreReserva = "";
    String contraReserva = "";
    String numPersonas = "";
    String noches = "";
    String fechaSel = "", horaSel = "";
    Date fechaConv;
    String cuantasPersonasFormat = "";
    String nochesFormat = "";
    String valMar = "";
    int personas = 1;
    int minNoches = 2;

    Calendar calendario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mar = findViewById(R.id.mar);

        cuantasPersonas = findViewById(R.id.cuantasPersonas);
        barraPersonas = findViewById(R.id.personas);

        numNoches = findViewById(R.id.numNoches);
        barraNoches = findViewById(R.id.barraNoches);

        fecha = findViewById(R.id.fecha);
        hora = findViewById(R.id.hora);

        barraPersonas.setOnSeekBarChangeListener(this);
        barraNoches.setOnSeekBarChangeListener(this);

        nombre = findViewById(R.id.nombre);
        contra = findViewById(R.id.contra);

        cuantasPersonasFormat = cuantasPersonas.getText().toString();
        nochesFormat = numNoches.getText().toString();
        // cuantasPersonasFormat = "personas: %d";
        cuantasPersonas.setText("Personas: 1"); // condicion inicial
        numNoches.setText("Noches: 2");

        // Para seleccionar la fecha y la hora
        Calendar fechaSeleccionada = Calendar.getInstance();
        fechaSeleccionada.set(Calendar.HOUR_OF_DAY, 12); // hora inicial
        fechaSeleccionada.clear(Calendar.MINUTE); // 0
        fechaSeleccionada.clear(Calendar.SECOND); // 0

        // formatos de la fecha y hora
        fechaFormato = new SimpleDateFormat(fecha.getText().toString());
        horaFormato = new SimpleDateFormat("HH:mm");
        // horaFormato = new SimpleDateFormat(hora.getText().toString());

        // La primera vez mostramos la fecha actual
        Date fechaReservacion = fechaSeleccionada.getTime();
        fechaSel = fechaFormato.format(fechaReservacion);
        fecha.setText(fechaSel); // fecha en el

        horaSel = horaFormato.format(fechaReservacion);
        // boton
        hora.setText(horaSel); // hora en el boton

        // Otra forma de ocupar los botones
        fecha.setOnClickListener(this);
        hora.setOnClickListener(this);

    }

    @Override
    public void onProgressChanged(SeekBar barra, int progreso,
                                  boolean delUsuario) {

        numPersonas = String.format(cuantasPersonasFormat, barraPersonas.getProgress() + 1);
        personas = barraPersonas.getProgress() + 1;
        cuantasPersonas.setText(numPersonas);

        noches = String.format(nochesFormat, barraNoches.getProgress() + 2);
        minNoches = barraNoches.getProgress() + 2;
        numNoches.setText(noches);
    }

    @Override
    public void onStartTrackingTouch(SeekBar arg0) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar arg0) {
    }

    @Override
    public void onClick(View v) {
        if (v == fecha) {
            Calendar calendario = parseCalendar(fecha.getText(), fechaFormato);
            new DatePickerDialog(this, this, calendario.get(Calendar.YEAR),
                    calendario.get(Calendar.MONTH),
                    calendario.get(Calendar.DAY_OF_MONTH)).show();
        } else if (v == hora) {
            Calendar calendario = parseCalendar(hora.getText(), horaFormato);
            new TimePickerDialog(this, this,
                    calendario.get(Calendar.HOUR_OF_DAY),
                    calendario.get(Calendar.MINUTE), false) // /true = 24 horas
                    .show();
        }
    }

    private Calendar parseCalendar(CharSequence text,
                                   SimpleDateFormat fechaFormat2) {
        try {
            fechaConv = fechaFormat2.parse(text.toString());
        } catch (ParseException e) { // import java.text.ParsedExc
            throw new RuntimeException(e);
        }
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fechaConv);
        return calendario;
    }

    @Override
    public void onDateSet(DatePicker picker, int anio, int mes, int dia) {
        calendario = Calendar.getInstance();
        calendario.set(Calendar.YEAR, anio);
        calendario.set(Calendar.MONTH, mes);
        calendario.set(Calendar.DAY_OF_MONTH, dia);

        fechaSel = fechaFormato.format(calendario.getTime());
        fecha.setText(fechaSel);

    }

    public void onTimeSet(TimePicker picker, int horas, int minutos) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, horas);
        calendar.set(Calendar.MINUTE, minutos);

        horaSel = horaFormato.format(calendar.getTime());
        hora.setText(horaSel);
    }

    public void reserva(View v) {
        Intent envia = new Intent(this, Main2Activity.class);
        Bundle datos = new Bundle();
        datos.putString("nombre", nombre.getText().toString().trim());
        datos.putString("contra", contra.getText().toString().trim());
        datos.putInt("personas", personas);
        datos.putString("fecha", fechaSel);
        datos.putString("hora", horaSel);
        datos.putInt("noches", minNoches);
        datos.putBoolean("mar", mar.isChecked());
        envia.putExtras(datos);
        finish();
        startActivity(envia);
    }

}