package com.classmatch.orientador.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.classmatch.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;

import java.util.Objects;

public class CriarClasseFragment extends BottomSheetDialogFragment {

    private AutoCompleteTextView autoCompleteSemestre;
    private MaterialButton btnContinue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottomsheet_criar_classe, container, false);

        // Inicializa os componentes
        autoCompleteSemestre = view.findViewById(R.id.autoCompleteSemestre);
        btnContinue = view.findViewById(R.id.btnContinue);

        this.addSemestreDropdown();

        btnContinue.setOnClickListener(v -> {
            // Adicione sua lógica de clique aqui
            dismiss();
        });


        return view;
    }

//    public void addSemestreDropdown() {
//        MaterialAutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteSemestre);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_dropdown_item_1line, orderOpcoes);
//        autoCompleteTextView.setAdapter(adapter);
//        autoCompleteTextView.setText(orderOpcoes[0], false);
//    }

    private void addSemestreDropdown() {
        String[] semestreOptions = new String[]{
                "1º Semestre",
                "2º Semestre",
                "3º Semestre",
                "4º Semestre",
                "5º Semestre",
                "6º Semestre",
                "7º Semestre",
                "8º Semestre",
                "9º Semestre",
                "10º Semestre"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, semestreOptions);
        autoCompleteSemestre.setAdapter(adapter);
    }
}
