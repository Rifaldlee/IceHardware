package com.example.icehardware;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity implements View.OnClickListener{
    private Button btnCreate;
    private Button btnEdit;
    private Button btnlogout;
    private Button btnHome;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btnCreate = findViewById(R.id.btn_create);
        btnCreate.setOnClickListener(this);
        btnEdit = findViewById(R.id.btn_edit);
        btnEdit.setOnClickListener(this);
        btnlogout = findViewById(R.id.btn_logout);

        btnlogout.setOnClickListener(v ->{
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
        });
    }
    @Override
    public void onClick(View view){
        if (view.getId() == R.id.btn_create) {
            Intent intent = new Intent(Dashboard.this, Create.class);
            startActivity(intent);
        }
        if (view.getId() == R.id.btn_edit) {
            Intent intent = new Intent(Dashboard.this, Main.class);
            startActivity(intent);
        }
    }
    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed(){
        if(doubleBackToExitPressedOnce){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this,getResources().getText(R.string.pls_back_again),Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = true;
            }
        },2000);
    }
}