package com.classmatch.orientador.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
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
import com.classmatch.orientador.OrientadorContracts;
import com.classmatch.orientador.entity.ClasseCard;
import com.classmatch.orientador.presenter.OrientadorPresenter;
import com.classmatch.orientador.router.OrientadorRouter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;

import java.util.ArrayList;

public class OrientadorActivity extends AppCompatActivity implements OrientadorContracts.View {

    private OrientadorContracts.Presenter presenter;
    private ClasseCardAdapter classeCardAdapter;
    private RecyclerView recyclerView;
    private TextView selectedCount;
    private final String[] orderOpcoes = new String[]{"Alunos", "Professores"};;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_orientador);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_orientador), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.presenter = new OrientadorPresenter(this, new OrientadorRouter(this));

        this.criarLista();
        this.addSearchBar();
        this.addSelectedCount();
        this.addDropdown();
        this.addCriarClassBottomSheet();

        presenter.requestClasseCardList();
    }

    private void criarLista() {
        recyclerView = findViewById(R.id.recyclerview_lista_classes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        classeCardAdapter = new ClasseCardAdapter(new ArrayList<>(), this, this.presenter);
        recyclerView.setAdapter(classeCardAdapter);
        classeCardAdapter.ordenarPorAlunos();
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
                classeCardAdapter.gridSearch(editable.toString());
            }
        });
    }

    public void addDropdown() {
        MaterialAutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, orderOpcoes);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setText(orderOpcoes[0], false);
        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().equals(orderOpcoes[0])) {
                    classeCardAdapter.ordenarPorAlunos();
                } else {
                    classeCardAdapter.ordenarPorProfessores();
                }
            }
        });
    }

    public void addCriarClassBottomSheet() {
        FloatingActionButton addClasseButton = findViewById(R.id.fab);
        addClasseButton.setOnClickListener(v -> {
            CriarClasseFragment bottomSheet = new CriarClasseFragment(this.presenter);
            bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());
        });
    }

    @Override
    public void onClasseCardListRetrived(ArrayList<ClasseCard> classCardList) {
        this.classeCardAdapter.setItems(classCardList);
        setSelectedCount(classCardList.size());
    }

    public void setSelectedCount(int count) {
        this.selectedCount.setText("" + count);
    }

    public String[] getOrderOpcoes() {
        return this.orderOpcoes;
    }
}