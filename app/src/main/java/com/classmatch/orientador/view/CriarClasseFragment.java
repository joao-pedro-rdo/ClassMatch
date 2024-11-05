package com.classmatch.orientador.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.classmatch.R;
import com.classmatch.orientador.OrientadorContracts;
import com.classmatch.orientador.entity.ClasseCard;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;


public class CriarClasseFragment extends BottomSheetDialogFragment {

    private ClasseCard classeCard = new ClasseCard();
    private AutoCompleteTextView autoCompleteSemestre;
    private MaterialButton btnContinue;
    private EditText editTextNome, editTextCodigo, editTextCurso;
    private CheckBox checkboxRequisito;
    private OrientadorContracts.Presenter presenter;

    public CriarClasseFragment(OrientadorContracts.Presenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottomsheet_criar_classe, container, false);

        // Inicializa os componentes
        editTextNome = view.findViewById(R.id.editTextNome);
        editTextCodigo = view.findViewById(R.id.editTextCodigo);
        editTextCurso = view.findViewById(R.id.editTextCurso);
        autoCompleteSemestre = view.findViewById(R.id.autoCompleteSemestre);
        checkboxRequisito = view.findViewById(R.id.checkboxRequisito);
        btnContinue = view.findViewById(R.id.btnContinue);

        addSemestreDropdown();
        addSalvarBotao();

        return view;
    }

    private void addSemestreDropdown() {
        String[] semestreOptions = new String[]{
                "1º Semestre", "2º Semestre", "3º Semestre", "4º Semestre",
                "5º Semestre", "6º Semestre", "7º Semestre", "8º Semestre",
                "9º Semestre", "10º Semestre"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, semestreOptions);
        autoCompleteSemestre.setAdapter(adapter);
    }

    private void addSalvarBotao() {
        btnContinue.setOnClickListener(v -> {
            if (coletarDados()) {
                presenter.salvarClasse(classeCard);
                dismiss();
            } else {
                Toast.makeText(getContext(), "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean coletarDados() {
        String nome = editTextNome.getText().toString().trim();
        String codigo = editTextCodigo.getText().toString().trim();
        String curso = editTextCurso.getText().toString().trim();
        String semestreStr = autoCompleteSemestre.getText().toString().trim();
        boolean requisito = checkboxRequisito.isChecked();

        // Validação simples dos campos
        if (nome.isEmpty() || codigo.isEmpty() || curso.isEmpty() || semestreStr.isEmpty()) {
            return false;
        }

        int semestre = 1;
        try {
            semestre = Integer.parseInt(semestreStr.split("º")[0].trim());
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Semestre inválido.", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Preenche o objeto ClasseCard com os dados
        classeCard.setNome(nome);
        classeCard.setCodigo(codigo);
        classeCard.setCurso(curso);
        classeCard.setSemestre(semestre);
        classeCard.setRequisito(requisito);
        classeCard.setAlunos(0);
        classeCard.setProfessores(0);

        return true;
    }
}
