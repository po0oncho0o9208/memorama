package com.toposdeus.memorama;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ajustes extends AppCompatActivity implements View.OnClickListener {

    CheckBox sonido, vibrar;
    Context context;

    Button btnatras, reestablecer, botoncomparte, botoncalifica;
    SharedPreferences sharedPref;
    TextView txtest;

    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opciones);

        btnatras = findViewById(R.id.atras);
        btnatras.setOnClickListener(this);
        reestablecer = findViewById(R.id.botonreestablecer);
        reestablecer.setOnClickListener(this);
        txtest = findViewById(R.id.txtestrella);
        botoncomparte = findViewById(R.id.botoncomparte);
        botoncomparte.setOnClickListener(this);
        botoncalifica = findViewById(R.id.botoncalifica);
        botoncalifica.setOnClickListener(this);
        sharedPref = getSharedPreferences("record", Context.MODE_PRIVATE);

        int contador = 0;

        for (int n = 0; n < 4; n++) {
            for (int i = 0; i < 27; i++) {
                contador += sharedPref.getInt(n + "record" + i, 0);
            }
        }
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/birdyame.ttf");
        txtest.setText(contador + " X ");
        txtest.setTypeface(font);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.botoncomparte:
                if (ContextCompat.checkSelfPermission(Ajustes.this,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(Ajustes.this,
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    } else {

                        ActivityCompat.requestPermissions(Ajustes.this,
                                new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                    }
                }
                    Intent intento = new Intent(Intent.ACTION_SEND);
                    intento.setType("*/*");
                    String paramString1 = Integer.toString(R.drawable.atras4);
                    Bitmap topo2 = BitmapFactory.decodeResource(getResources(), R.drawable.atras4);
                    String fileName = paramString1 + "" + ".png";
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    topo2.compress(Bitmap.CompressFormat.PNG, 40, bytes);
                    File ExternalStorageDirectory = Environment.getExternalStorageDirectory();
                    File file = new File(ExternalStorageDirectory + File.separator + fileName);
                    FileOutputStream fileOutputStream = null;
                    try {
                        file.createNewFile();
                        fileOutputStream = new FileOutputStream(file);
                        fileOutputStream.write(bytes.toByteArray());
                    } catch (IOException e) {

                    } finally {
                        if (fileOutputStream != null) {
                            Uri bmpUri = Uri.parse(file.getPath());
                            intento.putExtra(Intent.EXTRA_TEXT, "En esta aplicación podrás descargar y ver tu tarjetón digital, veras sus promociones" + Html.fromHtml("<br />") +
                                    "y recibirás notificaciones de cuando llegue el tarjetón , así como noticias relevantes del IMSS  " + Html.fromHtml("<br />") +
                                    "https://play.google.com/store/apps/details?id=com.tarjetonimss.user.imsswebtarjeton");
                            intento.putExtra(
                                    Intent.EXTRA_STREAM,
                                    bmpUri);
                            startActivity(Intent.createChooser(intento,
                                    "Siguenos en nuestra pagina "));
                        }

                }
                break;
            case R.id.botoncalifica:
                Intent intentae4 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.tarjetonimss.user.imsswebtarjeton"));
                startActivity(intentae4);
                break;
            case R.id.botonreestablecer:

                final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
                final LayoutInflater inflater = getLayoutInflater();
                View vi = inflater.inflate(R.layout.dialogoconfirm2, null);
                builder.setView(vi);
                final android.app.AlertDialog dialog = builder.create();
                //decidir despues si sera cancelable o no
                dialog.setCancelable(false);
                Button botonsi = vi.findViewById(R.id.botonsi1);

                botonsi.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //sharedPref = getSharedPreferences("record", 0);
                                //sharedPref.edit().remove("record").commit();
                                sharedPref.edit().clear().commit();
                                txtest.setText(0 + " X ");
                                Toast.makeText(Ajustes.this, "Se han restablecido sus datos ", Toast.LENGTH_SHORT).show();
                                dialog.cancel();

                            }
                        }
                );
                Button botonno = vi.findViewById(R.id.botonno1);
                botonno.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.cancel();

                            }
                        }
                );
                dialog.show();
                //Metodos.dialogo( this, getLayoutInflater(), "¿seguro deseas salir de la aplicacion?", 0 );

                break;

            case R.id.atras:
                Intent intent = new Intent(Ajustes.this, Principal.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent intent = new Intent(Ajustes.this, Principal.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
