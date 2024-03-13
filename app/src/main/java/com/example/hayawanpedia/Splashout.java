package com.example.hayawanpedia;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class Splashout {

    private Activity activity;

    public Splashout(Activity activity) {
        this.activity = activity;
    }

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
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
                // Menutup MainActivity dan keluar dari aplikasi
                activity.finishAffinity();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}