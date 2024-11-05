package com.classmatch.aluno.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.classmatch.R;
import com.classmatch.aluno.AlunoContracts;
import com.classmatch.aluno.entity.ClasseCard;
import com.classmatch.aluno.presenter.AlunoPresenter;
import com.classmatch.home.view.AdapterClients;

import java.util.ArrayList;

public class AlunoActivity extends AppCompatActivity implements AlunoContracts.View {

    private AlunoContracts.Presenter presenter;
    private ClasseCardAdaptor classeCardAdaptor;
    private RecyclerView recyclerView;
    private TextView selectedCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_aluno);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_aluno), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.presenter = new AlunoPresenter(this);

        this.criarLista();
        this.addSearchBar();
        this.addSelectedCount();

        presenter.requestClasseCardList();
    }

    private void criarLista() {
        recyclerView = findViewById(R.id.recyclerview_lista_classes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        classeCardAdaptor = new ClasseCardAdaptor(new ArrayList<>(), this, presenter);
        recyclerView.setAdapter(classeCardAdaptor);
    }

    private void addSelectedCount() {
        selectedCount = findViewById(R.id.textview_contador_classes);
        setSelectedCount(0);
    }

    public void addSearchBar() {
        EditText editText = findViewById(R.id.edittext_filtro_classes);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                classeCardAdaptor.gridSearch(editable.toString());
            }
        });
    }

    @Override
    public void onClasseCardListRetrived(ArrayList<ClasseCard> classCardList) {
        this.classeCardAdaptor.setItems(classCardList);
    }

    public void setSelectedCount(int count) {
        this.selectedCount.setText(count + "/5");
    }
}