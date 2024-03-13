package com.example.hayawanpedia;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void darat(View view) { startActivity(new Intent(MainActivity.this, HabitatDarat.class));
    }

    public void air(View view) { startActivity(new Intent(MainActivity.this, HabitatAir.class));
    }

    public void udara(View view) { startActivity(new Intent(MainActivity.this, HabitatUdara.class));
    }

    public void quiz(View view) { startActivity(new Intent(MainActivity.this, Quiz.class));
    }

    public void profil(View view) { startActivity(new Intent(MainActivity.this, Profil.class));
    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Apakah kamu yakin ingin keluar?");
        builder.setCancelable(true);
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        }).setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                startActivity(new Intent(MainActivity.this, Splashout.class));
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}