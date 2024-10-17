package com.classmatch.home.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import com.classmatch.R;
import com.classmatch.home.entity.ClientCard;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddClientBottomSheet extends BottomSheetDialogFragment {

    public static final String FRAGMENT_KEY = "com.yosoy.beta.core_chat.view.response.ConvenioBottomSheet";

    private DatabaseReference databaseReference;
    private EditText inputCourse, inputName, inputCode;
    private Spinner spinnerSemestre;
    private CheckBox checkboxAgree;
    private ProgressBar progressBar;
    private MainActivity main;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // Infla o layout do BottomSheet
        View layout = inflater.inflate(R.layout.bottom_sheet_register_client, container, false);

        // Inicializa o Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference("materias");

        // Inicializa os componentes da interface
        inputCourse = layout.findViewById(R.id.inputCourse);
        inputName = layout.findViewById(R.id.inputName);
        inputCode = layout.findViewById(R.id.inputCode);
        spinnerSemestre = layout.findViewById(R.id.spinnerSemestre);
        checkboxAgree = layout.findViewById(R.id.checkboxAgree);
        //progressBar = layout.findViewById(R.id.progressBar);  // Se existir

        // Inicializa o MainActivity
        main = (MainActivity) getActivity();

        // Configura o Spinner com os valores dos semestres
        String[] semestres = {
                "1º Semestre", "2º Semestre", "3º Semestre", "4º Semestre",
                "5º Semestre", "6º Semestre", "7º Semestre", "8º Semestre"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, semestres);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSemestre.setAdapter(adapter);

        // Configura o botão para criar a matéria
        MaterialButton btnContinue = layout.findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(view -> {
            createMateria(); // Cria a matéria e envia para o Firebase
            dismiss(); // Fecha o BottomSheet
        });

        return layout;
    }

    // Método para criar uma nova matéria e enviar para o Firebase
    private void createMateria() {
        String course = inputCourse.getText().toString().trim();
        String name = inputName.getText().toString().trim();
        String code = inputCode.getText().toString().trim();
        String semestre = spinnerSemestre.getSelectedItem().toString();
        boolean hasPrerequisites = checkboxAgree.isChecked();

        if (course.isEmpty() || name.isEmpty() || code.isEmpty()) {
            Toast.makeText(getContext(), "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Cria um objeto ClientCard com os dados inseridos
        ClientCard newMateria = new ClientCard(name, course, code, semestre, hasPrerequisites);

        // Envia os dados para o Firebase
        databaseReference.push().setValue(newMateria)
                .addOnSuccessListener(aVoid -> Toast.makeText(getContext(), "Matéria adicionada com sucesso!", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(getContext(), "Erro ao adicionar matéria", Toast.LENGTH_SHORT).show());
    }
}
