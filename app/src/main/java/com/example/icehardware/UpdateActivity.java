package com.example.icehardware;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtbrand, edtNama, edtjenis, edtharga;
    private Button btnUpdate;
    public static final String EXTRA_PRODUCT = "extra_product";
    public final int ALERT_DIALOG_CLOSE = 1;
    public final int ALERT_DIALOG_DELETE = 2;
    private product Product;
    private String ProductId;
    DatabaseReference mDatabase;
    
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        edtbrand = findViewById(R.id.udt_brand);
        edtNama = findViewById(R.id.udt_nama);
        edtjenis = findViewById(R.id.udt_jenis);
        edtharga = findViewById(R.id.udt_harga);
        btnUpdate = findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(this);
        Product = getIntent().getParcelableExtra(EXTRA_PRODUCT);
        if (Product != null) {
            ProductId = Product.getId();
        }
        else {
            Product = new product();
        }
        if (Product != null) {
            edtbrand.setText(Product.getBrand());
            edtNama.setText(Product.getNama());
            edtjenis.setText(Product.getJenis());
            edtharga.setText(Product.getHarga());
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Edit Data");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_update) {
            updateProduct();
        }
    }
    private void updateProduct() {
        String brand = edtbrand.getText().toString().trim();
        String nama = edtNama.getText().toString().trim();
        String jenis = edtjenis.getText().toString().trim();
        String harga = edtharga.getText().toString().trim();
        boolean isEmptyFields = false;
        if (TextUtils.isEmpty(brand)) {
            isEmptyFields = true;
            edtbrand.setError("Field ini tidak boleh kosong");
        }
        if (TextUtils.isEmpty(nama)) {
            isEmptyFields = true;
            edtNama.setError("Field ini tidak boleh kosong");
        }
        if (TextUtils.isEmpty(jenis)) {
            isEmptyFields = true;
            edtjenis.setError("Field ini tidak boleh kosong");
        }
        if (TextUtils.isEmpty(harga)) {
            isEmptyFields = true;
            edtharga.setError("Field ini tidak boleh kosong");
        }
        if (! isEmptyFields) {
            Toast.makeText(UpdateActivity.this, "Updating Data...", Toast.LENGTH_SHORT).show();
            Product.setBrand(brand);
            Product.setNama(nama);
            Product.setJenis(jenis);
            Product.setHarga(harga);
            DatabaseReference dbProduct = mDatabase.child("product");
//update data
            dbProduct.child(ProductId).setValue(Product);
            finish();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_form, menu);
        return super.onCreateOptionsMenu(menu);
    }
    //pilih menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                showAlertDialog(ALERT_DIALOG_DELETE);
                break;
            case android.R.id.home:
                showAlertDialog(ALERT_DIALOG_CLOSE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        showAlertDialog(ALERT_DIALOG_CLOSE);
    }
    private void showAlertDialog(int type) {
        final boolean isDialogClose = type == ALERT_DIALOG_CLOSE;
        String dialogTitle, dialogMessage;
        if (isDialogClose) {
            dialogTitle = "Batal";
            dialogMessage = "Apakah anda ingin membatalkan perubahan?";
        }
        else {
            dialogTitle = "Hapus Data";
            dialogMessage = "Apakah anda yakin ingin menghapus item ini?";
        }
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(dialogTitle);
        alertDialogBuilder.setMessage(dialogMessage).setCancelable(false).setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (isDialogClose) {
                    finish();
                }
                else {
//hapus data
                    DatabaseReference dbProduct = mDatabase.child("product").child(ProductId);
                    dbProduct.removeValue();
                    Toast.makeText(UpdateActivity.this, "Deleting data...", Toast.LENGTH_SHORT).show();finish();
                }
            }
        }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}