package com.example.icehardware;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Create extends AppCompatActivity implements View.OnClickListener {
    private EditText crtBrand, crtNama, crtJenis, crtHarga;
    private Button btnSave;
    private product product;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        crtNama = findViewById(R.id.crt_nama);
        crtBrand = findViewById(R.id.crt_brand);
        crtJenis = findViewById(R.id.crt_jenis);
        crtHarga = findViewById(R.id.crt_harga);
        btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(this);
        product = new product();
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_save) {
            saveproduct();
        }
    }
    private void saveproduct() {
        String nama = crtNama.getText().toString().trim();
        String brand = crtBrand.getText().toString().trim();
        String jenis = crtJenis.getText().toString().trim();
        String harga = crtHarga.getText().toString().trim();
        boolean isEmptyFields = false;
        if (TextUtils.isEmpty(nama)) {
            isEmptyFields = true;
            crtNama.setError("Field ini tidak boleh kosong");
        }
        if (TextUtils.isEmpty(brand)) {
            isEmptyFields = true;
            crtBrand.setError("Field ini tidak boleh kosong");
        }
        if (TextUtils.isEmpty(jenis)) {
            isEmptyFields = true;
            crtBrand.setError("Field ini tidak boleh kosong");
        }
        if (TextUtils.isEmpty(harga)) {
            isEmptyFields = true;
            crtBrand.setError("Field ini tidak boleh kosong");
        }
        if (! isEmptyFields) {
            try {
                Toast.makeText(Create.this, "Data Saved", Toast.LENGTH_SHORT).show();
                DatabaseReference dbproduct = mDatabase.child("product");
                String id = dbproduct.push().getKey();
                product.setId(id);
                product.setBrand(brand);
                product.setNama(nama);
                product.setJenis(jenis);
                product.setHarga(harga);
                dbproduct.child(id).setValue(product);
                finish();
            }
            catch (Exception er) {
                System.out.println("Error : " + er);
            }
        }
    }
}