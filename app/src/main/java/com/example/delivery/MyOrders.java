package com.example.delivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MyOrders extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);

        ListView listView = (ListView) findViewById(R.id.listview);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button display = (Button) findViewById(R.id.display);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection(Data.email)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    List<String> data = new ArrayList<>();
                                    for (QueryDocumentSnapshot document : task.getResult()) {

                                        String username = document.getString("name");
                                        double password = document.getDouble("price");
                                        data.add(username + "  " +password);
                                    }
                                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MyOrders.this,
                                            android.R.layout.simple_list_item_1, android.R.id.text1, data);
                                    listView.setAdapter(adapter);
                                } else {
                                    // Handle errors
                                }
                            }
                        });
            }
        });


    }
}