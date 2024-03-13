package com.example.hayawanpedia;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HabitatAir extends AppCompatActivity {
    private DatabaseReference DBKoneksi;
    private ListView listViewContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habitat_air);

        DBKoneksi = FirebaseDatabase.getInstance().getReference("air");
        listViewContact = findViewById(R.id.listViewContact);

        readData();

        listViewContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String idContact = ((ListViewAdapter) listViewContact.getAdapter()).getId(position);
                Intent tampil_data_darat = new Intent(HabitatAir.this, DataAir.class);
                tampil_data_darat.putExtra("ID", idContact);
                startActivity(tampil_data_darat);
            }
        });
    }

    private void readData() {
        final ArrayList<String> id         = new ArrayList<>();
        final ArrayList<String> indo       = new ArrayList<>();
        final ArrayList<String> arab       = new ArrayList<>();
        final ArrayList<String> penjelasan = new ArrayList<>();
        final ArrayList<String> foto       = new ArrayList<>();

        DBKoneksi.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                id.clear();
                indo.clear();
                arab.clear();
                penjelasan.clear();
                foto.clear();

                Iterable<DataSnapshot> snapshotIterable = dataSnapshot.getChildren();
                for (DataSnapshot snapshot : snapshotIterable) {
                    Model value = snapshot.getValue(Model.class);
                    assert value != null;
                        id.add(value.getId());
                        indo.add(value.getIndo());
                        arab.add(value.getArab());
                        penjelasan.add(value.getPenjelasan());
                        foto.add(value.getFoto());
                    }
                ListViewAdapter adapter = new ListViewAdapter(id, indo, arab, penjelasan, foto, HabitatAir.this);
                listViewContact.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("DatabaseError", "Error fetching data from Firebase: " + databaseError.getMessage());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        readData();
    }
}