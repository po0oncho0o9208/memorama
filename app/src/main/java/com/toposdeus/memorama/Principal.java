package com.toposdeus.memorama;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Principal extends AppCompatActivity {


    ImageView play, ajustes, trofeos;
    LinearLayout layout;
    ProgressBar progresbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


        play = findViewById(R.id.btnplay);
        ajustes = findViewById(R.id.btnajustes);
        trofeos = findViewById(R.id.btntrofeos);
        layout = findViewById(R.id.layoutprincipal);
        progresbar = findViewById(R.id.pgbr);


    }

    public void play(View view) {
        Ajustes.vibrar(this, 50);
        Intent intent1 = new Intent(this, Niveles.class);
        startActivity(intent1);
        // Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://rh.imss.gob.mx/tarjetondigital/"));
        // startActivity(intent1);
    }

    public void ajustes(View view) {
        Ajustes.vibrar(this, 50);
        Intent intent12 = new Intent(this, Ajustes.class);
        startActivity(intent12);
        // Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://rh.imss.gob.mx/tarjetondigital/"));
        // startActivity(intent1);
    }

    public void trofeos(View view) {
        Ajustes.vibrar(this, 50);
        Intent intent13 = new Intent(this, Trofeos.class);
=======
        Intent intent13 = new Intent(this, MainActivity.class);
>>>>>>> d9d26a9dc2f2bebd809a7610e32769e5bc1ad761
        startActivity(intent13);
        // Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://rh.imss.gob.mx/tarjetondigital/"));
        // startActivity(intent1);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Ajustes.vibrar(this, 50);
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = getLayoutInflater();
            View vi = inflater.inflate(R.layout.dialogoconfirm, null);
            builder.setView(vi);
            final AlertDialog dialog = builder.create();
            //decidir despues si sera cancelable o no
            dialog.setCancelable(false);
            Button botonsi = vi.findViewById(R.id.botonsi);

            botonsi.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Ajustes.vibrar(Principal.this, 50);
                            dialog.cancel();
                            Principal.super.onDestroy();
                            System.exit(0);
                        }
                    }
            );
            Button botonno = vi.findViewById(R.id.botonno);
            botonno.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Ajustes.vibrar(Principal.this, 50);
                            dialog.cancel();

                        }
                    }
            );
            dialog.show();
            //Metodos.dialogo( this, getLayoutInflater(), "¿seguro deseas salir de la aplicacion?", 0 );
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        layout.setVisibility(View.GONE);
        progresbar.setVisibility(View.GONE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        layout.setVisibility(View.VISIBLE);
        progresbar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        layout.setVisibility(View.VISIBLE);
        progresbar.setVisibility(View.VISIBLE);

    }

}
