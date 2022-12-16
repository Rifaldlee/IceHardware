package com.example.icehardware;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class Main extends AppCompatActivity{
    private ListView listView;
    private ProductAdapter adapter;
    private ArrayList<product> ProductList;
    DatabaseReference IceHardware;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.lv_list);
        IceHardware = FirebaseDatabase.getInstance().getReference("product");
        ProductList = new ArrayList<>();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Main.this, UpdateActivity.class);
                intent.putExtra(UpdateActivity.EXTRA_PRODUCT, ProductList.get(i));
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        IceHardware.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ProductList.clear();
                for (DataSnapshot ProductSnapshot : dataSnapshot.getChildren()) {
                    product Product = ProductSnapshot.getValue(product.class);
                    ProductList.add(Product);
                }
                ProductAdapter adapter = new ProductAdapter(Main.this);
                adapter.setProductList(ProductList);
                listView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Main.this, "Terjadi kesalahan.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}