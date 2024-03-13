package com.example.hayawanpedia;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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

public class DataUdara extends AppCompatActivity {
    private TextView textViewIndo, textViewArab, textViewPenjelasan;
    private ImageView imageViewFoto;

    private SimpleExoPlayer exoPlayer;
    private ImageButton btnUdara;
    private DatabaseReference DBKoneksi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_udara);

        textViewIndo       = findViewById(R.id.textViewIndo);
        textViewArab       = findViewById(R.id.textViewArab);
        textViewPenjelasan = findViewById(R.id.textViewPenjelasan);
        imageViewFoto      = findViewById(R.id.imageDataUdara);
        btnUdara = findViewById(R.id.btn_udara);

        DBKoneksi = FirebaseDatabase.getInstance().getReference().child("udara");

        String idContact = getIntent().getStringExtra("ID");
        if (idContact != null) {
            readData(idContact);
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

                    btnUdara.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.d("ButtonClicked", "Button clicked.");
                            playDummySoundWithExoPlayer(idContact);
                        }
                    });
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
            case "u1":
                soundResourceId = R.raw.merpati;
                break;
            case "u10":
                soundResourceId = R.raw.capung;
                break;
            case "u11":
                soundResourceId = R.raw.kupu_kupu;
                break;
            case "u12":
                soundResourceId = R.raw.rajawali;
                break;
            case "u13":
                soundResourceId = R.raw.bangau;
                break;
            case "u14":
                soundResourceId = R.raw.gagak;
                break;
            case "u15":
                soundResourceId = R.raw.pelikan;
                break;
            case "u16":
                soundResourceId = R.raw.pipit;
                break;
            case "u17":
                soundResourceId = R.raw.merak;
                break;
            case "u18":
                soundResourceId = R.raw.kunang_kunang;
                break;
            case "u19":
                soundResourceId = R.raw.kumbang;
                break;
            case "u2":
                soundResourceId = R.raw.kelelawar;
                break;
            case "u20":
                soundResourceId = R.raw.cendrawasih;
                break;
            case "u3":
                soundResourceId = R.raw.lebah;
                break;
            case "u4":
                soundResourceId = R.raw.burung_hantu;
                break;
            case "u5":
                soundResourceId = R.raw.lalat;
                break;
            case "u6":
                soundResourceId = R.raw.flamingo;
                break;
            case "u7":
                soundResourceId = R.raw.elang;
                break;
            case "u8":
                soundResourceId = R.raw.nyamuk;
                break;
            case "u9":
                soundResourceId = R.raw.jalak;
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