package com.classmatch.orientador.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import com.classmatch.R;

import java.util.Objects;

public class ResultadoActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private String idClasse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resultado);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_resultado), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Retrieve the Intent
        Intent intent = getIntent();

        // Get the data using the key
        String nomeClasse = intent.getStringExtra("nomeClasse");
        idClasse = intent.getStringExtra("idClasse");

        // Configurando a Toolbar
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(nomeClasse);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        OnBackPressedDispatcher dispatcher = getOnBackPressedDispatcher();
        dispatcher.addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button press
                finish(); // or use a specific navigation method
            }
        });


        // Configurando o ViewPager
        viewPager = findViewById(R.id.viewPager);
        ResultadoTabAdapter adapter = new ResultadoTabAdapter(this, idClasse);
        viewPager.setAdapter(adapter);

        // Configurando o TabLayout
        tabLayout = findViewById(R.id.tabLayout);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("Professores");
                            tab.setIcon(R.drawable.school_24px);
                            break;
                        case 1:
                            tab.setText("Alunos");
                            tab.setIcon(R.drawable.group_24px);
                            break;
                    }
                }).attach();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Handle the back button press in the toolbar
            finish(); // or use a specific navigation method
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}