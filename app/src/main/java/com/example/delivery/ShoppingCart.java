package com.example.delivery;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;


public class ShoppingCart extends AppCompatActivity {


    private List<Product> mCartList;
    private ProductAdapter mProductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        mCartList = CartHelper.getCartList();

        // Make sure to clear the selections
        for(int i=0; i<mCartList.size(); i++) {
            mCartList.get(i).selected = false;
        }

        // Create the list
        final ListView listViewCatalog = (ListView) findViewById(R.id.ListViewCatalog);
        mProductAdapter = new ProductAdapter(mCartList, getLayoutInflater(), true);
        listViewCatalog.setAdapter(mProductAdapter);

        listViewCatalog.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent productDetailsIntent = new Intent(getBaseContext(),ProductDetails.class);
                productDetailsIntent.putExtra(CartHelper.PRODUCT_INDEX, position);
                startActivity(productDetailsIntent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh the data
        if(mProductAdapter != null) {
            mProductAdapter.notifyDataSetChanged();
        }

        double subTotal = 0;
        for(Product p : mCartList) {
            int quantity = CartHelper.getProductQuantity(p);
            subTotal += p.price * quantity;
        }

        Data.total = subTotal;
        TextView productPriceTextView = (TextView) findViewById(R.id.TextViewSubtotal);
        productPriceTextView.setText("Subtotal: $" + subTotal);

        Button checkout = (Button) findViewById(R.id.checkout);
        checkout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                double totalCost = Data.total;

                FirebaseFirestore db = FirebaseFirestore.getInstance();

                List<Product> cartList = CartList.getCartList(); // Get the cartList from the CartList class

                for (Product product : cartList) {
                    Map<String, Object> item = new HashMap<>();
                    item.put("name", product.getTitle());
                    item.put("price", product.getPrice());


                    db.collection(Data.email).add(item)
                            .addOnSuccessListener(documentReference -> {

                                Intent checkout = new Intent(getBaseContext(),MainActivity.class);
                                startActivity(checkout);
                            })
                            .addOnFailureListener(e -> {
                                Log.e("FirestoreHelper", "Error adding item to Firestore: ", e);
                            });
                }
            }
        });
    }
}