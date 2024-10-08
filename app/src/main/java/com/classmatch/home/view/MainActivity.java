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
        addRecyclerView(); // Configurando a RecyclerView e os dados da lista
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
    // Método responsável por configurar a RecyclerView e os itens
    private void addRecyclerView() {
        // Adicionando alguns itens à lista de clientes (exemplos de dados)
        // Inicializando a RecyclerView e o ArrayList para armazenar os clientes
        recyclerView = findViewById(R.id.recycler);
        clients = new ArrayList<>();

        //adapter = new AdapterClients(this, getApplicationContext(), clients, "username teste = presenter.getUsername", );

        // Inicializando o Adapter, passando o listener para detectar o clique nos itens
        adapter = new AdapterClients(this, getApplicationContext(), clients, "username teste = presenter.getUsername",
                new AdapterClients.OnItemClickListener() {
                    @Override
                    public void onItemClick(ClientCard client) {
                        // Ação a ser executada quando um item da lista for clicado
                        // Exemplo: Mostrando um Toast com o nome do cliente clicado
                        Toast.makeText(getApplicationContext(), "Clicou em: " + client.getName(), Toast.LENGTH_SHORT).show();
                    }
                });

        // Adicionando alguns itens à lista de clientes (exemplos de dados)
        adapter.addItem(new ClientCard("Estrutura de Dados", "ES", "001", ""));
        adapter.addItem(new ClientCard("Computabilidade", "CS", "001", ""));
        adapter.addItem(new ClientCard("Engenahria de Software 1", "CC", "001", ""));
        adapter.addItem(new ClientCard("Silvio", "MedBe", "001", ""));
        adapter.addItem(new ClientCard("Joaoa", "unipampa", "002", ""));

        // Configurando o layout manager para o RecyclerView (Grid com 1 coluna)
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);





        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // Definindo o adapter na RecyclerView
        recyclerView.setAdapter(adapter);
        System.out.println("imprimindo recycler" + recyclerView.getAdapter());
        System.out.println("imprimindo adapter" + adapter.getItemCount());
        YLog.d("ActivityClientRegister", "addRecyclerView", "adapter size:: " + adapter.getItemCount());
        // Notificando que os dados mudaram para atualizar a lista
        adapter.notifyDataSetChanged();
    }


}