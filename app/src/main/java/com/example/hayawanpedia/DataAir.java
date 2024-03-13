package com.example.hayawanpedia;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DataAir extends AppCompatActivity {
    private TextView textViewIndo;
    private TextView textViewArab;
    private TextView textViewPenjelasan;
    private ImageView imageViewFoto;
    private DatabaseReference DBKoneksi;

    private SimpleExoPlayer exoPlayer;
    private ImageButton btnAir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_air);

        textViewIndo       = findViewById(R.id.tvIndoAir);
        textViewArab       = findViewById(R.id.tvArabAir);
        textViewPenjelasan = findViewById(R.id.tvPenjelasanAir);
        imageViewFoto      = findViewById(R.id.imageDataAir);
        btnAir = findViewById(R.id.btn_air);


        DBKoneksi = FirebaseDatabase.getInstance().getReference().child("air");

        String idContact = getIntent().getStringExtra("ID");
        Log.d("DataAir", "idContact: " + idContact);
        if (idContact != null) {
            readData(idContact);
        } else {

            Toast.makeText(this, "No data to display.", Toast.LENGTH_SHORT).show();
            Log.e("DataAir", "No 'id' extra provided.");
        }

    }

    private void readData(String idContact) {
        DatabaseReference dataRef = DBKoneksi.child(idContact);
        dataRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("DataAir", "DataSnapshot exists: " + dataSnapshot.exists());
                if (dataSnapshot.exists()) {
                    Model value = dataSnapshot.getValue(Model.class);
                    if (value != null) {
                        textViewIndo.setText(value.getIndo());
                        textViewArab.setText(value.getArab());
                        textViewPenjelasan.setText(value.getPenjelasan());
                        Picasso.get().load(value.getFoto()).into(imageViewFoto);

                        btnAir.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Log.d("ButtonClicked", "Button clicked.");
                                playDummySoundWithExoPlayer(idContact);
                            }
                        });



                    } else {
                        Toast.makeText(DataAir.this, "Data not found.", Toast.LENGTH_SHORT).show();
                        Log.e("DataAir", "Data with idContact is null.");
                    }
                } else {
                    Toast.makeText(DataAir.this, "Data not found.", Toast.LENGTH_SHORT).show();
                    Log.e("DataAir", "Data with idContact does not exist in Firebase.");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("DatabaseError", "Error fetching data from Firebase: " + databaseError.getMessage());
            }
        });
    }

    private void playDummySoundWithExoPlayer(String identifier) {
        if (exoPlayer != null) {
            exoPlayer.release();
        }


        int soundResourceId;
        switch (identifier) {
            case "a1":
                soundResourceId = R.raw.lumba;
                break;
            case "a10":
                soundResourceId = R.raw.kerang;
                break;
            case "a11":
                soundResourceId = R.raw.penyu;
                break;
            case "a12":
                soundResourceId = R.raw.ikan_pari;
                break;
            case "a13":
                soundResourceId = R.raw.kodok;
                break;
            case "a14":
                soundResourceId = R.raw.ikan_badut;
                break;
            case "a15":
                soundResourceId = R.raw.lele;
                break;
            case "a16":
                soundResourceId = R.raw.lobster;
                break;
            case "a17":
                soundResourceId = R.raw.kepiting;
                break;
            case "a18":
                soundResourceId = R.raw.buaya;
                break;
            case "a19":
                soundResourceId = R.raw.ular_laut;
                break;
            case "a2":
                soundResourceId = R.raw.udang;
                break;
            case "a20":
                soundResourceId = R.raw.landak_laut;
                break;
            case "a3":
                soundResourceId = R.raw.gurita;
                break;
            case "a4":
                soundResourceId = R.raw.ubur_ubur;
                break;
            case "a5":
                soundResourceId = R.raw.hiu;
                break;
            case "a6":
                soundResourceId = R.raw.paus;
                break;
            case "a7":
                soundResourceId = R.raw.kuda_laut;
                break;
            case "a8":
                soundResourceId = R.raw.cumi_cumi;
                break;
            case "a9":
                soundResourceId = R.raw.bintang_laut;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + identifier);
        }

        exoPlayer = new SimpleExoPlayer.Builder(this).build();

        Uri soundUri = Uri.parse("android.resource://" + getPackageName() + "/" + soundResourceId);
        MediaItem mediaItem = MediaItem.fromUri(soundUri);
        exoPlayer.setMediaItem(mediaItem);
        exoPlayer.prepare();
        exoPlayer.play();

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (exoPlayer != null) {
            exoPlayer.release();
            exoPlayer = null;
        }
    }

}