package com.example.hayawanpedia;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class DataDarat extends AppCompatActivity {
    private TextView textViewIndo, textViewArab, textViewPenjelasan;
    private ImageView imageViewFoto;

    private SimpleExoPlayer exoPlayer;
    private ImageButton btnDarat;
    private DatabaseReference DBKoneksi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_darat);

        textViewIndo       = findViewById(R.id.textViewIndo);
        textViewArab       = findViewById(R.id.textViewArab);
        textViewPenjelasan = findViewById(R.id.textViewPenjelasan);
        imageViewFoto      = findViewById(R.id.imageDataDarat);
        btnDarat = findViewById(R.id.btn_darat);


        DBKoneksi = FirebaseDatabase.getInstance().getReference().child("darat");

        String idContact = getIntent().getStringExtra("ID");
        if (idContact != null) {
            readData(idContact);
        }else {

            Toast.makeText(this, "No data to display.", Toast.LENGTH_SHORT).show();
            Log.e("DataAir", "No 'id' extra provided.");
        }
    }

    private void readData(String idContact) {
        DBKoneksi.child(idContact).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Model value = dataSnapshot.getValue(Model.class);
                if (value != null) {
                    textViewIndo.setText(value.getIndo());
                    textViewArab.setText(value.getArab());
                    textViewPenjelasan.setText(value.getPenjelasan());
                    Picasso.get().load(value.getFoto()).into(imageViewFoto);

                    btnDarat.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.d("ButtonClicked", "Button clicked.");
                            playDummySoundWithExoPlayer(idContact);
                        }
                    });

                } else {
                    Toast.makeText(DataDarat.this, "Data not found.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void playDummySoundWithExoPlayer(String identifier) {
        if (exoPlayer != null) {
            exoPlayer.release();
        }


        int soundResourceId;
        switch (identifier) {
            case "d1":
                soundResourceId = R.raw.gajah;
                break;
            case "d10":
                soundResourceId = R.raw.kambing;
                break;
            case "d11":
                soundResourceId = R.raw.harimau;
                break;
            case "d12":
                soundResourceId = R.raw.beruang;
                break;
            case "d13":
                soundResourceId = R.raw.serigala;
                break;
            case "d14":
                soundResourceId = R.raw.jerapah;
                break;
            case "d15":
                soundResourceId = R.raw.tikus;
                break;
            case "d16":
                soundResourceId = R.raw.babi;
                break;
            case "d17":
                soundResourceId = R.raw.anjing;
                break;
            case "d18":
                soundResourceId = R.raw.monyet;
                break;
            case "d19":
                soundResourceId = R.raw.rusa;
                break;
            case "d2":
                soundResourceId = R.raw.kucing;
                break;
            case "d20":
                soundResourceId = R.raw.tupai;
                break;
            case "d3":
                soundResourceId = R.raw.kuda;
                break;
            case "d4":
                soundResourceId = R.raw.kelinci;
                break;
            case "d5":
                soundResourceId = R.raw.sapi;
                break;
            case "d6":
                soundResourceId = R.raw.ayam;
                break;
            case "d7":
                soundResourceId = R.raw.bebek;
                break;
            case "d8":
                soundResourceId = R.raw.landak;
                break;
            case "d9":
                soundResourceId = R.raw.singa;
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