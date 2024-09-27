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


public class MainActivity extends AppCompatActivity {

    HomeContracts.Router router;
    private RecyclerView recyclerView;
    private ArrayList<ClientCard> clients;
    private AdapterClients adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.router = new HomeRouter();
        addRecyclerView();
        addButtons();
        addSearchBar();
        YLog.d("ActivityClientRegister", "addRecyclerView", "adapter size:: " + adapter.getItemCount());

    }

    public void addSearchBar() {
        EditText editText = findViewById(R.id.editText);
        editText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                AdapterClients adapterModified = new AdapterClients(adapter, router);
                adapterModified.setItens(adapterModified.gridSearch(s.toString()));
                recyclerView.setAdapter(adapterModified);
                adapterModified.notifyDataSetChanged();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
    }

    private void addButtons() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddClientBottomSheet fragment = new AddClientBottomSheet();
                fragment.show(getSupportFragmentManager(), AddClientBottomSheet.FRAGMENT_KEY);
            }
        });
    }

    private void addRecyclerView() {
        recyclerView = findViewById(R.id.recycler);
        clients = new ArrayList<>();
        adapter = new AdapterClients(this, getApplicationContext(), clients, "username teste = presenter.getUsername");
        adapter.addItem(new ClientCard("Estrutura de Dados", "ES", "001", ""));
        adapter.addItem(new ClientCard("Computabilidade", "CS", "001", ""));
        adapter.addItem(new ClientCard("Engenahria de Software 1", "CC", "001", ""));
        adapter.addItem(new ClientCard("Silvio", "MedBe", "001", ""));
        adapter.addItem(new ClientCard("Joaoa", "unipampa", "002", ""));

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        System.out.println("imprimindo recycler" + recyclerView.getAdapter());
        System.out.println("imprimindo adapter" + adapter.getItemCount());
        YLog.d("ActivityClientRegister", "addRecyclerView", "adapter size:: " + adapter.getItemCount());
        adapter.notifyDataSetChanged();
    }


}