package com.example.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    public static final String SHARED_PREF_NAME = "preferencias_color";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    ConstraintLayout layoutPrincipal;
    TextView txtEstadoColor;
    AppCompatButton btnAzul, btnVerde, btnRojo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        layoutPrincipal = findViewById(R.id.main);
        txtEstadoColor = findViewById(R.id.txtEstadoColor);
        btnAzul = findViewById(R.id.btnAzul);
        btnVerde = findViewById(R.id.btnVerde);
        btnRojo = findViewById(R.id.btnRojo);

        cargarColorGuardado();

        btnAzul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarYAplicarColor("azul");
            }
        });

        btnVerde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarYAplicarColor("verde");
            }
        });

        btnRojo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarYAplicarColor("rojo");
            }
        });
    }

    private void guardarYAplicarColor(String colorElegido) {
        editor.putString("color_fondo", colorElegido);
        editor.commit();

        aplicarColorEnPantalla(colorElegido);
    }

    private void cargarColorGuardado() {
        String colorGuardado = sharedPreferences.getString("color_fondo", "0");

        if (!colorGuardado.equals("0")) {
            aplicarColorEnPantalla(colorGuardado);
        }
    }

    private void aplicarColorEnPantalla(String color) {
        switch (color) {
            case "azul":
                layoutPrincipal.setBackgroundColor(ContextCompat.getColor(this, R.color.fondo_azul));
                txtEstadoColor.setText("Color actual: Azul");
                txtEstadoColor.setTextColor(ContextCompat.getColor(this, R.color.neon_azul));
                break;

            case "verde":
                layoutPrincipal.setBackgroundColor(ContextCompat.getColor(this, R.color.fondo_verde));
                txtEstadoColor.setText("Color actual: Verde");
                txtEstadoColor.setTextColor(ContextCompat.getColor(this, R.color.neon_verde));
                break;

            case "rojo":
                layoutPrincipal.setBackgroundColor(ContextCompat.getColor(this, R.color.fondo_rojo));
                txtEstadoColor.setText("Color actual: Rojo");
                txtEstadoColor.setTextColor(ContextCompat.getColor(this, R.color.neon_rojo));
                break;
        }
    }
}