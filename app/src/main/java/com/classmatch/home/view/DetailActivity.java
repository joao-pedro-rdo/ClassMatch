package com.classmatch.home.view;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.classmatch.R;
import com.classmatch.home.HomeContracts;
import com.classmatch.home.entity.ClientCard;
import com.classmatch.home.router.HomeRouter;
import com.classmatch.util.YLog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import android.widget.Toast;

import android.content.Intent;
import android.content.Context;
import android.widget.TextView;





public class DetailActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        // Obter os dados passados via Intent

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String company = intent.getStringExtra("company");

        // Configurar os TextViews com os dados recebidos
        TextView nameTextView = findViewById(R.id.textViewNameDetail);
        TextView companyTextView = findViewById(R.id.textViewCompanyDetail);

        nameTextView.setText(name);
        companyTextView.setText(company);
    }
}
