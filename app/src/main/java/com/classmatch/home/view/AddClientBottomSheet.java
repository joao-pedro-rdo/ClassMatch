package com.classmatch.home.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import com.classmatch.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddClientBottomSheet extends BottomSheetDialogFragment {

    public static final String FRAGMENT_KEY = "com.yosoy.beta.core_chat.view.response.ConvenioBottomSheet";
    Button btnContinue;
    MainActivity main;
    ProgressBar progressBar;
    BottomSheetDialogFragment fragment;
    TextView textViewTitle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // Infla o layout para o fragmento
        View layout = inflater.inflate(R.layout.bottom_sheet_register_client, container, false); // Adicione 'false' aqui
        main = (MainActivity) getActivity();
        fragment = this;

        // Inicializando os componentes da interface gráfica
        textViewTitle = layout.findViewById(R.id.textViewTitle);

        // Configuração do Spinner
        Spinner spinnerSemestre = layout.findViewById(R.id.spinnerSemestre); // Use 'layout.findViewById'
        String[] semestres = {
                "1º Semestre", "2º Semestre", "3º Semestre", "4º Semestre",
                "5º Semestre", "6º Semestre", "7º Semestre", "8º Semestre"
        };

        // Usando requireContext() para o contexto do fragmento
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, semestres);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSemestre.setAdapter(adapter);

        // Configurando o botão continuar
        btnContinue = layout.findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Fecha o BottomSheet quando o botão continuar for clicado
                fragment.dismiss();
            }
        });

        return layout; // Retorne o layout inflado
    }
}
